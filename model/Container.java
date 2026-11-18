package br.com.nexuscloud.model;

import java.util.Objects;

public class Container {
    private String id;
    private double ramNecessaria;

    public Container(String id, double ramNecessaria) {
        this.id = id;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public double getRamNecessaria() { return ramNecessaria; }
    public void setRamNecessaria(double ramNecessaria) {
        this.ramNecessaria = ramNecessaria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container container = (Container) obj;
        return Objects.equals(id, container.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
