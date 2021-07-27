package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazarenoandrade, adnanbezerra
 *
 */
public class Agenda {
	
	/**
	 * Define o tamanho fixo da agenda, que é de 100 contatos.
	 */
	private static final int TAMANHO_AGENDA = 100;
	
	/** 
	 * Array que irá conter todos os contatos da Agenda.
	 */
	private Contato[] contatos;

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 * @return O array de contatos.
	 */
	public Contato[] getContatos() {
		return this.contatos.clone();
	}

	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public String getContato(int posicao) {
		return contatos[posicao - 1].toString();
	}

	/**
	 * Acessa um contato específico para que seja favoritado.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public Contato contatoAFavoritar(int posicao) {
		return contatos[posicao - 1];
	}

	/**
	 * Tipo boolean que detecta se o contato que entra é igual a algum presente na agenda.
	 */
	private boolean ehIgual;

	/**
	 * Testa se um contato a ser cadastrado já está registrado na agenda ou não.
	 * @param posicao O número o qual o contato é salvo
	 * @param contato O contato a ser testado
	 * @return o boolean ehIgual, que diz se um contato já foi registrado ou não
	 */
	public boolean ehIgual(Contato contato){
		for (int i = 0; i < 100; i++){
			if (contatos[i] != null){
				if (contato.equals(contatos[i])){
					ehIgual = true;
					break;
				}
			}
		}
		return ehIgual;
	}


	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 */
	public void cadastraContato(int posicao, Contato contato) {
		this.contatos[posicao - 1] = contato;
	}

}