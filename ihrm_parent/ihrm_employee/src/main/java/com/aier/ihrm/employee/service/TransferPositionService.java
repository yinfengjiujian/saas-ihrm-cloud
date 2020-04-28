package com.aier.ihrm.employee.service;

/**
 * <p>Title: com.aier.ihrm.employee.service</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 22:03
 * Description: No Description
 */
import com.aier.ihrm.domain.employee.EmployeeTransferPosition;
import com.aier.ihrm.employee.dao.TransferPositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TransferPositionService {
    @Autowired
    private TransferPositionDao transferPositionDao;

    public EmployeeTransferPosition findById(String uid, Integer status) {
        EmployeeTransferPosition transferPosition = transferPositionDao.findByUserId(uid);
        if (status != null && transferPosition != null) {
            if (!transferPosition.getEstatus().equals(status)) {
                transferPosition = null;
            }
        }
        return transferPosition;
    }

    public void save(EmployeeTransferPosition transferPosition) {
        transferPosition.setCreateTime(new Date());
        transferPosition.setEstatus(1); //未执行
        transferPositionDao.save(transferPosition);
    }
}
