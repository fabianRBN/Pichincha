

-- Insercion de datos en la tabla Persona
INSERT INTO persona ( nombre, genero, edad, identificacion, direccion, telefono)
VALUES
( 'Juan Perez', 'M', 35, '0102030405', 'Av. Siempre Viva 123', '0987654321'),
( 'Maria Gomez', 'F', 28, '0203040506', 'Calle Falsa 456', '0998765432'),
( 'Carlos Ramirez', 'M', 40, '0304050607', 'Av. Central 789', '0986543210'),
( 'Ana Torres', 'F', 32, '0405060708', 'Calle Luna 101', '0995432108'),
( 'Luis Sanchez', 'M', 45, '0506070809', 'Av. Sol 202', '0984321098');

-- Insercion de datos en la tabla Cliente
INSERT INTO cliente ( persona_id, contrasena, estado)
VALUES
( 1, 'password123', TRUE),
( 2, 'securepass', TRUE),
( 3, 'mysecret', FALSE),
( 4, 'admin123', TRUE),
( 5, 'qwerty2025', FALSE);

-- Insercion de datos en la tabla Cuenta
INSERT INTO cuenta ( cliente_id, numero_cuenta, tipo_cuenta, saldo_inicial, estado)
VALUES
( 1, '1234567890', 'Ahorros', 1000.50, TRUE),
( 2, '2345678901', 'Corriente', 2000.00, TRUE),
( 3, '3456789012', 'Ahorros', 1500.75, TRUE),
( 4, '4567890123', 'Corriente', 3000.00, TRUE),
( 5, '5678901234', 'Ahorros', 500.00, FALSE);

-- Insercion de datos en la tabla Movimientos
INSERT INTO movimientos ( cuenta_id, fecha, tipo_movimiento, valor, saldo,disponible)
VALUES
( 1, '2025-01-10 08:30:00', 'Deposito', 500.00, 1500.50,2000.50),
( 1, '2025-01-11 09:00:00', 'Retiro', 200.00, 1300.50,1500.50),
( 2, '2025-01-12 10:15:00', 'Deposito', 1000.00, 3000.00,4000.00),
( 3, '2025-01-13 11:45:00', 'Retiro', 250.00, 1250.75,1500.75),
( 4, '2025-01-14 13:00:00', 'Deposito', 1500.00, 4500.00,6000.00),
( 5, '2025-01-15 14:30:00', 'Retiro', 100.00, 400.00,500.00);