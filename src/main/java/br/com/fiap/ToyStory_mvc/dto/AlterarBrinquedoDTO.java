package br.com.fiap.ToyStory_mvc.dto;

public record AlterarBrinquedoDTO(
        Long idBrinquedo,
        String nomeBrinquedo,
        String tipoBrinquedo,
        Integer classificacaoBrinquedo,
        Float preco,
        String imagemUrlBrinquedo
) {
}
