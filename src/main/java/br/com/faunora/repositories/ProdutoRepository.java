package br.com.faunora.repositories;

import br.com.faunora.domain.enums.ProdutoTipo;
import br.com.faunora.domain.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    List<ProdutoModel> findByCategoria(ProdutoTipo categoria);
}
