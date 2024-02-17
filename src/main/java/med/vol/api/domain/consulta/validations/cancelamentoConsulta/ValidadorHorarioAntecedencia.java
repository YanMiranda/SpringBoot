package med.vol.api.domain.consulta.validations.cancelamentoConsulta;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.Consulta;
import med.vol.api.domain.consulta.ConsultaRepository;
import med.vol.api.domain.consulta.records.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validate(DadosCancelamentoConsulta dados) {
        Consulta consulta = repository.getReferenceById(dados.idConsulta());
        LocalDateTime agora = LocalDateTime.now();
        long diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}