package repository.actions;

import model.Imprumut;

public interface LoanRepository {

    int adaugaImprumut(Imprumut imprumut);
    int cautaImprumut(int idCititor, int idCarte);
    void modificaDataActualaReturnareImprumut(int idImprumut, String dataActualaReturnare);

}
