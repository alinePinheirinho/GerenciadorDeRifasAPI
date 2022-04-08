package br.com.pinheirostec.gerenciadorrifaonline.entity;

import br.com.pinheirostec.gerenciadorrifaonline.enums.StatusRifa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Rifa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String imagem;
    private LocalDate dataSorteio;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusRifa statusRifa;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;
    private int quantidadeCampos;




}
