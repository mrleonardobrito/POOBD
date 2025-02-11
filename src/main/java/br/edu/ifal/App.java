package br.edu.ifal;

import java.util.Scanner;

import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.dao.ProdutoDao;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.Produto;

public class App {
    private Scanner scanner;
    private ProdutoDao produtoDao;
    private ClienteDao clienteDao;
    
    private void cadastrarProduto() {
        String nome = readString("Digite o nome do produto:");
        double valorUnitario = readDouble("Digite o valor unitário do produto: ");
        int quantidade = readInt("Digite a quantidade do produto em estoque: ");

        Produto produto = new Produto(0, nome, valorUnitario, quantidade);
        this.produtoDao.save(produto);
    }

    public void cadastrarCliente() {
        String cpf = readString("Digite o cpf do cliente:");
        String nome = readString("Digite o nome do cliente:");
        String endereco = readString("Digite o endereço do cliente:");
        String telefone = readString("Digite o telefone do cliente:");

        Cliente cliente = new Cliente(cpf, nome, endereco, telefone);
        this.clienteDao.save(cliente);
    }
    
    public void buscarProdutoPorId() {

    }

    // public void listarProdutos() {

    // }

    // public void efetuarVenda() {

    // }

    // public void listarVendas() {

    // }

    private String readString(String prompt) {
        System.out.println(prompt);
        return this.scanner.nextLine();
    }

    private int readInt(String prompt) {
        while(true){
            try{
                System.out.println(prompt);
                return Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, tente novamente outro input!");
            }
        }
    }

    private double readDouble(String prompt) {
        while(true){
            try{
                System.out.println(prompt);
                return Double.parseDouble(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, tente novamente outro input!");
            }
        }
    }

    public App() {
        this.produtoDao = new ProdutoDao();
        this.clienteDao = new ClienteDao();

        this.scanner = new Scanner(System.in);
        int opt;

        do {
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Buscar Produto(por id)");
            System.out.println("4. Listar os produtos disponíveis");
            System.out.println("5. Efetuar venda");
            System.out.println("6. Listar vendas");
            System.out.println("0. Sair");

            opt = scanner.nextInt();

            switch (opt) {
                case 1: cadastrarProduto();
                    break;
                case 2: cadastrarCliente();
                    break;
                // case 3: buscarProdutoPorId();
                //     break;
                // case 4: listarProdutos();
                //     break;
                // case 5: efetuarVenda();
                //     break;
                // case 6: listarVendas();
                //     break;
                default: System.out.println("Opção inválida, tente novamente!");
            }
        } while(opt != 0);
        this.scanner.close();
    }
}
