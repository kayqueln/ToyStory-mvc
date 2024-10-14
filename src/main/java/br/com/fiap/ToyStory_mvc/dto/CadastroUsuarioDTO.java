package br.com.fiap.ToyStory_mvc.dto;

public record CadastroUsuarioDTO(
        String nome,
        String email,
        String senha
) {
}
