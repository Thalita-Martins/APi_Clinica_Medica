package med.voll.api.controller;

import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.DTO.DadosListaMedicos;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medico")
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

    @GetMapping("/listar/{medicoId}")
    public ResponseEntity findByMedicoId(@PathVariable(name = "medicoId") Long medicoId) {
        try {
            var medico = medicoService.findByMedicoId(medicoId);
            return ResponseEntity.ok(medico);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    public DadosListaMedicos create(@RequestBody DadosCadastroMedico dados){
        return medicoService.create(dados);
    }

    @PutMapping("/atualizar")
    public ResponseEntity update(@RequestBody DadosAtualizacaoMedico dados){
        try{
            var medico = medicoService.update(dados);
            return ResponseEntity.ok(medico);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{medicoId}")
    public ResponseEntity delete(@PathVariable Long medicoId){
        try{
            var status = medicoService.delete(medicoId);
            return ResponseEntity.ok(status);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
