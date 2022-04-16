package br.com.pinheirostec.gerenciadorrifaonline.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NegocioException extends RuntimeException {
    private String msg;

}
