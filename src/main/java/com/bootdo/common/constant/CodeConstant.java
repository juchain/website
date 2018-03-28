package com.bootdo.common.constant;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 返回编码映射表
 *
 *  @author maxiaodong
 */
public class CodeConstant {

    private static ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<String, String>();

    static {
        codeMap.put("success", "success");
        codeMap.put("PARAM_LOST", "PARAM_LOST");

    }





    /**
     * 请求open_plate_form  失败
     */
    public static final int OPEN_PLATFORM_ERROR = 1000001;






    /**
     * 账户创建成功
     */
    public static final int ACCOUNT_CREATE = 1000002;



    public static interface CHAIN_TYPE{

        int CHAIN_TYPE_PRIVATE = 1;

        int CHAIN_TYPE_PUBLIC = 0;

    }





}
