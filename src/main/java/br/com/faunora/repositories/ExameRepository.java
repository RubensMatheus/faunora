package br.com.faunora.repositories;

import br.com.faunora.domain.enums.ExameTipo;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.ExameModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {
    List<ExameModel> findAllByTipoAndPaciente(ExameTipo tipo, PetModel paciente);
    List<ExameModel> findAllByPaciente(PetModel paciente);
    List<ExameModel> findAllByVeterinarioAndData(UserModel veterinario, LocalDate data);
    List<ExameModel> findAllByVeterinario(UserModel veterinario);
    List<ExameModel> findAllByPacienteAndDataAndHora(PetModel petModel, LocalDate data, LocalTime hora);
}
