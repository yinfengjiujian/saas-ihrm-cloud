package com.aier.ihrm.system.service;

import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.common.utils.QiniuUploadUtil;
import com.aier.ihrm.domain.system.Role;
import com.aier.ihrm.domain.system.User;
import com.aier.ihrm.system.dao.RoleDao;
import com.aier.ihrm.system.dao.UserDao;
import com.aier.ihrm.system.utils.BaiduAiUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * <p>Title: com.aier.ihrm.system.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 21:50
 * Description: No Description
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BaiduAiUtil baiduAiUtil;

    public void save(User user) {
        user.setId(idWorker.getId()+"");
        //md5加密密码
        String password = new Md5Hash("123456", user.getMobile(), 3).toString();
        user.setLevel("user");
        user.setPassword(password);//设置初始密码
        user.setEnableState(1);
        userDao.save(user);
    }

    public void update(User user) {
        User userTemp = userDao.findById(user.getId()).get();
        userTemp.setUsername(user.getUsername());
        userTemp.setPassword(user.getPassword());
        userTemp.setDepartmentId(user.getDepartmentId());
        userTemp.setDepartmentName(user.getDepartmentName());
        userDao.save(userTemp);
    }

    public User findById(String id) throws CommonException {
        Optional<User> byId = userDao.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new CommonException(ResultCode.FAIL);
        }
    }

    public void deleteById(String id) {
        userDao.deleteById(id);
    }


    /**
     * 查询全部用户列表
     *   参数，Map集合形式
     *     hasDept
     *     departmentId
     *     companyId
     *
     * @param map
     * @return
     */
    public Page<User> findAll(Map<String,Object> map, int page, int size) {
        //1、构造查询条件
        Specification<User> specification = new Specification<User>() {
            /**
             * 动态拼装查询条件，根据前台传入的值
             * @param root
             * @param criteriaQuery
             * @param criteriaBuilder
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                // 根据请求的companyId是否为空构造查询条件
                if (!StringUtils.isEmpty(map.get("companyId"))) {
                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),(String)map.get("companyId")));
                }
                // 根据请求的部门Id是否为空构造查询条件
                if (!StringUtils.isEmpty(map.get("departmentId"))) {
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),(String)map.get("departmentId")));
                }
                if (!StringUtils.isEmpty(map.get("hasDept"))) {
                    // 根据请求的hasDept判断  是否分配部门 0 未分配  1已分配
                    if ("0".equals((String) map.get("hasDept"))) {
                        list.add(criteriaBuilder.isNull(root.get("departmentId")));
                    } else {
                        list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //2、分页查询
        Page<User> pageUser = userDao.findAll(specification, new PageRequest(page-1, size));
        return pageUser;
    }

    public void assignRoles(String userId, List<String> roleIds) {
        // 1、根据ID查询用户
        User user = userDao.findById(userId).get();
        // 根据角色ID集合获取角色对象集合
        Set<Role> roles = new HashSet<>();
        roleIds.stream().forEach(e -> {
            Role role = roleDao.findById(e).get();
            roles.add(role);
        });
        // 2、设置用户和角色的集合关系
        user.setRoles(roles);
        // 3、更新用户
        userDao.save(user);
    }

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    /**
     *  完成图片处理 (上传到七牛云存储并且注册到百度云AI人脸库中)
     * @param id    用户id
     * @param file  用户上传的头像文件
     * @return      请求路径
     */
    public String uploadImage(String id, MultipartFile file) throws Exception {
        //1.根据id查询用户
        User user = userDao.findById(id).get();
        //2.根据图片上传到七牛云存储,获取到请求路径
        String imgUrl = new QiniuUploadUtil().upload(user.getId(), file.getBytes());
        //3.更新用户头像地址
        user.setStaffPhoto(imgUrl);
        userDao.save(user);
        //判断是否已经注册面部信息
        Boolean faceExist = baiduAiUtil.faceExist(id);
        String imgBase64 = Base64.encode(file.getBytes());
        if (faceExist){
            //更新
            baiduAiUtil.faceUpdate(id , imgBase64);
        }else{
            //注册
            baiduAiUtil.faceRegister(id , imgBase64);
        }
        //4.返回路径
        return imgUrl;
    }
}
