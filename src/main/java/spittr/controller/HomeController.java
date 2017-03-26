package spittr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {
  private final Logger logger = LogManager.getLogger(HomeController.class);


  @RequestMapping(method = GET)
  public String home(Model model) {
    logger.debug("--------------HomeController");
    return "home";
  }

}
