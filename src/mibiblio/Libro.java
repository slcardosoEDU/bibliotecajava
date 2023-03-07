package mibiblio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Libro {
    private String isbn;
    private String titulo;
    private List<Autor> autores;
    private LocalDate publicacion;
    private String editorial;

    public Libro(String isbn, String titulo, List<Autor> autores, LocalDate publicacion, String editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores!=null?autores:new ArrayList();
        this.publicacion = publicacion;
        this.editorial = editorial;
    }

    /**
     * Devuelve un valor lógico indicando si el autor pasado es uno de los 
     * autores del libro.
     * @param autor Autor a comprobar.
     * @return True si el libro ha sido escrito por el autor
     * @throws AutorNuloException Se escala la excepción si el autor pasado es null.
     */
    public boolean esDeAutor(Autor autor) throws AutorNuloException{
        if(autor==null){
            throw new AutorNuloException();
        }
        return autores.contains(autor);
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }


    public LocalDate getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(LocalDate publicacion) {
        this.publicacion = publicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.isbn);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        
        return Objects.equals(this.isbn, other.isbn);
    }

    @Override
    public String toString() {
        String autores="";
        for(Autor a : this.autores){
            autores += "\t"+a+"\n";
        }
        if(autores.equals(""))
            autores = "\tAnónimo\n";
        return titulo+"\n"+autores+editorial+" ("+publicacion.getYear()+")\n"+isbn;
    }
    
   
    
    

    
    
}
