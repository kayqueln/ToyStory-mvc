package br.com.fiap.ToyStory_mvc.model;

import br.com.fiap.ToyStory_mvc.dto.AlterarBrinquedoDTO;
import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table
@Entity
public class Brinquedo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String tipo;
    private Integer classificacao;
    private Float preco;
    private String imagemUrl;
    private LocalDateTime dataCadastro;

    public Brinquedo(CadastroBrinquedoDTO cadastroBrinquedoDTO) {
        this.nome = cadastroBrinquedoDTO.nomeBrinquedo();
        this.tipo = cadastroBrinquedoDTO.tipoBrinquedo();
        this.classificacao = cadastroBrinquedoDTO.classificacaoBrinquedo();
        this.preco = cadastroBrinquedoDTO.preco();
        this.imagemUrl = cadastroBrinquedoDTO.imagemUrlBrinquedo();
        this.dataCadastro = LocalDateTime.now();
    }

    public void alterar(AlterarBrinquedoDTO alterarBrinquedoDTO) {
        if(alterarBrinquedoDTO.nomeBrinquedo() != null) this.nome = alterarBrinquedoDTO.nomeBrinquedo();
        if(alterarBrinquedoDTO.tipoBrinquedo() != null) this.tipo = alterarBrinquedoDTO.tipoBrinquedo();
        if(alterarBrinquedoDTO.classificacaoBrinquedo() != null) this.classificacao = alterarBrinquedoDTO.classificacaoBrinquedo();
        if(alterarBrinquedoDTO.preco() != null) this.preco = alterarBrinquedoDTO.preco();
        if(alterarBrinquedoDTO.imagemUrlBrinquedo() != null) this.imagemUrl = alterarBrinquedoDTO.imagemUrlBrinquedo();
    }
}
