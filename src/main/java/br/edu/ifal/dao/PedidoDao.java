package br.edu.ifal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.domain.Pedido;


public class PedidoDao {
    public List<Pedido> findAll() {
        String sql = "SELECT * FROM PEDIDO ORDER BY ID ASC;";

        List<Pedido> pedidos = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int pedidoId = Integer.parseInt(rs.getString("ID"));
                String cpfCliente = rs.getString("CPF_CLIENTE_FK");
                String cpfFuncionario = rs.getString("CPF_FUNCIONARIO_FK");
                double valorTotal = Double.parseDouble(rs.getString("VALOR_TOTAL"));

                Pedido pedido = new Pedido(pedidoId, cpfFuncionario, cpfCliente, valorTotal, null);
                pedidos.add(pedido);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    public void save(Pedido pedido) {
        String pedidoSQL = "INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?, ?, ?);";
        String itemPedidoSQL = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(pedidoSQL, Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, pedido.getCpfCliente());
            pst.setString(2, pedido.getCpfFuncionario());
            pst.setDouble(3, pedido.getValorTotal());
            
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro ao inserir o pedido, nenhuma linha afetada.");
            }
            
            ResultSet generatedKeys = pst.getGeneratedKeys();
            int pedidoId = -1;
            if (generatedKeys.next()) {
                pedidoId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Falha ao obter o ID do pedido.");
            }
            
            pst.close();
            
            PreparedStatement itemPst = connection.prepareStatement(itemPedidoSQL);
            for (ItemPedido item : pedido.getItens()) {
                itemPst.setInt(1, pedidoId);
                itemPst.setInt(2, item.getIdProduto());
                itemPst.setInt(3, item.getQuantidade());
                itemPst.setDouble(4, item.getValor());
                itemPst.addBatch();
            }
            
            itemPst.executeBatch();
            itemPst.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao salvar o pedido: " + e.getMessage(), e);
        }
    }

}
