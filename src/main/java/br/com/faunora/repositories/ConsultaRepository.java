package br.com.faunora.repositories;

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
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    List<ConsultaModel> findAllByVeterinario(UserModel veterinario);
    List<ConsultaModel> findAllByPaciente(PetModel petModel);
    List<ConsultaModel> findAllByPacienteAndDataAndHora(PetModel petModel, LocalDate data, LocalTime hora);
    List<ConsultaModel> findAllByVeterinarioAndData(UserModel veterinario, LocalDate data);
}
