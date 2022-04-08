package br.com.pinheirostec.gerenciadorrifaonline.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProprietarioDto {

    private long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("telefone")
    private String telefone;
}
