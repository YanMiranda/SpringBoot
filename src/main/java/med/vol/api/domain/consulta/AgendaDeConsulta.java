package med.vol.api.domain.consulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.vol.api.domain.consulta.records.DadosCancelamentoConsulta;
import med.vol.api.domain.consulta.records.DadosDetalhamentoConsulta;
import med.vol.api.domain.consulta.validations.agendamentoConsulta.ValidadorAgendamentoConsultas;
import med.vol.api.domain.consulta.validations.cancelamentoConsulta.ValidadorCancelamentoDeConsultas;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.interfaces.MedicoRepository;
import med.vol.api.domain.paciente.Paciente;
import med.vol.api.domain.paciente.interfaces.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {

    @Autowired
    private ConsultaRepository repository;
    
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsultas> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        Medico medico = buscarMedico(dados);
        DadosAgendamentoConsulta data = new DadosAgendamentoConsulta(medico.getId(), dados.idPaciente(), dados.data(), dados.especialidade());

        if (data.idMedico() == null) {
            throw new ValidacaoException("Médico não encontrado");
        }

        validadores.forEach(v -> v.validate(data));

        Paciente paciente = pacienteRepository.findById(data.idPaciente()).orElseThrow();

        Consulta consulta = new Consulta(null, medico, paciente, data.data(), null);
        
        repository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validate(dados));

        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico buscarMedico(DadosAgendamentoConsulta dados) {
        if (dados != null && dados.idMedico() != null){
            return medicoRepository.findById(dados.idMedico()).orElseThrow();
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Informe a especialidade ou o médico");
        }

        return medicoRepository.encontrePelaEspecialidade(dados.especialidade(), dados.data());
    }


}
