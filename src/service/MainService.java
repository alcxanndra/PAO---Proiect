package service;

import model.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        citireSectiuni();
        citireAutori();
        citireCarti();
        citireAngajatiBiblioteca();
        citireCititori();
        citireImprumuturi();
        citireRezervari();

    }

    public void adaugaCarte(Carte carte) {
        logToCsv("Adăugare carte în sistem");
        addBookToCsv(carte);
        carti.add(carte);
        carte.getAutor().getCartiScrise().add(carte);
        carte.getSectiune().getCartiSectiune().add(carte);
        System.out.println("S-a adăugat cartea cu titlul <" + carte.getTitlu() + "> în sistem!");
    }

    public void adaugaAngajat(AngajatBiblioteca angajat) {
        logToCsv("Adăugare angajat bibliotecă nou în sistem");
        addEmployeeToCsv(angajat);
        angajati.add(angajat);
        System.out.println("S-a adăugat angajatul cu numele " + angajat.getNume() + ", prenumele "
                            + angajat.getPrenume() + " și salariul " + angajat.getSalariu() + " în sistem!");
        System.out.println("ID-ul angajatului nou este: " + angajat.getId());
    }

    public void adaugaCititor(Cititor cititor) {
        logToCsv("Adăugare cititor nou în sistem");
        addReaderToCsv(cititor);
        cititori.add(cititor);
        System.out.println("S-a adăugat cititorul " + cititor.getPrenume() + " "
                + cititor.getNume() + " în sistem!");
        System.out.println("ID-ul cititorului nou adăugat este: " + cititor.getId());
    }

    public void adaugaAutor(Autor autor) {
        logToCsv("Adăugare autor nou în sistem");
        addAuthorToCsv(autor);
        autori.add(autor);
        System.out.println("S-a adăugat autorul " + autor.getPrenume() + " "
                + autor.getNume() + " în sistem!");
        System.out.println("ID-ul autorului nou adăugat este: " + autor.getId());
    }

    public void adaugaSectiune(Sectiune sectiune) {
        logToCsv("Adăugare secțiune nouă în sistem");
        addSectionToCsv(sectiune);
        sectiuni.put(sectiune, sectiune.getCartiSectiune().size());
        System.out.println("S-a adaugat secțiunea <" + sectiune.getNume() + "> în sistem!");
    }

    public void afiseazaInformatiiCarte(int idCarte){
        int existaCarte = -1;
        for (int i = 0; i < carti.size() && existaCarte == -1; i++){
            if (carti.get(i).getId() == idCarte){
                logToCsv("Afișare informații despre carte");
                System.out.println(carti.get(i));
                existaCarte = 1;
            }
        }
        if (existaCarte == -1){
            System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
        }
    }

    public void afiseazaRezervariCarte(int idCarte){
        int existaCarte = -1;
        for (int i = 0; i < carti.size() && existaCarte == -1; i++){
            if (carti.get(i).getId() == idCarte){
                logToCsv("Afișare rezervări carte");
                System.out.println(carti.get(i).getRezervariCarte());
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
            logToCsv("Afișare cărți autor");
            ReportGeneratorService.getInstance().generateBooksOfAuthorOrderedByTitleReport(autor);
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
        logToCsv("Adăugare împrumut nou în sistem");
        addLoanCsv(i);
        imprumuturi.add(i);
        carte.setEsteImprumutata(true);
        cititor.getImprumuturiCarti().add(i);
        System.out.println("Împrumutul cărții a fost efectuat cu succes!");
    }

    public void rezervaCarteCititor(Cititor cititor, Carte carte, Date dataRezervare){
        Rezervare r = new Rezervare(cititor, carte, dataRezervare);
        logToCsv("Adăugare rezervare nouă în sistem");
        addHoldRequestToCsv(r);
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
        logToCsv("Returnare carte cititor");
        Date dataActualaReturnare = new Date();
        carte.setEsteImprumutata(false);
        Imprumut imprumut = gasesteImprumut(carte, cititor);
        imprumut.setDataActualaReturnare(dataActualaReturnare);
        cititor.getImprumuturiCarti().remove(imprumut);
        System.out.println("Cartea a fost returnată cu succes!");
    }

    public void afiseazaImprumuturiCititor(int idCititor) {
        Cititor c = gasesteCititorDupaId(idCititor);
        if (c == null){
            System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
        }
        else {
            if (c.getImprumuturiCarti().size() == 0){
                System.out.println("Cititorul nu are nici-o carte împrumutată în prezent!");
            }
            else {
                logToCsv("Afișare împrumuturi cititor");
                ReportGeneratorService.getInstance().generateLoansOfReaderReport(c);
            }
        }
    }

    public void afiseazaRezervariCititor(int idCititor) {
        Cititor c = gasesteCititorDupaId(idCititor);
        if (c == null){
            System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
        }
        else {
            if (c.getRezervariCarti().size() == 0){
                System.out.println("Cititorul nu are nici-o carte rezervată în prezent!");
            }
            else {
                logToCsv("Afișare rezervări de cărți cititor");
                ReportGeneratorService.getInstance().generateHoldRequestsOfReaderReport(c);
            }
        }
    }

    public Sectiune gasesteSectiuneDupaTitlu(String titluSectiune) {
        for (Sectiune s : sectiuni.keySet())
            if (s.getNume().equals(titluSectiune)){
                return s;
            }
        return null;
    }

    public void afiseazaCartiAlfabetic() {
        Collections.sort(carti, new Comparator<Carte>() {
            @Override
            public int compare(final Carte c1, final Carte c2) {
                return c1.getTitlu().compareTo(c2.getTitlu());
            }
        });

        logToCsv("Afișare cărți în ordine alfabetică");
        ReportGeneratorService.getInstance().generateBooksOrderedByTitleReport(carti);
    }

    public void afiseazaSectiuniAlfabetic() {
        ArrayList<Sectiune> sortedKeys =
                new ArrayList<Sectiune>(sectiuni.keySet());
        Collections.sort(sortedKeys);

        logToCsv("Afișare secțiuni în ordine alfabetică");
        ReportGeneratorService.getInstance().generateSectionsOrderedByNameReport(sortedKeys);

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

        logToCsv("Afișare secțiuni după numărul de cărți");
        ReportGeneratorService.getInstance().generateSectionsOrderedByNumberOfBooksReport(sortedKeys);
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

        logToCsv("Afișare autori în ordine alfabetică");
        ReportGeneratorService.getInstance().generateAuthorsOrderedByNameReport(autori);

    }

    public void afiseazaCititoriAlfabetic() {
        Collections.sort(cititori, new Comparator<Cititor>() {
            @Override
            public int compare(final Cititor c1, final Cititor c2) {
                if (c1.getNume().equals(c2.getNume())){
                    return c1.getPrenume().compareTo(c2.getPrenume());
                }
                else {
                    return c1.getNume().compareTo(c2.getNume());
                }
            }
        });

        logToCsv("Afișare cititori în ordine alfabetică");
        ReportGeneratorService.getInstance().generateReadersOrderedByNameReport(cititori);
    }

    public void afiseazaAngajatiBibliotecaAlfabetic() {
        Collections.sort(angajati, new Comparator<AngajatBiblioteca>() {
            @Override
            public int compare(final AngajatBiblioteca object1, final AngajatBiblioteca object2) {
                if (object1.getNume().equals(object2.getNume())){
                    return object1.getPrenume().compareTo(object2.getPrenume());
                }
                else {
                    return object1.getNume().compareTo(object2.getNume());
                }
            }
        });

        logToCsv("Afișare angajați bibliotecă în ordine alfabetică");
        ReportGeneratorService.getInstance().generateEmployeesOrderedByNameReport(angajati);
    }

    public void citireAngajatiBiblioteca(){
        List<String[]> angajatiBiblioteca = ReportGeneratorService.getInstance().readFromCsvFile("AngajatiBiblioteca.csv");
        for (int i = 0; i < angajatiBiblioteca.size(); i++) {
            String[] angajat = angajatiBiblioteca.get(i);
            AngajatBiblioteca a = new AngajatBiblioteca(angajat[0], angajat[1], Double.parseDouble(angajat[2]));
            angajati.add(a);
        }
    }

    public void citireAutori(){
        List<String[]> autoriBiblioteca = ReportGeneratorService.getInstance().readFromCsvFile("Autori.csv");
        for (int i = 0; i < autoriBiblioteca.size(); i++) {
            String[] autor = autoriBiblioteca.get(i);
            Autor a = new Autor(autor[0], autor[1]);
            autori.add(a);
        }
    }

    public void citireCititori(){
        List<String[]> cititoriBiblioteca = ReportGeneratorService.getInstance().readFromCsvFile("Cititori.csv");
        for (int i = 0; i < cititoriBiblioteca.size(); i++) {
            String[] cititor = cititoriBiblioteca.get(i);
            Cititor c = new Cititor(cititor[0], cititor[1]);
            cititori.add(c);
        }
    }

    public void citireSectiuni(){
        List<String[]> sectiuniBiblioteca = ReportGeneratorService.getInstance().readFromCsvFile("Sectiuni.csv");
        for (int i = 0; i < sectiuniBiblioteca.size(); i++) {
            String[] sectiune = sectiuniBiblioteca.get(i);
            Sectiune s = new Sectiune(sectiune[0]);
            sectiuni.put(s, s.getCartiSectiune().size());
        }
    }

    public void citireCarti(){
        List<String[]> cartiBiblioteca = ReportGeneratorService.getInstance().readFromCsvFile("Carti.csv");
        for (int i = 0; i < cartiBiblioteca.size(); i++) {
            String[] carte = cartiBiblioteca.get(i);
            Autor autor = gasesteAutor(carte[2], carte[3]);
            Sectiune sectiune = gasesteSectiuneDupaTitlu(carte[1]);
            Carte c = new Carte(carte[0], sectiune, autor, false);
            carti.add(c);
            c.getAutor().getCartiScrise().add(c);
            c.getSectiune().getCartiSectiune().add(c);
        }
    }

    public void citireImprumuturi(){
        try {
            List<String[]> imprumuturiCarti = ReportGeneratorService.getInstance().readFromCsvFile("Imprumuturi.csv");
            for (int i = 0; i < imprumuturiCarti.size(); i++) {
                String[] imprumut = imprumuturiCarti.get(i);
                int idCititor = Integer.parseInt(imprumut[0]);
                int idCarte = Integer.parseInt(imprumut[1]);

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date dataImprumut = dateFormat.parse(imprumut[2]);
                Date dataDeadline = dateFormat.parse(imprumut[3]);
                Date dataReturnare = null;

                if (imprumut.length > 4)
                    dataReturnare = dateFormat.parse(imprumut[4]);

                Imprumut imp  = new Imprumut(gasesteCititorDupaId(idCititor), gasesteCarteDupaId(idCarte), dataImprumut, dataDeadline, dataReturnare);
                imprumuturi.add(imp);
                imp.getCititor().getImprumuturiCarti().add(imp);
                imp.getCarte().setEsteImprumutata(true);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void citireRezervari(){
        try {
            List<String[]> rezervariCarti = ReportGeneratorService.getInstance().readFromCsvFile("Rezervari.csv");
            for (int i = 0; i < rezervariCarti.size(); i++) {
                String[] rezervare = rezervariCarti.get(i);
                int idCititor = Integer.parseInt(rezervare[0]);
                int idCarte = Integer.parseInt(rezervare[1]);

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                Date dataRezervare = dateFormat.parse(rezervare[2]);

                Rezervare r = new Rezervare(gasesteCititorDupaId(idCititor), gasesteCarteDupaId(idCarte), dataRezervare);
                rezervari.add(r);
                r.getCititor().getRezervariCarti().add(r);
                r.getCarte().getRezervariCarte().add(r);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addBookToCsv(Carte newBook){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newBook.getTitlu());
            reportContent.append(",");
            reportContent.append(newBook.getSectiune().getNume());
            reportContent.append(",");
            reportContent.append(newBook.getAutor().getPrenume());
            reportContent.append(",");
            reportContent.append(newBook.getAutor().getNume());
            ReportGeneratorService.getInstance().writeToCsvFile("Carti.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addAuthorToCsv(Autor newAuthor){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newAuthor.getPrenume());
            reportContent.append(",");
            reportContent.append(newAuthor.getNume());
            ReportGeneratorService.getInstance().writeToCsvFile("Autori.csv", reportContent);
            System.out.println();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSectionToCsv(Sectiune newSection){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newSection.getNume());
            ReportGeneratorService.getInstance().writeToCsvFile("Sectiuni.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLoanCsv(Imprumut newLoan){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newLoan.getCititor().getId());
            reportContent.append(",");
            reportContent.append(newLoan.getCarte().getId());
            reportContent.append(",");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataImprumutStr = dateFormat.format(newLoan.getDataImprumut());
            String dataDeadlineReturnareStr = dateFormat.format(newLoan.getDataDeadlineReturnare());
            String dataActualaReturnareStr = "";

            reportContent.append(dataImprumutStr);
            reportContent.append(",");
            reportContent.append(dataDeadlineReturnareStr);
            reportContent.append(",");
            reportContent.append(dataActualaReturnareStr);

            ReportGeneratorService.getInstance().writeToCsvFile("Imprumuturi.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addHoldRequestToCsv(Rezervare newHoldRequest){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newHoldRequest.getCititor().getId());
            reportContent.append(",");
            reportContent.append(newHoldRequest.getCarte().getId());
            reportContent.append(",");

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataRezervareStr = dateFormat.format(newHoldRequest.getDataRezervare());
            reportContent.append(dataRezervareStr);

            ReportGeneratorService.getInstance().writeToCsvFile("Rezervari.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addEmployeeToCsv(AngajatBiblioteca newEmployee){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newEmployee.getNume());
            reportContent.append(",");
            reportContent.append(newEmployee.getPrenume());
            reportContent.append(",");
            reportContent.append(newEmployee.getSalariu());
            ReportGeneratorService.getInstance().writeToCsvFile("AngajatiBiblioteca.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addReaderToCsv(Cititor newReader){
        try {
            StringBuilder reportContent = new StringBuilder();
            reportContent.append("\n");
            reportContent.append(newReader.getNume());
            reportContent.append(",");
            reportContent.append(newReader.getPrenume());
            ReportGeneratorService.getInstance().writeToCsvFile("Cititori.csv", reportContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logToCsv(String numeActiune){
        StringBuilder rowContent = new StringBuilder();
        rowContent.append("\n");
        rowContent.append(numeActiune);
        rowContent.append(",");
        String logTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        rowContent.append(logTime);
        ReportGeneratorService.getInstance().writeToCsvFile("Logare.csv",rowContent);
    }



}
