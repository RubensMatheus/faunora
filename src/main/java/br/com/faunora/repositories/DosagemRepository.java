package br.com.faunora.repositories;

import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.models.ConsultaModel;
import br.com.faunora.domain.models.DosagemModel;
import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DosagemRepository extends JpaRepository<DosagemModel, Long> {
    List<DosagemModel> findAllByPaciente(PetModel paciente);
    List<DosagemModel> findAllByTipo(DosagemTipo tipo);
    List<DosagemModel> findAllByVeterinarioAndData(UserModel veterinario, LocalDate data);
    List<DosagemModel> findAllByVeterinario(UserModel veterinario);
    List<DosagemModel> findAllByPacienteAndDataAndHora(PetModel petModel, LocalDate data, LocalTime hora);
}
