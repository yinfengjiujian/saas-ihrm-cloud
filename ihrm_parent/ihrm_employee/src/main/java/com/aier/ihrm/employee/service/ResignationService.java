package com.aier.ihrm.employee.service;

/**
 * <p>Title: com.aier.ihrm.employee.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 22:02
 * Description: No Description
 */
import com.aier.ihrm.domain.employee.EmployeeResignation;
import com.aier.ihrm.employee.dao.EmployeeResignationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ResignationService {

    @Autowired
    EmployeeResignationDao resignationDao;

    public void save(EmployeeResignation resignation) {
        resignation.setCreateTime(new Date());
        resignationDao.save(resignation);
    }

    public EmployeeResignation findById(String userId) {
        return resignationDao.findByUserId(userId);
    }
}
