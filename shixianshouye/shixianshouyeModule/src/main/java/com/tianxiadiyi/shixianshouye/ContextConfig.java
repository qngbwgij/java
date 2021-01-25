package com.tianxiadiyi.shixianshouye;

//import com.jspxcms.common.captcha.CaptchaServlet;
//import com.jspxcms.common.image.ImageHandler;
//import com.jspxcms.common.image.ImageMagickHandler;
//import com.jspxcms.common.image.ImageScalrHandler;
//import com.tianxiadiyi.common.orm.MyJpaRepositoryFactoryBean;
//import com.jspxcms.common.web.KeepSessionServlet;
import com.tianxiadiyi.shixianshouye.constant.Constants;
import com.tianxiadiyi.shixianshouye.support.SiteResolver;
//import com.jspxcms.core.support.WeixinProxyFactory;
//import com.tianxiadiyi.shixianshouye.taobaoke.nativedevelop.GetJuhuasuanCommodity;
import org.apache.commons.lang3.StringUtils;
//import org.owasp.html.HtmlPolicyBuilder;
//import org.owasp.html.PolicyFactory;
//import org.owasp.html.Sanitizers;
//import org.owasp.html.examples.EbayPolicyExample;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
//@EntityScan({"com.jspxcms.core.domain", "com.jspxcms.ext.domain"})
@EntityScan({"com.tianxiadiyi.shixianshouye.domain"})
//@EnableJpaRepositories(basePackages = {"com.tianxiadiyi.shixianshouye.repository", "com.jspxcms.ext.repository"}, repositoryFactoryBeanClass = MyJpaRepositoryFactoryBean.class)
@EnableJpaRepositories(basePackages = {"com.tianxiadiyi.shixianshouye.repository"})
//@ComponentScan({"com.jspxcms.core.service.impl", "com.jspxcms.ext.service.impl", "com.jspxcms.core.web.fore", "com.jspxcms.ext.web.fore"})
@ComponentScan({"com.tianxiadiyi.shixianshouye.service.impl", "com.tianxiadiyi.shixianshouye.web.fore"})
public class ContextConfig {
    /**
     * 验证码
     *
     * @return
     */
//    @Bean
//    public ServletRegistrationBean captchaServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new CaptchaServlet(), "/captcha");
//        registration.setName("captchaServlet");
//        return registration;
//    }

    /**
     * 设备识别器，用于识别是否手机访问
     *
     * @return
     */
//    @Bean
//    public LiteDeviceResolver liteDeviceResolver() {
//        LiteDeviceResolver liteDeviceResolver = new LiteDeviceResolver();
//        return liteDeviceResolver;
//    }

    /**
     * 保持登录状态
     *
     * @return
     */
//    @Bean
//    public ServletRegistrationBean keepSessionServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new KeepSessionServlet(), "/keep_session");
//        registration.setName("keepSessionServlet");
//        return registration;
//    }

    /**
     * HTML过滤器。防止跨站攻击
     *
     * @return
     */
//    @Bean
//    @Primary
//    public PolicyFactory policyFactory() {
////        PolicyFactory policyFactory = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS).and(Sanitizers.STYLES).and(Sanitizers.LINKS).and(Sanitizers.TABLES).and(Sanitizers.IMAGES);
//        PolicyFactory policy = EbayPolicyExample.POLICY_DEFINITION.and(new HtmlPolicyBuilder()
//                .allowAttributes("class", "controls", "preload", "width", "height", "src").onElements("video")
//                .allowAttributes("src", "type").onElements("source")
//                .allowElements("video", "source").toFactory());
//        return policy;
//    }

    /**
     * 微信代理工厂
     *
     * @return
     */
//    @Bean
//    public WeixinProxyFactory weixinProxyFactory() {
//        return new WeixinProxyFactory();
//    }

//	@Value("${weixin.appid}")
//	private String weixinAppid;
//	@Value("${weixin.secret}")
//	private String weixinSecret;
//
//	/**
//	 * 微信公众号TokenHolder
//	 *
//	 * @return
//	 */
//	@Bean
//	public WeixinProxy weixinProxy() {
//		if (StringUtils.isBlank(weixinAppid) || StringUtils.isBlank(weixinSecret)) {
//			return null;
//		}
//		// 使用HttpComponent4作为HttpClient。默认使用Netty，由于没有依赖相关的jar包，会报错
//		HttpClientFactory.setDefaultFactory(new HttpComponent4Factory());
//		WeixinProxy weixinProxy = new WeixinProxy(new WeixinAccount(weixinAppid, weixinSecret),
//				new FileCacheStorager<Token>());
//		return weixinProxy;
//	}

    /**
     * 图片处理器
     *
     * @return
     */
//    @Bean
//    public ImageHandler getImageHandler() {
//        if (Constants.isGraphicsMagick() || Constants.isImageMagick()) {
//            ImageMagickHandler imageMagickHandler = new ImageMagickHandler(Constants.isGraphicsMagick());
//            if (StringUtils.isNotBlank(Constants.IM4JAVA_TOOLPATH)) {
//                imageMagickHandler.setSearchPath(Constants.IM4JAVA_TOOLPATH);
//            }
//            return imageMagickHandler;
//        } else {
//            return new ImageScalrHandler();
//        }
//    }

//	/**
//	 * JPA查询Factory
//	 * 
//	 * @return
//	 */
//	@Bean
//	public JPAQueryFactory jpaQueryFactory() {
//		PersistenceProvider provider = PersistenceProvider.fromEntityManager(entityManager);
//		switch (provider) {
//		case ECLIPSELINK:
//			return new JPAQueryFactory(EclipseLinkTemplates.DEFAULT, entityManager);
//		case HIBERNATE:
//			return new JPAQueryFactory(HQLTemplates.DEFAULT, entityManager);
//		case OPEN_JPA:
//			return new JPAQueryFactory(OpenJPATemplates.DEFAULT, entityManager);
//		case GENERIC_JPA:
//		default:
//			return new JPAQueryFactory(entityManager);
//		}
//	}
//
//	@Autowired
//	private EntityManager entityManager;

    @Bean
    public SiteResolver foreSiteResolver() {
        return new SiteResolver();
    }

//    @Bean
//    public GetJuhuasuanCommodity getGetJuhuasuanCommodity() {
//        return new GetJuhuasuanCommodity();
//    }

    /**
     * WebLogic12cR2(12.2.1.3.0) 上传有会出错，要使用CommonsMultipartResolver
     *
     * @return
     */
//    @Bean
//    public CommonsMultipartResolver commonsMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        return multipartResolver;
//    }
}
