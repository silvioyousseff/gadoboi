package br.com.gadoboi.api.infrastructure.adapter.in.rest.dto;

public record AuthResponse(String token, String tipo) {
    public AuthResponse(String token) {
        this(token, "Bearer");
    }
}
