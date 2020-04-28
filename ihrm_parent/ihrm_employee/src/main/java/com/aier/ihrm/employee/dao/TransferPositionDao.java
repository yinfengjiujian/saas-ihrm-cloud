package com.aier.ihrm.employee.dao;



import com.aier.ihrm.domain.employee.EmployeeTransferPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>Title: com.aier.ihrm.employee.dao</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 21:40
 * Description: No Description
 */
public interface TransferPositionDao extends JpaRepository<EmployeeTransferPosition, String>, JpaSpecificationExecutor<EmployeeTransferPosition> {
    EmployeeTransferPosition findByUserId(String uid);
}
