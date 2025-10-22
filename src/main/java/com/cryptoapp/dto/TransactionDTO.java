
package com.cryptoapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionDTO {

    @NotNull(message = "userId es obligatorio")
    private Long userId;

    @NotBlank(message = "crypto es obligatorio")
    private String crypto;

    @NotNull(message = "cantidad es obligatoria")
    @Min(value = 0, message = "cantidad debe ser positiva")
    private Double cantidad;

    @NotNull(message = "precio es obligatorio")
    @Min(value = 0, message = "precio debe ser positivo")
    private Double precio;

    @NotBlank(message = "tipo es obligatorio: compra o venta")
    private String tipo;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getCrypto() { return crypto; }
    public void setCrypto(String crypto) { this.crypto = crypto; }
    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
