package Egeszsegugy;

public class Paciens extends Lakos{
    private String allergia;
    private String mutet;

    public Paciens(String name, String address, int birthYear,
                   Korzet korzet, String allergia, String mutet) {
        super(name, address, birthYear, korzet);
        this.allergia = allergia;
        this.mutet = mutet;
    }

    public String getAllergia() {
        return allergia;
    }

    public void setAllergia(String allergia) {
        this.allergia = allergia;
    }

    public String getMutet() {
        return mutet;
    }

    public void setMutet(String mutet) {
        this.mutet = mutet;
    }

}
