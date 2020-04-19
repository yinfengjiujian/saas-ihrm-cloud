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
 * Date: 2020/4/19 18:09
 * Description: 菜单资源
 */
@Entity
@Table(name = "pe_permission_menu")
@Data
public class PermissionMenu implements Serializable {

    private static final long serialVersionUID = -1002411490113957485L;
    @Id
    private String id;
    private String menuIcon; //展示图标
    private String menuOrder; //排序号

}
