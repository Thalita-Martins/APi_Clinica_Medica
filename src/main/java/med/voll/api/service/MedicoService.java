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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Transactional(readOnly = true)
    public Page<DadosListaMedicos> findAllMedico(Pageable pageable){
        return medicoRepository.findAllByAtivoTrue(pageable);
    }

    @Transactional(readOnly = true)
    public DadosListaMedicos findByMedicoId(Long id){
        var medico = medicoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        return new DadosListaMedicos(medico);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DadosListaMedicos create(DadosCadastroMedico dados) {
        var medico = new Medico(dados);
        save(medico);
        return new DadosListaMedicos(medico);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DadosAtualizacaoMedico update(DadosAtualizacaoMedico dados){
        var medicoRecuperado = medicoRepository.findById(dados.id()).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        medicoRecuperado.mergeUpdate(dados);
        save(medicoRecuperado);
        return new DadosAtualizacaoMedico(medicoRecuperado);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean delete(Long id){
        var medicoRecuperado = medicoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        medicoRecuperado.setAtivo(false);
        save(medicoRecuperado);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Medico medico){
        medicoRepository.save(medico);
    }
}
