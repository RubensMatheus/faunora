package br.com.faunora.repositories;

import br.com.faunora.domain.models.DosagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DosagemRepository extends JpaRepository<DosagemModel, UUID> {
}
