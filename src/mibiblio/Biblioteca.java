package mibiblio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Clase que representa a una <b>Biblioteca</b> que contiene un conjunto de libros.
 * Los libros pueden estar repetidos o no:
 * Actualmente un objeto biblioteca puede realizar las siguientes operaciones:
 * <ol>
 * <li>Agregar libros a la colección.</li>
 * <li>Obtener el número total de libros (contando  o excluyendo repetidos</li>
 * <li>Obtener libros buscando por alguno de sus autores</li>
 * <li>Obtener libros buscando por su título o parte de su título.</li>
 * <li>Obtener todos los autores para los que existe algún libro almacenado</li>
 * </ol>
 * @author Aquí tu nombre
 * @see Libro
 * @see Autor
 */
public class Biblioteca {
    /**
     * Capacidad máxima de ejemplares que pueden ser almacenados en la biblioteca.
     */
    public static int CAPACIDAD_MAX = 1000;
    private List<Libro> libros;

    /**
     * Crea una biblioteca vacía.
     */
    public Biblioteca() {
        libros = new ArrayList();
    }
    
    /**
     * Agrega un libro a la biblioteca. <br>
     * Los libros se mantendrán ordenados alfabéticamente según su título.
     * @param lib Libro a agregar.
     * @return True si se agrega el libro y false si no se agrega por haber alcanzado
     * la capacidad máxima.
     * @see #CAPACIDAD_MAX
     */
    public boolean addLibro(Libro lib){
        if(libros.size()==Biblioteca.CAPACIDAD_MAX)
            return false;
        
        int i = 0;
        for(i=0;i<libros.size();i++){
            if(libros.get(i).getTitulo().compareTo(lib.getTitulo())>=0){
                break;
            }
        }
        libros.add(i,lib);
        return true;
    }
    
    /**
     * Devuelve el número total de ejemplares almacenados. <br>
     * Se incluyen repetidos.
     * @return Número de libros almacenados.
     */
    public int getNumLibros(){
        return libros.size();
    }
    
    /**
     * Devuelve el número de libros almacenados sin contar repetidos.
     * @return Número de libros diferentes almacenados.
     */
    public int getNumTitulos(){
        return (new HashSet(libros)).size();
    }
    
    /**
     * Devuelve una lista de libros almacenados escritos por el autor indicado.
     * @param autor Autor por el que se desea filtrar.
     * @return Libros de la biblioteca escritos por el autor.
     * @throws mibiblio.AutorNuloException Si el autor es null.
     */
    public List<Libro> getLibrosPorAutor(Autor autor) throws AutorNuloException{
        HashSet<Libro> libros = new HashSet();
        for(Libro l:this.libros){
            if(l.esDeAutor(autor)){
                libros.add(l);
            }
        }
        return new ArrayList(libros);
    }
    
    /**
     * Deveuelve una lista de libros filtrando por el título. <br>
     * El título del libro debe contener todo parte del texto pasado como parámetro.
     * @param titulo Filtro a aplicar sobre el título del libro.
     * @return Conjunto de libros cuyo título contienen parte del texto indicado en el parámetro.
     */
    public List<Libro> getLibrosPorTitulo(String titulo){
        HashSet<Libro> libros = new HashSet();
        for(Libro l:this.libros){
            if(l.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                libros.add(l);
            }
        }
        return new ArrayList(libros); 
    } 
    
    /**
     * Devuelve una lista ordenada alfabéticamente con todos los autores
     * de los que se tiene algún libro almacenado.
     * @return Lista de autores.
     */
    public List<Autor> getAutores(){
        HashSet<Autor> autores = new HashSet();
        for(Libro l:this.libros){
            autores.addAll(l.getAutores());
        }
        List<Autor> ordenados = new ArrayList(autores);
        //Los ordeno con un comparator
        Comparator<Autor> cmp = (Autor a1, Autor a2) -> {            
            String sA1 = a1.getApellidos().toLowerCase()+a1.getNombre();
            String sA2 = a2.getApellidos()+a2.getNombre();
            return sA1.compareToIgnoreCase(sA2);
        };
        
        Collections.sort(ordenados, cmp);
        return ordenados;        
    }
    

    @Override
    public String toString() {
        String libros = "";
        for(Libro l:this.libros){
            libros+="-----------------------------------------\n";
            libros+=l;
            libros+="\n-----------------------------------------\n";
        }
        return libros;
    }
    
    
    
    
}
