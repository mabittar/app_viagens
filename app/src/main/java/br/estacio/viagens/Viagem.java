package br.estacio.viagens;

import java.io.Serializable;

public class Viagem implements Serializable {
    private Long id;
    private String destino;
    private String diario;
    private Double nota;

    public Viagem(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDiario() {
        return diario;
    }

    public void setDiario(String diario) {
        this.diario = diario;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {return id + ". " + destino + " - " + diario; }
}
