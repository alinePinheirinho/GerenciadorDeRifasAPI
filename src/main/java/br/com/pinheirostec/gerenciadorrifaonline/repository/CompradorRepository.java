package br.com.pinheirostec.gerenciadorrifaonline.repository;

import br.com.pinheirostec.gerenciadorrifaonline.entity.CompradorCampoRifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompradorRepository extends JpaRepository<CompradorCampoRifa, Long> {

    CompradorCampoRifa findFirstByNomeAndTelefone(String nome, String telefone);

}
