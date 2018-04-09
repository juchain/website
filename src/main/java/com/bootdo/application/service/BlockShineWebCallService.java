package com.bootdo.application.service;

import com.alibaba.fastjson.JSONObject;

import com.bootdo.application.domain.AddressDO;
import com.bootdo.application.domain.ApplicationDO;
import com.bootdo.application.service.impl.AddressServiceImpl;
import com.bootdo.common.constant.CodeConstant;
import com.bootdo.common.utils.HttpClientUtils;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlockShineWebCallService {


    private static Logger logger = Logger.getLogger(BlockShineWebCallService.class);

    @Autowired
    AddressServiceImpl addressService;

	@Value("${platform_web_url}")
	private String platformWebUrl;

	// 创建账户
    @Transactional
	public R bsw_newAddress(ApplicationDO applicationDO)  {
        Map map  =new HashMap();
        map.put("appKey",applicationDO.getAppKey());
        String password = "";
        try {
            password = MD5Utils.encrypt(applicationDO.getAppKey());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("密码生成失败",e);
        }
        map.put("password",password);


        String params = JSONObject.toJSONString(map);
        String url =platformWebUrl+"account/init";
        logger.info("url:"+url+"======params:"+params);

        JSONObject jo = null;
        try {
            jo = HttpClientUtils.httpPostJsonStr(platformWebUrl + "account/init",params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        R r =R.ok();

        if(jo!=null &"0".equals(jo.get("code"))){

            AddressDO addressDo = new AddressDO();
            addressDo.setAddressFrom((String)jo.get("from"));
            addressDo.setAddressTo((String)jo.get("to"));
            addressDo.setCreated(new Date());
            addressDo.setAppId(applicationDO.getAppId());
            addressDo.setAppKey(applicationDO.getAppKey());
            addressDo.setPassword(password);
            addressDo.setType(CodeConstant.CHAIN_TYPE.CHAIN_TYPE_PRIVATE);
            addressDo.setStatus(1);
            addressService.save(addressDo);
            r.put("code", 0);
            r.put("msg", "账户创建成功");
        }else{
            logger.info("账户创建失败");
            r.put("code", CodeConstant.ACCOUNT_CREATE);
            r.put("msg", "账户创建失败");
        }
		return r;
	}




}
