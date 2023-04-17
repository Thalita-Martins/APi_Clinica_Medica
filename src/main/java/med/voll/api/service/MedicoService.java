package med.voll.api.service;

import med.voll.api.DTO.DadosAtualizacaoMedico;
import med.voll.api.DTO.DadosCadastroMedico;
import med.voll.api.DTO.DadosListaMedicos;
import med.voll.api.domain.Medico;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<DadosListaMedicos> findAllMedico(Pageable pageable){
        return medicoRepository.findAllByAtivoTrue(pageable);
    }

    public DadosListaMedicos findById(Long id){
        var medico = medicoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        return new DadosListaMedicos(medico);
    }

    public Medico create(DadosCadastroMedico dados) {
        var medico = new Medico(dados);
        return save(medico);
    }

    public DadosAtualizacaoMedico update(DadosAtualizacaoMedico dados){
        var medicoRecuperado = medicoRepository.findById(dados.id()).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        medicoRecuperado.mergeUpdate(dados);
        save(medicoRecuperado);
        return new DadosAtualizacaoMedico(medicoRecuperado);
    }

    public void delete(Long id){
        var medicoRecuperado = medicoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        medicoRecuperado.setAtivo(false);
        save(medicoRecuperado);
    }

    public Medico save(Medico medico){
        return medicoRepository.save(medico);
    }
}
