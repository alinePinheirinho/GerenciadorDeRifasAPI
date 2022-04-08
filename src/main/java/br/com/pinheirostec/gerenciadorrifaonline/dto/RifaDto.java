package br.com.pinheirostec.gerenciadorrifaonline.dto;

import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(description = "RifaDto Request")
public class RifaDto {

    private Long id;

    @NotBlank
    @ApiModelProperty(name = "nome",value = "Nome da Rifa", example = "Nome")
    @JsonProperty("nome")
    private String nome;

    @NotBlank
    @ApiModelProperty(name = "descricao",value = "Descricao da Rifa", example = "Rifa de fone de ouvido")
    @JsonProperty("descricao")
    private String descricao;

    @ApiModelProperty(name = "imagem",value = "imagem da Rifa", example = "base64")
    @JsonProperty("imagem")
    private String imagem;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @ApiModelProperty(name = "dataSorteio",value = "Data do Sorterio", example = "dd/MM/yyyy")
    private LocalDate dataSorteio;

    @NotNull
    @ApiModelProperty(name = "valor",value = "valor da Rifa", example = "10.0")
    @JsonProperty("valor")
    private BigDecimal valor;

    @NotNull
    @ApiModelProperty(name = "camposRifa",value = "Campos da Rifa", example = "{\nome\": \"1\", \"valor\": \"10.0\"}")
    @JsonProperty("camposRifa")
    private List<CampoRifaDto> camposRifa;

    @ApiModelProperty(name = "statusRifa",value = "Status da Rifa", example = "INICIADA | ENCERRADA | CANCELADA")
    @JsonProperty("statusRifa")
    private StatusRifa statusRifa;

    @NotNull
    @ApiModelProperty(name = "proprietario",value = "Proprietario da Rifa", example = "{\nome\": \"Gabriel\", \"telefone\": \"99999999\"}")
    @JsonProperty("proprietario")
    private ProprietarioDto proprietario;

    @NotNull
    @ApiModelProperty(name = "quantidadeCampos",value = "Quantidade de campos na Rifa", example = "100")
    @JsonProperty("quantidadeCampos")
    private int quantidadeCampos;

}
