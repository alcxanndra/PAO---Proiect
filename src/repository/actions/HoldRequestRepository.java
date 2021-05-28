package repository.actions;

import model.Rezervare;

public interface HoldRequestRepository {

    int adaugaRezervare(Rezervare rezervare);
    void stergeRezervare(int idRezervare);


}
