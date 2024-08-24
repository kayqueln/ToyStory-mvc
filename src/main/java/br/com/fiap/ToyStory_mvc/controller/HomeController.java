package br.com.fiap.ToyStory_mvc.controller;


import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.service.BrinquedoService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BrinquedoService brinquedoService;

    @GetMapping
    public String home(Model model){
        List<Brinquedo> brinquedos = brinquedoService.listar();
        model.addAttribute("brinquedos", brinquedos);
        return "home";
    }

}
