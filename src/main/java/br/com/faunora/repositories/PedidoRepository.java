package br.com.faunora.repositories;

import br.com.faunora.domain.enums.PedidoStatus;
import br.com.faunora.domain.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {
    List<PedidoModel> findAllByStatus(PedidoStatus status);
}
