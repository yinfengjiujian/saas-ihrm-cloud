package com.aier.ihrm.system.service;

import com.aier.ihrm.common.service.BaseService;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.common.utils.PermissionConstants;
import com.aier.ihrm.domain.system.Permission;
import com.aier.ihrm.domain.system.Role;
import com.aier.ihrm.system.dao.PermissionDao;
import com.aier.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Title: com.aier.ihrm.system.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/20 21:47
 * Description: No Description
 */
@Service
@Transactional
public class RoleService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 添加角色
     */
    public void save(Role role){
        role.setId(idWorker.getId() + "");
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role){
        Role target = roleDao.getOne(role.getId());
        target.setDescription(role.getDescription());
        target.setName(role.getName());
        roleDao.save(target);
    }

    /**
     * 根据ID查询角色
     */
    public Role findById(String id){
        return roleDao.findById(id).get();
    }

    /**
     * 查询所有角色:
     *      根据内部维护的公司id进行查询该公司的所有角色
     */
    public List<Role> findAll(String companyId){
        return roleDao.findAll(getSpec(companyId));
    }

    /**
     * 根据id删除角色
     */
    public void delete(String id){
        roleDao.deleteById(id);
    }

    /**
     * 获取分页角色集合
     * @param companyId 公司id
     * @param page  第几页
     * @param size  每页大小
     * @return  角色集合
     */
    public Page<Role> findByPage(String companyId, int page, int size){
        return roleDao.findAll(getSpec(companyId) , PageRequest.of(page-1 , size));
    }

    /**
     * 给角色分配权限
     * @param roleId
     * @param permIds
     */
    public void assignPerms(String roleId, List<String> permIds) {
        // 1、根据ID查询角色对象
        Role role = roleDao.findById(roleId).get();
        // 根据权限ID集合获取权限对象集合
        Set<Permission> permissionSet = new HashSet<>();
        permIds.stream().forEach(e -> {
            Permission permission = permissionDao.findById(e).get();
            /** 页面上能够选中的权限级别只是到菜单、按钮，但是按钮对应的API是没有选中的，根据逻辑
             *  分配了按钮权限，那么按钮对应的API也应该分配给这个角色
             *  根据类型type为API，权限表的父ID 查询权限集合
             * */
            List<Permission> apiList = permissionDao.findByTypeAndPid(PermissionConstants.PY_API, permission.getId());
            permissionSet.addAll(apiList); // 自动赋予API权限
            permissionSet.add(permission); // 当前菜单或按钮权限
        });
        // 2、设置角色和权限的集合关系
        role.setPermissions(permissionSet);
        // 3、更新角色
        roleDao.save(role);
    }
}
