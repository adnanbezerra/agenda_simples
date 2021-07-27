package agenda;

/**
 * Uma classe que fica responsável por guardar todos os contatos e registra-los
 * 
 * @author adnanbezerra
 */
public class Contato {
    
    /**
     * String designada para registrar qual será o nome do contato.
     */
    private String nome;

    /**
     * String designada para registrar o sobrenome do contato.
     */
    private String sobrenome;

    /**
     * String designada para registrar o telefone prioritário do contato.
     */
    private String prioritario;

    /**
     * String designada para registrar o Whatsapp do contato.
     */
    private String zapper;

    /**
     * String designada para registrar o número adicional do contato.
     */
    private String adicional;

    /**
     * Construtor da classe Contato a partir do nome, sobrenome, telefone prioritário, telefone com WhatsApp e número adicional.
     * 
     * @param nome o primeiro nome do contato
     * @param sobrenome o sobrenome do contato
     * @param prioritario o telefone prioritário do contato
     * @param zapper o telefone de WhatsApp do contato
     * @param adicional o telefone adicional do contato
     */
    public Contato(String nome, String sobrenome, String prioritario, String zapper, String adicional){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.prioritario = prioritario;
        this.zapper = zapper;
        this.adicional = adicional;
    }

    /**
     * Método getter para o sobrenome da classe Contato.
     * 
     * @return o sobrenome do contato
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * Método getter para o nome da classe Contato
     * 
     * @return o nome do contato
     */
    public String getNome() {
        return nome;
    }

    /** 
     * Boolean que decide, no método .equals(), se os contatos são iguais ou não.
     */
    private boolean ehIgual = false;

    /**
     * Método que testa se um contato registrado na agenda é igual a outro que está entrando agora, baseado em seu nome e sobrenome.
     * 
     * @param novoContato é o contato o qual será comparado com o contato com o qual o método foi invocado.
     * @return o boolean ehIgual com o resultado sobre se os dois contatos são iguais ou não
     */
    public boolean equals(Contato novoContato){
        if (this.nome.equals(novoContato.getNome()) && this.sobrenome.equals(novoContato.getSobrenome())){
            ehIgual = true;
        } else {
            ehIgual = false;
        }
        return ehIgual;
    }

    /**
     * String que irá armazenar o tipo toString.
     */
    private String saidaContato;

    /**
     * Boolean que irá testar se o contato a ser exibido está na lista dos favoritos.
     */
    private boolean ehFavorito;

    /**
     * Método do tipo setter para receber do código a informação de se um determinado contato é favorito ou não.
     * @param ehFavorito
     */
    public void setEhFavorito(boolean ehFavorito) {
        this.ehFavorito = ehFavorito;
    }

    /**
     * Método privado que é responsável por determinar como funcionará a String de saída de informações do método toString
     * @return a String saidaContato a ser usada no método toString
     */
    private String saidaContato(){
         
        if (!this.adicional.trim().equals("\"\"") && !this.adicional.equals("")){
            if(ehFavorito){
                saidaContato = ("❤️ " + nome + " " + sobrenome + "\n" + prioritario + " (Prioritário)" + "\n" + zapper + " (WhatsApp)" + "\n" + adicional + " (Adicional)");
            } else {
                saidaContato = (nome + " " + sobrenome + "\n" + prioritario + " (Prioritário)" + "\n" + zapper + " (WhatsApp)" + "\n" + adicional + " (Adicional)");
            }
        } else {
            if(ehFavorito){
                saidaContato = ("❤️ " + nome + " " + sobrenome + "\n" + prioritario + " (Prioritário)" + "\n" + zapper + " (WhatsApp)");
            } else {
                saidaContato = (nome + " " + sobrenome + "\n" + prioritario + " (Prioritário)" + "\n" + zapper + " (WhatsApp)");  
            }
        }
        return saidaContato;
    }

    /**
     * Override do método toString para que ele sirva para coletar algum contato.
     * 
     * @return o toString, que representará algum contato
     */
    @Override
    public String toString(){
        saidaContato();
        return saidaContato;
    }
}
