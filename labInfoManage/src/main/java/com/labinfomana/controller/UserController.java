package com.labinfomana.controller;

import org.springframework.beans.BeanUtils;

import com.labinfomana.entity.User;
import com.labinfomana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping({"/home"})
public class UserController {

    @Autowired
    public UserService userService;
    @RequestMapping(value = "/user")
    @ResponseBody
    public String user(){

        User user=userService.findByName("htt");
        return "htt hello hello hello heeeeee黄甜甜水瓶座女王大人"+"年龄"+user.getAge();
    }

    @RequestMapping("/login")
    String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    String userLogin(User user, Model model) {
        boolean verify = userService.verifyUser(user);
        System.out.println(verify+"登录用户名"+user.getUsername()+"登录密码"+user.getPassword());
        if (verify) {
            model.addAttribute("name", user.getUsername());
            model.addAttribute("password", user.getPassword());
            return "index";
        } else {
            return "redirect:/notVerify";
        }

    }
    public void userRegister(User user,Model model){
        boolean verify=userService.verifyUser(user);
        if(!verify){
            userService.insertUser(user);
        }
    }

    @RequestMapping("/logining")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("name"+username+"password"+password);

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        Map<String,Object> model=new HashMap<String,Object>();
        if(userService.findByName(username)!=null){
            user=userService.findByName(username);
            System.out.println("能查到信息");
            model.put("user",user);
            return new ModelAndView("login",model);
        }else{
            System.out.println("查不到信息");
            model.put("error","用户名不存在或者密码错误");
            model.put("user",user);
            return new ModelAndView("login",model);
        }
    }



}
