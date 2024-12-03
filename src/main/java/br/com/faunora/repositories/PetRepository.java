package br.com.faunora.repositories;

import br.com.faunora.domain.models.PetModel;
import br.com.faunora.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetModel, Long> {
    List<PetModel> findAllByTutor(UserModel userModel);
}
