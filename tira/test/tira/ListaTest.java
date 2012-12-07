package tira;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListaTest {

    /**
     * Test of add method, of class Lista.
     */
    @Test
    public void testAdd() {
        Siirto uusi;

        Lista instance = new Lista();

        for (int i = 0; i < 1000; i++) {
            uusi = new Siirto(-1, -1, -1, -1, i);
            instance.add(uusi);
        }
    }

    /**
     * Test of get method, of class Lista.
     */
    @Test
    public void testGet() {
        Siirto uusi;
        Lista instance = new Lista();

        for (int i = 0; i < 1000; i++) {
            uusi = new Siirto(-1, -1, -1, -1, i);
            instance.add(uusi);

            assertEquals(instance.get(i), uusi);
        }
    }

    /**
     * Test of length method, of class Lista.
     */
    @Test
    public void testLength() {
        Siirto uusi;
        Lista instance = new Lista();

        for (int i = 0; i < 123; i++) {
            uusi = new Siirto(-1, -1, -1, -1, i);
            instance.add(uusi);
        }
        assertEquals(instance.length(), 123);
    }

    /**
     * Test of getMax method, of class Lista.
     */
    @Test
    public void testGetMax() {
        Siirto uusi;
        Lista instance = new Lista();

        for (int i = 0; i < 123; i++) {
            uusi = new Siirto(-1, -1, -1, -1, i);
            instance.add(uusi);
        }
        assertEquals(instance.getMax().arvo(), 122);
    }

    /**
     * Test of getMin method, of class Lista.
     */
    @Test
    public void testGetMin() {
       Siirto uusi;
        Lista instance = new Lista();

        for (int i = 0; i > -123; i--) {
            uusi = new Siirto(-1, -1, -1, -1, i);
            instance.add(uusi);
        }
        assertEquals(instance.getMin().arvo(), -122);
    }
}
