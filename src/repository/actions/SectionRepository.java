package repository.actions;

import model.Sectiune;

import java.util.LinkedHashMap;
import java.util.List;

public interface SectionRepository {

    List<Sectiune> returneazaSectiuniAlfabetic();
    LinkedHashMap<Sectiune, Integer> returneazaSectiuniDupaNrCarti();
    int gasesteIdSectiune(String titluSectiune);
    int adaugaSectiune(Sectiune sectiune);
    void modificaNumeSectiune(int idSectiune, String titluNou);
    void stergeSectiune(int idSectiune);

}
