package com.aier.ihrm.system.controller;

import com.aier.ihrm.common.controller.BaseController;
import com.aier.ihrm.common.entity.PageResult;
import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.domain.system.User;
import com.aier.ihrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>Title: com.aier.ihrm.system.controller</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 22:30
 * Description: No Description
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/sys")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Result save(@RequestBody User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        userService.save(user);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id) throws CommonException {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS,user);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        userService.deleteById(id);
        return Result.SUCCESS();
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Result findAll(int page, int size, @RequestParam Map map) {
        map.put("companyId",companyId);
        Page<User> userPage = userService.findAll(map, page, size);
        PageResult<User> pageResult = new PageResult<>(userPage.getTotalElements(),userPage.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }

}
