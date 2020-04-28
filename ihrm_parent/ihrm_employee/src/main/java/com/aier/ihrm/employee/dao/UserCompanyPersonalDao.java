package com.aier.ihrm.employee.dao;



import com.aier.ihrm.domain.employee.UserCompanyPersonal;
import com.aier.ihrm.domain.employee.response.EmployeeReportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>Title: com.aier.ihrm.employee.dao</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 21:41
 * Description: No Description
 */
public interface UserCompanyPersonalDao extends JpaRepository<UserCompanyPersonal, String>, JpaSpecificationExecutor<UserCompanyPersonal> {

    UserCompanyPersonal findByUserId(String userId);

    @Query(value = "select new com.aier.ihrm.domain.employee.response.EmployeeReportResult(a,b) from UserCompanyPersonal a " +
            "LEFT JOIN EmployeeResignation b on a.userId = b.userId where a.companyId = ?1 and a.timeOfEntry " +
            "like ?2 or (b.resignationTime like ?2)")
    List<EmployeeReportResult> findByReport(String companyId, String month);
}
