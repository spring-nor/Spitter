package spittr.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.model.entity.Spitter;
import spittr.service.ISpitterService;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
    private final Logger logger = LogManager.getLogger(HomeController.class);
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";



    @Autowired
    private ISpitterService spitterService;


//    private SpitterRepository spitterRepository;

//    @Autowired
//    public SpitterController(SpitterRepository spitterRepository) {
//        logger.debug("--------------SpitterController Constructor");
//
//        this.spitterRepository = spitterRepository;
//    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        logger.debug("--------------SpitterController showRegistrationForm");

        model.addAttribute("spitter", new Spitter());

        return "registerForm";
    }


//    @RequestMapping(value = "/register", method = POST)
//    public String processRegistration(
//            @Valid Spitter spitterForm,
//            Errors errors) throws IOException, IllegalStateException {
//        logger.debug("--------------SpitterController processRegistration");
//
//        if (errors.hasErrors()) {
//            logger.debug("--------------SpitterController errors accur");
//            return "registerForm";
//        }
//        Spitter spitter = spitterForm.toSpitter();
//        spitterRepository.save(spitter);
//
//        if (spitter.getProfilePicture() != null &&
//                !spitter.getProfilePicture().isEmpty())
//            spitter.getProfilePicture().transferTo(new File(spitterForm.getProfilePicture().getOriginalFilename()));
//
//        // redirects directly to the controller whit a concatened string. Insicure method
//        return "redirect:/spitter/" + spitter.getUsername();
//    }

//    @RequestMapping(value = "/register", method = POST)
//    public String processRegistration(
//            @Valid Spitter spitterForm, Model model,
//            Errors errors) throws IOException, IllegalStateException {
//        logger.debug("--------------SpitterController processRegistration");
//
//        if (errors.hasErrors()) {
//            logger.debug("--------------SpitterController errors accur");
//            return "registerForm";
//        }
//        Spitter spitter = spitterForm.toSpitter();
//        spitterRepository.save(spitter);
//
//        if (spitter.getProfilePicture() != null &&
//                !spitter.getProfilePicture().isEmpty())
//            spitter.getProfilePicture().transferTo(new File(spitterForm.getProfilePicture().getOriginalFilename()));
//
//        model.addAttribute("username", spitter.getUsername());
//        model.addAttribute("spitter_id", spitter.getId());
//
//        // redirects directly to the controller with the value of "username" passed in the Model.
//        // In this way any unsafe characters in the "username" proprety are escaped
//        return "redirect:/spitter/{username}?spitter_id={spitter_id}";
//    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @Valid Spitter spitterForm, RedirectAttributes model,
            Errors errors) throws IOException, IllegalStateException {
        logger.debug("--------------SpitterController processRegistration");

        if (errors.hasErrors()) {
            logger.debug("--------------SpitterController errors accur");
            return "registerForm";
        }
        Spitter spitter = spitterService.toSpitter(spitterForm);

        spitterService.persist(spitter);
//
//        spitterRepository.save(spitter);
//
//        if (spitter.getProfilePicture() != null &&
//                !spitter.getProfilePicture().isEmpty())
//            spitter.getProfilePicture().transferTo(new File(spitterForm.getProfilePicture().getOriginalFilename()));

//        model.addAttribute("username", spitter.getUsername());
//        model.addAttribute("spitter_id", spitter.getId());
//
//        model.addFlashAttribute("spitter", spitter);

        // redirects directly to the controller with the value of "username" passed in the Model.
        // In this way any unsafe characters in the "username" proprety are escaped
        return "redirect:/spitter/{username}?spitter_id={spitter_id}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(
            @PathVariable String username,
            @RequestParam("spitter_id") long spitterId,
            Model model) {

//        if (!model.containsAttribute("spitter")) {
//            model.addAttribute(
//                    spitterRepository.findByUsername(username));
//        }

//      Spitter spitter = spitterRepository.findByUsername(username);
//      model.addAttribute(spitter);

        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllSpitter(Model model) {

        logger.debug("--------------SpitterController showAllSpitter");

//        model.addAttribute("spitterList",
//                spitterRepository.showAllSpitter());

        return "spitter";
    }

}
