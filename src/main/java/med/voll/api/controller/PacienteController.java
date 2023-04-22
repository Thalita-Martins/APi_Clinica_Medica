package med.voll.api.controller;

import med.voll.api.DTO.DadosAtualizacaoPaciente;
import med.voll.api.DTO.DadosCadastroPaciente;
import med.voll.api.DTO.DadosListaPaciente;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/listar")
    public Page<DadosListaPaciente> findAllPaciente(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return pacienteService.findAllPaciente(pageable);
    }

    @GetMapping("/listar/{pacienteId}")
    public ResponseEntity findByPacienteId(@PathVariable(name = "pacienteId") Long pacienteId) {
        try {
            var pacientes = pacienteService.findByPacienteId(pacienteId);
            return ResponseEntity.ok(pacientes);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public DadosListaPaciente create(@RequestBody DadosCadastroPaciente dados) {
        return pacienteService.create(dados);
    }

    @PutMapping("/atualizar")
    public ResponseEntity update(@RequestBody DadosAtualizacaoPaciente dados) {
        try{
            var paciente = pacienteService.update(dados);
            return ResponseEntity.ok(paciente);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{pacienteId}")
    public ResponseEntity delete(@PathVariable Long pacienteId){
        try {
            var status = pacienteService.delete(pacienteId);
            return ResponseEntity.ok(status);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
