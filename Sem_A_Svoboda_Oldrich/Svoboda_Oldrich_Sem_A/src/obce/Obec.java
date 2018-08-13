/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

/**
 *
 * @author Oldřich
 */
public class Obec implements IObec {

    private int psc;
    private String obec;
    private int pocetMuzu;
    private int pocetZen;
    private int celkem;

    public Obec(int psc, String obec, int celkem) {
        this.psc = psc;
        this.obec = obec;
        this.celkem = celkem;
    }

    @Override
    public int getPsc() {
        return psc;
    }

    @Override
    public void setPsc(int psc) {
        this.psc = psc;
    }

    @Override
    public String getObec() {
        return obec;
    }

    @Override
    public void setObec(String obec) {
        this.obec = obec;
    }

    @Override
    public int getPocetMuzu() {
        return pocetMuzu;
    }

    @Override
    public void setPocetMuzu(int pocetMuzu) {
        this.pocetMuzu = pocetMuzu;
    }

    @Override
    public int getPocetZen() {
        return pocetZen;
    }

    @Override
    public void setPocetZen(int pocetZen) {
        this.pocetZen = pocetZen;
    }

    @Override
    public int getCelkem() {
        return celkem;
    }

    @Override
    public void setCelkem(int celkem) {
        this.celkem = celkem;
    }

    @Override
    public String toString() {
        return "Obec{" + "psc=" + psc + ", obec=" + obec + ", pocetMuzu=" + pocetMuzu + ", pocetZen=" + pocetZen + ", celkem=" + celkem + '}';
    }

}
