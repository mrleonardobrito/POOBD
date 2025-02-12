package br.edu.ifal.service;

import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.domain.Cliente;

public class ClienteService {
    private ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
    
    public void cadastrarCliente(Cliente cliente) {
        try {
            this.clienteDao.save(cliente);
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o cliente: " + e.getMessage());
        }
    }
}
