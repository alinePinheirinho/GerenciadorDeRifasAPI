package br.com.pinheirostec.gerenciadorrifaonline.mapper;

import br.com.pinheirostec.gerenciadorrifaonline.dto.CampoRifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.dto.RifaDto;
import br.com.pinheirostec.gerenciadorrifaonline.entity.CampoRifa;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Rifa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RifaMapper {

    Rifa paraRifa(RifaDto rifaDto);

    RifaDto paraRifaDto(Rifa rifa);

    List<CampoRifa> paraCampoRifa(List<CampoRifaDto> campoRifaDto);

    List<CampoRifaDto> paraCampoRifaDto(List<CampoRifa> camposRifa);

}


