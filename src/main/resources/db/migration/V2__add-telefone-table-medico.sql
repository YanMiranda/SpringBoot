ALTER TABLE medicos DROP COLUMN telefone;

-- Adicionando a nova coluna
ALTER TABLE medicos ADD COLUMN telefone VARCHAR(20) NOT NULL;

-- Atualizando os valores existentes (se necessário)
UPDATE medicos SET telefone = '';













