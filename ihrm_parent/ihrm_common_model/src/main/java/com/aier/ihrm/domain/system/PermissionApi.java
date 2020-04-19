package com.aier.ihrm.domain.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>Title: com.aier.ihrm.domain.system</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 18:12
 * Description: API资源
 */
@Entity
@Table(name = "pe_permission_api")
@Data
public class PermissionApi implements Serializable {

    private static final long serialVersionUID = -1803315043290784820L;

    @Id
    private String id;
    private String apiUrl;
    private String apiMethod;
    private String apiLevel;//权限等级，1为通用接口权限，2为需校验接口权限

}
