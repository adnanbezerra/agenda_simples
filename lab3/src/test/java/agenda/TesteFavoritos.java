package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TesteFavoritos{

    private Agenda minhaAgenda;
    private Contato bixinho;
    private Favoritos meusFavoritos;

    @BeforeEach
    void minhaAgenda(){
        this.minhaAgenda = new Agenda();
        this.meusFavoritos = new Favoritos();
        bixinho = new Contato("Matheus", "Gaudêncio", "(83) 99999-0000", "(83) 99999-0001", "(83) 99999-0002");
        minhaAgenda.cadastraContato(1, bixinho);
    }

    /**
     * Testa se é possível favoritar um contato em alguma posição qualquer.
     */
    @Test
    void testeComum(){
        try{
            meusFavoritos.setFavorito(5, bixinho);
        }
        catch(Exception e){
            fail("Tá dando ruim");
        }
    }
    
    /**
     * Testa se é possível salvar um contato numa posição que já tenha outro contato no lugar.
     */
    @Test
    void testeSobrescrever(){
        meusFavoritos.setFavorito(1, bixinho);
        Contato bixao = new Contato("Zé", "das Tapioca", "40028922", "+55 083 9", "4");
        try {
            meusFavoritos.setFavorito(1, bixao);
        } catch(Exception e) {
            fail("Deu bom não, chefe");
        }
    }

    /**
     * Testa se é possível cadastrar um contato na posição 0 ou 11, que estão abaixo e acima dos limites da classe, respectivamente.
     */
    @Test
    void testeAcimaEAbaixo(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            meusFavoritos.setFavorito(0, bixinho);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            meusFavoritos.setFavorito(11, bixinho);
        });
    }

    /**
     * Testa se um contato é favorito. O resultado esperado é "true".
     */
    @Test
    void testeÉFavorito(){
        meusFavoritos.setFavorito(1, bixinho);
        Contato contato1 = new Contato("Matheus", "Gaudêncio", "123", "321", "5672");
        assertTrue(meusFavoritos.ehFavorito(contato1));
    }

    /**
     * Testa se um contato é favorito. O resultado esperado é "false".
     */
    @Test
    void testeNãoÉFavorito(){
        assertFalse(meusFavoritos.ehFavorito(bixinho));
    }

    /**
     * Testa se é possível substituir um contato por outro na lista de favoritos e se o contato antigo deixou de ser um favorito
     * enquanto que o novo se tornou um.
     */
    @Test
    void testeExFavorito(){
        meusFavoritos.setFavorito(1, bixinho);
        assertTrue(meusFavoritos.ehFavorito(bixinho));
        Contato bixote = new Contato("Varyan", "Wrynn", "(83) 9", "(83) 8", "(83, 7");
        meusFavoritos.setFavorito(1, bixote);
        assertTrue(meusFavoritos.ehFavorito(bixote));
        assertFalse(meusFavoritos.ehFavorito(bixinho));
    }
}