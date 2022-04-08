package br.com.pinheirostec.gerenciadorrifaonline.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NegocioException extends Exception {
    private String msg;

}
