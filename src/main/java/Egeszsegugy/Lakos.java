package Egeszsegugy;

public class Lakos {
    private String name;
    private String address;
    private int birthYear;
    private Korzet korzet;


    public Lakos(String name, String address, int birthYear) {
        this(name, address, birthYear, Korzet.ELSO);
    }

    public Lakos(String name, String address, int birthYear, Korzet korzet) {
        this.name = name;
        this.address = address;
        this.birthYear = birthYear;
        this.korzet = korzet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Korzet getKorzet() {
        return korzet;
    }

    public void setKorzet(Korzet korzet) {
        this.korzet = korzet;
    }

    @Override
    public String toString() {
        return name;
    }
}
