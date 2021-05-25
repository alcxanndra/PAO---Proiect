package utils;

public class Queries {
    public static final String RETRIEVE_ALL_BOOKS = "SELECT id, titlu, idSectiune, idAutor, esteImprumutata FROM carti ORDER BY titlu;";
    public static final String RETRIEVE_ALL_EMPLOYEES = "SELECT id, prenume, nume, salariu FROM angajatibiblioteca ORDER BY nume, prenume;";
    public static final String RETRIEVE_ALL_EMPLOYEES_BY_SALARY_DESC = "SELECT id, prenume, nume, salariu FROM angajatibiblioteca ORDER BY salariu DESC;";
    public static final String RETRIEVE_ALL_READERS = "SELECT id, prenume, nume FROM cititori ORDER BY nume, prenume;";
    public static final String RETRIEVE_ALL_AUTHORS = "SELECT id, prenume, nume FROM autori ORDER BY nume, prenume;";
    public static final String RETRIEVE_ALL_SECTIONS = "SELECT id, nume FROM sectiuni ORDER BY nume;";
    public static final String RETRIEVE_ALL_SECTIONS_BY_NR_OF_BOOKS = "SELECT s.id, s.nume, COUNT(c.id) AS nr_carti FROM sectiuni s LEFT JOIN carti c ON s.id = c.idSectiune GROUP BY s.id, s.nume ORDER BY nr_carti DESC;";

    public static final String DELETE_BOOK = "DELETE FROM carti WHERE id = ?;";
    public static final String DELETE_AUTHOR = "DELETE FROM autori WHERE id = ?;";
    public static final String DELETE_SECTION = "DELETE FROM sectiuni WHERE id = ?;";
    public static final String DELETE_EMPLOYEE = "DELETE FROM angajatibiblioteca WHERE id = ?;";
    public static final String DELETE_READER = "DELETE FROM cititori WHERE id = ?;";
    public static final String DELETE_HOLD_REQUEST = "DELETE FROM rezervari WHERE idRezervare = ?;";

    public static final String UPDATE_BOOK_TITLE = "UPDATE carti SET titlu = ? WHERE id = ?;";
    public static final String UPDATE_BOOK_AUTHOR = "UPDATE carti SET idAutor = ? WHERE id = ?;";
    public static final String UPDATE_BOOK_SECTION = "UPDATE carti SET idSectiune = ? WHERE id = ?;";
    public static final String UPDATE_BOOK_STATUS = "UPDATE carti SET esteImprumutata = ? WHERE id = ?;";
    public static final String UPDATE_SECTION_NAME = "UPDATE sectiuni SET nume = ? WHERE id = ?;";
    public static final String UPDATE_EMPLOYEE_SALARY = "UPDATE angajatibiblioteca SET salariu_lunar = ? WHERE id = ?;";
    public static final String UPDATE_LOAN_RETURN_DATE = "UPDATE imprumuturi SET dataActualaReturnare = STR_TO_DATE(?, '%d-%m-%Y') WHERE idImprumut = ?;";
    
    public static final String GET_BOOK_BY_ID = "SELECT * FROM carti WHERE id = ?";
    public static final String GET_AUTHOR_BY_ID = "SELECT * FROM autori WHERE id = ?";
    public static final String GET_SECTION_BY_ID = "SELECT * FROM sectiuni WHERE id = ?";
    public static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM angajatibiblioteca WHERE id = ?";
    public static final String GET_READER_BY_ID = "SELECT * FROM cititori WHERE id = ?";
    public static final String GET_HOLD_REQUEST_BY_ID = "SELECT * FROM rezervari WHERE idRezervare = ?";
    public static final String GET_BOOK_LOAN_STATUS = "SELECT esteImprumutata FROM carti WHERE id = ?";
    public static final String GET_BOOK_RESERVED_STATUS = "SELECT * FROM rezervari WHERE idCarte = ? AND dataRezervare = STR_TO_DATE(?, '%d-%m-%Y') AND idCititor <> ?";

    public static final String GET_AUTHOR_ID_BY_NAME = "SELECT id FROM autori WHERE prenume = ? AND nume = ?;";
    public static final String GET_SECTION_ID_BY_NAME = "SELECT id FROM sectiuni WHERE nume = ?;";

    public static final String RETRIEVE_BOOK_INFO = "SELECT c.titlu, s.nume, a.prenume, a.nume FROM carti c JOIN autori a ON c.idAutor = a.id JOIN sectiuni s ON s.id = c.idSectiune WHERE c.id = ?;";
    public static final String RETRIEVE_LOAN_BY_READER_BOOK = "SELECT * FROM imprumuturi WHERE idCititor = ? and idCarte = ?;";

    public static final String RETRIEVE_AUTHOR_BOOKS = "SELECT c.id, c.titlu, c.idSectiune, c.idAutor, c.esteImprumutata FROM autori a LEFT JOIN carti c ON a.id = c.idAutor WHERE a.id = ? order by c.titlu;";
    public static final String GET_BOOK_HOLD_REQUESTS = "SELECT r.idRezervare, r.idCarte, r.idCititor, r.dataRezervare FROM carti c JOIN rezervari r ON c.id = r.idCarte WHERE c.id = ?;";
    public static final String GET_READER_LOANS = "SELECT idImprumut, idCititor, idCarte, dataImprumut, dataDeadlineReturnare, dataActualaReturnare FROM cititori c JOIN imprumuturi i ON c.id = i.idCititor WHERE c.id = ?;";
    public static final String GET_READER_HOLD_REQUESTS = "SELECT idRezervare, idCititor, idCarte, dataRezervare FROM cititori c JOIN rezervari r ON c.id = r.idCititor WHERE c.id = ?;";

    public static final String INSERT_NEW_BOOK = "INSERT INTO carti(id, titlu, idSectiune, idAutor, esteImprumutata) VALUES (null,?,?,?,0)";
    public static final String INSERT_NEW_SECTION = "INSERT INTO sectiuni(id, nume) VALUES (null,?)";
    public static final String INSERT_NEW_LIBRARY_EMPLOYEE = "INSERT INTO angajatibiblioteca(id, prenume, nume, salariu) VALUES (null,?,?,?)";
    public static final String INSERT_NEW_READER = "INSERT INTO cititori(id, prenume, nume) VALUES (null,?,?)";
    public static final String INSERT_NEW_AUTHOR = "INSERT INTO autori(id, prenume, nume) VALUES (null,?,?)";
    public static final String INSERT_NEW_HOLD_REQUEST = "insert into rezervari(idCititor, idCarte, dataRezervare)  VALUES (?,?,STR_TO_DATE(?, '%d-%m-%Y'));";
    public static final String INSERT_NEW_LOAN = "insert into imprumuturi(idCititor, idCarte, dataImprumut, dataDeadlineReturnare, dataActualaReturnare) VALUES (?,?,STR_TO_DATE(?, '%d-%m-%Y'),STR_TO_DATE(?, '%d-%m-%Y'),null);";

}