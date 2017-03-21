package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import spittr.constant.Profiles;

/**
 * Created by norman on 20/03/17.
 */

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonPropertiesConfiguration extends PropertiesConfiguration {

    @Bean
    @Profile(Profiles.TEST)
    public static PropertySourcesPlaceholderConfigurer testProperties() {
        return createPropertySourcesPlaceholderConfigurer(
                "common_application.properties",
                "test/database.properties");
    }

    @Bean
    @Profile(Profiles.DEV)
    public static PropertySourcesPlaceholderConfigurer devProperties() {
        return createPropertySourcesPlaceholderConfigurer(
                "common_application.properties",
                "dev/database.properties");
    }

    @Bean
    @Profile(Profiles.PROD)
    public static PropertySourcesPlaceholderConfigurer prodProperties() {
        return createPropertySourcesPlaceholderConfigurer(
                "common_application.properties",
                "prod/database.properties");
    }
}