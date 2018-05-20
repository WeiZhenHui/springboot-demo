package com.veaf.controller;

import com.veaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.veaf.service.UserService;

import java.util.List;

/**
 * user控制器
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        User user = userService.findUserByName(name);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getSex()+"/"+user.getAge();
        else return "null";
    }

    @RequestMapping(value = "/showAll")
    @ResponseBody
    public String showAll(){
        List<User> userList= userService.getAllUsers();
        String userStr= "";
        if(null!=userList && userList.size()>0) {
            for (User user : userList) {
                userStr += "[" + user.getId() + " - " + user.getName() + " - " + user.getSex() + " - " + user.getAge() + "]<br/>";
            }
            return userStr;
        }else
            return "null";
    }
}
