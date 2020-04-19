package com.aier.ihrm.domain.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>Title: com.aier.ihrm.domain.system</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 18:07
 * Description: 权限表
 */
@Entity
@Table(name = "pe_permission")
@Data
@NoArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {

    private static final long serialVersionUID = -4990810027542971546L;
    /**
     * 主 键
     */
    @Id
    private String id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;


    /**
     * 权限编码
     */
    private String code;


    /**
     * 权限描述
     */
    private String description;


    private String pid;

    //可见状态
    private String enVisible;


    public Permission(String name, Integer type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }
}
