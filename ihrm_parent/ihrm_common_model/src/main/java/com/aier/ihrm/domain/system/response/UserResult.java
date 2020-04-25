package com.aier.ihrm.domain.system.response;

import com.aier.ihrm.domain.system.Role;
import com.aier.ihrm.domain.system.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: com.aier.ihrm.domain.system.response</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/22 21:54
 * Description: No Description
 */
@Getter
@Setter
public class UserResult implements Serializable {

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
    private Date timeOfDimission;

    //用户具备的角色集合
    private List<String> roleIds = new ArrayList<>();

    public UserResult(User user) {
        BeanUtils.copyProperties(user,this);
        for (Role role : user.getRoles()) {
            this.roleIds.add(role.getId());
        }
    }
}
