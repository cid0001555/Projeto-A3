package exe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Carta;
import entidades.Dlc;
import entidades.Jogo;
import entidades.Produto;

public class Programa {

	private final List<Produto> catalogoProdutos;
	private final Scanner entrada;

	public Programa() {
		this.catalogoProdutos = new ArrayList<>();
		this.entrada = new Scanner(System.in);
		inicializarDadosPadrao();
	}

	public static void main(String[] args) {
		Programa programa = new Programa();
		programa.executarMenuPrincipal();
	}

	private void executarMenuPrincipal() {
		boolean continuar = true;
		while (continuar) {
			exibirMenu();
			String opcao = entrada.nextLine().trim();
			switch (opcao) {
			case "1":
				cadastrarProdutoViaMenu();
				break;
			case "2":
				ListarProdutosTabela();
				break;
			case "3":
				buscarProdutoNome();
				break;
			case "4":
				removerProduto();
				break;
			case "5":
				System.out.println("Saindo do programa. :3");
				continuar = false;
				break;
			default:
				System.out.println("Opção inválida. Escolha 1 ,2 ,3, 4 ou 5.");
			}
			System.out.println();
		}
		entrada.close();
	}

	private void exibirMenu() {
		System.out.println("==== Menu ====");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar Produto (tabela)");
		System.out.println("3 - Buscar produto por nome");
		System.out.println("4 - Remover produto");
		System.out.println("5 - Sair");
		System.out.print("Escolher uma opção: ");
	}

