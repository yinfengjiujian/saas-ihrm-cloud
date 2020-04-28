package com.aier.ihrm.employee.service;

/**
 * <p>Title: com.aier.ihrm.employee.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 22:05
 * Description: No Description
 */
import com.aier.ihrm.domain.employee.UserCompanyJobs;
import com.aier.ihrm.employee.dao.UserCompanyJobsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCompanyJobsService {
    @Autowired
    private UserCompanyJobsDao userCompanyJobsDao;

    public void save(UserCompanyJobs jobsInfo) {
        userCompanyJobsDao.save(jobsInfo);
    }

    public UserCompanyJobs findById(String userId) {
        return userCompanyJobsDao.findByUserId(userId);
    }
}
