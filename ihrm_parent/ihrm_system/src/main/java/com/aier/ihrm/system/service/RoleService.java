package com.aier.ihrm.system.service;

import com.aier.ihrm.common.service.BaseService;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.domain.system.Role;
import com.aier.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: com.aier.ihrm.system.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/20 21:47
 * Description: No Description
 */
@Service
public class RoleService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

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
}
