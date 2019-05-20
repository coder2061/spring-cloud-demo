package com.springcloud.common.tool.exception;

import com.springcloud.common.tool.util.StringUtil;

import java.util.*;

/**
 * 通用错误码 10000 - 10099
 * <p>
 * created by jiangyf on 2019-04-22
 */
public enum BizErrorCode implements BaseErrorCode {
    SUCCESS(10000, "成功", "success"),
    FAIL(10001, "失败", "success"),
    PARAM_ERROR(10002, "参数异常", "params error"),

    ACCOUNT_UP_ERROR(10011, "登录账号或密码错误"),
    ACCOUNT_FREEZED(10012, "登录账号已冻结"),
    ACCOUNT_DELETED(10013, "登录账号已作废"),
    INPUT_PWD_OUT_LIMIT(10014, "密码输入错误次数超限，请稍后登录"),
    LOGOUT_FAILED(10015, "退出登录失败"),
    ACCOUNT_NO_ACCESS(10016, "该登录账号没有访问权限"),
    LOGIN_TIME_OUT(10017, "用户登录信息失效，请重新登录", "You haven't log on yet or your session has time out, please log on again"),

    DATA_CONVERT_ERROR(10030, "数据复制转换错误#Data convert error"),
    UPLOAD_FILE_FAIL(10031, "上传文件失败", "Upload file fail"),
    UPLOAD_FILE_CONTENT_ERROR(10032, "上传文件内容错误", "Upload file content error"),
    UPLOAD_FILE_CONTENT_EMPTY(10033, "上传文件内容为空", "Upload file content empty"),
    UPLOAD_FILE_HEADMAP_EMPTY(10034, "文件头部格式配置为空", "Upload file headmap empty"),
    UPLOAD_FILE_HEADSIZE_ERROR(10035, "文件格式头部长度异常", "Upload file headsize error"),
    UPLOAD_FILE_MASTERID_ERROR(10036, "批量导入masterId配置异常", "Upload file masterid error"),
    UPLOAD_FILE_HEAD_ERROR(10037, "文件格式头部异常", "Upload file head error"),
    EXPORT_EXCEL_ERROR(10038, "导出Excel数据失败", "Export excel error"),
    IMPORT_EXCEL_CELL_ERROR(10039, "导入Excel数据错误", "Import excel cell error");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述（默认中文）
     */
    private String msg;
    /**
     * 错误描述（英文）
     */
    private String msgEN;

    /**
     * 错误代码基类
     *
     * @param code 错误码
     * @param msg  错误描述
     */
    BizErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 错误代码基类
     *
     * @param code  错误码
     * @param msg   错误描述
     * @param msgEN 错误描述
     */
    BizErrorCode(int code, String msg, String msgEN) {
        this.code = code;
        this.msg = msg;
        this.msgEN = msgEN;
    }

    /**
     * 错误代码列表
     */
    public static List<BizErrorCode> list;

    /**
     * 错误码以及对应的错误枚举对应关系
     */
    public static Map<Integer, BizErrorCode> map = new HashMap<>();

    static {
        list = Arrays.asList(BizErrorCode.values());
        for (BizErrorCode error : BizErrorCode.values()) {
            map.put(error.getCode(), error);
        }
    }

    /**
     * 错误码
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 错误描述
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 根据错误码返回对应的错误枚举
     *
     * @param code
     * @return
     */
    public BizErrorCode getByCode(String code) {
        return map.get(code);
    }

    /**
     * 根据错误码返回对应的错误描述
     *
     * @param code
     * @return
     */
    public String getMsgByCode(String code) {
        return getByCode(code).getMsg();
    }

    /**
     * 根据错误码枚举构建对应异常
     *
     * @param e
     * @return
     */
    public BizException build(Throwable e) {
        throw new BizException(getCode(), getMsg(), e);
    }

    /**
     * 根据错误码枚举构建对应异常
     * <p>
     * ex:( {code:1000, msg:"a{0}c{1}e{2}"}, "b", "d", "f" ) => {code:1000, msg:"abcdef"}
     *
     * @param paramObj
     * @return
     */
    public BizException build(Object... paramObj) {
        throw new BizException(getCode(), StringUtil.format(getMsg(), paramObj));
    }

}
