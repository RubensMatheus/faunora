package br.com.faunora.repositories;

import br.com.faunora.domain.enums.UserTipo;
import br.com.faunora.domain.models.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    boolean existsByEmail(String email);
    List<UserModel> findAllByTipo(UserTipo tipo);
}
