package spittr.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ViewResolver;
import spittr.controller.HomeController;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by norman on 24/02/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class})


public class BeanConfigTest {
    private final Logger logger = LogManager.getLogger(HomeController.class);


    @Autowired
    @Qualifier("web_config")
    private ViewResolver resolver;

    @Test
    public void resolverShouldNotBeNull() {
        logger.debug("--------------resolverShouldNotBeNull");
        assertNotNull(resolver);
    }
}
