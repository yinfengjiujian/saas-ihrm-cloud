package com.aier.ihrm.company.controller;

import com.aier.ihrm.common.controller.BaseController;
import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.company.service.CompanyService;
import com.aier.ihrm.company.service.DepartmentService;
import com.aier.ihrm.domain.company.Company;
import com.aier.ihrm.domain.company.Department;
import com.aier.ihrm.domain.company.response.DeptListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: com.aier.ihrm.company.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 10:25
 * Description: No Description
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/company")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    /**
     * 添加部门
     */
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Result add(@RequestBody Department department) throws Exception {
        department.setCompanyId(companyId);
        departmentService.save(department);
        return Result.SUCCESS();
    }

    /**
     * 组织架构列表
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public Result findAll() throws Exception {
        List<Department> list = departmentService.findAll(companyId);
        Company company = companyService.findById(companyId);
        return	new Result(ResultCode.SUCCESS,new DeptListResult(company,list));
    }

    /**
     * 根据id查询
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Department department = departmentService.findById(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 修改部门信息
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Department department) throws Exception {
        department.setId(id);
        departmentService.update(department);
        return Result.SUCCESS();
    }

    /**
     * 删除部门
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        departmentService.deleteById(id);
        return Result.SUCCESS();
    }


}
