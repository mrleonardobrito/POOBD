package br.edu.ifal.domain;

public class Produto {
    private int id;
    private String nome;
    private double valorUnidade;
    private int quantidade;

    public Produto(int id, String nome, double valorUnidade, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.valorUnidade = valorUnidade;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValorUnidade() {
        return valorUnidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto {id=" + id + ", nome=" + nome + ", valorUnidade=" + valorUnidade + ", quantidade=" + quantidade
                + "}";
    }
}
