package br.edu.ifal.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.dao.ProdutoDao;
import br.edu.ifal.domain.Produto;

public class ProdutoService {
    private ProdutoDao produtoDao;

    public ProdutoService(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public void cadastrarProduto(Produto produto) {
        try {
            this.produtoDao.save(produto);
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar produto: " + e.getMessage());
        }
    }

    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        try {
            produto = this.produtoDao.findById(id);
        } catch (Exception e){
            System.out.println("A busca pelo produto falhou! " + e.getMessage());
        }
        return produto;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            produtos = this.produtoDao.findAll();
        } catch (Exception e) {
            System.out.println("A busca pelos produtos falhou! " + e.getMessage());
        }
        return produtos;
    }
}
