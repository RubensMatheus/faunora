package br.com.faunora.repositories;

import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {
    List<ExameModel> findAllByTipoAndPaciente(ExameTipo tipo, PetModel paciente);
    List<ExameModel> findAllByPaciente(PetModel paciente);
}
