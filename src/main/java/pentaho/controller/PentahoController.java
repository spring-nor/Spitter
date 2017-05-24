package pentaho.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pentaho")
public class PentahoController {
    private final Logger logger = LogManager.getLogger(PentahoController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {

        logger.debug("--------------PentahoController pentaho");

        return "pentaho";
    }

    //http://localhost:8080/pentaho/report
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String showSpittle(Model model) {
        return "report";
    }

}
