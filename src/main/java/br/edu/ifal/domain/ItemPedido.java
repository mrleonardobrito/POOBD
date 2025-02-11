package br.edu.ifal.domain;

public class ItemPedido {
    private int idProduto;
    private int idPedido;
    private int quantidade;
    private double valor;

    public ItemPedido(int idProduto, int idPedido, int quantidade, double valor) {
        this.idProduto = idProduto;
        this.idPedido = idPedido;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public double getValor() {
        return valor;
    }
}
