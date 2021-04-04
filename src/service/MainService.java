package service;

import model.*;

import java.lang.reflect.Array;
import java.util.*;

public class MainService {

    private static ArrayList<Carte> carti = new ArrayList<Carte>();
    private static ArrayList<AngajatBiblioteca> angajati = new ArrayList<AngajatBiblioteca>();
    private static ArrayList<Autor> autori = new ArrayList<Autor>();
    private static ArrayList<Cititor> cititori = new ArrayList<Cititor>();
    /*
    Obiectul de tip HashMap stochează perechi cheie-valoare cu semnificația:
    secțiune - număr de cărți aparținând secțiunii.
     */
    private static HashMap<Sectiune, Integer> sectiuni = new HashMap<Sectiune, Integer>();
    private static ArrayList<Rezervare> rezervari = new ArrayList<Rezervare>();
    private static ArrayList<Imprumut> imprumuturi = new ArrayList<Imprumut>();

    public MainService() {
        Sectiune s1 = new Sectiune("Cărți de dragoste");
        Sectiune s2 = new Sectiune("Cărți thriller și polițiste");
        Sectiune s3 = new Sectiune("Cărți science-fiction și fantasy");

        adaugaSectiune(s1);
        adaugaSectiune(s2);
        adaugaSectiune(s3);

        Autor a1 = new Autor("Fiodor", "Dostoievski");
        Autor a2 = new Autor("Lev", "Tolstoi");
        Autor a3 = new Autor("Margaret", "Mitchell");

        adaugaAutor(a1);
        adaugaAutor(a2);
        adaugaAutor(a3);

        Carte c1 = new Carte("Pe aripile vântului",s1, a3, false);
        Carte c2 = new Carte("Anna Karenina",s1, a2, false);
        Carte c3 = new Carte("Crimă și pedeapsă",s2, a1, false);

        adaugaCarte(c1);
        adaugaCarte(c2);
        adaugaCarte(c3);

        Cititor cit1 = new Cititor("Mihai", "Popescu");
        Cititor cit2 = new Cititor("Valentina", "Amărioarei");
        Cititor cit3 = new Cititor("Cristina", "Lungu");

        adaugaCititor(cit1);
        adaugaCititor(cit2);
        adaugaCititor(cit3);

        AngajatBiblioteca ang1 = new AngajatBiblioteca("Doru", "Vasilescu", 2500);
        AngajatBiblioteca ang2 = new AngajatBiblioteca("Daniel", "Tudoran", 2800.5);
        AngajatBiblioteca ang3 = new AngajatBiblioteca("Maria", "Dumitrescu", 2400);

        adaugaAngajat(ang1);
        adaugaAngajat(ang2);
        adaugaAngajat(ang3);


    }

    public void adaugaCarte(Carte carte) {
        carti.add(carte);
        carte.getAutor().getCartiScrise().add(carte);
        carte.getSectiune().getCartiSectiune().add(carte);
        System.out.println("S-a adăugat cartea cu titlul <" + carte.getTitlu() + "> în sistem!");
    }

    public void adaugaAngajat(AngajatBiblioteca angajat) {
        angajati.add(angajat);
        System.out.println("S-a adăugat angajatul cu numele " + angajat.getNume() + ", prenumele "
                            + angajat.getPrenume() + " și salariul " + angajat.getSalariu() + " în sistem!");
        System.out.println("ID-ul angajatului nou este: " + angajat.getId());
    }

    public void adaugaCititor(Cititor cititor) {
        cititori.add(cititor);
        System.out.println("S-a adăugat cititorul " + cititor.getPrenume() + " "
                + cititor.getNume() + " în sistem!");
        System.out.println("ID-ul cititorului nou adăugat este: " + cititor.getId());
    }

    public void adaugaAutor(Autor autor) {
        autori.add(autor);
        System.out.println("S-a adăugat autorul " + autor.getPrenume() + " "
                + autor.getNume() + " în sistem!");
        System.out.println("ID-ul autorului nou adăugat este: " + autor.getId());
    }

    public void adaugaSectiune(Sectiune sectiune) {
        sectiuni.put(sectiune, sectiune.getCartiSectiune().size());
        System.out.println("S-a adaugat secțiunea <" + sectiune.getNume() + "> în sistem!");
    }

    public void afiseazaInformatiiCarte(int idCarte){
        int existaCarte = -1;
        for (int i = 0; i < carti.size(); i++){
            if (carti.get(i).getId() == idCarte){
                System.out.println(carti.get(i));
                existaCarte = 1;
            }
        }
        if (existaCarte == -1){
            System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
        }

    }

    public void afiseazaCartiAutor(String prenumeAutor, String numeAutor){
        Autor autor = gasesteAutor(prenumeAutor, numeAutor);
        if (autor == null){
            System.out.println("Autorul cu prenumele și numele introduse nu există în sistem!");
        }
        else {
            System.out.println("Cărțile scrise de " + prenumeAutor + " " + numeAutor + " din sistem:");
            for (Carte c: autor.getCartiScrise()){
                System.out.println(c + "\n");
            }
        }
    }


