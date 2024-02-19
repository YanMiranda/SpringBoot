package med.vol.api.domain.medico.interfaces;

import med.vol.api.domain.consulta.Consulta;
import med.vol.api.domain.endereco.DadosEndereco;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.enums.Especialidade;
import med.vol.api.domain.medico.record.DadosCadastroMedico;
import med.vol.api.domain.paciente.Paciente;
import med.vol.api.domain.paciente.records.DadosCadastroPaciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Caso único médico disponivel na data não esteja disponivel")
    void encontrePelaEspecialidadeCase1() {
        //given/arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        System.out.println(nextMondayAt10);
        //whem/act
        var medico = cadastrarMedico("Dr. pereira", "medico@vell.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("silva", "silva@email.com", "123456");
        cadastrarConsulta(medico, paciente, nextMondayAt10);

        //then/assert
        var medicoLivre = medicoRepository.encontrePelaEspecialidade(Especialidade.CARDIOLOGIA, nextMondayAt10);
        assertThat(medicoLivre).isNull();

    }

    @Test
    @DisplayName("Caso único médico disponivel na data esteja disponivel")
    void encontrePelaEspecialidadeCase2() {
        var nextMondayAt10 = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10);

       var medico = cadastrarMedico("Dr. pereira", "medico@vell.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.encontrePelaEspecialidade(Especialidade.CARDIOLOGIA, nextMondayAt10);
        assertThat(medicoLivre).isEqualTo(medico);

    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "DF",
                null,
                null,
                "Brasilia"
        );
    }
}