package br.com.fiap.ToyStory_mvc.service;

import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.repository.BrinquedoRepository;
import br.com.fiap.ToyStory_mvc.utils.ToyStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrinquedoService implements ToyStoryServices<Brinquedo, CadastroBrinquedoDTO> {

    @Autowired
    private BrinquedoRepository brinquedoRepository;


    @Override
    public Brinquedo cadastrar(CadastroBrinquedoDTO cadastroBrinquedoDTO) throws Exception {
        Brinquedo brinquedo = new Brinquedo(cadastroBrinquedoDTO);
        brinquedoRepository.save(brinquedo);
        return brinquedo;
    }

    @Override
    public List<Brinquedo> listar() {
        return brinquedoRepository.findAll();
    }

    @Override
    public Brinquedo alterar(Long id, CadastroBrinquedoDTO cadastroBrinquedoDTO) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }

    public List<Brinquedo> listarOrdenado() {
        return brinquedoRepository.findAllByOrderByDataCadastroDesc();
    }
}
