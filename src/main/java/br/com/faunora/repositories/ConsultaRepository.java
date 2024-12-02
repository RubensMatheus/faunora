package br.com.faunora.repositories;

import br.com.faunora.domain.models.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
}
