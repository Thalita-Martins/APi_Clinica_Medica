package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.DTO.DadosAtualizacaoPaciente;
import med.voll.api.DTO.DadosCadastroPaciente;
import med.voll.api.DTO.DadosListaPaciente;
import med.voll.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
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

    @GetMapping("/listar/{id}")
    public DadosListaPaciente findByPacienteId(@PathVariable(name = "id") Long id){
        return pacienteService.findByPacienteId(id);
    }

    @PostMapping("/cadastrar")
    public DadosListaPaciente create(@RequestBody @Valid DadosCadastroPaciente dados) {
        return pacienteService.create(dados);
    }

    @PutMapping("/atualizar")
    public DadosAtualizacaoPaciente update(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
       return pacienteService.update(dados);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable Long id){
        pacienteService.delete(id);
    }
}
