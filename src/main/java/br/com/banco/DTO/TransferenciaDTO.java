package br.com.banco.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {
    private Long id;
    private LocalDate dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private ContaDTO conta;

    // Construtores, getters e setters aqui
}
