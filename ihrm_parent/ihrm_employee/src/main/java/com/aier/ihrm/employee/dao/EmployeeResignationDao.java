package com.aier.ihrm.employee.dao;



import com.aier.ihrm.domain.employee.EmployeeResignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>Title: com.aier.ihrm.employee.dao</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 21:39
 * Description: No Description
 */
public interface EmployeeResignationDao extends JpaRepository<EmployeeResignation, String>, JpaSpecificationExecutor<EmployeeResignation> {
    EmployeeResignation findByUserId(String uid);
}