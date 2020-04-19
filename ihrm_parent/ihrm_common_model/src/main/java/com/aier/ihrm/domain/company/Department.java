package com.aier.ihrm.domain.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>Title: com.aier.ihrm.domain.company</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 10:10
 * Description: No Description
 */
/**
 * (Department)实体类
 */
@Entity
@Table(name = "co_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private static final long serialVersionUID = -9084332495284489553L;
    //ID
    @Id
    private String id;
    /**
     * 父级ID
     */
    private String parentId;
    /**
     * 企业ID
     * */
    private String companyId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门编码，同级部门不可重复
     */
    private String code;


    /**
     * 负责人ID
     */
    private String managerId;
    /**
     *	负责人名称
     */
    private String manager;

    /**
     * 介 绍
     */
    private String introduce;
    /**
     * 创建时间
     */
    private Date createTime;
}
