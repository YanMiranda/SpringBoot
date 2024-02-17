package med.vol.api.domain.consulta.validations.cancelamentoConsulta;

import med.vol.api.domain.consulta.records.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsultas {

    void validate(DadosCancelamentoConsulta dados);
}