	private void cadastrarProdutoViaMenu() {
		System.out.println("Escolha o tipo de produto para cadastrar:");
		System.out.println("1 - Jogo");
		System.out.println("2 - Dlc");
		System.out.println("3 - Carta");
		System.out.print("Opção: ");
		String tipo = entrada.nextLine().trim();

		try {
			System.out.print("Nome: ");
			String nome = entrada.nextLine().trim();

			System.out.print("Valor: ");
			double valor = Double.parseDouble(entrada.nextLine().trim());

			System.out.print("Tipo: ");
			String categoria = entrada.nextLine().trim();

			switch (tipo) {
			case "1":
				System.out.print("Dev: ");
				String desenvolvedor = entrada.nextLine().trim();
				System.out.print("Genero: ");
				String genero = entrada.nextLine().trim();
				Jogo novoJogo = new Jogo(nome, valor, categoria, desenvolvedor, genero);
				catalogoProdutos.add(novoJogo);
				System.out.println("Jogo cadastrado com sucesso.");
				break;
			case "2":
				System.out.print("Jogo base da DLC: ");
				String jogoBase = entrada.nextLine().trim();
				Dlc novaDlc = new Dlc(nome, valor, categoria, jogoBase);
				catalogoProdutos.add(novaDlc);
				System.out.println("DLC cadastrada com sucesso");
				break;
			case "3":
				System.out.println("Quantidade total da coleção: ");
				int quantidadeTotal = Integer.parseInt(entrada.nextLine().trim());
				System.out.print("Quantidade atual: ");
				int quantidade = Integer.parseInt(entrada.nextLine().trim());
				Carta novaCarta = new Carta(nome, valor, categoria, quantidadeTotal, quantidade);
				catalogoProdutos.add(novaCarta);
				System.out.println("Carta cadastrada com sucesso.");
				break;
			default:
				System.out.println("Tipo inválido. Cadastro cancelado.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Entrada numérica invalida. Cadastro cancelado.");
		} catch (Exception e) {
			System.out.println("Erro inesperado durante o cadastro: " + e.getMessage());
		}
	}

	private void ListarProdutosTabela() {
		if (catalogoProdutos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado.");
			return;
		}

		imprimirCabecalhoTabela();

		for (Produto p : catalogoProdutos) {
			String nome = p.getNome();
			String valorStr = String.format("R$ %.2f", p.getValor());
			String tipo = p.getTipo();
			String especifico = obterDescricaoRapidaTipo(p);
			System.out.printf("%-25s %-12s %-15s %20s%n", nome, valorStr, tipo, especifico);
		}
	}

	private void imprimirCabecalhoTabela() {
		System.out.printf("%-25s %-12s %-15s %-20s%n", "Nome", "Valor", "Tipo", "Especifico");
		System.out.println("-------------------------------------------------------");
	}

	private String obterDescricaoRapidaTipo(Produto produto) {
		if (produto instanceof Jogo) {
			Jogo j = (Jogo) produto;
			return "Jogo;Dev:" + j.getDesenvolvedor();
		} else if (produto instanceof Dlc) {
			Dlc d = (Dlc) produto;
			return "DLC;base:" + "(ver detalhes)";
		} else if (produto instanceof Carta) {
			Carta c = (Carta) produto;
			return "Carta;Qtd:" + c.getQuantidade() + "/" + c.getQuantidadeTotal();
		} else {
			return "Produto generico";
		}
	}

	private void buscarProdutoNome() {
		System.out.print("Informe o nome do produto para busca (texto): ");
		String nomeBusca = entrada.nextLine().trim();

		Produto encontrado = null;
		for (Produto p : catalogoProdutos) {
			if (p.getNome().equalsIgnoreCase(nomeBusca)) {
				encontrado = p;
				break;
			}
		}
		if (encontrado == null) {
			System.out.println("Produto não encontrado com nome: " + nomeBusca);
			return;
		}
		exibirProdutoDetalhado(encontrado);
	}

	private void exibirProdutoDetalhado(Produto produto) {
	    System.out.println("=== Detalhes Do Produto ===");

	    if (produto instanceof Jogo) {
	        Jogo jogo = (Jogo) produto;
	        System.out.println("Nome: " + jogo.getNome());
	        System.out.println("Valor: " + jogo.getValor());
	        System.out.println("tipo do produto: " + jogo.getTipo());
	        System.out.println("desenvolvedor: " + jogo.getDesenvolvedor());
	        System.out.println("genero: " + jogo.getGenero());
	    } else if (produto instanceof Dlc) {
	        Dlc dlc = (Dlc) produto;
	        System.out.println("Nome: " + dlc.getNome());
	        System.out.println("Valor: " + dlc.getValor());
	        System.out.println("tipo do produto: " + dlc.getTipo());
	        System.out.println("Jogo base: " + dlc.getJogoBase());
	    } else if (produto instanceof Carta) {
	        Carta carta = (Carta) produto;
	        System.out.println("Nome: " + carta.getNome());
	        System.out.println("Valor: " + carta.getValor());
	        System.out.println("tipo do produto: " + carta.getTipo());
	        System.out.println("Quantidade Total : " + carta.getQuantidadeTotal());
	        System.out.println("Quantidade : " + carta.getQuantidade());
	        Carta.Broche(carta);
	    } else {
	        System.out.println("Tipo de produto não reconhecido. Exibindo apenas informações básicas.");
	        System.out.println("Nome: " + produto.getNome());
	        System.out.println("Valor: " + produto.getValor());
	    }
	}


	private void inicializarDadosPadrao() {
		catalogoProdutos.add(new Jogo("Hollow Knight", 79.90, "Rpg", "team cherry", "aventura"));
		catalogoProdutos.add(new Jogo("Dark Souls", 79.90, "Rpg", "FromSoftware", "Souls Like"));

		catalogoProdutos.add(new Dlc("Hollow Knight - Dlc 1", 30.00, "DLC", "Hollow Knight"));
		catalogoProdutos.add(new Dlc("Dark Souls - Dlc 1", 15.00, "DLC", "Dark Souls"));
		catalogoProdutos.add(new Dlc("Hollow Knight - Dlc 2", 70.00, "DLC", "Hollow Knight"));

		catalogoProdutos.add(new Carta("CartaHollow", 12.00, "Colectable", 4, 4));
		catalogoProdutos.add(new Carta("CartaDark", 8.00, "Colectable", 5, 2));
	}
	
	private void removerProduto() {
		if(catalogoProdutos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado.");
			return;
		}
		
		System.out.println("Informe o nome do produto a remover: ");
		String RemovedorNome = entrada.nextLine().trim();
		
		Produto encontrado = null;
		int indice = -1;
		for (int i = 0; i < catalogoProdutos.size(); i++) {
			Produto p = catalogoProdutos.get(i);
			if (p.getNome().equalsIgnoreCase(RemovedorNome)) {
				encontrado = p;
				indice = i;
				break;
			}
		}
		
		if (encontrado == null) {
			System.out.println("Produto não encontrado com nome: " + RemovedorNome);
			return;
		}
		System.out.println("Produto encontrado:");
		exibirProdutoDetalhado(encontrado);
		
		System.out.print("Confirma remoção deste produto? (s/n): ");
		String confirma = entrada.nextLine().trim().toLowerCase();
		if (confirma.equals("s") || confirma.equals("sim")) {
			catalogoProdutos.remove(indice);
			System.out.println("produto removido");
		} else {
			System.out.println("remoção cancelada");
		}
	}
}