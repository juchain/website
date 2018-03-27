package com.bootdo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
	@Autowired
	BootdoConfig bootdoConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String userDir = System.getProperty("user.dir");
		bootdoConfig.setUploadPath(userDir + "/uploaded_files/");
		registry.addResourceHandler("/files/**").addResourceLocations("file:///" + bootdoConfig.getUploadPath());
		//System.out.println("------------------UploadPath: "+bootdoConfig.getUploadPath());
	}

}