package br.edu.ifal.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.dao.PedidoDao;
import br.edu.ifal.dao.ProdutoDao;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.domain.Pedido;

public class PedidoService {
    private PedidoDao pedidoDao;

    public PedidoService(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public List<Pedido> exibirVendas() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = this.pedidoDao.findAll();
        } catch (Exception e) {
            System.out.println("A busca pelos pedidos falhou! " + e.getMessage());
        }
        return pedidos;
    }

    public void efetuarVenda(Pedido pedido) {
        try {
            this.pedidoDao.save(pedido);
        } catch (Exception e) {
            System.out.println("Não foi possível efetuar a venda: " + e.getMessage());
        }
    }
}
