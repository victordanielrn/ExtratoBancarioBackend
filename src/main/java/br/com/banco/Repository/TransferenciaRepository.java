package br.com.banco.Repository;
import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByConta_NomeResponsavel(String nomeResponsavel);
    List<Transferencia> findByContaId(Long contaId);
    List<Transferencia> findByContaIdAndNomeOperadorTransacao(Long contaId, String nomeOperador);
    List<Transferencia> findByContaIdAndDataTransferenciaBetween(Long contaId, LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperador);
    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Transferencia> findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
            Long contaId, String nomeOperador, LocalDateTime dataInicial, LocalDateTime dataFinal
    );

    List<Transferencia> findByNomeOperadorTransacaoAndDataTransferenciaBetween(String nomeOperador, LocalDateTime dataInicial, LocalDateTime dataFinal);
}
