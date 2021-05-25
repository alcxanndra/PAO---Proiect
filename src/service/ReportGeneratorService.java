package service;

import model.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportGeneratorService {
//    private static final String reportsPath = "C:\\Users\\Bianca C\\Desktop\\PAO_Proiect\\src\\reports\\";
//    private ReportGeneratorService(){}
//
//    private static class  SINGLETON_HOLDER{
//        private static final ReportGeneratorService INSTANCE = new ReportGeneratorService();
//    }
//
//    public static ReportGeneratorService getInstance() {
//        return SINGLETON_HOLDER.INSTANCE;
//    }
//
//    public List<String[]> readFromCsvFile(String fileName){
//        try (BufferedReader reader = new BufferedReader(new FileReader(reportsPath + fileName))){
//            List<String[]> list = new ArrayList<>();
//            String line = reader.readLine();
//            while((line = reader.readLine()) != null){
//                String[] array = line.split(",");
//                list.add(array);
//
//            }
//        return list;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public void writeToCsvFile(String fileName, StringBuilder rowContent){
//        try {
//            String filePath = reportsPath + fileName;
//            Files.write(Paths.get(filePath), rowContent.toString().getBytes(), StandardOpenOption.APPEND);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * generating BooksOrderedByTitleReport
//     */
//
//    public void generateBooksOrderedByTitleReport(List<Carte> bookList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_CARTE");
//        reportContent.append(",");
//        reportContent.append("TITLU_CARTE");
//        reportContent.append(",");
//        reportContent.append("SECTIUNE");
//        reportContent.append(",");
//        reportContent.append("PRENUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("NUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("STATUS");
//        reportContent.append("\n");
//
//        bookList.forEach(book -> {
//            reportContent.append(book.getId());
//            reportContent.append(",");
//            reportContent.append(book.getTitlu());
//            reportContent.append(",");
//            reportContent.append(book.getSectiune().getNume());
//            reportContent.append(",");
//            reportContent.append(book.getAutor().getPrenume());
//            reportContent.append(" ");
//            reportContent.append(book.getAutor().getNume());
//            reportContent.append(",");
//            if (book.isEsteImprumutata() == false) {
//                reportContent.append("Neimprumutata");
//            }
//            else {
//                reportContent.append("Imprumutata");
//            }
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateSectionsOrderedByNameReport(List<Sectiune> sectionList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_SECTIUNE");
//        reportContent.append(",");
//        reportContent.append("NUME_SECTIUNE");
//        reportContent.append("\n");
//
//        sectionList.forEach(section -> {
//            reportContent.append(section.getId());
//            reportContent.append(",");
//            reportContent.append(section.getNume());
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateSectionsOrderedByNumberOfBooksReport(List<Sectiune> sectionList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_SECTIUNE");
//        reportContent.append(",");
//        reportContent.append("NUME_SECTIUNE");
//        reportContent.append(",");
//        reportContent.append("NUMAR_CARTI");
//        reportContent.append("\n");
//
//        sectionList.forEach(section -> {
//            reportContent.append(section.getId());
//            reportContent.append(",");
//            reportContent.append(section.getNume());
//            reportContent.append(",");
//            reportContent.append(section.getCartiSectiune().size());
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateAuthorsOrderedByNameReport(List<Autor> authorList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_AUTOR");
//        reportContent.append(",");
//        reportContent.append("PRENUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("NUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("CARTI_IN_SISTEM");
//        reportContent.append("\n");
//
//        authorList.forEach(author -> {
//            reportContent.append(author.getId());
//            reportContent.append(",");
//            reportContent.append(author.getPrenume());
//            reportContent.append(",");
//            reportContent.append(author.getNume());
//            reportContent.append(",");
//            for (Carte c : author.getCartiScrise()) {
//                reportContent.append(c.getTitlu());
//                if (author.getCartiScrise().indexOf(c) != (author.getCartiScrise().size() - 1)){
//                    reportContent.append("; ");
//                }
//            }
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateReadersOrderedByNameReport(List<Cititor> readersList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_CITITOR");
//        reportContent.append(",");
//        reportContent.append("NUME_CITITOR");
//        reportContent.append(",");
//        reportContent.append("PRENUME_CITITOR");
//
//        reportContent.append("\n");
//
//        readersList.forEach(reader -> {
//            reportContent.append(reader.getId());
//            reportContent.append(",");
//            reportContent.append(reader.getNume());
//            reportContent.append(",");
//            reportContent.append(reader.getPrenume());
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateEmployeesOrderedByNameReport(List<AngajatBiblioteca> employeesList){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_ANGAJAT");
//        reportContent.append(",");
//        reportContent.append("PRENUME_ANGAJAT");
//        reportContent.append(",");
//        reportContent.append("NUME_ANGAJAT");
//        reportContent.append(",");
//        reportContent.append("SALARIU_ANGAJAT");
//        reportContent.append("\n");
//
//        employeesList.forEach(employee -> {
//            reportContent.append(employee.getId());
//            reportContent.append(",");
//            reportContent.append(employee.getPrenume());
//            reportContent.append(",");
//            reportContent.append(employee.getNume());
//            reportContent.append(",");
//            reportContent.append(employee.getSalariu());
//            reportContent.append("\n");
//        });
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//    }
//
//    public void generateBooksOfAuthorOrderedByTitleReport(Autor autor){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_AUTOR");
//        reportContent.append(",");
//        reportContent.append("PRENUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("NUME_AUTOR");
//        reportContent.append(",");
//        reportContent.append("TITLU_CARTE");
//        reportContent.append("\n");
//
//        for (Carte c : autor.getCartiScrise()) {
//            reportContent.append(autor.getId());
//            reportContent.append(",");
//            reportContent.append(autor.getPrenume());
//            reportContent.append(",");
//            reportContent.append(autor.getNume());
//            reportContent.append(",");
//            reportContent.append(c.getTitlu());
//            reportContent.append("\n");
//        }
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    public void generateLoansOfReaderReport(Cititor cititor){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_CITITOR");
//        reportContent.append(",");
//        reportContent.append("PRENUME_CITITOR");
//        reportContent.append(",");
//        reportContent.append("NUME_CITITOR");
//        reportContent.append(",");
//        reportContent.append("TITLU_CARTE_IMPRUMUTATA");
//        reportContent.append(",");
//        reportContent.append("DATA_IMPRUMUT");
//        reportContent.append(",");
//        reportContent.append("TERMEN_DE_RETURNARE");
//        reportContent.append(",");
//        reportContent.append("DATA_ACTUALA_RETURNARE");
//        reportContent.append("\n");
//
//        for (Imprumut i : cititor.getImprumuturiCarti()) {
//            reportContent.append(i.getCititor().getId());
//            reportContent.append(",");
//            reportContent.append(i.getCititor().getPrenume());
//            reportContent.append(",");
//            reportContent.append(i.getCititor().getNume());
//            reportContent.append(",");
//            reportContent.append(i.getCarte().getTitlu());
//            reportContent.append(",");
//
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            String dataImprumutStr = dateFormat.format(i.getDataImprumut());
//            String dataDeadlineReturnareStr = dateFormat.format(i.getDataDeadlineReturnare());
//            String dataActualaReturnareStr = dateFormat.format(i.getDataActualaReturnare());
//
//            reportContent.append(dataImprumutStr);
//            reportContent.append(",");
//            reportContent.append(dataDeadlineReturnareStr);
//            reportContent.append(",");
//            reportContent.append(dataActualaReturnareStr);
//            reportContent.append("\n");
//        }
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    public void generateHoldRequestsOfReaderReport(Cititor cititor){
//        StringBuilder reportContent = new StringBuilder();
//
//        reportContent.append("ID_CITITOR");
//        reportContent.append(",");
//        reportContent.append("PRENUME_CITITOR");
//        reportContent.append(",");
//        reportContent.append("NUME_CITITOR");
//        reportContent.append(",");
//        reportContent.append("TITLU_CARTE_REZERVATA");
//        reportContent.append(",");
//        reportContent.append("DATA_REZERVARII");
//        reportContent.append("\n");
//
//        for (Rezervare r : cititor.getRezervariCarti()) {
//            reportContent.append(r.getCititor().getId());
//            reportContent.append(",");
//            reportContent.append(r.getCititor().getPrenume());
//            reportContent.append(",");
//            reportContent.append(r.getCititor().getNume());
//            reportContent.append(",");
//            reportContent.append(r.getCarte().getTitlu());
//            reportContent.append(",");
//
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            String dataRezervareStr = dateFormat.format(r.getDataRezervare());
//
//            reportContent.append(dataRezervareStr);
//            reportContent.append("\n");
//        }
//
//        String fileExtension = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String filePath = reportsPath + fileExtension + ".csv";
//
//        try {
//            Files.createFile(Paths.get(filePath));
//            Files.write(Paths.get(filePath), reportContent.toString().getBytes());
//            System.out.println("Report was generated to: " + filePath);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
}