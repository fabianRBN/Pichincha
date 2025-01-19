-- crear tabla persona
DROP TABLE IF EXISTS Movimientos;
DROP TABLE IF EXISTS Cuenta;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Persona;

create table if not exists persona  (
    persona_id     int primary key generated always as IDENTITY,
    nombre         varchar(100) not null,
    genero         char(1) not null,
    edad           int not null,
    identificacion varchar(50) not null,
    direccion      varchar(255),
    telefono       varchar(15)
);


-- crear tabla cliente
create table if not exists cliente (
    cliente_id int primary key generated always as IDENTITY,
    persona_id int not null,
    contrasena varchar(100) not null,
    estado boolean not null,
    unique (persona_id),
    constraint fk_persona foreign key (persona_id) references persona (persona_id) on delete cascade
);


-- crear tabla cuenta
create table if not exists cuenta (
    cuenta_id int primary key generated always as IDENTITY,
    cliente_id int not null,
    numero_cuenta varchar(50) not null unique,
    tipo_cuenta varchar(50) not null,
    saldo_inicial numeric(15, 2) not null,
    estado boolean not null,
    constraint fk_cliente foreign key (cliente_id) references cliente (cliente_id) on delete cascade
);


-- crear tabla movimientos
create table if not exists movimientos (
    movimiento_id bigint primary key generated always as IDENTITY,
    cuenta_id int not null,
    fecha timestamp not null default current_timestamp,
    tipo_movimiento varchar(50) not null,
    valor numeric(15, 2) not null,
    saldo numeric(15, 2) not null,
    disponible numeric(15, 2) not null,
    constraint fk_cuenta foreign key (cuenta_id) references cuenta (cuenta_id) on delete cascade
);
