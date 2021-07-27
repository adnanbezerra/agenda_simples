package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TesteAgenda{

    private Agenda minhaAgenda;
    private Contato bixinho;
    private Favoritos meusFavoritos;

    @BeforeEach
    void minhaAgenda(){
        this.minhaAgenda = new Agenda();
        this.meusFavoritos = new Favoritos();
        bixinho = new Contato("Matheus", "Gaudêncio", "(83) 99999-0000", "(83) 99999-0001", "(83) 99999-0002");
    }

    /**
     * Teste simples de se um contato qualquer pode ser registrado em uma posição qualquer da agenda.
     */
    @Test
    void testeCadastroSimples(){
        Contato contato1 = new Contato("Vice", "Treco", "4613", "58241", "1845");
        try{
            minhaAgenda.cadastraContato(50, contato1);
        }
        catch(Exception e){
            fail("Opa, amigão! Tá errado, ein?");
        }
    }

    /**
     * Testa se um contato pode ser cadastrado na posição limite, isto é, 100.
     */
    @Test
    void testeNoLimite(){
        try{
            minhaAgenda.cadastraContato(100, bixinho);
        }
        catch(Exception e){
            fail("Isso tá esquisito..");
        }
    }

    /**
     * Testa se se pode adicionar um contato que não tenha número adicional
     */
    @Test
    void testeSemAdicional(){
        Contato bixao = new Contato("Matheus", "Gaudêncio", "(83) 99999-0000", "(83) 99999-0001", "");
        try{
            minhaAgenda.cadastraContato(1, bixao);
        }
        catch(Exception e){
            fail("Sinto muito, Matheus sem adicional");
        }
    }

    /**
     * Testa cadastrar o contato Pedro Silva numa posição já utilizada.
     */
    @Test
    void testePosicaoRepetida(){
        minhaAgenda.cadastraContato(1, bixinho);
        Contato contato2 = new Contato("Pedro", "Silva", "(84) 98888-1111", "(84) 98888-1112", "(84) 98888-1113");
        try {
            minhaAgenda.cadastraContato(1, contato2);
        }
        catch(Exception e){
            fail("Oops! Deu errado!");
        }
    }

    /**
     * Testa se o contato pode ser registrado em uma posição acima de 100 ou abaixo de 0.
     */
    @Test
    void testeCadastroInvalido(){
        Contato contato1 = new Contato("Mini", "boi", "123", "333", "456");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            minhaAgenda.cadastraContato(101, contato1);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            minhaAgenda.cadastraContato(0, contato1);
        });
    }
    
    /**
     * Testa se o método ehIgual para testar se um contato a ser registrado na agenda 
     * já existe está funcionando corretamente.
     */
    @Test
    void testeIgualdade(){
        minhaAgenda.cadastraContato(1, bixinho);
        Contato contato2 = new Contato("Matheus", "Gaudêncio", "1289", "102347", "1234");
        assertTrue(minhaAgenda.ehIgual(contato2));
    }

    /**
     * Testa se a saída de contatos através do método getContato funciona corretamente.
     */
    @Test
    void testeToStringComAdicional(){
        minhaAgenda.cadastraContato(1, bixinho);
        String getContato = minhaAgenda.getContato(1);
        String resultadoEsperado = "Matheus Gaudêncio\n(83) 99999-0000 (Prioritário)\n(83) 99999-0001 (WhatsApp)\n(83) 99999-0002 (Adicional)";
        assertEquals(resultadoEsperado, getContato);
    }

    /**
     * Testa a saída de contatos para um número que não tenha telefone adicional.
     */
    @Test
    void testeToStringSemAdicional(){
        Contato contato3 = new Contato("Oxe", "Mainha", "123", "321", "");
        minhaAgenda.cadastraContato(2, contato3);
        String getContato = minhaAgenda.getContato(2);
        String resultadoEsperado = "Oxe Mainha\n123 (Prioritário)\n321 (WhatsApp)";
        assertEquals(resultadoEsperado, getContato);
    }

    /**
     * Testa o que ocorre se tentarmos chamar o toString de algum contato que esteja fora dos limites do array,
     * tanto no superior quanto no inferior.
     */
    @Test
    void testeToStringPosicaoInvalida(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            minhaAgenda.getContato(101);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            minhaAgenda.getContato(0);
        });
    }

    /**
     * Testa o que acontece se tentar buscar informações de um contato em uma posição vazia.
     */
    @Test
    void testeListarVazio(){
        assertThrows(NullPointerException.class, () -> {
            minhaAgenda.getContato(100);
        });
    }

    /**
     * Testa o método toString para quando um contato está favoritado. Note que, como uma parte da lógica é feita
     * dentro da classe Agenda e outra dentro da classe Contato, que se usa do resultado que vem da classe Favoritos,
     * foi preciso fazer adaptações para que o teste desse certo.
     */
    @Test
    void testeFavoritado(){
        minhaAgenda.cadastraContato(1, bixinho);
        meusFavoritos.setFavorito(1, bixinho);
        boolean ehFavorito = meusFavoritos.ehFavorito(bixinho);
        bixinho.setEhFavorito(ehFavorito);
        String toString = minhaAgenda.getContato(1);
        String resultadoEsperado = "❤️ Matheus Gaudêncio\n(83) 99999-0000 (Prioritário)\n(83) 99999-0001 (WhatsApp)\n(83) 99999-0002 (Adicional)";
        assertEquals(resultadoEsperado, toString);
    }
}