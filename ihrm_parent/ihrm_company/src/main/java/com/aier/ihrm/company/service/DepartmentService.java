package com.aier.ihrm.company.service;

import com.aier.ihrm.common.service.BaseService;
import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.company.dao.DepartmentDao;
import com.aier.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: com.aier.ihrm.company.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 10:16
 * Description: No Description
 */
@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private IdWorker idWorker;

    public void save(Department department) {
        department.setId(idWorker.getId()+"");
        department.setCreateTime(new Date());
        departmentDao.save(department);
    }

    public void update(Department department) {
        Department sourceDepartment = departmentDao.findById(department.getId()).get();
        sourceDepartment.setName(department.getName());
        sourceDepartment.setParentId(department.getParentId());
        sourceDepartment.setManagerId(department.getManagerId());
        sourceDepartment.setIntroduce(department.getIntroduce());
        sourceDepartment.setManager(department.getManager());
        sourceDepartment.setCode(department.getCode());
        departmentDao.save(sourceDepartment);
    }

    /**
     *根据ID获取部门信息
     *
     *@param id 部门ID
     *@return 部门信息
     */
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }

    /**
     *获取部门列表
     */
    public List<Department> findAll(String companyId) {
        return departmentDao.findAll(getSpec(companyId));
    }

    /**
     *删除部门
     *
     *@param id 部门ID
     */
    public void deleteById(String id) {
        departmentDao.deleteById(id);
    }

}
