package med.voll.api.service;

import med.voll.api.domain.Agendamento;
import med.voll.api.enumTipos.StatusAgendamento;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.repository.AgendamentoRepository;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final MedicoRepository medicoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository, MedicoRepository medicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional(readOnly = true)
    public List<Agendamento> findallAgendamento(Long medicoId) {
        var medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        return agendamentoRepository.findByMedico(medico);
    }

    @Transactional(readOnly = true)
    public Agendamento findByAgendamento(Long agendamentoId) {
       return agendamentoRepository.findById(agendamentoId).orElseThrow(() -> new RegraNegocioException("Agendamento não encontrado"));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Agendamento create(Agendamento agendamento) {
        var medico = medicoRepository.findByAtivoTrue(agendamento.getMedico().getId()).orElseThrow(() -> new RegraNegocioException("Medico não encontrado"));
        return save(agendamento);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Agendamento update(Agendamento agendamento) {
        var agendamentoRecuperado = agendamentoRepository.findById(agendamento.getId()).orElseThrow(() -> new RegraNegocioException("Agendamento não encontrado"));
        agendamentoRecuperado.mergeForUpdate(agendamento);
        return save(agendamentoRecuperado);
    }

    public Agendamento atualizarStatus(Long agendamentoId, String status) {
        var agendamentoRecuperado = agendamentoRepository.findById(agendamentoId).orElseThrow(() -> new RegraNegocioException("Agendamento não encontrado"));
        agendamentoRecuperado.setStatus(StatusAgendamento.valueOf(status));
        save(agendamentoRecuperado);
        return agendamentoRecuperado;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Agendamento save (Agendamento agendamento){
        return agendamentoRepository.save(agendamento);
    }
}
