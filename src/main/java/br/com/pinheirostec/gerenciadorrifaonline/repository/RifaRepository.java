package br.com.pinheirostec.gerenciadorrifaonline.repository;

import br.com.pinheirostec.gerenciadorrifaonline.entity.Proprietario;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Rifa;
import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RifaRepository extends JpaRepository<Rifa, Long> {

    List<Rifa> findByProprietario(Proprietario proprietario);

    Page<Rifa> findByStatusRifa(@Param("statusRifa") StatusRifa statusRifa, Pageable pageable);

}
