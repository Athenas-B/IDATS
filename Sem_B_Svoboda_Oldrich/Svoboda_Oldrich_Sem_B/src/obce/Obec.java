/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

import java.util.Objects;

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
    private int kraj;

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

    public int getKraj() {
        return kraj;
    }

    public void setKraj(int kraj) {
        this.kraj = kraj;
    }

    @Override
    public String toString() {
        return "Obec{" + "psc=" + psc + ", obec=" + obec + ", pocetMuzu=" + pocetMuzu + ", pocetZen=" + pocetZen + ", celkem=" + celkem + ", kraj=" + kraj + '}';
    }

    @Override
    public int compareTo(IObec o) {
        return this.obec.compareToIgnoreCase(o.getObec());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.psc;
        hash = 29 * hash + Objects.hashCode(this.obec);
        hash = 29 * hash + this.pocetMuzu;
        hash = 29 * hash + this.pocetZen;
        hash = 29 * hash + this.celkem;
        hash = 29 * hash + this.kraj;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Obec other = (Obec) obj;
        if (this.psc != other.psc) {
            return false;
        }
        if (!Objects.equals(this.obec, other.obec)) {
            return false;
        }
        if (this.pocetMuzu != other.pocetMuzu) {
            return false;
        }
        if (this.pocetZen != other.pocetZen) {
            return false;
        }
        if (this.celkem != other.celkem) {
            return false;
        }
        if (this.kraj != other.kraj) {
            return false;
        }
        return true;
    }

}
