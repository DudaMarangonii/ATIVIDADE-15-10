package br.com.nexuscloud.model;

import java.util.ArrayList;
import java.util.Objects;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.exception.ServidorOfflineException;

public class Servidor {
    private String ip;
    private double ramTotal;
    private double precoPorHora;
    private boolean statusLigado;

    private ArrayList<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public double getRamTotal() { return ramTotal; }
    public void setRamTotal(double ramTotal) { this.ramTotal = ramTotal; }

    public double getPrecoPorHora() { return precoPorHora; }
    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public boolean isStatusLigado() { return statusLigado; }

    public ArrayList<Container> getContainers() { return containers; }

    public void ligar() {
        statusLigado = true;
    }

    public void desligar() {
        statusLigado = false;
    }

    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public void alocarContainer(Container c)
            throws CapacidadeExcedidaException {

        if (!statusLigado) {
            throw new ServidorOfflineException(
                    "Erro: Não é possível alocar containers em um servidor desligado."
            );
        }

        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                    "Falha Crítica: Memória RAM insuficiente no servidor " + ip + "."
            );
        }

        containers.add(c);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servidor servidor = (Servidor) obj;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
