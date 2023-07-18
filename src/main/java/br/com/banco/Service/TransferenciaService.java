package br.com.banco.Service;
import br.com.banco.Repository.TransferenciaRepository;
import br.com.banco.entities.Transferencia;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }
    public List<Transferencia> listarTransferenciasPorData(LocalDate dataInicial, LocalDate dataFinal) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal);
    }



    public List<Transferencia> listarTransferencias(String nomeOperador, LocalDate dataInicial, LocalDate dataFinal) {
        if (nomeOperador != null && dataInicial != null && dataFinal != null) {
            return transferenciaRepository.findByNomeOperadorTransacaoAndDataTransferenciaBetween(nomeOperador, dataInicial, dataFinal);
        } else if (nomeOperador != null && dataInicial == null && dataFinal == null) {
            return transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
        } else if (nomeOperador == null && dataInicial != null && dataFinal != null) {
            return transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal);
        } else {
            return transferenciaRepository.findAll();
        }
    }
}

