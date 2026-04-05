package com.alexis.sprinfg.msvc.usuarios;

public class AutorizacionServiceImpl implements AutorizacionService {
    @Override
    public boolean estaAutorizada(String transactionId) {
        return true; // por ahora autoriza todo
    }
}