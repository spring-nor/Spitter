package spittr.controller;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.data.Spittle;
import spittr.data.SpittleRepository;
import spittr.exception.SpittleNotFoundException;

import java.util.Date;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
//    private final Logger logger = LogManager.getLogger(HomeController.class);


    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
//        logger.debug("--------------SpittleController Constructor");

        this.spittleRepository = spittleRepository;
    }

    //    @RequestMapping(method = RequestMethod.GET)
//    public List<Spittle> spittles(
//            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
//            @RequestParam(value = "count", defaultValue = "20") int count) {
//        logger.debug("--------------spittles");
//        //  return the logical view name like the request path, than in this case spittles.jsp
//        //  and tha model key for the attribute is inferred from the type, in this case a list thane spittleList
//        return spittleRepository.findSpittles(max, count);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model,
                           @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                           @RequestParam(value = "count", defaultValue = "20") int count) {

//        logger.debug("--------------SpittleController spittles");

        model.addAttribute("spittle", new Spittle());

        model.addAttribute("spittleList",
                spittleRepository.findSpittles(max, count));

        return "spittles";
    }

    //http://localhost:8080/spittles/499
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
//        logger.debug("--------------spittle");
        Spittle spittle = spittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }

        model.addAttribute("spittle",
                spittle);
        return "spittle";
    }

    //http://localhost:8080/spittles/show?spittle_id=499
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSpittle(
            @RequestParam("spittle_id") long spittleId,
            Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(Spittle spittleForm, Model model) {
        spittleRepository.save(new Spittle(null, spittleForm.getMessage(), new Date(),
                spittleForm.getLongitude(), spittleForm.getLatitude()));
        return "redirect:/spittles";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String saveSpittle(Spittle spittleForm, Model model) {
//        try {
//            spittleRepository.save(new Spittle(null, spittleForm.getMessage(), new Date(),
//                    spittleForm.getLongitude(), spittleForm.getLatitude()));
//            return "redirect:/spittles";
//        } catch (DuplicateSpittleException e) {
//            return "error/duplicate";
//        }
//    }

//  @RequestMapping(method= RequestMethod.POST)
//  public String saveSpittle(SpittleForm form, Model model) throws Exception {
//    spittleRepository.save(new Spittle(null, form.getMessage(), new Date(),
//        form.getLongitude(), form.getLatitude()));
//    return "redirect:/spittles";
//  }
//
//    @ExceptionHandler(DuplicateSpittleException.class)
//    public String handleNotFound() {
//        return "error/duplicate";
//    }

}
