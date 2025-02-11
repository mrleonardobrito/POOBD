package br.edu.ifal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.db.ConnectionHelper;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.Produto;

public class ProdutoDao {
    public void save(Produto produto) {
        String sql = "INSERT INTO PRODUTO VALUES (?,?,?);";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, produto.getNome());
            pst.setString(2, String.valueOf(produto.getValorUnidade()));
            pst.setString(3, String.valueOf(produto.getQuantidade()));

            pst.execute();

            pst.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto findById(int id) {
        String sql = "SELECT UNIQUE(*) FROM PRODUTO WHERE id=?;";

        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int produtoId = Integer.parseInt(rs.getString("ID"));
                String nome = rs.getString("NOME");
                double valorUnitario = Double.parseDouble(rs.getString("VALOR_UNIT"));
                int quantidade = Integer.parseInt(rs.getString("QUANTIDADE"));

                return new Produto(produtoId, nome, valorUnitario, quantidade);
            }

            pst.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
