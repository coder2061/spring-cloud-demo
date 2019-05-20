package com.springcloud.common.tool.shiro.token;

import com.springcloud.common.tool.exception.BizErrorCode;
import com.springcloud.common.tool.shiro.bean.LoginUser;
import com.springcloud.common.tool.shiro.bean.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Token工具类
 *
 * @author jiangyf
 */
public class TokenManager {
    /**
     * 获取主体
     *
     * @return
     */
    public static Subject subject() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw BizErrorCode.LOGIN_TIME_OUT.build();
        }
        return subject;
    }

    /**
     * 登录
     *
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 密码
     */
    public static LoginUser login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        subject().login(token);
        return getLoginUser();
    }

    /**
     * 登录
     *
     * @param user
     * @param rememberMe
     * @return
     */
    public static LoginUser login(UserLogin user, Boolean rememberMe) {
        ShiroToken token = new ShiroToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);
        subject().login(token);
        return getLoginUser();
    }

    /**
     * 判断是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != subject().getPrincipal();
    }

    /**
     * 退出登录
     */
    public static void logout() {
        subject().logout();
    }

    /**
     * 获取当前登录的用户User对象
     *
     * @return
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) subject().getPrincipal();
        if (loginUser == null) {
            throw BizErrorCode.LOGIN_TIME_OUT.build();
        }
        return loginUser;
    }

    /**
     * 获取当前用户的Session
     *
     * @return
     */
    public static Session getSession() {
        return subject().getSession();
    }


    /**
     * 获取当前用户名称
     *
     * @return
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 把值放入到当前登录用户的Session里
     *
     * @param key
     * @param value
     */
    public static void setVal2Session(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 从当前登录用户的Session里取值
     *
     * @param key
     * @return
     */
    public static Object getVal2Session(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 获取验证码，获取一次后删除
     *
     * @return
     */
    public static String getYZM() {
        String code = (String) getSession().getAttribute("CODE");
        getSession().removeAttribute("CODE");
        return code;
    }

}

