package br.com.pinheirostec.gerenciadorrifaonline.repository;

import br.com.pinheirostec.gerenciadorrifaonline.entity.CampoRifa;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Rifa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampoRifaRepository extends JpaRepository<CampoRifa, Long> {

    List<CampoRifa> findByRifa(Rifa rifa);
}
