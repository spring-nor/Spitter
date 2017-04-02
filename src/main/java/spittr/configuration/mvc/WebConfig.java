package spittr.configuration.mvc;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import spittr.converter.RoleToUserProfileConverter;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.configuration")
@PropertySource("classpath:config.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
    private final Logger logger = LogManager.getLogger(WebConfig.class);

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;

    @Value("${env}")
    String env;

    @Value("${application.properties}")
    String application_properties;

    @Value("${common.application.properties}")
    String common_application_properties;

    @Value("${spring.profiles.active}")
    String spring_profiles_active;

    @Bean
    public ViewResolver viewResolver() {
        logger.debug("----------------------Bean viewResolver " + this.getClass().getName() + " loaded");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return resolver;
    }

    //    @Bean
//    public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//    }

//    @Bean
//    @Qualifier("web_config")
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        logger.debug("----------------------Bean viewResolver " + this.getClass().getName() + " loaded");
//
//
//        logger.debug("getActiveProfiles: " + applicationContext.getEnvironment().getActiveProfiles()[0]);
//        logger.debug(">>>>>>>>>>> env : " + env);
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//        logger.debug("----------------------templateEngine " + this.getClass().getName() + " loaded");
//
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        return templateEngine;
//    }

    @Bean
    public TemplateResolver templateResolver() {
        logger.debug("----------------------Bean templateResolver " + this.getClass().getName() + " loaded");

        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }


//    @Bean
//    public SpittleRepositoryImpl spittleRepository() {
//        return new SpittleRepositoryImpl();
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // TODO Auto-generated method stub
//        super.addResourceHandlers(registry);
//    }

    // Messages
    @Bean
    public MessageSource messageSource() {
        logger.debug("----------------------Bean messageSource " + this.getClass().getName() + " loaded");

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages/messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    // Tiles
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[]{
//                "/WEB-INF/layout/tiles.xml",
//                "/WEB-INF/views/**/tiles.xml"
//        });
//        tiles.setCheckRefresh(true);
//        return tiles;
//    }

    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        logger.debug("----------------------Bean multipartResolver " + this.getClass().getName() + " loaded");

        return new StandardServletMultipartResolver();
    }

    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }

    /**
     * Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

}
