-- Limpieza previa (opcional pero recomendada en desarrollo)
-- INSERT INTO se ejecuta después del DDL auto de Hibernate

-- 1. Insertar un Empleado y un Huésped (La contraseña es '123456' encriptada con BCrypt)
INSERT INTO usuarios (id, username, password, rol) VALUES (1, 'empleado1', '$2a$10$X5pGZ893e9vK/b77P6MhI.gY4R7hVpCjS9f9Wv3e4kYVf7g2m5CqG', 'ROLE_EMPLEADO');
INSERT INTO empleados (nombre, cargo, turno, extension_interna, id) VALUES ('Juan Perez', 'Recepcionista', 'Mañana', '101', 1);

INSERT INTO usuarios (id, username, password, rol) VALUES (2, 'huesped1', '$2a$10$X5pGZ893e9vK/b77P6MhI.gY4R7hVpCjS9f9Wv3e4kYVf7g2m5CqG', 'ROLE_HUESPED');
INSERT INTO huespedes (nombre, pasaporte_cedula, nacionalidad, email, id) VALUES ('Maria Lopez', '1712345678', 'Ecuatoriana', 'maria@example.com', 2);

-- 2. Insertar algunas habitaciones
INSERT INTO habitaciones (numero, tipo, precio_noche, capacidad, estado) VALUES ('101', 'Sencilla', 50.00, 1, 'LIBRE');
INSERT INTO habitaciones (numero, tipo, precio_noche, capacidad, estado) VALUES ('102', 'Doble', 80.00, 2, 'LIBRE');
INSERT INTO habitaciones (numero, tipo, precio_noche, capacidad, estado) VALUES ('201', 'Suite', 150.00, 2, 'OCUPADA');
