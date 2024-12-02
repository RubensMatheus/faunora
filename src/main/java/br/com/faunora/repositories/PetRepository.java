package br.com.faunora.repositories;

import br.com.faunora.domain.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetModel, Long> {
}
