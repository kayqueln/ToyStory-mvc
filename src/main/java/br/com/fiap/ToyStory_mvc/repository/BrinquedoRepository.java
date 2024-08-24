package br.com.fiap.ToyStory_mvc.repository;

import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
    List<Brinquedo> findAllByOrderByDataCadastroDesc();

}
