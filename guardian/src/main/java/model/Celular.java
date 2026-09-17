package model;

public class Celular {

    private int id;
    private String marca;
    private String modelo;
    private String IMEI;
    private String statusCelular;
    private int usuarioId;

    public Celular(int id, String marca, String modelo, String IMEI,
                   String statusCelular, int usuarioId) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.IMEI = IMEI;
        this.statusCelular = statusCelular;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getStatusCelular() {
        return statusCelular;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public void setStatusCelular(String statusCelular) {
        this.statusCelular = statusCelular;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}