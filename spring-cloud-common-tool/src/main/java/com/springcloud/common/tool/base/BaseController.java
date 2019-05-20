package com.springcloud.common.tool.base;

import com.springcloud.common.tool.bean.ResultBean;
import com.springcloud.common.tool.exception.BaseErrorCode;
import com.springcloud.common.tool.exception.BizErrorCode;
import com.springcloud.common.tool.shiro.bean.LoginUser;
import com.springcloud.common.tool.shiro.token.TokenManager;

import javax.servlet.http.HttpServletResponse;

/**
 * controller 基类
 *
 * @author jiangyf
 */
public class BaseController {

    protected ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<HttpServletResponse>();

    /**
     * 获取缓存的用户信息
     *
     * @return
     */
    protected LoginUser getLoginUser() {
        return TokenManager.getLoginUser();
    }

    /**
     * 入参异常
     */
    protected ResultBean paramsError() {
        return new ResultBean(BizErrorCode.PARAM_ERROR);
    }

    /**
     * 返回成功结果
     *
     * @return
     */
    protected ResultBean success() {
        return new ResultBean(BizErrorCode.SUCCESS);
    }

    /**
     * 返回成功结果
     *
     * @param data 响应数据
     * @return
     */
    protected ResultBean success(Object data) {
        return new ResultBean(BizErrorCode.SUCCESS, data);
    }

    /**
     * 返回失败结果
     *
     * @return
     */
    protected ResultBean fail() {
        return new ResultBean(BizErrorCode.FAIL);
    }

    /**
     * 返回失败结果
     *
     * @param data 响应数据
     * @return
     */
    protected ResultBean fail(Object data) {
        return new ResultBean(BizErrorCode.FAIL, data);
    }

    /**
     * 返回失败结果
     *
     * @param errorCode
     * @return
     */
    protected ResultBean fail(BaseErrorCode errorCode) {
        return new ResultBean(errorCode);
    }

}
