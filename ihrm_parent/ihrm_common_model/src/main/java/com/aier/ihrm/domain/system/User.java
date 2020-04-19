package com.aier.ihrm.domain.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: com.aier.ihrm.domain.system</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 18:01
 * Description: No Description
 */

/**
 * 用户实体类
 */
@Entity
@Table(name = "bs_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4297464181093070302L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密 码
     */
    private String password;


    /**
     * 启用状态 0为禁用  1为启用
     */
    private Integer enableState;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    private String companyId;


    private String companyName;


    /**
     * 部门ID
     */

    private String departmentId;


    /**
     * 入职时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeOfEntry;


    /**
     * 聘用形式
     */
    private Integer formOfEmployment;


    /**
     * 工 号
     */
    private String workNumber;


    /**
     * 管理形式
     */
    private String formOfManagement;


    /**
     * 工作城市
     */
    private String workingCity;


    /**
     * 转正时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date correctionTime;


    /**
     * 用户级别：saasAdmin，coAdmin，user
     */
    private String level;

    /**
     * 在职状态 1.在职	2.离职
     */
    private Integer inServiceStatus;


    private String departmentName;

    /**
     * 离职时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timeOfDimission;


    /**
     * @ JsonIgnore
     * 此注解的意思是JSON的时候忽略此属性
     */
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "pe_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<Role>(); // 用户与角色	多对多
}