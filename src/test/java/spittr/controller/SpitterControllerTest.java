package spittr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SpitterControllerTest {

    private final Logger logger = LogManager.getLogger(HomeController.class);


    @Test
    public void shouldShowRegistration() throws Exception {

        logger.debug("--------------shouldShowRegistration");
//
//
//        SpitterRepository mockRepository = mock(SpitterRepository.class);
//        SpitterController controller = new SpitterController(mockRepository);
//
//        MockMvc mockMvc = standaloneSetup(controller).build();
//        mockMvc.perform(get("/spitter/register"))
//                .andExpect(view().name("registerForm"));
    }

//    @Test
//    public void shouldProcessRegistration() throws Exception {
//        SpitterRepository mockRepository = mock(SpitterRepository.class);
//        Spitter unsaved = new Spitter((long) 1, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
//        Spitter saved = new Spitter((long) 1, "jbauer", "24hours", "Jack", "Bauer", "jbauer@ctu.gov");
//        when(mockRepository.save(unsaved)).thenReturn(saved);
//
//        SpitterController controller = new SpitterController(mockRepository);
//
//        MockMvc mockMvc = standaloneSetup(controller).build();
//        mockMvc.perform(post("/spitter/register")
//                .param("id", "1")
//                .param("firstName", "Jacbk")
//                .param("lastName", "Bauer")
//                .param("username", "jbauer")
//                .param("password", "24hours")
//                .param("email", "jbauer@ctu.gov"))
//                .andExpect(redirectedUrl("/spitter/jbauer?spitter_id=1"));
//
//        verify(mockRepository, atLeastOnce()).save(unsaved);
//    }
////
////  @Test
////  public void shouldFailValidationWithNoData() throws Exception {
////    SpitterRepository mockRepository = mock(SpitterRepository.class);
////    SpitterController controller = new SpitterController(mockRepository);
////    MockMvc mockMvc = standaloneSetup(controller).build();
////
////    mockMvc.perform(post("/spitter/register"))
////        .andExpect(status().isOk())
////        .andExpect(view().name("registerForm"))
////        .andExpect(model().errorCount(5))
////        .andExpect(model().attributeHasFieldErrors(
////            "spitter", "firstName", "lastName", "username", "password", "email"));
////  }

}
