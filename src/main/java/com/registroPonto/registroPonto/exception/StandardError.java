package com.registroPonto.registroPonto.exception;

import java.time.Instant;

public class StandardError {

    private Instant instant;
    private Integer status;
    private String mesage;
    private String path;

    public StandardError() {

    }
    public StandardError(Instant instante, Integer status, String mesage, String path) {
        this.instant = instante;
        this.status = status;
        this.mesage = mesage;
        this.path = path;
    }

    public Instant getInstante() {
        return instant;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMesage() {
        return mesage;
    }

    public void setInstante(Instant instante) {
        this.instant = instante;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getPath() {
        return path;
    }
}
