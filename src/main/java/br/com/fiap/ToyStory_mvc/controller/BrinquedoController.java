package br.com.fiap.ToyStory_mvc.controller;

import br.com.fiap.ToyStory_mvc.dto.AlterarBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.repository.UsuarioRepository;
import br.com.fiap.ToyStory_mvc.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BrinquedoController {

    @Autowired
    private BrinquedoService brinquedoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/brinquedo")
    public String listar(Authentication authentication, Model model){
        List<Brinquedo> brinquedos = brinquedoService.listar();
        model.addAttribute("brinquedos", brinquedos);

        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }

        return "brinquedo/lista-brinquedo";
    }

    @GetMapping("/brinquedo/lancamentos")
    public String listarOrdenado(Authentication authentication, Model model){
        List<Brinquedo> brinquedos = brinquedoService.listarOrdenado();
        model.addAttribute("brinquedos", brinquedos);

        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }

        return "brinquedo/lista-brinquedo";
    }

    @GetMapping("/admin")
    public String paginaAdmin(Authentication authentication, Model model){
        List<Brinquedo> brinquedos = brinquedoService.listarOrdenado();
        model.addAttribute("brinquedos", brinquedos);

        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }

        return "brinquedo/admin-brinquedos";
    }

    @GetMapping("/brinquedo/novo")
    public String formulario(Authentication authentication, Model model, CadastroBrinquedoDTO cadastroBrinquedoDTO){
        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }
        return "brinquedo/cadastro-brinquedo";
    }

    @Transactional
    @PostMapping("/brinquedo/salvar")
    public String salvarBrinquedo(@ModelAttribute CadastroBrinquedoDTO cadastroBrinquedoDTO) throws Exception {
        brinquedoService.cadastrar(cadastroBrinquedoDTO);
        return "redirect:/admin";
    }

    @Transactional
    @GetMapping("/brinquedo/editar/formulario/{id}")
    public String formularioEditar(Authentication authentication, @PathVariable Long id, Model model) {
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

        if(authentication != null ){
            String nome = usuarioRepository.findByEmail(authentication.getName()).getNome();
            model.addAttribute("authentication", nome);

            if(authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))){
                model.addAttribute("authenticationAdmin", authentication);
            }else{
                model.addAttribute("authenticationUser", authentication);
            }
        }

        return "brinquedo/altera-brinquedo";
    }

    @Transactional
    @PostMapping("/brinquedo/editar/{id}")
    public String editarBrinquedo(@PathVariable Long id, @ModelAttribute AlterarBrinquedoDTO alterarBrinquedoDTO) throws Exception {
        brinquedoService.alterar(id, alterarBrinquedoDTO);
        return "redirect:/admin";
    }

    @Transactional
    @GetMapping("/brinquedo/excluir/{id}")
    public String excluirBrinquedo(@PathVariable Long id, Model model){
        brinquedoService.deletar(id);
        return "redirect:/admin";
    }

}
