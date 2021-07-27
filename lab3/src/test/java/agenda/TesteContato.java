package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TesteContato{

    private Contato bixinho;

    @BeforeEach
    void minhaAgenda(){
        bixinho = new Contato("Matheus", "Gaudêncio", "123", "321", "568912");
    }

    /**
     * Testa o construtor de maneira generalizada, com todos os dados do contato. Este teste, devido à
     * maneira como é feito, testa também o toString da classe quando o contato tem um número adicional.
     */
    @Test
    void testeConstrutorComum() {
        Contato contato1 = new Contato("José", "da Mata", "(83) 9", "(83) 8", "(83) 7");
        String resultado = contato1.toString();
        String esperado = ("José da Mata\n(83) 9 (Prioritário)\n(83) 8 (WhatsApp)\n(83) 7 (Adicional)");
        assertEquals(esperado, resultado);
    }

    /**
     * Testa o construtor quando o contato não tem número adicional. Este teste, devido à maneira como
     * é feito, testa também o toString da classe quando o contato não tem um número adicional.
     */
    @Test
    void testeConstrutorSemAdicional(){
        Contato contato1 = new Contato("José", "da Mata", "(83) 9", "(83) 8", "");
        String resultado = contato1.toString();
        String esperado = ("José da Mata\n(83) 9 (Prioritário)\n(83) 8 (WhatsApp)");
        assertEquals(esperado, resultado);
    }

    /**
     * Testa o método equals da classe Contato. O resultado esperado é "true".
     */
    @Test
    void testeEqualsTrue(){
        Contato contato1 = new Contato("Matheus", "Gaudêncio", "123", "321", "");
        assertTrue(bixinho.equals(contato1));
    }

    /**
     * Testa o método equals da classe Contato. O resutlado esperado é "false".
     */
    @Test
    void testeEqualsFalse(){
        Contato contato1 = new Contato("Varyan", "Wrynn", "(83) 9", "(83) 8", "(83) 7");
        assertFalse(bixinho.equals(contato1));
    }

    /**
     * Testa o método getNome para coletar o primeiro nome de um contato.
     */
    @Test
    void testeGetNome(){
        String expected = "Matheus";
        String nome = bixinho.getNome();
        assertEquals(expected, nome);
    }

    /**
     * Testa o método getSobrenome para coletar o sobrenome de um contato.
     */
    @Test
    void testeGetSobrenome(){
        String expected = "Gaudêncio";
        String actual = bixinho.getSobrenome();
        assertEquals(expected, actual);
    }

    /**
     * Testa o método toString quando o contato é favorito e não tem número adicional. Note que, devido à forma
     * como o teste é construído, ele também testa o método setEhFavorito.
     */
    @Test
    void testeToStringFavoritoSemAdicional(){
        Contato contato1 = new Contato("Varyan", "Wrynn", "(83) 9", "(83) 8", "");
        contato1.setEhFavorito(true);
        String actual = contato1.toString();
        String expected = "❤️ Varyan Wrynn\n(83) 9 (Prioritário)\n(83) 8 (WhatsApp)";
        assertEquals(expected, actual);
    }

    /**
     * Testa o método toString quando o contato é favorito e tem número adicional. Note que, devido à forma como o teste é construído,
     * ele também testa o método setEhFavorito.
     */
    @Test
    void testeToStringFavoritoComAdicional(){
        bixinho.setEhFavorito(true);
        String actual = bixinho.toString();
        String expected = "❤️ Matheus Gaudêncio\n123 (Prioritário)\n321 (WhatsApp)\n568912 (Adicional)";
        assertEquals(expected, actual);
    }
}