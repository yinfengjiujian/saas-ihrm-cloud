package com.aier.ihrm.system.controller;

import com.aier.ihrm.common.controller.BaseController;
import com.aier.ihrm.common.entity.PageResult;
import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.domain.system.Role;
import com.aier.ihrm.domain.system.response.RoleResult;
import com.aier.ihrm.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: com.aier.ihrm.system.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/20 21:45
 * Description: No Description
 */
//解决跨域
@CrossOrigin
@RestController
@RequestMapping(value = "/sys")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    //添加角色
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) throws Exception {
        role.setCompanyId(companyId);
        roleService.save(role);
        return Result.SUCCESS();
    }

    //更新角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role) throws Exception {
        roleService.update(role);
        return Result.SUCCESS();
    }

    //删除角色
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.findById(id);
        RoleResult roleResult = new RoleResult(role);
        return new Result(ResultCode.SUCCESS, roleResult);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page, int pagesize, Role role) throws Exception {
        Page<Role> searchPage = roleService.findByPage(companyId, page, pagesize);
        PageResult<Role> pageResult = new PageResult(searchPage.getTotalElements(), searchPage.getContent());
        return new Result(ResultCode.SUCCESS, pageResult);
    }

    @RequestMapping(value="/role/list" ,method=RequestMethod.GET)
    public Result findAll() throws Exception {
        List<Role> roleList = roleService.findAll(companyId);
        return new Result(ResultCode.SUCCESS,roleList);
    }

    /**
     * 给角色分配权限
     * @param map
     * @return
     */
    @RequestMapping(value = "/role/assignPerm", method = RequestMethod.PUT)
    public Result saveRoleToAuthority(@RequestBody Map<String,Object> map) {
        //获取角色ID
        String roleId = (String) map.get("id");
        // 获取到权限Id列表
        List<String> permIds = (List<String>) map.get("permIds");
        roleService.assignPerms(roleId,permIds);
        return Result.SUCCESS();
    }

}
