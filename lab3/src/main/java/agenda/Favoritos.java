package agenda;

/**
 * Classe que tratará de cuidar dos contatos favoritados pelo usuário ao longo do sistema.
 * 
 * @author adnanbezerra
 */
public class Favoritos {

    /**
     * Atributo que contém o tamanho da lista de favoritos
     */
    private int TAMANHO_LISTA = 10;

    /** 
     * Array do tipo Contato que conterá quais são os contatos listados como favoritos
     */
    private Contato[] listaFavoritos;

    /**
     * Construtor da classe Favoritos, que basicamente inicializa o array listaFavoritos ao ser chamado.
     */
    public Favoritos(){
        listaFavoritos = new Contato[TAMANHO_LISTA];
    }
    
    /**
     * Método que serve para inserir um contato na lista de favoritos.
     * 
     * @param indice o índice em que o contato ficará favoritado
     * @param bixinho o contato que será favoritado
     */
    public void setFavorito(int indice, Contato bixinho){
        if(this.listaFavoritos[indice-1] != null){
            this.listaFavoritos[indice-1] = null;
        }
        this.listaFavoritos[indice - 1] = bixinho;
    }

    /**
	 * Acessa a lista de favoritos mantida.
	 * @return O array de favoritos.
	 */
	public Contato[] getFavoritos() {
		return this.listaFavoritos.clone();
	}

    /**
     * Boolean que trata de verificar se um determinado contato já está inserido na lista de favoritos ou não.
     */
    private boolean ehFavorito = false;

    /**
     * Método que testa se o contato a ser adicionado já está na lista dos favoritos ou não.
     * @param bixinho o contato a ser comparado.
     * @return o boolean ehFavorito, que dita se o contato "bixinho" já está na lista de favoritos ou não.
     */
    public boolean ehFavorito(Contato bixinho){
        for(int i = 0; i < 10; i++){
            if(listaFavoritos[i] != null){
                if(bixinho.equals(listaFavoritos[i])){
                    ehFavorito = true;
                    break;
                } else {
                    ehFavorito = false;
                }
            }
        }
        return ehFavorito;
    }

}
