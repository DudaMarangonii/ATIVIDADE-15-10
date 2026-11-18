package br.com.nexuscloud.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Servidor;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public void cadastrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void adicionarServidor(Cliente c, Servidor s) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
        bancoDeDados.get(c).add(s);
    }

    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, new HashSet<>())
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}
