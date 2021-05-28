import model.*;
import service.MainService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MainService mainService = new MainService();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Alegeți una dintre opțiunile următoare: ");
            System.out.println();

            System.out.println("**************** CĂRȚI ***************");
            System.out.println("(1).Afișează toate cărțile din sistem (în ordine alfabetică după titlu).");
            System.out.println("(2).Adaugă carte în sistem.");
            System.out.println("(3).Șterge carte din sistem.");
            System.out.println("(4).Afișează informații despre carte.");
            System.out.println("(5).Afișează rezervările unei cărți.");
            System.out.println("(6).Modifică titlul unei cărți din sistem.");
            System.out.println("(7).Modifică autorul unei cărți din sistem.");
            System.out.println("(8).Modifică secțiunea unei cărți din sistem.");
            System.out.println();

            System.out.println("**************** AUTORI ***************");
            System.out.println("(9).Afișează toți autorii din sistem (în ordine alfabetică).");
            System.out.println("(10).Adaugă autor în sistem.");
            System.out.println("(11).Șterge autor din sistem.");
            System.out.println("(12).Afișează toate cărțile scrise de un autor din sistem (în ordine alfabetică).");
            System.out.println();

            System.out.println("**************** SECȚIUNI ***************");
            System.out.println("(13).Afișează toate secțiunile din bibliotecă (în ordine alfabetică).");
            System.out.println("(14).Afișează toate secțiunile din bibliotecă (după numărul descrescător de cărți).");
            System.out.println("(15).Adaugă secțiune în sistem.");
            System.out.println("(16).Șterge secțiune din sistem.");
            System.out.println("(17).Modifică numele unei secțiuni din sistem.");
            System.out.println();

            System.out.println("**************** ANGAJAȚI ***************");
            System.out.println("(18).Afișează toți angajații din bibliotecă (în ordine alfabetică).");
            System.out.println("(19).Afișează toți angajații din bibliotecă (în ordine descrescătoare a salariului).");
            System.out.println("(20).Adaugă angajat bibliotecă în sistem.");
            System.out.println("(21).Șterge angajat bibliotecă din sistem.");
            System.out.println("(22).Actualizează salariul unui angajat din sistem.");
            System.out.println();

            System.out.println("**************** CITITORI ***************");
            System.out.println("(23).Afișează toți cititorii din sistem (în ordine alfabetică).");
            System.out.println("(24).Adaugă cititor nou în sistem.");
            System.out.println("(25).Șterge cititor din sistem.");
            System.out.println();

            System.out.println("**************** ÎMPRUMUTURI ***************");
            System.out.println("(26).Afișează cărțile împrumutate de un cititor.");
            System.out.println("(27).Împrumută carte unui cititor.");
            System.out.println("(28).Returnează carte de la cititor.");
            System.out.println();

            System.out.println("**************** REZERVĂRI ***************");
            System.out.println("(29).Afișează cărțile rezervate de un cititor.");
            System.out.println("(30).Rezervă carte pentru un cititor.");
            System.out.println("(31).Șterge rezervare din sistem.");
            System.out.println();

            System.out.println("(0).Opreste programul.");

            int optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {

                case 1:
                    List<Carte> carti = mainService.returneazaCartiAlfabetic();
                    System.out.println("Cărțile existente în sistem returnate în ordinea alfabetică a titlului sunt: ");
                    System.out.println(carti);
                    break;

                case 2:
                    System.out.println("Introduceți titlul cărții:");
                    String titlu = scanner.nextLine();
                    System.out.println("Introduceți titlul secțiunii din care face parte cartea:");
                    String sectiuneCarte = scanner.nextLine();
                    System.out.println("Introduceți prenumele autorului cărții:");
                    String prenumeAutor = scanner.nextLine();
                    System.out.println("Introduceți numele autorului cărții:");
                    String numeAutor = scanner.nextLine();
                    int idAutor = mainService.gasesteIdAutor(prenumeAutor, numeAutor);
                    int idSectiune = mainService.gasesteIdSectiune(sectiuneCarte);
                    if (idSectiune == -1) {
                        System.out.println("Secțiunea cu titlul introdus nu există în sistem!");
                    } else if (idAutor == -1) {
                        System.out.println("Autorul cu numele și prenumele introduse nu există în sistem!");
                    } else {
                        Carte carte = new Carte(
                                titlu,
                                idSectiune,
                                idAutor
                        );
                        int idCarte = mainService.adaugaCarte(carte);
                        System.out.println("S-a adăugat cartea cu id-ul: " + idCarte);
                    }
                    break;

                case 3:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idCarte = Integer.parseInt(scanner.nextLine());
                    mainService.stergeCarte(idCarte);
                    System.out.println("S-a șters din sistem cartea cu id-ul: " + idCarte);
                    break;
//
                case 4:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idCarte2 = Integer.parseInt(scanner.nextLine());
                    List<String> infoCarte = mainService.afiseazaInformatiiCarte(idCarte2);
                    if (infoCarte.size() == 0) {
                        System.out.println("Nu există nici-o carte cu ID-ul introdus în sistem!");
                    }
                    else {
                        System.out.println("Informațiile returnate despre cartea cu id-ul " + idCarte2 + " sunt:");
                        System.out.println("Titlu carte: " + infoCarte.get(0));
                        System.out.println("Secțiune carte: " + infoCarte.get(1));
                        System.out.println("Autor carte: " + infoCarte.get(2) + " " + infoCarte.get(3) + "\n");
                    }
                    break;

                case 5:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idBook = Integer.parseInt(scanner.nextLine());
                    List<Rezervare> rezervariCarte = mainService.afiseazaRezervariCarte(idBook);
                    if (rezervariCarte.size() == 0){
                        System.out.println("Cartea cu id-ul " + idBook + " nu are nici-o rezervare în prezent!");
                    }
                    else {
                        System.out.println("Rezervările din sistem pentru cartea cu id-ul " + idBook + " sunt:");
                        for (Rezervare r : rezervariCarte) {
                            System.out.println(r);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Introduceți ID-ul cărții pentru care doriți să actualizați informațiile:");
                    int idCarte6 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCarte(idCarte6) == -1){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Introduceți titlul nou al cărții:");
                        String titlu6 = scanner.nextLine();
                        mainService.modificaTitluCarte(idCarte6, titlu6);
                    }
                    break;

                case 7:
                    System.out.println("Introduceți ID-ul cărții pentru care doriți să actualizați informațiile:");
                    int idCarte7 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCarte(idCarte7) == -1){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Introduceți noua secțiune a cărții (id-ul secțiunii):");
                        int idSectiune7 = Integer.parseInt(scanner.nextLine());
                        mainService.modificaSectiuneCarte(idCarte7, idSectiune7);
                    }
                    break;

                case 8:
                    System.out.println("Introduceți ID-ul cărții pentru care doriți să actualizați informațiile:");
                    int idCarte8 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCarte(idCarte8) == -1){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Introduceți noul autor al cărții (id-ul autorului):");
                        int idAutor8 = Integer.parseInt(scanner.nextLine());
                        mainService.modificaAutorCarte(idCarte8, idAutor8);
                    }
                    break;

                case 9:
                    List<Autor> autori = mainService.returneazaAutoriAlfabetic();
                    System.out.println("Autorii din sistem în ordinea alfabetică a numelui și prenumelui sunt: ");
                    System.out.println(autori);
                    break;

                case 10:
                    System.out.println("Introduceți prenumele și numele autorului separate prin spațiu (ex. 'Ion Creangă'):");
                    String[] detaliiAutor = scanner.nextLine().split(" ");
                    String prenumeleAutorului = detaliiAutor[0];
                    String numeleAutorului = detaliiAutor[1];
                    Autor autorNou = new Autor(prenumeleAutorului, numeleAutorului);
                    int idAutor10 = mainService.adaugaAutor(autorNou);
                    System.out.println("S-a adăugat autorul cu id-ul: " + idAutor10);
                    break;

                case 11:
                    System.out.println("Introduceți ID-ul autorului:");
                    int idAutor11 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaAutor(idAutor11) == -1){
                        System.out.println("Autorul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.stergeAutor(idAutor11);
                        System.out.println("S-a șters din sistem autorul cu id-ul: " + idAutor11);
                    }
                    break;

                case 12:
                    System.out.println("Introduceți ID-ul autorului:");
                    int idAutor12 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaAutor(idAutor12) == -1){
                        System.out.println("Autorul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Cărțile scrise de autorul cu id-ul " + idAutor12 + " din sistem sunt:");
                        List<Carte> cartiAutor = mainService.cautaCartiAutor(idAutor12);
                        for (Carte c : cartiAutor){
                            System.out.println(c);
                        }
                    }
                    break;

                case 13:
                    System.out.println("Secțiunile existente în sistem în ordine alfabetică sunt:");
                    List<Sectiune> sectiuni = mainService.returneazaSectiuniAlfabetic();
                    for (Sectiune s : sectiuni){
                        System.out.println(s);
                    }
                    break;

                case 14:
                    System.out.println("Secțiunile existente în sistem în ordine descrescătoare a numărului de cărți conținute sunt:");
                    LinkedHashMap<Sectiune, Integer> sectiuni14 = mainService.returneazaSectiuniDupaNrCarti();
                    Iterator it = sectiuni14.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        System.out.println("Secțiune{" +
                                "Id: " + ((Sectiune)pair.getKey()).getId() + ", " +
                                "Titlu: " + ((Sectiune) pair.getKey()).getNume() + ", " +
                                "Număr cărți secțiune: " + pair.getValue() + "}");
                    }
                    break;

                case 15:
                    System.out.println("Introduceți numele noii secțiuni:");
                    String newSectionName = scanner.nextLine();
                    Sectiune newSection = new Sectiune(newSectionName);
                    int idSectiune15 = mainService.adaugaSectiune(newSection);
                    System.out.println("S-a adăugat secțiunea cu id-ul: " + idSectiune15);
                    break;

                case 16:
                    System.out.println("Introduceți ID-ul secțiunii de șters:");
                    int idSectiune16 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaSectiune(idSectiune16) == -1){
                        System.out.println("Secțiunea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.stergeSectiune(idSectiune16);
                        System.out.println("S-a șters din sistem secțiunea cu id-ul: " + idSectiune16);
                    }
                    break;

                case 17:
                    System.out.println("Introduceți ID-ul secțiunii pentru care doriți să actualizați informațiile:");
                    int idSectiune17 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaSectiune(idSectiune17) == -1){
                        System.out.println("Secțiunea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Introduceți noul nume pentru secțiune:");
                        String numeNou = scanner.nextLine();
                        mainService.modificaNumeSectiune(idSectiune17, numeNou);
                    }
                    break;

                case 18:
                    List<AngajatBiblioteca> angajati = mainService.returneazaAngajatiAlfabetic();
                    System.out.println("Angajații bibliotecii în ordinea alfabetică a numelui și prenumelui sunt: ");
                    System.out.println(angajati);
                    break;

                case 19:
                    List<AngajatBiblioteca> angajati2 = mainService.returneazaAngajatiDupaSalariu();
                    System.out.println("Angajații bibliotecii în ordinea descrescătoare a salariului sunt: ");
                    System.out.println(angajati2);
                    break;

                case 20:
                    System.out.println("Introduceți numele angajatului:");
                    String numeAng = scanner.nextLine();
                    System.out.println("Introduceți prenumele angajatului:");
                    String prenumeAng = scanner.nextLine();
                    System.out.println("Introduceți salariul angajatului:");
                    double salariuAng = Double.parseDouble(scanner.nextLine());
                    AngajatBiblioteca angajatNou = new AngajatBiblioteca(prenumeAng, numeAng, salariuAng);
                    mainService.adaugaAngajatBiblioteca(angajatNou);
                    break;

                case 21:
                    System.out.println("Introduceți ID-ul angajatului:");
                    int idAngajat = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaAngajatBiblioteca(idAngajat) == -1){
                        System.out.println("Angajatul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.stergeAngajatBiblioteca(idAngajat);
                        System.out.println("S-a șters din sistem angajatul cu id-ul: " + idAngajat);
                    }
                    break;

                case 22:
                    System.out.println("Introduceți ID-ul angajatului pentru care doriți să actualizați informațiile:");
                    int idAngajat22 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaAngajatBiblioteca(idAngajat22) == -1){
                        System.out.println("Angajatul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        System.out.println("Introduceți noul salariu al angajatului:");
                        double salariuNou = Double.parseDouble(scanner.nextLine());
                        mainService.modificaSalariuAngajat(idAngajat22, salariuNou);
                    }
                    break;

                case 23:
                    List<Cititor> cititori = mainService.returneazaCititoriAlfabetic();
                    System.out.println("Cititorii din sistem în ordinea alfabetică a numelui și prenumelui sunt: ");
                    System.out.println(cititori);
                    break;

                case 24:
                    System.out.println("Introduceți numele de familie al cititorului:");
                    String numeCititor = scanner.nextLine();
                    System.out.println("Introduceți prenumele cititorului:");
                    String prenumeCititor = scanner.nextLine();
                    Cititor cititor = new Cititor(prenumeCititor, numeCititor);
                    int idCititor = mainService.adaugaCititor(cititor);
                    System.out.println("S-a adăugat cititorul cu id-ul: " + idCititor);
                    break;

                case 25:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititor30 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCititor(idCititor30) == -1){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.stergeCititor(idCititor30);
                        System.out.println("S-a șters din sistem cititorul cu id-ul: " + idCititor30);
                    }
                    break;

                case 26:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititor25 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCititor(idCititor25) == -1){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        List<Imprumut> imprumuturiCititor = mainService.returneazaImprumuturiCititor(idCititor25);
                        if (imprumuturiCititor.size() == 0){
                            System.out.println("Cititorul nu are nici-o carte împrumutată în prezent!");
                        }
                        else {
                            System.out.println("Împrumuturile cititorului cu id-ul " + idCititor25 + " existente în sistem sunt:");
                            System.out.println(imprumuturiCititor);
                        }
                    }
                    break;

                case 27:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idReader = Integer.parseInt(scanner.nextLine());
                    int idCititor27 = mainService.cautaCititor(idReader);

                    if (idCititor27 == -1){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }

                    else {
                        System.out.println("Introduceți ID-ul cărții:");
                        int idCarte27 = Integer.parseInt(scanner.nextLine());
                        int carte = mainService.cautaCarte(idCarte27);

                        if (carte == -1) {
                            System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                        } else if (mainService.esteCarteImprumutata(idCarte27) == true) {
                            System.out.println("Cartea cu ID-ul introdus este împrumutată altui cititor!");
                        } else if (mainService.esteCarteRezervata(idCarte27, new Date(), idCititor27) == true) {
                            System.out.println("Cartea cu ID-ul introdus este rezervată altui cititor!");
                        } else while(true) {
                            Date dataImprumut = new Date();
                            System.out.println("Introduceți data limită de returnare a cărții (dd-MM-yyyy, ex. '30-06-2021'):");
                            String string = scanner.nextLine();
                            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                            Date dataDeadline = format.parse(string);

                            if (dataDeadline.compareTo(dataImprumut) <= 0) {
                                System.out.println("Termenul de returnare a cărții nu poate fi înainte de ziua curentă" +
                                        " sau să coincidă cu aceasta!");
                            } else {
                                Imprumut imprumut = new Imprumut(
                                        idCititor27,
                                        idCarte27,
                                        dataImprumut,
                                        dataDeadline,
                                        null);
                                int idImprumut = mainService.adaugaImprumut(imprumut);
                                mainService.modificaStatusCarte(idCarte27, 1);
                                System.out.println("Cartea a fost împrumutată cu succes! Id-ul împrumutului efectuat este: " + idImprumut);
                                break;
                            }
                        }
                    }
                    break;

                case 28:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int readerId = Integer.parseInt(scanner.nextLine());

                    if (mainService.cautaCititor(readerId) == -1){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }

                    else {
                        System.out.println("Introduceți ID-ul cărții:");
                        int bookId = Integer.parseInt(scanner.nextLine());

                        if (mainService.cautaCarte(bookId) == -1){
                            System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                        }

                        else {
                            int idImprumut = mainService.cautaImprumut(readerId, bookId);
                            if (idImprumut == -1){
                                System.out.println("Cititorul și cartea cu ID-urile introduse nu corespund nici-unui împrumut activ din prezent!");
                            }
                            else{
                                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String dataReturnare = dateFormat.format(new Date());
                                mainService.modificaDataActualaReturnareImprumut(idImprumut, dataReturnare);
                                mainService.modificaStatusCarte(bookId, 0);
                            }
                        }
                    }
                    break;

                case 29:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititor26 = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaCititor(idCititor26) == -1){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        List<Rezervare> rezervariCititor = mainService.returneazaRezervariCititor(idCititor26);
                        if (rezervariCititor.size() == 0){
                            System.out.println("Cititorul nu are nici-o rezervare de carte efectuată în prezent!");
                        }
                        else {
                            System.out.println("Rezervările cititorului cu id-ul " + idCititor26 + " existente în sistem sunt:");
                            System.out.println(rezervariCititor);
                        }
                    }
                    break;

                case 30:
                    System.out.println("Introduceți ID-ul cărții de rezervat:");
                    int idCarteDeRezervat = Integer.parseInt(scanner.nextLine());

                    if (mainService.cautaCarte(idCarteDeRezervat) == -1){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }

                    else {
                        System.out.println("Introduceți ID-ul cititorului:");
                        int idCititorRezervare = Integer.parseInt(scanner.nextLine());

                        if (mainService.cautaCititor(idCititorRezervare) == -1) {
                            System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                        } else {
                            while (true) {
                                System.out.println("Introduceți data rezervării (dd-MM-yyyy, ex. '30-06-2021'):");
                                String string = scanner.nextLine();
                                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                                Date dataRezervare = format.parse(string);

                                if (dataRezervare.compareTo(new Date()) <= 0) {
                                    System.out.println("Data de rezervare a cărții nu poate fi înainte de ziua curentă sau să coincidă cu aceasta!");
                                } else if (mainService.esteCarteRezervata(idCarteDeRezervat, dataRezervare, idCititorRezervare)) {
                                    System.out.println("Cartea cu ID-ul introdus este deja rezervată altui cititor pentru data introdusă!");
                                } else {
                                    Rezervare r = new Rezervare(
                                            idCititorRezervare,
                                            idCarteDeRezervat,
                                            dataRezervare
                                    );
                                    int idRezervare = mainService.adaugaRezervare(r);
                                    System.out.println("Cartea a fost rezervată cu succes! Id-ul rezervării efectuate este: " + idRezervare);
                                    break;
                                }
                            }
                        }
                    }
                    break;

                case 31:
                    System.out.println("Introduceți ID-ul rezervării:");
                    int idRezervare = Integer.parseInt(scanner.nextLine());
                    if (mainService.cautaRezervare(idRezervare) == -1){
                        System.out.println("Rezervarea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.stergeRezervare(idRezervare);
                        System.out.println("S-a șters din sistem rezervarea cu id-ul: " + idRezervare);
                    }
                    break;

                case 0:

                    System.out.println("Oprire program....");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opțiunea aleasă este invalidă, vă rugăm să alegeți altă opțiune!");
            }
        }
    }
}

