package com.yangsm.demo.myshiro.auth;

import com.yangsm.demo.myshiro.entity.Permission;
import com.yangsm.demo.myshiro.entity.Role;
import com.yangsm.demo.myshiro.entity.User;
import com.yangsm.demo.myshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 身份认证:验证用户输入的账号和密码是否正确。
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("==========================Realm doGetAuthenticationInfo 身份认证");

        //获取用户输入的账号
        String userName = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUserName(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,//这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()//realm name
        );

        return authenticationInfo;
    }

    /**
     * 权限信息
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("==========================Realm doGetAuthorizationInfo 权限信息");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user  = (User)principals.getPrimaryPrincipal();
        for(Role role : user.getRoleList()){
            //添加角色
            authorizationInfo.addRole(role.getRole());
            for(Permission p:role.getPermissions()){
                //添加权限
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }

        //测试增加 Button权限
        authorizationInfo.addStringPermission("deleteall");

        return authorizationInfo;
    }

}
