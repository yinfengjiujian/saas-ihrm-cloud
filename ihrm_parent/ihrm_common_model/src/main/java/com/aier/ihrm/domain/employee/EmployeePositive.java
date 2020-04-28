package com.aier.ihrm.domain.employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: com.aier.ihrm.domain.employee</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/26 21:44
 * Description: No Description
 */
//转正申请
@Entity
@Table(name = "em_positive")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePositive implements Serializable {
    private static final long serialVersionUID = 2391824518947910773L;
    /**
     * 员工ID
     */
    @Id
    private String userId;
    /**
     * 转正日期
     */
    private Date dateOfCorrection;
    /**
     * 转正评价
     */
    private String correctionEvaluation;
    /**
     * 附件
     */
    private String enclosure;
    /**
     * 单据状态 1是未执行，2是已执行
     */
    private Integer estatus;
    /**
     * 创建时间
     */
    private Date createTime;
}
