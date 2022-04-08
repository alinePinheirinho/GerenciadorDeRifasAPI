package br.com.pinheirostec.gerenciadorrifaonline.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompradorCampoRifaDto {

    private long id;
    private long idRifa;
    private String nome;
    private String telefone;

}
