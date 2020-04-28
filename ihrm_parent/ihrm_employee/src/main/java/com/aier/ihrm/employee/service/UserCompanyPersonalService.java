package com.aier.ihrm.employee.service;

/**
 * <p>Title: com.aier.ihrm.employee.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 22:05
 * Description: No Description
 */
import com.aier.ihrm.domain.employee.UserCompanyPersonal;
import com.aier.ihrm.domain.employee.response.EmployeeReportResult;
import com.aier.ihrm.employee.dao.UserCompanyPersonalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserCompanyPersonalService {
    @Autowired
    private UserCompanyPersonalDao userCompanyPersonalDao;

    public void save(UserCompanyPersonal personalInfo) {
        userCompanyPersonalDao.save(personalInfo);
    }

    public UserCompanyPersonal findById(String userId) {
        return userCompanyPersonalDao.findByUserId(userId);
    }

    public List<EmployeeReportResult> findByReport(String companyId, String month) {
        return userCompanyPersonalDao.findByReport(companyId , month + "%");
    }
}
