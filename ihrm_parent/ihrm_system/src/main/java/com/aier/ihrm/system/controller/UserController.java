package com.aier.ihrm.system.controller;

import com.aier.ihrm.common.controller.BaseController;
import com.aier.ihrm.common.entity.PageResult;
import com.aier.ihrm.common.entity.Result;
import com.aier.ihrm.common.entity.ResultCode;
import com.aier.ihrm.common.exception.CommonException;
import com.aier.ihrm.domain.system.User;
import com.aier.ihrm.domain.system.response.ProfileResult;
import com.aier.ihrm.domain.system.response.UserResult;
import com.aier.ihrm.system.service.PermissionService;
import com.aier.ihrm.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
 * Date: 2020/4/19 22:30
 * Description: No Description
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

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
        UserResult userResult = new UserResult(user);
        return new Result(ResultCode.SUCCESS,userResult);
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Result.SUCCESS();
    }

    @RequiresPermissions(value = "API-USER-DELETE")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE,name = "API-USER-DELETE")
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

    /**
     * 给用户分配角色
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result saveUserToRole(@RequestBody Map<String,Object> map) {
        String userId = (String) map.get("id");
        List<String> roleIds = (List<String>) map.get("roleIds");
        userService.assignRoles(userId,roleIds);
        return Result.SUCCESS();
    }

    /**
     * 用户登录
     * @param loginMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        try {
            //构造登录令牌
            password = new Md5Hash(password , mobile , 3).toString();// 密码、盐、加密次数
            UsernamePasswordToken token = new UsernamePasswordToken(mobile , password);
            //获取subject
            Subject subject = SecurityUtils.getSubject();
            //调用login方法,进入realm完成认证
            subject.login(token);

            subject.getSession().setTimeout(3600000 * 24);
            //获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //构造返回结果
            return new Result(ResultCode.SUCCESS , sessionId);
        }catch (Exception e){
            return new Result(ResultCode.MOBILE_OR_PASSWORD_ERROR);
        }
//        User user = userService.findByMobile(mobile);
//        if (Objects.isNull(user) || !user.getPassword().equals(password)) {
//            return new Result(ResultCode.MOBILE_OR_PASSWORD_ERROR);
//        } else {
//            // 登录成功
//            // 获取到用户所有的可访问API权限
//            StringBuffer stringBuffer = new StringBuffer();
//            for (Role role : user.getRoles()) {
//                for (Permission permission : role.getPermissions()) {
//                    if (permission.getType().equals(PermissionConstants.PY_API)) {
//                        stringBuffer.append(permission.getCode()).append(",");
//                    }
//                }
//            }
//            Map<String,Object> map = new HashMap<>();
//            map.put("companyId",user.getCompanyId());
//            map.put("companyName",user.getCompanyName());
//            map.put("apis",stringBuffer.toString());//用户能访问的API权限
//            String token = jwtUtils.createJWT(user.getId(), user.getUsername(), map);
//            return new Result(ResultCode.SUCCESS,token);
//        }
    }

    /**
     * 用户登录成功后，获取用户信息
     * 1、从请求头中获取token，解析获取用户ID
     * 2、根据用户ID查询用户信息
     * 3、构建返回结果
     *
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public Result getUserInfo() {
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        //获取安全数据
        ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
        return new Result(ResultCode.SUCCESS, result);
    }

    /**
     *
     * @param args
    public static void main(String[] args) {
        String password = new Md5Hash("123456", "13800000001", 3).toString();
        System.out.println("13800000001==>" + password);
        String password2 = new Md5Hash("123456", "13800000002", 3).toString();
        System.out.println("13800000002==>" + password2);
        String password3 = new Md5Hash("123456", "13800000003", 3).toString();
        System.out.println("13800000003==>" + password3);
        String password4 = new Md5Hash("123456", "13800000004", 3).toString();
        System.out.println("13800000004==>" + password4);
        String password5 = new Md5Hash("123456", "13400000001", 3).toString();
        System.out.println("13400000001==>" + password5);
        String password6 = new Md5Hash("123456", "13400000002", 3).toString();
        System.out.println("13400000002==>" + password6);
        String password7 = new Md5Hash("123456", "13500000001", 3).toString();
        System.out.println("13500000001==>" + password7);
        String password8 = new Md5Hash("123456", "13500000002", 3).toString();
        System.out.println("13500000002==>" + password8);
        String password9 = new Md5Hash("123456", "13500000003", 3).toString();
        System.out.println("13500000003==>" + password9);
        String password10 = new Md5Hash("123456", "13458695856", 3).toString();
        System.out.println("13458695856==>" + password10);
    }
     */
}
