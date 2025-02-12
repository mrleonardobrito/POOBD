package br.edu.ifal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.ifal.dao.ClienteDao;
import br.edu.ifal.dao.PedidoDao;
import br.edu.ifal.dao.ProdutoDao;
import br.edu.ifal.domain.Cliente;
import br.edu.ifal.domain.ItemPedido;
import br.edu.ifal.domain.Pedido;
import br.edu.ifal.domain.Produto;
import br.edu.ifal.service.ClienteService;
import br.edu.ifal.service.PedidoService;
import br.edu.ifal.service.ProdutoService;

public class App {
    private Scanner scanner;
    private ProdutoService produtoService;
    private ClienteService clienteService;
    private PedidoService pedidoService;
    
    private void cadastrarProduto() {
        String nome = readString("Digite o nome do produto:");
        double valorUnitario = readDouble("Digite o valor unitário do produto: ");
        int quantidade = readInt("Digite a quantidade do produto em estoque: ");

        Produto produto = new Produto(0, nome, valorUnitario, quantidade);
        this.produtoService.cadastrarProduto(produto);
    }

    public void cadastrarCliente() {
        String cpf = readString("Digite o cpf do cliente:");
        String nome = readString("Digite o nome do cliente:");
        String endereco = readString("Digite o endereço do cliente:");
        String telefone = readString("Digite o telefone do cliente:");

        Cliente cliente = new Cliente(cpf, nome, endereco, telefone);
        this.clienteService.cadastrarCliente(cliente);
    }
    
    public void buscarProdutoPorId() {
        int id = readInt("Digite o id do produto que deseja buscar:");
        Produto produto = this.produtoService.buscarProdutoPorId(id);
        if (produto != null) {
            System.out.println(produto);
        }
    }

    public void listarProdutos() {
        this.produtoService.listarProdutos().forEach(System.out::println);
    }

    public void editarPedido(Produto produto, ItemPedido item, List<ItemPedido> itens) {
        if (produto.getQuantidade() == 0) {
            itens.remove(item);
            System.out.println("Removendo" + produto.getNome() + "do pedido...");
            return;
        }

        int quantidade = readInt("Digite a quantidade de " + produto.getNome() + " que deseja comprar: ");
        item.setQuantidade(quantidade);
    }

    public void efetuarVenda() {
        String cpfCliente = readString("Digite o cpf do cliente:");
        String cpfFuncionario = readString("Digite o cpf do funcionário:");
        listarProdutos();
        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        int idProduto;
        do {
            int quantidade = readInt("Digite a quantidade dso produto desejado:");
            idProduto = readInt("Digite o id do produto que deseja comprar (Digite -1 caso não queira mais nenhum):");
            if(idProduto == -1) {
                break;
            }
            itens.add(new ItemPedido(idProduto, 0, quantidade, 0));
        } while(idProduto != -1);

        System.out.println("Verifique o Pedido:");
        double valorTotal = 0;
        for (ItemPedido item : itens) {
            System.out.print(item.getIdPedido() + " - ");
            Produto produto = this.produtoService.buscarProdutoPorId(item.getIdProduto());
            if(produto == null) {
                System.out.println("Produto não encontrado!");
                itens.remove(item);
                continue;
            }

            if (produto.getQuantidade() == 0 || produto.getQuantidade() < item.getQuantidade()) {
                System.out.println("Produto " + produto.getNome() + " sem estoque!");
                itens.remove(item);
                continue;
            }

            System.out.println(produto.getNome() + " - " + item.getQuantidade() + " - " + produto.getValorUnidade());
            item.setValor(produto.getValorUnidade() * item.getQuantidade());
            valorTotal += item.getValor();
        }

        Pedido pedido = new Pedido(0, cpfFuncionario, cpfCliente, valorTotal, itens);

        this.pedidoService.efetuarVenda(pedido);
    }

    public void listarVendas() {
        this.pedidoService.exibirVendas().forEach(System.out::println);
    }

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
        this.produtoService = new ProdutoService(new ProdutoDao());
        this.clienteService = new ClienteService(new ClienteDao());
        this.pedidoService = new PedidoService(new PedidoDao());

        this.scanner = new Scanner(System.in);
        int opt;

        do {
            System.out.println("---------------------------------");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Buscar Produto(por id)");
            System.out.println("4. Listar os produtos disponíveis");
            System.out.println("5. Efetuar venda");
            System.out.println("6. Listar vendas");
            System.out.println("0. Sair");

            opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1: cadastrarProduto();
                    break;
                case 2: cadastrarCliente();
                    break;
                case 3: buscarProdutoPorId();
                    break;
                case 4: listarProdutos();
                    break;
                case 5: efetuarVenda();
                    break;
                case 6: listarVendas();
                    break;
                case 0: System.out.println("Saindo...");
                    break;
                default: System.out.println("Opção inválida, tente novamente!");
            }
        } while(opt != 0);
        this.scanner.close();
    }
}
