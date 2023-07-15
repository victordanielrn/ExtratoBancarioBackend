package br.com.banco.Controller;
import br.com.banco.DTO.ContaDTO;
import br.com.banco.DTO.TransferenciaDTO;
import br.com.banco.Service.ContaService;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<ContaDTO> listarContas() {
        List<Conta> contas = contaService.listarContas();
        return contas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{contaId}/transferencias")
    public List<TransferenciaDTO> listarTransferenciasPorConta(
            @PathVariable Long contaId,
            @RequestParam(required = false) String nomeOperador,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        List<Transferencia> transferencias = contaService.listarTransferenciasPorConta(contaId, nomeOperador, dataInicial, dataFinal);
        return transferencias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ContaDTO convertToDTO(Conta conta) {
        ContaDTO dto = new ContaDTO();
        dto.setId(conta.getId());
        dto.setNomeResponsavel(conta.getNomeResponsavel());
        return dto;
    }

    private TransferenciaDTO convertToDTO(Transferencia transferencia) {
        TransferenciaDTO dto = new TransferenciaDTO();
        dto.setId(transferencia.getId());
        dto.setDataTransferencia(transferencia.getDataTransferencia());
        dto.setValor(transferencia.getValor());
        dto.setTipo(transferencia.getTipo());
        dto.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());

        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(transferencia.getConta().getId());
        contaDTO.setNomeResponsavel(transferencia.getConta().getNomeResponsavel());

        dto.setConta(contaDTO);

        return dto;
    }
}
