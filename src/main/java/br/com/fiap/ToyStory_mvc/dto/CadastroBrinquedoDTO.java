package br.com.fiap.ToyStory_mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroBrinquedoDTO(
        @NotBlank
        String nomeBrinquedo,
        @NotNull
        String tipoBrinquedo,
        @NotNull
        Integer classificacaoBrinquedo,
        @NotNull
        Float preco,
        @NotBlank
        String imagemUrlBrinquedo

) {
}
