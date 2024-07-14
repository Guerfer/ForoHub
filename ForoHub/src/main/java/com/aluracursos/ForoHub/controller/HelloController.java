package com.aluracursos.ForoHub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String bienvenidosOne(){
        return "Bienvenidos equipo One al proyecto de ForoHub.";
    }
}
