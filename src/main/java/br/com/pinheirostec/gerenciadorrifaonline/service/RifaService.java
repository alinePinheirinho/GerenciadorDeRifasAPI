package br.com.pinheirostec.gerenciadorrifaonline.service;

import br.com.pinheirostec.gerenciadorrifaonline.dto.CampoRifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.dto.RifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import br.com.pinheirostec.gerenciadorrifaonline.exceptions.NegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RifaService {
    void cadastraRifa(RifaDto rifaDto);

    RifaDto buscaRifa(Long id) throws NegocioException;

    List<RifaDto> buscaRifasPorProprietario(Long id) throws NegocioException;

    Page<RifaDto> buscarTodasRifasExistentes(StatusRifa statusRifa, Pageable page);

    void compraCamposRifa(Long id, List<CampoRifaDto> camposRifaDto);

    void confirmaCompraCamposRifa(Long id, List<CampoRifaDto> camposRifaDto);
}

