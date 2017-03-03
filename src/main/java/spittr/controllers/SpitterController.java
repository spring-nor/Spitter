package spittr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.data.Spitter;
import spittr.data.SpitterRepository;

import javax.servlet.http.Part;
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


    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        logger.debug("--------------SpitterController Constructor");

        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        logger.debug("--------------SpitterController showRegistrationForm");

        model.addAttribute("spitter", new Spitter());

        return "registerForm";
    }


    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @Valid Spitter spitterForm,
            Errors errors) throws IOException, IllegalStateException {
        logger.debug("--------------SpitterController processRegistration");

        if (errors.hasErrors()) {
            logger.debug("--------------SpitterController errors accur");
            return "registerForm";
        }
        Spitter spitter = spitterForm.toSpitter();
        spitterRepository.save(spitter);

        if (!spitter.getProfilePicture().isEmpty())
            spitter.getProfilePicture().transferTo(new File(spitterForm.getProfilePicture().getOriginalFilename()));

        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllSpitter(Model model) {

        logger.debug("--------------SpitterController showAllSpitter");

        model.addAttribute("spitterList",
                spitterRepository.showAllSpitter());

        return "spitter";
    }

}
