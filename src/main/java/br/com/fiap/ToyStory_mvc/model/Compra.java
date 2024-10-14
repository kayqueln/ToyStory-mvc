package br.com.fiap.ToyStory_mvc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Brinquedo brinquedo;

    private Integer quantidade;

    private Date dataDaCompra;

}
