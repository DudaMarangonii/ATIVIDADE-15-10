package br.com.nexuscloud;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;
import br.com.nexuscloud.manager.CloudManager;

public class MainCloud {
    public static void main(String[] args) {
        CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        manager.cadastrarCliente(cliente);
        manager.adicionarServidor(cliente, servidor);

        Container c1 = new Container("C-01", 10.0);
        Container c2 = new Container("C-02", 8.0);
        servidor.ligar();

        try {
            servidor.alocarContainer(c1);
            System.out.println("Container C-01 alocado com sucesso!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            servidor.alocarContainer(c2);
            System.out.println("Container C-02 alocado com sucesso!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        double fatura = manager.calcularFaturaAtiva(cliente);
        System.out.printf("Fatura ativa: R$ %.2f%n", fatura);
    }
}
