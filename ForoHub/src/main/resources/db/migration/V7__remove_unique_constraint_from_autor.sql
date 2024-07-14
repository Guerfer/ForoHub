-- Eliminar la restricción de unicidad en el campo 'autor'
ALTER TABLE topicos DROP INDEX autor;

-- Asegurarse de que el campo 'autor' no tenga restricción de unicidad
ALTER TABLE topicos MODIFY autor VARCHAR(255) NOT NULL;