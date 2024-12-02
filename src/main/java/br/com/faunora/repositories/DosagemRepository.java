package br.com.faunora.repositories;

import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.domain.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DosagemRepository extends JpaRepository<DosagemModel, Long> {
    List<DosagemModel> findAllByPaciente(PetModel paciente);
    List<DosagemModel> findAllByTipo(DosagemTipo tipo);
}
