package br.com.fiap.ToyStory_mvc.controller;

import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/brinquedo")
@Controller
public class BrinquedoController {

    @Autowired
    private BrinquedoService brinquedoService;


    @GetMapping
    public String listar(Model model){
        List<Brinquedo> brinquedos = brinquedoService.listar();
        model.addAttribute("brinquedos", brinquedos);
        return "brinquedo/lista-brinquedo";
    }

    @GetMapping("/lancamentos")
    public String listarOrdenado(Model model){
        List<Brinquedo> brinquedos = brinquedoService.listarOrdenado();
        model.addAttribute("brinquedos", brinquedos);
        return "brinquedo/lista-brinquedo";
    }

    @GetMapping("/admin")
    public String paginaAdmin(Model model){
        List<Brinquedo> brinquedos = brinquedoService.listarOrdenado();
        model.addAttribute("brinquedos", brinquedos);
        return "brinquedo/admin-brinquedos";
    }

    @GetMapping("/novo")
    public String formulario(CadastroBrinquedoDTO cadastroBrinquedoDTO){
        return "/brinquedo/cadastro-brinquedo";
    }

    @PostMapping("/salvar")
    public String salvarBrinquedo(@ModelAttribute CadastroBrinquedoDTO cadastroBrinquedoDTO) throws Exception {
        brinquedoService.cadastrar(cadastroBrinquedoDTO);
        return "redirect:/brinquedo/admin";
    }

}
