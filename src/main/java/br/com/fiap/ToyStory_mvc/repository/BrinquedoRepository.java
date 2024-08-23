package br.com.fiap.ToyStory_mvc.repository;

import br.com.fiap.ToyStory_mvc.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}
