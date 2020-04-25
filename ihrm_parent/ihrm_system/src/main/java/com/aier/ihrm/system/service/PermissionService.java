package com.aier.ihrm.system.service;

import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.common.utils.BeanMapUtils;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.common.utils.PermissionConstants;
import com.aier.ihrm.domain.system.Permission;
import com.aier.ihrm.domain.system.PermissionApi;
import com.aier.ihrm.domain.system.PermissionMenu;
import com.aier.ihrm.domain.system.PermissionPoint;
import com.aier.ihrm.system.dao.PermissionApiDao;
import com.aier.ihrm.system.dao.PermissionDao;
import com.aier.ihrm.system.dao.PermissionMenuDao;
import com.aier.ihrm.system.dao.PermissionPointDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * Date: 2020/4/20 22:41
 * Description: No Description
 */
@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionMenuDao permissionMenuDao;

    @Autowired
    private PermissionPointDao permissionPointDao;

    @Autowired
    private PermissionApiDao permissionApiDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存权限
     */
    public void save(Map<String, Object> map) throws Exception {
        // 主键的值
        String id = idWorker.getId() + "";
        // 1、通过map构造出permission对象
        Permission permission = BeanMapUtils.mapToBean(map, Permission.class);
        permission.setId(id);
        // 2、根据type类型构造不同的资源对象(菜单、按钮、API)
        Integer type = permission.getType();
        switch (type) {
            case PermissionConstants.PY_MENU:
                PermissionMenu permissionMenu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                permissionMenu.setId(id);
                permissionMenuDao.save(permissionMenu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint permissionPoint = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                permissionPoint.setId(id);
                permissionPointDao.save(permissionPoint);
                break;
            case PermissionConstants.PY_API:
                PermissionApi permissionApi = BeanMapUtils.mapToBean(map, PermissionApi.class);
                permissionApi.setId(id);
                permissionApiDao.save(permissionApi);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        // 3、保存
        permissionDao.save(permission);
    }

    /**
     * 更新
     */
    public void update(Map<String, Object> map) throws Exception {
        Permission perm = BeanMapUtils.mapToBean(map, Permission.class);
        //通过传递的权限id查询权限
        Permission permission = permissionDao.findById(perm.getId()).get();
        permission.setName(perm.getName());
        permission.setCode(perm.getCode());
        permission.setDescription(perm.getDescription());
        perm.setEnVisible(perm.getEnVisible());
        //根据类型构造不同的资源
        int type = perm.getType();
        switch (type) {
            case PermissionConstants.PY_MENU:
                PermissionMenu menu = BeanMapUtils.mapToBean(map, PermissionMenu.class);
                menu.setId(perm.getId());
                permissionMenuDao.save(menu);
                break;
            case PermissionConstants.PY_POINT:
                PermissionPoint point = BeanMapUtils.mapToBean(map, PermissionPoint.class);
                point.setId(perm.getId());
                permissionPointDao.save(point);
                break;
            case PermissionConstants.PY_API:
                PermissionApi api = BeanMapUtils.mapToBean(map, PermissionApi.class);
                api.setId(perm.getId());
                permissionApiDao.save(api);
                break;
            default:
                throw new CommonException(ResultCode.FAIL);
        }
        //更新权限,更新资源
        permissionDao.save(permission);
    }


    /**
     * 根据id查询权限
     */
    public Map findById(String id) throws CommonException {
        Permission permission = permissionDao.findById(id).get();
        Integer type = permission.getType();

        //构造map集合
        Object object = null;

        if (type == PermissionConstants.PY_MENU) {
            object = permissionMenuDao.findById(id).get();
        } else if (type == PermissionConstants.PY_POINT) {
            object = permissionPointDao.findById(id).get();
        } else if (type == PermissionConstants.PY_API) {
            object = permissionApiDao.findById(id).get();
        } else {
            throw new CommonException(ResultCode.FAIL);
        }
        Map<String, Object> map = BeanMapUtils.beanToMap(object);
        map.put("name" , permission.getName());
        map.put("type" , permission.getType());
        map.put("code" , permission.getCode());
        map.put("description" , permission.getDescription());
        map.put("pid" , permission.getPid());
        map.put("enVisible" , permission.getEnVisible());

        return map;
    }


    /**
     * 查询全部列表
     * type     :   查询全部权限列表
     * 0 : 菜单 + 按钮(权限点)    1 ： 菜单  2 : 按钮(权限点) 3 ： API接口
     * enVisible :
     * 0 ： 查询SaaS平台的最高权限   1 ： 查询企业的权限
     */
    public List<Permission> findAll(Map<String, Object> map) {
        Specification<Permission> specification = new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据父id查询
                if (!StringUtils.isEmpty(map.get("pid"))){
                    list.add(criteriaBuilder.equal(root.get("pid").as(String.class) , (String)map.get("pid")));
                }

                //根据enVisible查询
                if (!StringUtils.isEmpty(map.get("enVisible"))){
                    list.add(criteriaBuilder.equal(root.get("enVisible").as(String.class) , (String)map.get("enVisible")));
                }
                //根据类型type进行查询
                if (!StringUtils.isEmpty(map.get("type"))){
                    String type = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                    if ("0".equals(type)){
                        in.value(1).value(2);
                    }else {
                        in.value(Integer.parseInt(type));
                    }
                    list.add(in);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(specification);
    }


    /**
     * 根据id删除权限
     */
    public void deleteById(String id) throws CommonException {
        Permission permission = permissionDao.findById(id).get();
        Integer type = permission.getType();
        if (type == PermissionConstants.PY_MENU) {
            permissionMenuDao.deleteById(id);
        } else if (type == PermissionConstants.PY_POINT) {
            permissionPointDao.deleteById(id);
        } else if (type == PermissionConstants.PY_API) {
            permissionApiDao.deleteById(id);
        } else {
            throw new CommonException(ResultCode.FAIL);
        }
        permissionDao.deleteById(id);
    }

}
