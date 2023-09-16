package com.red.bo.web.swagger;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SwaggerController {

    @Hidden
    @GetMapping("")
    public ModelAndView redirectToSwaggerUI(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
}
