package repository.actions;

import model.Autor;
import model.Carte;

import java.util.List;

public interface AuthorRepository{

    int adaugaAutor(Autor autor);
    List<Autor> returneazaAutoriAlfabetic();
    List<Carte> cautaCartiAutor(int idAutor);
    void stergeAutor(int idAutor);
    int gasesteIdAutor(String prenumeAutor, String numeAutor);



}
