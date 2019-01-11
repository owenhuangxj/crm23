package com.ss.crm.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
   // @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CrmInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/**/static/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**")  // resources是页面中引入静态资源的前缀
                .addResourceLocations("classpath:/static/")  // 指定资源在项目中的具体路径
                .setCachePeriod(604800); // 设置缓存周期单位为秒：7天
    }



}
