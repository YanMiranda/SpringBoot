package med.vol.api.medico;

public record DatosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DatosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
