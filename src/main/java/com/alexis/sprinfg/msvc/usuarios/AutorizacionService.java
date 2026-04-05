package com.alexis.sprinfg.msvc.usuarios;

// Esta interfaz simula un servicio externo — como una BD o API
public interface AutorizacionService {
    boolean estaAutorizada(String transactionId);
}
