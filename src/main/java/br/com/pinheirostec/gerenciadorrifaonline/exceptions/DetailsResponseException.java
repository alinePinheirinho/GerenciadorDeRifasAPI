package br.com.pinheirostec.gerenciadorrifaonline.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsResponseException {

    private String field;
    private String message;

    DetailsResponseException(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
