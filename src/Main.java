import model.*;
import service.MainService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MainService mainService = new MainService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Alegeți una dintre opțiunile următoare: ");

            System.out.println("**************** CĂRȚI ***************");
            System.out.println("(1).Afișează toate cărțile din sistem.");
            System.out.println("(2).Adaugă carte în sistem.");
            System.out.println("(3).Afișează informații despre carte.");

            System.out.println("**************** AUTORI ***************");
            System.out.println("(4).Afișează toți autorii din sistem (în ordine alfabetică).");
            System.out.println("(5).Adaugă autor în sistem.");
            System.out.println("(6).Afișează toate cărțile scrise de un autor din sistem.");

            System.out.println("**************** SECȚIUNI ***************");
            System.out.println("(7).Afișează toate secțiunile din bibliotecă (în ordine alfabetică).");
            System.out.println("(8).Afișează toate secțiunile din bibliotecă (după numărul de cărți).");
            System.out.println("(9).Adaugă secțiune în sistem.");

            System.out.println("**************** ANGAJAȚI ***************");
            System.out.println("(10).Afișează toți angajații din bibliotecă.");
            System.out.println("(11).Adaugă angajat bibliotecă în sistem.");

            System.out.println("**************** CITITORI ***************");
            System.out.println("(12).Afișează toți cititorii din sistem.");
            System.out.println("(13).Adaugă cititor nou în sistem.");
            System.out.println("(14).Afișează cărțile împrumutate de un cititor.");
            System.out.println("(15).Împrumută carte unui cititor.");
            System.out.println("(16).Returnează carte de la cititor.");
            System.out.println("(17).Rezervă carte pentru un cititor.");

            System.out.println("(0).Opreste programul.");

            int optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {

                case 1:
                    mainService.afiseazaCarti();
                    break;

                case 2:
                    System.out.println("Introduceți titlul cărții:");
                    String titlu = scanner.nextLine();
                    System.out.println("Introduceți ID-ul secțiunii din care face parte cartea:");
                    int sectiuneCarte = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceți prenumele autorului cărții:");
                    String prenumeAutor = scanner.nextLine();
                    System.out.println("Introduceți numele autorului cărții:");
                    String numeAutor = scanner.nextLine();
                    Autor autor = mainService.gasesteAutor(prenumeAutor, numeAutor);
                    Sectiune sectiune = mainService.gasesteSectiune(sectiuneCarte);
                    if (sectiune == null) {
                        System.out.println("Secțiunea cu ID-ul introdus nu există în sistem!");
                    } else if (autor == null) {
                        System.out.println("Autorul cu numele și prenumele introduse nu există în sistem!");
                    } else {
                        Carte carte = new Carte(
                                titlu,
                                mainService.gasesteSectiune(sectiuneCarte),
                                mainService.gasesteAutor(prenumeAutor, numeAutor),
                                false
                        );
                        mainService.adaugaCarte(carte);
                        break;
                    }

                case 3:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idCarte = Integer.parseInt(scanner.nextLine());
                    mainService.afiseazaInformatiiCarte(idCarte);

                case 4:
                    mainService.afiseazaAutoriAlfabetic();
                    break;

                case 5:
                    System.out.println("Introduceți prenumele și numele autorului:");
                    String[] detaliiAutor = scanner.nextLine().split(" ");
                    String prenumeleAutorului = detaliiAutor[0];
                    String numeleAutorului = detaliiAutor[1];
                    Autor autorNou = new Autor(prenumeleAutorului, numeleAutorului);
                    mainService.adaugaAutor(autorNou);
                    break;

                case 6:
                    System.out.println("Introduceți prenumele și numele autorului:");
                    String[] fragmente = scanner.nextLine().split(" ");
                    String autorPrenume = fragmente[0];
                    String autorNume = fragmente[1];
                    mainService.afiseazaCartiAutor(autorPrenume, autorNume);
                    break;

                case 7:
                    mainService.afiseazaSectiuniAlfabetic();
                    break;


                case 8:
                    mainService.afiseazaSectiuniDupaNrCarti();
                    break;

                case 9:
                    System.out.println("Introduceți numele secțiunii:");
                    String numeSectiune = scanner.nextLine();
                    Sectiune sectiuneNoua = new Sectiune(numeSectiune);
                    mainService.adaugaSectiune(sectiuneNoua);
                    break;

                case 10:
                    mainService.afiseazaAngajatiBiblioteca();
                    break;

                case 11:
                    System.out.println("Introduceți numele angajatului:");
                    String numeAng = scanner.nextLine();
                    System.out.println("Introduceți prenumele angajatului:");
                    String prenumeAng = scanner.nextLine();
                    System.out.println("Introduceți salariul angajatului:");
                    double salariuAng = Double.parseDouble(scanner.nextLine());
                    AngajatBiblioteca angajatNou = new AngajatBiblioteca(prenumeAng, numeAng, salariuAng);
                    mainService.adaugaAngajat(angajatNou);
                    break;

                case 12:
                    mainService.afiseazaCititori();
                    break;

                case 13:
                    System.out.println("Introduceți prenumele și numele cititorului:");
                    String[] detaliiCititor = scanner.nextLine().split(" ");
                    String prenumeCititor = detaliiCititor[0];
                    String numeCititor = detaliiCititor[1];
                    Cititor cititorNou = new Cititor(prenumeCititor, numeCititor);
                    mainService.adaugaCititor(cititorNou);
                    break;

                case 14:
                    System.out.println("Introduceți prenumele și numele cititorului:");
                    String[] input = scanner.nextLine().split(" ");
                    String cititorPrenume = input[0];
                    String cititorNume = input[1];
                    mainService.afiseazaImprumuturiCititor(cititorPrenume, cititorNume);
                    break;

                case 15:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCit = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceți ID-ul cărții:");
                    int carteId = Integer.parseInt(scanner.nextLine());
                    Cititor cititor = mainService.gasesteCititorDupaId(idCit);
                    Carte crt = mainService.gasesteCarteDupaId(carteId);
                    Date dataImprumut = new Date();
                    System.out.println("Introduceți data limită de returnare a cărții (dd/MM/yyyy):");
                    String string = scanner.nextLine();
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    Date dataDeadline = format.parse(string);

                    if (cititor == null){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else if (crt == null){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else if (crt.isEsteImprumutata() == true) {
                        System.out.println("Cartea cu ID-ul introdus este deja împrumutată altui cititor!");
                    }
                    else {
                        mainService.imprumutaCarteCititor(cititor, crt, dataImprumut, dataDeadline);
                    }
                    break;

                case 16:

                    System.out.println("Introduceți ID-ul cititorului:");
                    int readerId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Introduceți ID-ul cărții:");
                    int bookId = Integer.parseInt(scanner.nextLine());

                    Cititor reader = mainService.gasesteCititorDupaId(readerId);
                    Carte book = mainService.gasesteCarteDupaId(bookId);

                    if (reader == null){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else if (book == null){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.returneazaCarteCititor(book, reader);
                    }
                    break;

                case 17:

                    System.out.println("Introduceți ID-ul cărții de rezervat:");
                    int idCarteDeRezervat = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititorRezervare = Integer.parseInt(scanner.nextLine());
                    Date dataRezervare = new Date();

                    Cititor cititorRezervare = mainService.gasesteCititorDupaId(idCititorRezervare);
                    Carte carteDeRezervat = mainService.gasesteCarteDupaId(idCarteDeRezervat);

                    if (cititorRezervare == null){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else if (carteDeRezervat == null){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else {
                        mainService.rezervaCarteCititor(cititorRezervare, carteDeRezervat);
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

