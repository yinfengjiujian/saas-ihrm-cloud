package com.aier.ihrm.system.service;

import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.domain.system.User;
import com.aier.ihrm.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: com.aier.ihrm.system.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 21:50
 * Description: No Description
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    public void save(User user) {
        user.setId(idWorker.getId()+"");
        user.setPassword("123456");
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

    public User findById(String id) {
        return userDao.findById(id).get();
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
                // 根据请求的hasDept判断  是否分配部门 0 未分配  1已分配
                if (StringUtils.isEmpty(map.get("hasDept")) || "0".equals((String) map.get("hasDept"))) {
                    list.add(criteriaBuilder.isNull(root.get("departmentId")));
                } else {
                    list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //2、分页查询
        Page<User> pageUser = userDao.findAll(specification, new PageRequest(page-1, size));
        return pageUser;
    }
}
