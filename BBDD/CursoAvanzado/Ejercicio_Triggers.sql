USE registro;

DELIMITER //
CREATE TRIGGER Tabla_persona
AFTER INSERT ON personas
FOR EACH ROW
BEGIN

INSERT INTO users(idUsers, Usuario, Contrasena) VALUES (NEW.idPersonas, NEW.UsuarioGuardado, NEW.ContrasenaGuardado);
	
end//
DELIMITER ;