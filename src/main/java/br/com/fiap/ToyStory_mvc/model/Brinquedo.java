package br.com.fiap.ToyStory_mvc.model;

import br.com.fiap.ToyStory_mvc.dto.CadastroBrinquedoDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String classificacao;
    private Float preco;
    private String imagemUrl;
    private LocalDate dataCadastro;

    public Brinquedo(CadastroBrinquedoDTO cadastroBrinquedoDTO) {
        this.nome = cadastroBrinquedoDTO.nomeBrinquedo();
        this.tipo = cadastroBrinquedoDTO.tipoBrinquedo();
        this.classificacao = cadastroBrinquedoDTO.classificacaoBrinquedo();
        this.preco = cadastroBrinquedoDTO.preco();
        this.imagemUrl = cadastroBrinquedoDTO.imagemUrlBrinquedo();
        this.dataCadastro = LocalDate.now();
    }

    public void alterar(Brinquedo brinquedo) {
        this.nome = brinquedo.getNome();
        this.tipo = brinquedo.getTipo();
        this.classificacao = brinquedo.getClassificacao();
        this.preco = brinquedo.getPreco();
        this.imagemUrl = brinquedo.getImagemUrl();
    }
}