    public Autor gasesteAutor(String prenumeAutor, String numeAutor) {
        for (int i = 0; i < autori.size(); i++) {
            if (autori.get(i).getNume().equals(numeAutor) && autori.get(i).getPrenume().equals(prenumeAutor)){
                return autori.get(i);
            }
        }
        return null;
    }

    public Cititor gasesteCititorDupaNume(String prenumeCititor, String numeCititor) {
        for (int i = 0; i < cititori.size(); i++) {
            if (cititori.get(i).getNume().equals(numeCititor) && cititori.get(i).getPrenume().equals(prenumeCititor)){
                return cititori.get(i);
            }
        }
        return null;
    }

    public Cititor gasesteCititorDupaId(int idCititor) {
        for (int i = 0; i < cititori.size(); i++) {
            if (cititori.get(i).getId() == idCititor){
                return cititori.get(i);
            }
        }
        return null;
    }

    public void imprumutaCarteCititor(Cititor cititor, Carte carte, Date dataImprumut, Date dataDeadline){
        Imprumut i = new Imprumut(cititor, carte, dataImprumut, dataDeadline, null);
        imprumuturi.add(i);
        carte.setEsteImprumutata(true);
        cititor.getImprumuturiCarti().add(i);
        System.out.println("Împrumutul cărții a fost efectuat cu succes!");
    }

    public void rezervaCarteCititor(Cititor cititor, Carte carte){
        Date dataRezervare = new Date();
        Rezervare r = new Rezervare(cititor, carte, dataRezervare);
        rezervari.add(r);
        carte.setEsteImprumutata(true);
        cititor.getRezervariCarti().add(r);
        System.out.println("Rezervarea cărții a fost efectuată cu succes!");
    }

    public Carte gasesteCarteDupaId(int idCarte) {
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getId() == idCarte){
                return carti.get(i);
            }
        }
        return null;
    }

    public Imprumut gasesteImprumut(Carte carte, Cititor cititor){
        for (Imprumut i : imprumuturi){
            if (i.getCarte().getId() == carte.getId() && i.getCititor().getId() == cititor.getId()){
                return i;
            }
        }
        return null;
    }

    public void returneazaCarteCititor(Carte carte, Cititor cititor){
        Date dataActualaReturnare = new Date();
        carte.setEsteImprumutata(false);
        Imprumut imprumut = gasesteImprumut(carte, cititor);
        imprumut.setDataActualaReturnare(dataActualaReturnare);
        cititor.getImprumuturiCarti().remove(imprumut);
        System.out.println("Cartea a fost returnată cu succes!");
    }

    public void afiseazaImprumuturiCititor(String prenumeCititor, String numeCititor) {
        Cititor c = gasesteCititorDupaNume(prenumeCititor, numeCititor);
        if (c == null){
            System.out.println("Cititorul cu prenumele și numele introduse nu există în sistem!");
        }
        else {
            if (c.getImprumuturiCarti().size() == 0){
                System.out.println("Cititorul nu are nici-o carte împrumutată în prezent!");
            }
            else {
                System.out.println("Împrumuturile cititorului " + c.getNume() + " " + c.getPrenume() + " sunt:");
                for (Imprumut i: c.getImprumuturiCarti()) {
                    System.out.println(i + "\n");
                }
            }
        }
    }

    public Sectiune gasesteSectiune(int idSectiune) {
        for (Sectiune s : sectiuni.keySet())
            if (s.getId() == idSectiune){
                return s;
            }
        return null;
    }

    public void afiseazaCarti() {
        for (int i = 0; i < carti.size(); i++) {
            System.out.println(carti.get(i));
        }
    }

    public void afiseazaSectiuniAlfabetic() {
        ArrayList<Sectiune> sortedKeys =
                new ArrayList<Sectiune>(sectiuni.keySet());
        Collections.sort(sortedKeys);
        for (Sectiune x : sortedKeys) {
            System.out.println(x);
        }
    }

    public void afiseazaSectiuniDupaNrCarti() {
        ArrayList<Sectiune> sortedKeys =
                new ArrayList<Sectiune>(sectiuni.keySet());
        Collections.sort(sortedKeys, new Comparator<Sectiune>() {
            @Override
            public int compare(final Sectiune object1, final Sectiune object2) {
                return object2.getCartiSectiune().size() - object1.getCartiSectiune().size();
            }
        });
        for (Sectiune x : sortedKeys) {
            System.out.println("Nume secțiune: " + x.getNume() + "; Nr. cărți secțiune: " + x.getCartiSectiune().size());
        }
    }


    public void afiseazaAutoriAlfabetic() {
        Collections.sort(autori, new Comparator<Autor>() {
            @Override
            public int compare(final Autor object1, final Autor object2) {
                if (object1.getNume().equals(object2.getNume())){
                    return object1.getPrenume().compareTo(object2.getPrenume());
                }
                else {
                    return object1.getNume().compareTo(object2.getNume());
                }
            }
        });
        for (Autor a: autori){
            System.out.println(a);
        }
    }

    public void afiseazaCititori() {
        for (int i = 0; i < cititori.size(); i++) {
            System.out.println(cititori.get(i));
        }
    }

    public void afiseazaAngajatiBiblioteca() {
        for (int i = 0; i < angajati.size(); i++) {
            System.out.println(angajati.get(i));
        }
    }

}
