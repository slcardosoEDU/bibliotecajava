package mibiblio;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alumno
 */
public class LibroTest {
    private Libro libro;
    
    public LibroTest() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Autor> autores = new ArrayList();
        autores.add(new Autor("Lorca","Federico"));
        autores.add(new Autor("De Castro","Rosalia"));
        libro = new Libro("is", "titulo", autores, LocalDate.now(), "asdf");
    }

    @Test
    public void esDeAutorTest() throws AutorNuloException {
        assertTrue(libro.esDeAutor(new Autor("De Castro","Rosalia")));
        assertFalse(libro.esDeAutor(new Autor("García Marquez","Gabriel")));   
    }
    
    
    @Test(expected=AutorNuloException.class)
    public void esDeAutorAutorNuloExceptionTest() throws AutorNuloException  {
        
        libro.esDeAutor(null);
        
    }
    
    
}
