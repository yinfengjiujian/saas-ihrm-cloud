package com.aier.ihrm.company.dao;

import com.aier.ihrm.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>Title: com.aier.ihrm.company.dao</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/18 18:09
 * Description: No Description
 *
 *   自定义DAO接口继承
 *     JpaRepository<实体类，主键类型>
 *     JpaSpecificationExecutor<实体类>
 *
 */
public interface CompanyDao extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {

}
