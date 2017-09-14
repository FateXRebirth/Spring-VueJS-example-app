package com.adeveloperdiary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FateXRebirth on 14/09/2017.
 */
@RestController
public class Controller {

    @GetMapping(value = "/")
    public String index() {
        return "Hello World";
    }

}
