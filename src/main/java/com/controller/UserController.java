package com.controller;

import com.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/dologin.do") //url
    public String dologin(User user, Model model) {
        //logger.info("login ....");
        String info = loginUser(user);
        if (!"SUCC".equals(info)) {
            model.addAttribute("failMsg", "User does not exist or password error!");
            return "jsp/fail";
        } else {
            model.addAttribute("successMsg", "login Succ!");
            model.addAttribute("name", user.getUsername());
            return "jsp/success";
        }
    }
    private String loginUser(User user) {
        if (isRelogin(user)) return "SUCC"; //

        return shiroLogin(user); //
    }

    private String shiroLogin(User user) {
        //
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);

        // shiro
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException ex) {
            return "User does not exist or password error!";
        } catch (IncorrectCredentialsException ex) {
            return "User does not exist or password error!";
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ex.getMessage(); //
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Internal error, please try again!";
        }
        return "SUCC";
    }
    private boolean isRelogin(User user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; //
        }
        return false; //
    }
}
