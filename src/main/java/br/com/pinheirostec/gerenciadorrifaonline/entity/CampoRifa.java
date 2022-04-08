package br.com.pinheirostec.gerenciadorrifaonline.entity;

import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusCampoRifa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CampoRifa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rifa_id")
    @NotNull
    private Rifa rifa;

    private String valorCampo;

    @NotNull
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusCampoRifa statusCampo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "comprador_campo_rifa_id")
    private CompradorCampoRifa comprador;

}
