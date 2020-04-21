package com.aier.ihrm.system.controller;

import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.domain.system.Permission;
import com.aier.ihrm.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: com.aier.ihrm.system.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/20 22:36
 * Description: No Description
 */
@RestController
@RequestMapping(value = "/sys")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 保存
     *
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public Result save(@RequestBody Map<String, Object> map) throws Exception {
        permissionService.save(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public Result findAll(@RequestParam() Map map) {
        List<Permission> permissionList = permissionService.findAll(map);
        return new Result(ResultCode.SUCCESS, permissionList);
    }

    /**
     * 根据Id查询
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) throws CommonException {
        Map serviceById = permissionService.findById(id);
        return new Result(ResultCode.SUCCESS, serviceById);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Map<String, Object> map) throws Exception {
        //构造id
        map.put("id", id);
        permissionService.update(map);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据Id删除
     * // 1、删除权限
     *    2、删除资源
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) throws CommonException {
        permissionService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
