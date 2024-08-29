package br.com.fiap.ToyStory_mvc.service;

import br.com.fiap.ToyStory_mvc.dto.AlterarBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import br.com.fiap.ToyStory_mvc.repository.BrinquedoRepository;
import br.com.fiap.ToyStory_mvc.utils.ToyStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrinquedoService implements ToyStoryServices<Brinquedo, CadastroBrinquedoDTO, AlterarBrinquedoDTO> {

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
    public void alterar(Long id, AlterarBrinquedoDTO alterarBrinquedoDTO) {
        Brinquedo brinquedo = brinquedoRepository.getReferenceById(id);
        brinquedo.alterar(alterarBrinquedoDTO);
        brinquedoRepository.save(brinquedo);
    }

    @Override
    public void deletar(Long id) {
        Brinquedo brinquedo = brinquedoRepository.getReferenceById(id);
        brinquedoRepository.delete(brinquedo);
    }

    public List<Brinquedo> listarOrdenado() {
        return brinquedoRepository.findAllByOrderByDataCadastroDesc();
    }

    public Brinquedo buscarPorId(Long id){return brinquedoRepository.getReferenceById(id);}
}
