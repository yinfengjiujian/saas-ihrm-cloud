package com.aier.ihrm.domain.company.response;

import com.aier.ihrm.domain.company.Company;
import com.aier.ihrm.domain.company.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>Title: com.aier.ihrm.domain.company.response</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 10:43
 * Description: No Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptListResult {
    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> depts;

    public DeptListResult(Company company,List depts) {
        this.depts = depts;
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();
    }
}
