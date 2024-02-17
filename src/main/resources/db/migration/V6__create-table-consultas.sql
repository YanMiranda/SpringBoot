CREATE TABLE consultas(

                        id BIGINT NOT NULL auto_increment,
                        id_medico BIGINT NOT NULL,
                        id_paciente BIGINT NOT NULL,
                        data datetime NOT NULL,

                      PRIMARY KEY(id),
                      CONSTRAINT fk_consultas_id_medico FOREIGN KEY(id_medico) REFERENCES medicos(id),
                      CONSTRAINT fk_consultas_id_paciente FOREIGN KEY(id_paciente) REFERENCES pacientes(id)

);
