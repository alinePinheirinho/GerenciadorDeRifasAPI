package br.com.pinheirostec.gerenciadorrifaonline.mapper;

import br.com.pinheirostec.gerenciadorrifaonline.dto.ProprietarioDto;
import br.com.pinheirostec.gerenciadorrifaonline.entity.Proprietario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProprietarioMapper {

    @InheritInverseConfiguration
    ProprietarioDto paraProprietarioDto(Proprietario proprietario);

}


