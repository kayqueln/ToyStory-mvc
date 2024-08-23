package br.com.fiap.ToyStory_mvc.utils;


import java.util.List;

public interface ToyStoryServices<T, R extends Record> {

    public T cadastrar(R record) throws Exception;

    public List<T> listar();

    public T alterar(Long id, R record);

    public void deletar(Long id);

}
