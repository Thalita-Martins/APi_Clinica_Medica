package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.DTO.DadosListaMedicos;
import med.voll.api.domain.Medico;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping("/listar")
    public Page<DadosListaMedicos> findAllMedico(@PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return medicoService.findAllMedico(pageable);
    }

    @GetMapping("/listar/{id}")
    public DadosListaMedicos findById(@PathVariable(name = "id") Long id){
        return medicoService.findById(id);
    }

    @PostMapping("/cadastrar")
    public Medico create(@RequestBody @Valid DadosCadastroMedico dados){
        return medicoService.create(dados);
    }

    @PutMapping("/atualizar")
    public DadosAtualizacaoMedico update(@RequestBody @Valid DadosAtualizacaoMedico dados){
        return medicoService.update(dados);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable Long id){
        medicoService.delete(id);
    }
}
