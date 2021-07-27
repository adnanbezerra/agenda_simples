package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade, adnanbezerra
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();
		Favoritos favorites = new Favoritos();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner, favorites);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.print(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" +
						"(F)avoritos\n" +
						"(A)dicionar Favorito\n" + 
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner, Favoritos favorites) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner, favorites);
			break;
		case "F":
			listaFavoritos(favorites);
			break;
		case "A":
			adicionaFavorito(favorites, scanner, agenda);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/**
	 * Método que é responsabilizar por adicionar um contato à lista de favoritos.
	 * @param favorites A lista de favoritos a ser manipulada
	 * @param scanner Scanner para capturar as entradas do usuário.
	 * @param agenda A agenda a ser manipulada.
	 */
	private static void adicionaFavorito(Favoritos favorites, Scanner scanner, Agenda agenda){
		System.out.print("Contato> ");
		int contato = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Posicao> ");
		int indice = scanner.nextInt();
		scanner.nextLine();
		Contato bixinho = agenda.contatoAFavoritar(contato);
		boolean ehFavorito = favorites.ehFavorito(bixinho);
		if(ehFavorito){
			System.out.println("CONTATO JÁ FAVORITADO!");
		} else{
			favorites.setFavorito(indice, bixinho);
			System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + indice +"!");
		}
	}

	/**
	 * Imprime a lista de contatos favoritados.
	 * 
	 * @param favorites A lista de favoritos sendo manipulada.
	 */
	private static void listaFavoritos(Favoritos favorites){
		Contato[] favoritos = favorites.getFavoritos();
		for (int i = 0; i < 10; i++){
			if(favoritos[i] != null){
				System.out.println(i + 1 + " - " + favoritos[i].getNome() + " " + favoritos[i].getSobrenome());
			}
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		Contato[] contatos = agenda.getContatos();
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println(formataContato(i, contatos[i]));
			}
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner, Favoritos favorites) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		Contato bixinho = agenda.getContatos()[posicao - 1];
		boolean ehFavorito = favorites.ehFavorito(bixinho);
		if(ehFavorito){
			bixinho.setEhFavorito(ehFavorito);
		}
		String contato = agenda.getContato(posicao);
		System.out.println("Dados do contato:\n" + contato);
	}

	/**
	 * Formata um contato para impressão na interface. 
	 * 
	 * @param posicao A posição do contato (que é exibida)/
	 * @param contato O contato a ser impresso.
	 * @return A String formatada.
	 */
	private static String formataContato(int posicao, Contato contato) {
		return posicao + 1 + " - " + contato.getNome() + " " + contato.getSobrenome() ;
	}

	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		if(posicao < 1 || posicao > 100){
			System.out.print("POSIÇÃO INVÁLIDA!");
		} else {
			System.out.print("\nNome> ");
			String nome = scanner.nextLine();
			System.out.print("\nSobrenome> ");
			String sobrenome = scanner.nextLine();
			System.out.print("\nPrioritario> ");
			String prioritario = scanner.nextLine();
			System.out.print("\nWhatsApp> ");
			String zapper = scanner.nextLine();
			System.out.print("\nAdicional> ");
			String adicional = scanner.nextLine();
			Contato bixinho = new Contato(nome, sobrenome, prioritario, zapper, adicional);
			boolean ehIgual = agenda.ehIgual(bixinho);
			if(ehIgual){
				System.out.println("CONTATO JÁ CADASTRADO");
			} else {
				agenda.cadastraContato(posicao, bixinho);
				System.out.println("CADASTRO REALIZADO");
			}
		}
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
