package med.voll.api.service;

import med.voll.api.DTO.DadosAtualizacaoPaciente;
import med.voll.api.DTO.DadosCadastroPaciente;
import med.voll.api.DTO.DadosListaPaciente;
import med.voll.api.domain.Paciente;
import med.voll.api.exception.RegraNegocioException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Page<DadosListaPaciente> findAllPaciente(Pageable pageable) {
       return pacienteRepository.findAllByAtivoTrue(pageable);
    }

    public DadosListaPaciente findByPacienteId(Long id) {
        var pacienteRecuperado = pacienteRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Paciente não encontrado"));
        return new DadosListaPaciente(pacienteRecuperado);
    }

    public DadosListaPaciente create(DadosCadastroPaciente dados) {
        var paciente = new Paciente(dados);
        save(paciente);
        return new DadosListaPaciente(paciente);
    }

    public DadosAtualizacaoPaciente update(DadosAtualizacaoPaciente dados) {
        var pacienteRecuperado = pacienteRepository.findById(dados.id()).orElseThrow(() -> new RegraNegocioException("Paciente não encontrado"));
        pacienteRecuperado.mergeUpdate(dados);
        save(pacienteRecuperado);
        return new DadosAtualizacaoPaciente(pacienteRecuperado);
    }

    public void save(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void delete(Long id) {
        var pacienteRecuperado = pacienteRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Paciente não encontrado"));
        pacienteRecuperado.setAtivo(false);
        save(pacienteRecuperado);
    }
}
