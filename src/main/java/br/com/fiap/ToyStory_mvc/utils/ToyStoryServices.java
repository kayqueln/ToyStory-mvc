package br.com.fiap.ToyStory_mvc.utils;


import br.com.fiap.ToyStory_mvc.dto.AlterarBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.model.Brinquedo;

import java.util.List;

public interface ToyStoryServices<T, R extends Record, E extends Record> {

    public T cadastrar(R record) throws Exception;

    public List<T> listar();

    public void alterar(Long id, E record2);

    public void deletar(Long id);

}
