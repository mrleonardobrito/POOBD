package br.edu.ifal.domain;

public class Funcionario {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;

    public Funcionario(String cpf, String nome, String endereco, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Funcionario {cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + "}";
    }
}
