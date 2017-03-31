package spittr.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spittr.configuration.mvc.RootConfig;
import spittr.configuration.mvc.WebConfig;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;


public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    private final Logger logger = LogManager.getLogger(WebInitializer.class);

//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//
//        WebApplicationContext context =
//                (WebApplicationContext) super.createRootApplicationContext();
////        ((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles(Env.ENVIRONMENT);
//
//        //Set multiple active profiles
//        //((ConfigurableEnvironment)context.getEnvironment())
//        //          .setActiveProfiles(new String[]{"live", "testdb"});
//
//        return context;
//    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.debug("----------------------getRootConfigClasses " + this.getClass().getName());

        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.debug("----------------------getServletConfigClasses " + this.getClass().getName());

        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        logger.debug("----------------------getServletMappings " + this.getClass().getName());

        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
//        logger.debug("----------------------customizeRegistration " + this.getClass().getName());

        String temporaryLocation = "/home/norman/personal/spring/spittr/tmp/uploads";
        long maxBytesFileUploaded = 2097152;
        long maxBytesEntireMultipart = 2097152;
        int maxBytesFileUploadedWithoutTemporaryLocation = 0;

        registration.setMultipartConfig(
                new MultipartConfigElement(temporaryLocation,
                        maxBytesFileUploaded,
                        maxBytesEntireMultipart,
                        maxBytesFileUploadedWithoutTemporaryLocation));
    }

}