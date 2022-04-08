package br.com.pinheirostec.gerenciadorrifaonline.repository;

import br.com.pinheirostec.gerenciadorrifaonline.entity.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Proprietario findByNomeAndTelefone(String nome, String telefone);
}
