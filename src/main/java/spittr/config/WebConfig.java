package spittr.config;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan("spittr.config")
@PropertySource("classpath:config.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
//    private final Logger logger = LogManager.getLogger(WebConfig.class);

    @Autowired
    WebApplicationContext applicationContext;

    @Value("${env}")
    String env;

    @Value("${application.properties}")
    String application_properties;

    @Value("${common.application.properties}")
    String common_application_properties;

    @Value("${spring.profiles.active}")
    String spring_profiles_active;

//    @Bean
//    public ViewResolver viewResolver() {
//        logger.debug("----------------------Bean viewResolver " + this.getClass().getName() + " loaded");
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
//        return resolver;
//    }

    //    @Bean
//    public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
//        logger.debug("----------------------Bean viewResolver " + this.getClass().getName() + " loaded");
//
//
//        logger.debug("getActiveProfiles: " + applicationContext.getEnvironment().getActiveProfiles()[0]);
//        logger.debug(">>>>>>>>>>> env : " + env);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//        logger.debug("----------------------templateEngine " + this.getClass().getName() + " loaded");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public TemplateResolver templateResolver() {
//        logger.debug("----------------------Bean templateResolver " + this.getClass().getName() + " loaded");

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
//        logger.debug("----------------------Bean messageSource " + this.getClass().getName() + " loaded");

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
//        logger.debug("----------------------Bean multipartResolver " + this.getClass().getName() + " loaded");

        return new StandardServletMultipartResolver();
    }

}
