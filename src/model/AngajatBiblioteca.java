package model;

public class AngajatBiblioteca extends Persoana {

    private double salariu;

    public AngajatBiblioteca(String prenume, String nume, double salariu) {
        super(prenume, nume);
        this.salariu = salariu;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        return "Bibliotecar{" +
                "salariu=" + salariu +
                ", id=" + id +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
