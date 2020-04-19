package com.aier.ihrm.company;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: com.aier.ihrm.company</p>
 * <p>Company:爱尔眼科集团</p>
 * <p>Copyright:Copyright(c)</p>
 * User: Administrator
 * Date: 2020/4/19 9:47
 * Description: No Description
 */
public class MapToJSon {
    public static void main(String[] args) {
        Map<String,Object> roles = new HashMap<>();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        menus.add("2");
        menus.add("6");
        menus.add("7");
        points.add("8");
        points.add("4");
        apis.add("1");
        roles.put("menus",menus);
        roles.put("points",points);
        roles.put("apis",apis);
        System.out.println(JSON.toJSONString(roles));
    }
}
