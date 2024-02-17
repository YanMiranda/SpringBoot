package med.vol.api.domain.consulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Getter
@Table(name = "consultas")
@Entity(name = "Consulta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivosCancelamentos motivoCancelamento;

    public void cancelar(MotivosCancelamentos motivo) {
        this.motivoCancelamento = motivo;
    }
}
