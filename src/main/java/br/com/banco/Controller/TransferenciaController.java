package br.com.banco.Controller;

import br.com.banco.DTO.TransferenciaDTO;
import br.com.banco.Service.TransferenciaService;
import br.com.banco.entities.Transferencia;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public List<TransferenciaDTO> listarTransferencias(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ) {
        List<Transferencia> transferencias = transferenciaService.listarTransferenciasPorData(dataInicial, dataFinal);
        return transferencias.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
