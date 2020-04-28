package com.aier.ihrm.system.feign;

import com.aier.ihrm.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Title: com.aier.ihrm.system.feign</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/28 20:40
 * Description: No Description
 */
@FeignClient(value = "ihrm-company")
public interface CompanyFeign {

    @RequestMapping(value = "/company/department/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable(name = "id") String id);
}
