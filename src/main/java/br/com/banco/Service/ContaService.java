package br.com.banco.Service;
import br.com.banco.Repository.ContaRepository;
import br.com.banco.Repository.TransferenciaRepository;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;

    public ContaService(ContaRepository contaRepository, TransferenciaRepository transferenciaRepository) {
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }
    public List<Transferencia> listarTransferenciasPorNomeResponsavel(String nomeResponsavel) {
        return transferenciaRepository.findByConta_NomeResponsavel(nomeResponsavel);
    }


    public List<Transferencia> listarTransferenciasPorConta(
            Long contaId,
            String nomeOperador,
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    ) {
        if (nomeOperador != null && dataInicial != null && dataFinal != null) {
            return transferenciaRepository.findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                    contaId, nomeOperador, dataInicial, dataFinal);
        } else if (nomeOperador != null && dataInicial == null && dataFinal == null) {
            return transferenciaRepository.findByContaIdAndNomeOperadorTransacao(contaId, nomeOperador);
        } else if (nomeOperador == null && dataInicial != null && dataFinal != null) {
            return transferenciaRepository.findByContaIdAndDataTransferenciaBetween(contaId, dataInicial, dataFinal);
        } else {
            return transferenciaRepository.findByContaId(contaId);
        }
    }
}
