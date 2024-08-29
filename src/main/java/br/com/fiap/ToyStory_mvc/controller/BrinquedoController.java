package br.com.fiap.ToyStory_mvc.controller;

import br.com.fiap.ToyStory_mvc.dto.AlterarBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Transactional
    @PostMapping("/salvar")
    public String salvarBrinquedo(@ModelAttribute CadastroBrinquedoDTO cadastroBrinquedoDTO) throws Exception {
        brinquedoService.cadastrar(cadastroBrinquedoDTO);
        return "redirect:/brinquedo/admin";
    }

    @Transactional
    @GetMapping("/editar/formulario/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Brinquedo brinquedo = brinquedoService.buscarPorId(id);
        AlterarBrinquedoDTO alterarBrinquedoDTO = new AlterarBrinquedoDTO(
                brinquedo.getId(),
                brinquedo.getNome(),
                brinquedo.getTipo(),
                brinquedo.getClassificacao(),
                brinquedo.getPreco(),
                brinquedo.getImagemUrl()
        );
        model.addAttribute("alterarBrinquedoDTO", alterarBrinquedoDTO);
        return "/brinquedo/altera-brinquedo";
    }

    @Transactional
    @PostMapping("/editar/{id}")
    public String editarBrinquedo(@PathVariable Long id, @ModelAttribute AlterarBrinquedoDTO alterarBrinquedoDTO) throws Exception {
        brinquedoService.alterar(id, alterarBrinquedoDTO);
        return "redirect:/brinquedo/admin";
    }

    @Transactional
    @GetMapping("/excluir/{id}")
    public String excluirBrinquedo(@PathVariable Long id, Model model){
        brinquedoService.deletar(id);
        return "redirect:/brinquedo/admin";
    }

}
