package br.com.faunora.repositories;

import br.com.faunora.domain.models.ReceitaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, Long> {
}
