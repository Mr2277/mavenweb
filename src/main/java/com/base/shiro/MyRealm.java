package com.base.shiro;

import com.bean.User;
import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthenticatingRealm{
    @Autowired
    private UserService userService;
    /**
     * 认证回调函数，登录信息和用户验证信息验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 把token转换成User对象
        User userLogin = tokenToUser((UsernamePasswordToken) authenticationToken);
        System.out.println("fffff");
        // 验证用户是否可以登录
        User ui = userService.doUserLogin(userLogin);
        if(ui == null)
            return null; // 异常处理，找不到数据

        // 设置session
        Session session = SecurityUtils.getSubject().getSession();
        //session.setAttribute(ShiroDbRealm.SESSION_USER_KEY, ui);
        //当前 Realm 的 name
        String realmName = this.getName();
        //登陆的主要信息: 可以是一个实体类的对象, 但该实体类的对象一定是根据 token 的 username 查询得到的.
//		Object principal = ui.getUsername();
        Object principal = authenticationToken.getPrincipal();
        return new SimpleAuthenticationInfo(principal, userLogin.getPassword(), realmName);
    }
    private User tokenToUser(UsernamePasswordToken authcToken) {
        System.out.println("ttttt");
        User user = new User();
        user.setUsername(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }
}
