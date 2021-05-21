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
            System.out.println("(1).Afișează toate cărțile din sistem (în ordine alfabetică după titlu).");
            System.out.println("(2).Adaugă carte în sistem.");
            System.out.println("(3).Afișează informații despre carte.");
            System.out.println("(4).Afișează rezervările unei cărți.");


            System.out.println("**************** AUTORI ***************");
            System.out.println("(5).Afișează toți autorii din sistem (în ordine alfabetică).");
            System.out.println("(6).Adaugă autor în sistem.");
            System.out.println("(7).Afișează toate cărțile scrise de un autor din sistem.");

            System.out.println("**************** SECȚIUNI ***************");
            System.out.println("(8).Afișează toate secțiunile din bibliotecă (în ordine alfabetică).");
            System.out.println("(9).Afișează toate secțiunile din bibliotecă (după numărul descrescător de cărți).");
            System.out.println("(10).Adaugă secțiune în sistem.");

            System.out.println("**************** ANGAJAȚI ***************");
            System.out.println("(11).Afișează toți angajații din bibliotecă (în ordine alfabetică).");
            System.out.println("(12).Adaugă angajat bibliotecă în sistem.");

            System.out.println("**************** CITITORI ***************");
            System.out.println("(13).Afișează toți cititorii din sistem (în ordine alfabetică).");
            System.out.println("(14).Adaugă cititor nou în sistem.");
            System.out.println("(15).Afișează cărțile împrumutate de un cititor.");
            System.out.println("(16).Afișează cărțile rezervate de un cititor.");
            System.out.println("(17).Împrumută carte unui cititor.");
            System.out.println("(18).Returnează carte de la cititor.");
            System.out.println("(19).Rezervă carte pentru un cititor.");

            System.out.println("(0).Opreste programul.");

            int optiune = Integer.parseInt(scanner.nextLine());

            switch (optiune) {

                case 1:
                    mainService.afiseazaCartiAlfabetic();
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
                    Autor autor = mainService.gasesteAutor(prenumeAutor, numeAutor);
                    Sectiune sectiune = mainService.gasesteSectiuneDupaTitlu(sectiuneCarte);
                    if (sectiune == null) {
                        System.out.println("Secțiunea cu ID-ul introdus nu există în sistem!");
                    } else if (autor == null) {
                        System.out.println("Autorul cu numele și prenumele introduse nu există în sistem!");
                    } else {
                        Carte carte = new Carte(
                                titlu,
                                sectiune,
                                autor,
                                false
                        );
                        mainService.adaugaCarte(carte);
                        break;
                    }

                case 3:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idCarte = Integer.parseInt(scanner.nextLine());
                    mainService.afiseazaInformatiiCarte(idCarte);
                    break;

                case 4:
                    System.out.println("Introduceți ID-ul cărții:");
                    int idBook = Integer.parseInt(scanner.nextLine());
                    mainService.afiseazaRezervariCarte(idBook);
                    break;

                case 5:
                    mainService.afiseazaAutoriAlfabetic();
                    break;

                case 6:
                    System.out.println("Introduceți prenumele și numele autorului:");
                    String[] detaliiAutor = scanner.nextLine().split(" ");
                    String prenumeleAutorului = detaliiAutor[0];
                    String numeleAutorului = detaliiAutor[1];
                    Autor autorNou = new Autor(prenumeleAutorului, numeleAutorului);
                    mainService.adaugaAutor(autorNou);
                    break;

                case 7:
                    System.out.println("Introduceți prenumele și numele autorului:");
                    String[] fragmente = scanner.nextLine().split(" ");
                    String autorPrenume = fragmente[0];
                    String autorNume = fragmente[1];
                    mainService.afiseazaCartiAutor(autorPrenume, autorNume);
                    break;

                case 8:
                    mainService.afiseazaSectiuniAlfabetic();
                    break;


                case 9:
                    mainService.afiseazaSectiuniDupaNrCarti();
                    break;

                case 10:
                    System.out.println("Introduceți numele secțiunii:");
                    String numeSectiune = scanner.nextLine();
                    Sectiune sectiuneNoua = new Sectiune(numeSectiune);
                    mainService.adaugaSectiune(sectiuneNoua);
                    break;

                case 11:
                    mainService.afiseazaAngajatiBibliotecaAlfabetic();
                    break;

                case 12:
                    System.out.println("Introduceți numele angajatului:");
                    String numeAng = scanner.nextLine();
                    System.out.println("Introduceți prenumele angajatului:");
                    String prenumeAng = scanner.nextLine();
                    System.out.println("Introduceți salariul angajatului:");
                    double salariuAng = Double.parseDouble(scanner.nextLine());
                    AngajatBiblioteca angajatNou = new AngajatBiblioteca(prenumeAng, numeAng, salariuAng);
                    mainService.adaugaAngajat(angajatNou);
                    break;

                case 13:
                    mainService.afiseazaCititoriAlfabetic();
                    break;

                case 14:
                    System.out.println("Introduceți prenumele și numele cititorului:");
                    String[] detaliiCititor = scanner.nextLine().split(" ");
                    String prenumeCititor = detaliiCititor[0];
                    String numeCititor = detaliiCititor[1];
                    Cititor cititorNou = new Cititor(prenumeCititor, numeCititor);
                    mainService.adaugaCititor(cititorNou);
                    break;

                case 15:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititor = Integer.parseInt(scanner.nextLine());
                    mainService.afiseazaImprumuturiCititor(idCititor);
                    break;

                case 16:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCit = Integer.parseInt(scanner.nextLine());
                    mainService.afiseazaRezervariCititor(idCit);
                    break;

                case 17:
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idReader = Integer.parseInt(scanner.nextLine());
                    Cititor cititor = mainService.gasesteCititorDupaId(idReader);

                    if (cititor == null){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }

                    else {
                        System.out.println("Introduceți ID-ul cărții:");
                        int carteId = Integer.parseInt(scanner.nextLine());
                        Carte crt = mainService.gasesteCarteDupaId(carteId);

                        if (crt == null) {
                            System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                        } else if (crt.isEsteImprumutata() == true) {
                            System.out.println("Cartea cu ID-ul introdus este împrumutată altui cititor!");
                        } else if (crt.isEsteRezervata(new Date(), cititor) == true) {
                            System.out.println("Cartea cu ID-ul introdus este rezervată altui cititor!");
                        } else while(true) {
                            Date dataImprumut = new Date();
                            System.out.println("Introduceți data limită de returnare a cărții (dd/MM/yyyy):");
                            String string = scanner.nextLine();
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                            Date dataDeadline = format.parse(string);

                            if (dataDeadline.compareTo(dataImprumut) <= 0) {
                                System.out.println("Termenul de returnare a cărții nu poate fi înainte de ziua curentă" +
                                        " sau să coincidă cu aceasta!");
                            } else {
                                mainService.imprumutaCarteCititor(cititor, crt, dataImprumut, dataDeadline);
                                break;
                            }
                        }
                    }

                    break;

                case 18:
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
                        Imprumut i = mainService.gasesteImprumut(book, reader);

                        if (i == null){
                            System.out.println("Cititorul și cartea cu ID-urile introduse nu corespund nici-unui împrumut activ din prezent!");
                        }
                        else {
                            mainService.returneazaCarteCititor(book, reader);
                        }
                    }
                    break;

                case 19:
                    System.out.println("Introduceți ID-ul cărții de rezervat:");
                    int idCarteDeRezervat = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceți ID-ul cititorului:");
                    int idCititorRezervare = Integer.parseInt(scanner.nextLine());

                    Cititor cititorRezervare = mainService.gasesteCititorDupaId(idCititorRezervare);
                    Carte carteDeRezervat = mainService.gasesteCarteDupaId(idCarteDeRezervat);

                    if (cititorRezervare == null){
                        System.out.println("Cititorul cu ID-ul introdus nu există în sistem!");
                    }
                    else if (carteDeRezervat == null){
                        System.out.println("Cartea cu ID-ul introdus nu există în sistem!");
                    }
                    else while(true){
                        System.out.println("Introduceți data rezervării (dd/MM/yyyy):");

                        String string = scanner.nextLine();
                        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                        Date dataRezervare = format.parse(string);

                        if (dataRezervare.compareTo(new Date()) <= 0) {
                            System.out.println("Data de rezervare a cărții nu poate fi înainte de ziua curentă" +
                                    " sau să coincidă cu aceasta!");
                        }

                        else if (carteDeRezervat.isEsteRezervata(dataRezervare, cititorRezervare)){
                            System.out.println("Cartea cu ID-ul introdus este deja rezervată altui cititor la data introdusă!");
                        }

                        else {
                            mainService.rezervaCarteCititor(cititorRezervare, carteDeRezervat, dataRezervare);
                            break;
                        }
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

