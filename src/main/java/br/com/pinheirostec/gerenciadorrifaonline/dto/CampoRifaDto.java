package br.com.pinheirostec.gerenciadorrifaonline.dto;

import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusCampoRifa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CampoRifaDto {

    private long id;

    @JsonProperty("valorCampo")
    private String valorCampo;

    @JsonProperty("valor")
    private BigDecimal valor;
    
    @JsonProperty("statusCampo")
    private StatusCampoRifa statusCampo = StatusCampoRifa.DISPONIVEL;

    private CompradorCampoRifaDto comprador;
}
