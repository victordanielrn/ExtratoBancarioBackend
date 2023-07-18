package br.com.banco.Controller;
import br.com.banco.DTO.ContaDTO;
import br.com.banco.DTO.TransferenciaDTO;
import br.com.banco.Service.ContaService;
import br.com.banco.Service.TransferenciaService;
import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService, TransferenciaService transferenciaService) {
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
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal
    ) {
        List<Transferencia> transferencias = contaService.listarTransferenciasPorConta(contaId, nomeOperador, dataInicial, dataFinal);
        return transferencias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/nome/{nomeResponsavel}/transferencias")
    public List<TransferenciaDTO> listarTransferenciasPorNomeResponsavel(
            @PathVariable String nomeResponsavel
    ) {
        List<Transferencia> transferencias = contaService.listarTransferenciasPorNomeResponsavel(nomeResponsavel);
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
        dto.setConta(null); // Defina como null para evitar referências circulares
        return dto;
    }
}
