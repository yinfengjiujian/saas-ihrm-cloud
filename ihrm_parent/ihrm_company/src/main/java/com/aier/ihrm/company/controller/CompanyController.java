package com.aier.ihrm.company.controller;

import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.company.service.CompanyService;
import com.aier.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Title: com.aier.ihrm.company.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 19:27
 * Description: No Description
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result save(@RequestBody Company company) {
        companyService.add(company);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Company company) {
        company.setId(id);
        companyService.update(company);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        companyService.deleteById(id);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) {
        Company company = companyService.findById(id);
        return new Result(ResultCode.SUCCESS,company);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result findAll() {
        List<Company> all = companyService.findAll();
        return new Result(ResultCode.SUCCESS,all);
    }


}
