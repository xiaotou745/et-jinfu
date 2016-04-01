package com.etaofinance.wap.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 



import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
@Configuration
@EnableSwagger
public class MySwaggerConfig {
	private SpringSwaggerConfig springSwaggerConfig;
    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
    {
        this.springSwaggerConfig = springSwaggerConfig;
    }
 
    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation()
    {
    	 return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(
                 ".*?");
    }
 
    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "易淘金服-众筹", 
                "易淘金服众筹Wap接口",
                "My Apps API terms of service", 
                "ru.huaxiao@etao.cn", 
                "My Apps API Licence Type",
                "http:100.com");
        return apiInfo;
    }
}
