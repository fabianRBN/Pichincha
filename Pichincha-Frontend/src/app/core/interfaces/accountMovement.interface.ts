export interface AccountMovement {
    fecha: string;            // Fecha de la transacción
    cliente: string;          // Nombre del cliente
    numeroCuenta: string;     // Número de cuenta
    tipo: string;             // Tipo de cuenta (e.g., Ahorros)
    saldoInicial: number;     // Saldo inicial de la cuenta
    estado: boolean;          // Estado de la cuenta (activo/inactivo)
    movimiento: number;       // Movimiento realizado
    saldoDisponible: number;  // Saldo disponible después del movimiento
    available: number;        // Saldo total disponible
}