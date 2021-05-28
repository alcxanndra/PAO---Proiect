package repository.actions;

import model.AngajatBiblioteca;

import java.util.List;

public interface LibraryEmployeeRepository {

    List<AngajatBiblioteca> returneazaAngajatiAlfabetic();
    List<AngajatBiblioteca> returneazaAngajatiDupaSalariu();
    int adaugaAngajatBiblioteca(AngajatBiblioteca angajat);
    void stergeAngajatBiblioteca(int idAngajat);
    void modificaSalariuAngajat(int idAngajat, double salariuNou);

}
