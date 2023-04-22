package med.voll.api.controller;

import med.voll.api.domain.Agendamento;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/listar")
    public ResponseEntity findallAgendamento(@RequestParam(name = "medicoId") Long medicoId){
        try{
            var agendamentos = agendamentoService.findallAgendamento(medicoId);
            return ResponseEntity.ok(agendamentos);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar/{agendamentoId}")
    public ResponseEntity findByAgendamento(@PathVariable(name = "agendamentoId") Long agendamentoId){
        try {
            var agendamento = agendamentoService.findByAgendamento(agendamentoId);
            return ResponseEntity.ok(agendamento);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity create(@RequestBody Agendamento agendamento){
        try{
            var agendamentoSalvo = agendamentoService.create(agendamento);
            return ResponseEntity.ok(agendamentoSalvo);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity update(@RequestBody Agendamento agendamento){
        try{
            var agendamentoSalvo = agendamentoService.update(agendamento);
            return ResponseEntity.ok(agendamentoSalvo);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/status")
    public ResponseEntity atualizarStatus(@RequestParam(name = "agendamentoId") Long agendamentoId, @RequestParam(name = "status") String status){
        try{
            var agendamento = agendamentoService.atualizarStatus(agendamentoId,status);
            return ResponseEntity.ok(agendamento);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
