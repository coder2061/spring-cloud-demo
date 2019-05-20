package com.springcloud.common.tool.util;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串工具
 *
 * @author jiangyf
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().equals("");
    }

//    public static void main(String[] args) {
//        System.out.println(hideBank("1234888888885555"));
//        System.out.println(hideMobile("18779885555"));
//    }

    /**
     * 逗号连接的字符串转集合
     *
     * @param str
     * @return
     */
    public static List<Integer> strToListByComma(String str) {
        return strToList(str, ",");
    }

    /**
     * 分割符连接的字符串转集合
     *
     * @param str
     * @param spliter
     * @return
     */
    public static List<Integer> strToList(String str, String spliter) {
        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        return Arrays.asList(str.split(spliter)).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
    }

    /**
     * 格式化信息
     * <p>
     * ex: ("a{0}c{1}e{2}"}, "b", "d", "f") => "abcdef"
     *
     * @param formatStr
     * @param paramObj
     * @return
     */
    public static String format(String formatStr, Object... paramObj) {
        String msg = ObjectUtils.defaultIfNull(formatStr, "");
        try {
            if (paramObj != null && paramObj.length > 0) {
                msg = MessageFormat.format(msg, paramObj);
            }
        } catch (Exception e) {
        }
        return msg;
    }

    /**
     * url字符串转 map 集合
     *
     * @param str
     * @return
     */
    public static Map<String, String> stringToMap(String str) {
        //判断str是否有值
        if (null == str || "".equals(str)) {
            return null;
        }
        //根据&截取
        String[] strings = str.split("&");
        //设置HashMap长度
        int mapLength = strings.length;
        //判断hashMap的长度是否是2的幂。
        if ((strings.length % 2) != 0) {
            mapLength = mapLength + 1;
        }

        Map<String, String> map = new HashMap<>(mapLength);
        //循环加入map集合
        for (int i = 0; i < strings.length; i++) {
            //截取一组字符串
            String[] strArray = strings[i].split("=");
            //strArray[0]为KEY  strArray[1]为值
            if (strArray.length == 1)
                map.put(strArray[0], "");
            else
                map.put(strArray[0], strArray[1]);
        }
        return map;
    }

    /**
     * 隐藏银行卡号中间八位数
     *
     * @param bankNO
     * @return
     */
    public static String hideBank(String bankNO) {
        return bankNO.replaceAll("(\\d{4})\\d{8}(\\w{4})", "$1********$2");
    }

    /**
     * 隐藏手机号中间四位数
     *
     * @param mobile
     * @return
     */
    public static String hideMobile(String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
