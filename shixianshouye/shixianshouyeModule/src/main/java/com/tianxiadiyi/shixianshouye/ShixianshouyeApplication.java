package com.tianxiadiyi.shixianshouye;

import com.tianxiadiyi.shixianshouye.constant.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.WebApplicationInitializer;

//@SpringBootApplication
@Configuration
//@EnableAutoConfiguration(exclude = { MessageSourceAutoConfiguration.class, JmxAutoConfiguration.class,
//        CacheAutoConfiguration.class, DeviceResolverAutoConfiguration.class, SitePreferenceAutoConfiguration.class })
@EnableAutoConfiguration
//@Import({ContextConfig.class, ShiroConfig.class, MenuConfig.class })
//@Import({ContextConfig.class, ReriteFreeMarkerLoadingPath.class, AllowCrossDomain.class})
@Import({ContextConfig.class, ReriteFreeMarkerLoadingPath.class, AllowCrossDomain.class})
//@Import({ContextConfig.class, ReriteFreeMarkerLoadingPath.class})
//@PropertySource({ "classpath:conf/spring.jpa.properties", "${weixin.config.file}" })
//@ImportResource({ "classpath:conf/**/context*.xml", "classpath:custom.xml" }

//@MapperScan("com.tianxiadiyi.shixianshouye.taobaoke.mapper")

public class ShixianshouyeApplication {

        public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }


    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(ShixianshouyeApplication.class).listeners(
                new ApplicationListener<ApplicationEnvironmentPreparedEvent>() {
                    // 在应用环境准备好后执行（Application.properties和PoropertySource已读取），此时BeanFactory还未准备好（Bean还未创建）
                    @Override
                    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
                        ConfigurableEnvironment env = event.getEnvironment();
                        // 用配置文件中的内容覆盖替代Constants的内容
                        Constants.loadEnvironment(env);
                    }
                });
    }

}
