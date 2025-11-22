package exe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import entidades.Carta;
import entidades.Dlc;
import entidades.Jogo;
import entidades.Produto;

public class Programa {

	public static void main(String[] args) {

		ArrayList<Produto> produtos = new ArrayList<>(); // Lista para guardar produtos
		ArrayList<Produto> historico = new ArrayList<>();// Lista para guardar vendas
		Scanner scanner = new Scanner(System.in); // Scanner para ler o teclado
		produtos.add(new Jogo("Hollow Knight", 59.90, "Jogo", "Team Cherry", "Metroidvania")); // cadastra 7 objetos
		produtos.add(new Jogo("Elden Ring", 39.50, "Jogo", "FromSoftware", "RPG"));
		produtos.add(new Jogo("Five nights at freddys", 49.99, "Jogo", "Scott Cawthon", "Horror"));
		produtos.add(new Carta("Dark Knight Detective", 0.22, "Carta", "Epico"));
		produtos.add(new Carta("It Was You!", 0.15, "Carta", "Lendario"));
		produtos.add(new Dlc("Bloodsaga", 9.99, "DLC", "Elden Ring"));
		produtos.add(new Dlc("Pacote de skins", 4.99, "DLC", "Fifa")); // produto agr tem 7 referencias para objeto
		int opcao = 0; // Variavel para controlar o menu
		while (opcao != 5) { // loop do menu inicial
			System.out.println("\nMENU:");
			System.out.println("1 - Cadastrar (Jogo, DLC ou Carta)");
			System.out.println("2 - Listar (tabela simples)");
			System.out.println("3 - Buscar por nome / remover (Produto vendido) ");
			System.out.println("4 - Consultar lucro");
			System.out.println("5 - Sair");
			System.out.print("Escolha: ");
			opcao = scanner.nextInt(); // ler linha do teclado como inteiro
			if (opcao == 1) { // Cadastrar (Jogo, DLC ou Carta)
				System.out.println("Cadastrar: 1-Jogo  2-DLC 3- Carta");
				scanner.nextLine();
				String tipo = scanner.nextLine().trim(); // lê escolha do tipo
				if (tipo.equals("1")) { // Ler atributos de jogo
					System.out.print("Nome: ");
					String nome = scanner.nextLine();
					System.out.print("Valor (use ponto): ");
					double valor = scanner.nextDouble();
					String tipoProduto = "Jogo";
					System.out.print("Desenvolvedor: ");
					scanner.nextLine();
					String dev = scanner.nextLine();
					System.out.print("Genero: ");
					String genero = scanner.nextLine();
					produtos.add(new Jogo(nome, valor, tipoProduto, dev, genero)); // cria o obj jogo e add ele a lista
					System.out.println("Jogo cadastrado. [Aperte ENTER para voltar]");
				} else if (tipo.equals("2")) {
					System.out.print("Nome: ");
					String nome = scanner.nextLine();
					System.out.print("Valor (use ponto): ");
					double valor = scanner.nextDouble();
					String tipoProduto = "DLC";
					System.out.print("Jogo base: ");
					String jogoBase = scanner.nextLine();
					produtos.add(new Dlc(nome, valor, tipoProduto, jogoBase)); // cria o obj dlc e add a lista
					System.out.println("DLC cadastrada. [Aperte ENTER para voltar]");
				} else if (tipo.equals("3")) {
					System.out.println("Nome: ");
					String nome = scanner.nextLine();
					System.out.println("Valor (use ponto): ");
					double valor = scanner.nextDouble();
					System.out.println("Raridade da carta: ");
					String raridade = scanner.next();
					produtos.add(new Carta(nome, valor, "Carta", raridade));
					System.out.println("Carta cadastrada.");
				} else { // opção invalida para cadastro
					System.out.println("Opção inválida para cadastro.");
				}
				scanner.nextLine();
			} else if (opcao == 2) { // Listar (tabela simples)
				Collections.sort(produtos, Comparator.comparing(Produto::getNome)); // Ordena a tabela pelo nome
				System.out.printf("%-3s %-25s %-10s %-10s\n", "ID", "Nome", "Tipo", "Valor"); // Lista os produtos em
				System.out.println("-------------------------------------------"); // uma tabela com 3 atributos
				for (int i = 0; i < produtos.size(); i++) { // Percorre o array e imprime cada elemento
					Produto p = produtos.get(i); // produtos.get(i) retorna o elemento de indice i
					System.out.printf("%-3d %-25s %-10s %.2f \n", i, p.getNome(), p.getTipo(), p.getValor());
				}
			} else if (opcao == 3) { // Buscar por nome / remover (Vender)
				System.out.print("Digite o nome do produto para buscar: ");
				scanner.nextLine();
				String nomeBusca = scanner.nextLine();
				boolean encontrado = false; // flag para dizer se algum produto foi encontrado
				for (int i = 0; i < produtos.size(); i++) {
					Produto p = produtos.get(i); // p é um roduto do array produtos
					if (p.getNome().equalsIgnoreCase(nomeBusca)) {// equalsignore compara duas strings ignorando
																	// maiusculas ou minusculas
						encontrado = true;// se encontrou pelo menos um
						System.out.println("\n--- Produto encontrado ---");
						if (p instanceof Jogo) {// se produto for jogo mostrar os 5 atributos de jogo
							Jogo j = (Jogo) p; // Downcasting de produto para jogo
							System.out.println("Nome: " + j.getNome());
							System.out.println("Valor: " + j.getValor());
							System.out.println("Tipo: " + j.getTipo());
							System.out.println("Desenvolvedor: " + j.getDesenvolvedor());
							System.out.println("Genero: " + j.getGenero());

							System.out.println("\nMENU:"); // Menu de opcoes para o produto buscado
							System.out.println("1 - Modificar");
							System.out.println("2 - Remover");
							System.out.println("3 - Sair");
							System.out.print("Escolha: ");
							int escolha = scanner.nextInt();
							scanner.nextLine();
							switch (escolha) {
							case 1: // Modificar
								System.out.print("Nome: "); // Ler atributos novos do jogo
								String nome = scanner.nextLine();
								System.out.print("Valor (use ponto): ");
								double valor = scanner.nextDouble();
								System.out.print("Desenvolvedor: ");
								scanner.nextLine();
								String dev = scanner.nextLine();
								System.out.print("Genero: ");
								String genero = scanner.nextLine();
								j.setNome(nome); // Subistitui os atributos lidos no objeto
								j.setTipo("jogo");
								j.setValor(valor);
								j.setGenero(genero);
								j.setDesenvolvedor(dev);
								System.out.println("Jogo modificado.");
								break;
							case 2: // Remover
								System.out.println("Tem certeza que deseja prosseguir com a remoção? S/N");
								String c = scanner.nextLine().trim(); // Mensagem de confirmacao ao remover um objeto
								if (c.equalsIgnoreCase("S")) {
									historico.add(p); // Adiciona produto removido ao array historico
									produtos.remove(i);
									System.out.println("Produto removido");
									i--; // mantem o for dentro dos limites do array produtos
								} else {
									System.out.println("Remoção Cancelada");
								}
								break;
							case 3: // Sair
								break;
							default:
								System.out.println("Escolha invalida");
							}
						} else if (p instanceof Dlc) { // se produto for jogo mostrar os 4 atributos da dlc
							Dlc d = (Dlc) p; // Downcasting de produto para dlc
							System.out.println("Nome: " + d.getNome());
							System.out.println("Valor: " + d.getValor());
							System.out.println("Tipo: " + d.getTipo());
							System.out.println("Jogo base: " + d.getJogoBase());

							System.out.println("\nMENU:"); // Menu de opcoes para o produto buscado
							System.out.println("1 - Modificar");
							System.out.println("2 - Remover");
							System.out.println("3 - Sair");
							System.out.print("Escolha: ");
							int escolha = scanner.nextInt();
							scanner.nextLine();
							switch (escolha) {
							case 1: // Modificar
								System.out.print("Nome: "); // Ler atributos novos da dlc
								String nome = scanner.nextLine();
								System.out.print("Valor (use ponto): ");
								double valor = scanner.nextDouble();
								System.out.print("JogoBase: ");
								String JogoBase = scanner.nextLine();
								d.setNome(nome); // Subistitui os atributos lidos no objeto
								d.setValor(valor);
								d.setTipo("DLC");
								d.setJogoBase(JogoBase);
								System.out.println("DLC modificada.");
								break;
							case 2:// Remover
								System.out.println("Tem certeza que deseja prosseguir com a remoção? S/N");
								String c = scanner.nextLine().trim(); // Mensagem de confirmacao ao remover um objeto
								if (c.equalsIgnoreCase("S")) {
									historico.add(p);// Adiciona produto removido ao array historico
									produtos.remove(i);
									System.out.println("Produto removidosdosdos");
									i--;// mantem o for dentro dos limites do array produtos
								} else {
									System.out.println("Remoção Cancelada");
								}
								break;
							case 3: // Sair
								break;
							default:
								System.out.println("Escolha invalida");
							}
						} else if (p instanceof Carta) {// se produto for jogo mostrar os 4 atributos da carta
							Carta c = (Carta) p;// Downcasting de produto para carta
							System.out.println("Nome: " + c.getNome());
							System.out.println("Valor: " + c.getValor());
							System.out.println("Tipo: " + c.getTipo());
							System.out.println("Raridade: " + c.getRaridade());

							System.out.println("\nMENU:");// Menu de opcoes para o produto buscado
							System.out.println("1 - Modificar");
							System.out.println("2 - Remover");
							System.out.println("3 - Sair");
							System.out.print("Escolha: ");
							int escolha = scanner.nextInt();
							scanner.nextLine();
							switch (escolha) {
							case 1:// Modificar
								System.out.print("Nome: ");// Ler atributos novos da carta
								String nome = scanner.nextLine();
								System.out.print("Valor (use ponto): ");
								double valor = scanner.nextDouble();
								System.out.print("Quantidade total: ");
								String raridade = scanner.next();
								scanner.nextLine();
								c.setNome(nome);// Subistitui os atributos lidos no objeto
								c.setValor(valor);
								c.setTipo("Carta");
								c.setRaridade(raridade);
								System.out.println("Carta modificada.");
								break;
							case 2:// Remover
								System.out.println("Tem certeza que deseja prosseguir com a remoção? S/N");
								String d = scanner.nextLine().trim();// Mensagem de confirmacao ao remover um objeto
								if (d.equalsIgnoreCase("S")) {
									historico.add(p);// Adiciona produto removido ao array historico
									produtos.remove(i);
									System.out.println("Produto removido");
									i--;// mantem o for dentro dos limites do array produtos
								} else {
									System.out.println("Remoção Cancelada");
								}
								break;
							case 3:// Sair
								break;
							default:
								System.out.println("Escolha invalida");
							}
						}
					}
				}
				if (!encontrado) { // se o produto buscado n for encontrado
					System.out.println("Nenhum produto encontrado com esse nome.");
				}
			} else if (opcao == 4) { // Consultar lucro
				double faturamento = 0.0; //Somatorio dos valores dos produtos do array historico
				for (int i = 0; i < historico.size(); i++) {// Loop constroi a variavel faturamento
					faturamento += historico.get(i).getValor();
				}
				System.out.println();
				System.out.println("FATURAMENTO ");
				System.out.println("-------------------------------------------");
				System.out.printf("Total: R$%.2f %n", faturamento); //Lista o faturamento
				System.out.println("-------------------------------------------");
				System.out.println("LISTA DE PRODUTOS VENDIDOS: ");
				for (int i = 0; i < historico.size(); i++) { //Lista os produtos do historico
					System.out.println(historico.get(i).getNome());
				}
			} else if (opcao == 5) { // 5 = sair programa
				System.out.println("Encerrando o programa. Obrigado.");
			} else { // Se digitar um número diferente de 1,2,3,4,5
				System.out.println("Opção inválida. Digite 1, 2, 3, 4 ou 5.");
			}
		}
		scanner.close();
	}
}
