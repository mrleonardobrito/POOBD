package br.edu.ifal.domain;

import java.util.List;

public class Pedido {
    private int id;
    private String cpfFuncionario;
    private String cpfCliente;
    private double valorTotal;
    private List<ItemPedido> itens;

    public Pedido(int id, String cpfFuncionario, String cpfCliente, double valorTotal, List<ItemPedido> itens) {
        this.id = id;
        this.cpfFuncionario = cpfFuncionario;
        this.cpfCliente = cpfCliente;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }
    
    public int getId() {
        return id;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public List<ItemPedido> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "Pedido {id=" + id + ", cpfFuncionario=" + cpfFuncionario + ", cpfCliente=" + cpfCliente
                + ", valorTotal=" + valorTotal + "}";
    }
}
