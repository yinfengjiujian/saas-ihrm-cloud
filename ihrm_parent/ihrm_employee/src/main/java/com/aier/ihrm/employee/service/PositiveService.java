package com.aier.ihrm.employee.service;

/**
 * <p>Title: com.aier.ihrm.employee.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 22:00
 * Description: No Description
 */
import com.aier.ihrm.domain.employee.EmployeePositive;
import com.aier.ihrm.employee.dao.PositiveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PositiveService {
    @Autowired
    private PositiveDao positiveDao;

    public EmployeePositive findById(String uid, Integer status) {
        EmployeePositive positive = positiveDao.findByUserId(uid);
        if (status != null && positive != null) {
            if (!positive.getEstatus().equals(status)) {
                positive = null;
            }
        }
        return positive;
    }

    public EmployeePositive findById(String uid) {
        return positiveDao.findByUserId(uid);
    }

    public void save(EmployeePositive positive) {
        positive.setCreateTime(new Date());
        positive.setEstatus(1);//未执行
        positiveDao.save(positive);
    }
}
