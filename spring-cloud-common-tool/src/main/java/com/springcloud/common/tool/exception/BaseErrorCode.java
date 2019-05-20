package com.springcloud.common.tool.exception;

/**
 * 基础错误代码
 *
 * @author jiangyf
 */
public interface BaseErrorCode {

    /**
     * 错误码
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMsg();

    /**
     * 根据错误码返回对应的错误枚举
     *
     * @param code
     * @return
     */
    BaseErrorCode getByCode(String code);

    /**
     * 根据错误码返回对应的错误描述
     *
     * @param code
     * @return
     */
    String getMsgByCode(String code);

    /**
     * 根据错误码枚举构建对应异常
     *
     * @param e
     * @return
     */
    BizException build(Throwable e);

    /**
     * 根据错误码枚举构建对应异常
     * <p>
     * ex:( {code:1000, msg:"a{0}c{1}e{2}"}, "b", "d", "f" ) => {code:1000, msg:"abcdef"}
     *
     * @param paramObj
     * @return
     */
    BizException build(Object... paramObj);

}
