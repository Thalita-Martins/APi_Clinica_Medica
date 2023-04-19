CREATE TABLE agendamento (
id SERIAL PRIMARY KEY,
data DATE,
hora_inicial TIME,
hora_final TIME,
medico_id BIGINT NOT NULL,
paciente_id BIGINT NOT NULL,
status VARCHAR(100),
CONSTRAINT fk_agendamento_medico
FOREIGN KEY (medico_id)
REFERENCES medico(id),
CONSTRAINT fk_agendamento_paciente
FOREIGN KEY (paciente_id)
REFERENCES paciente(id)

);