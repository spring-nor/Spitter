package spittr.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;


public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final Logger logger = LogManager.getLogger(WebConfig.class);


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
        logger.debug("----------------------customizeRegistration " + this.getClass().getName());

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