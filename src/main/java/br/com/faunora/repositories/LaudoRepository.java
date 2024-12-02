package br.com.faunora.repositories;

import br.com.faunora.domain.models.LaudoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaudoRepository extends JpaRepository<LaudoModel, Long> {
}
