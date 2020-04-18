package com.aier.ihrm.company.service;

import com.aier.ihrm.common.utils.IdWorker;
import com.aier.ihrm.company.dao.CompanyDao;
import com.aier.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: com.aier.ihrm.company.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 18:39
 * Description: No Description
 */
@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存企业
     **/
    public Company add(Company company) {
        company.setId(idWorker.getId() + "");
        company.setCreateTime(new Date());
        company.setManagerId(idWorker.getId()+"");
        company.setState(1);	//启用
        company.setAuditState("0"); //待审核
        company.setBalance(0d);
        return companyDao.save(company);
    }

    /**
     * 更新企业
     **/
    public Company update(Company company) {
        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        return companyDao.save(temp);
    }

    /**
     * 删除企业
     **/
    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    /**
     * 根据ID查询企业
     **/
    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    /**
     * 查询企业列表
     **/
    public List<Company> findAll() {
        return companyDao.findAll();
    }

}
