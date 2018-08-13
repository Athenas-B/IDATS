/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doubleList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import obce.IObec;

/**
 *
 * @author Oldřich
 */
public class AbstrDoubleList<T> implements IAbstrDoubleList<T>, Serializable {

    private Prvek<T> aktualni = null;
    private Prvek<T> prvni = null;

    public AbstrDoubleList() {
    }

    @Override
    public void zrus() {
        aktualni = null;
        prvni = null;
    }

    @Override
    public boolean jePrazdy() {
        if (prvni == null) {
            return true;
        }
        return false;
    }

    @Override
    public void vlozPrvni(T obj) {
        if (prvni == null) {
            aktualni = new Prvek<>(obj);
            prvni = aktualni;
            aktualni.setNasledovnik(prvni);
            aktualni.setPredchudce(prvni);
        } else {
            Prvek<T> novy = new Prvek<>(obj);
            novy.setNasledovnik(prvni);
            novy.setPredchudce(prvni.getPredchudce());
            prvni.getPredchudce().setNasledovnik(novy);
            prvni.setPredchudce(novy);

            prvni = novy;

        }
    }

    @Override
    public void vlozPosledni(T obj) {
        if (prvni == null) {
            vlozPrvni(obj);
        } else {
            Prvek<T> novy = new Prvek<>(obj);
            novy.setPredchudce(prvni.predchudce);
            novy.setNasledovnik(prvni);
            prvni.getPredchudce().setNasledovnik(novy);
            prvni.setPredchudce(novy);
        }
    }

    @Override
    public void vlozNaslednika(T obj) {
        if (aktualni == null) {
            vlozPrvni(obj);
        } else {
            Prvek<T> novy = new Prvek<>(obj);
            novy.setPredchudce(aktualni);
            novy.setNasledovnik(aktualni.getNasledovnik());

            aktualni.getNasledovnik().setPredchudce(novy);
            aktualni.setNasledovnik(novy);
        }
    }

    @Override
    public void vlozPredchudce(T obj) {
        if (aktualni == null) {
            vlozPrvni(obj);
        } else {
            Prvek<T> novy = new Prvek<>(obj);

            novy.setPredchudce(aktualni.getPredchudce());
            novy.setNasledovnik(aktualni);

            aktualni.getPredchudce().setNasledovnik(novy);
            aktualni.setPredchudce(novy);
        }
    }

    @Override
    public T zpristupniAktualni() {
        if (aktualni == null) {
            return null;
        }
        return aktualni.getObsah();
    }

    @Override
    public T zpristupniPrvni() {
        if (prvni == null) {
            return null;
        }
        aktualni = prvni;
        return prvni.getObsah();
    }

    @Override
    public T zpristupniPosledni() {
        if (prvni == null) {
            return null;
        }
        aktualni = prvni.getPredchudce();
        return prvni.getPredchudce().getObsah();
    }

    @Override
    public T zpristupniNaslednika() {
        if (aktualni == null) {
            return null;
        }
        aktualni = aktualni.getNasledovnik();
        return aktualni.getObsah();
    }

    @Override
    public T zpristupniPredchudce() {
        if (aktualni == null) {
            return null;
        }
        aktualni = aktualni.getPredchudce();
        return aktualni.getObsah();
    }

    @Override
    public T odeberAktualni() {
        if (prvni == null) {
            return null;
        }
        Prvek<T> vrat = aktualni;
        if (vrat == null) {
            return null;
        } else if (vrat.nasledovnik == vrat) {
            zrus();
            return vrat.obsah;
        }

        aktualni.getPredchudce().setNasledovnik(aktualni.getNasledovnik());
        aktualni.getNasledovnik().setPredchudce(aktualni.getPredchudce());
        if (prvni == aktualni) {
            prvni = prvni.getNasledovnik();
        }
        aktualni = prvni;

        return vrat.obsah;
    }

    @Override
    public T odeberPrvni() {
        Prvek<T> vrat = prvni;
        if (vrat == null) {
            return null;
        } else if (vrat.nasledovnik == vrat) {
            zrus();
            return vrat.obsah;
        }

        prvni.getPredchudce().setNasledovnik(prvni.getNasledovnik());
        prvni.getNasledovnik().setPredchudce(prvni.getPredchudce());

        if (aktualni == prvni) {
            aktualni = prvni.nasledovnik;
        }
        prvni = prvni.nasledovnik;
        return vrat.obsah;
    }

    @Override
    public T odeberPosledni() {
        if (prvni == null) {
            return null;
        }
        Prvek<T> vrat = prvni.getPredchudce();
        if (vrat == null) {
            return null;
        } else if (vrat.nasledovnik == vrat) {
            zrus();
            return vrat.obsah;
        }

        vrat.getPredchudce().setNasledovnik(prvni);
        prvni.setPredchudce(vrat.getPredchudce());

        if (aktualni == vrat) {
            aktualni = vrat.nasledovnik;
        }
        return vrat.obsah;
    }

    @Override
    public T odeberNaslednika() {
        if (prvni == null) {
            return null;
        }
        Prvek<T> vrat = aktualni.getNasledovnik();
        if (vrat == null) {
            return null;
        } else if (vrat.nasledovnik == vrat) {
            zrus();
            return vrat.obsah;
        }

        aktualni.setNasledovnik(vrat.getNasledovnik());
        vrat.getNasledovnik().setPredchudce(aktualni);

        if (prvni == vrat) {
            prvni = vrat.nasledovnik;
        }
        return vrat.obsah;
    }

    @Override
    public T odeberPredchudce() {
        if (prvni == null) {
            return null;
        }
        Prvek<T> vrat = aktualni.getPredchudce();
        if (vrat == null) {
            return null;
        } else if (vrat.nasledovnik == vrat) {
            zrus();
            return vrat.obsah;
        }

        vrat.getPredchudce().setNasledovnik(aktualni);
        aktualni.setPredchudce(vrat.getPredchudce());

        if (prvni == vrat) {
            prvni = vrat.nasledovnik;
        }
        return vrat.obsah;
    }

    @Override
    public Iterator vytvorIterator() {
        return new Iterator<T>() {

            private Prvek<T> aktualni = null;

            @Override
            public boolean hasNext() {
                if (prvni == null) {
                    return false;
                }
                return (prvni.getPredchudce() != aktualni);

            }

            @Override
            public T next() {
                if (prvni == null) {
                    throw new NoSuchElementException();
                }
                if (aktualni == null) {
                    aktualni = prvni;
                } else {
                    Prvek nasledovnik = aktualni.getNasledovnik();
                    aktualni = nasledovnik;
                }
                return aktualni.getObsah();

            }

            @Override
            public void remove() {
                odeberAktualni();
            }
        };
    }

    @Override
    public String toString() {
        return "AbstrDoubleList{" + "aktualni=" + aktualni + ", prvni=" + prvni + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return vytvorIterator();
    }

    @Override
    public boolean najdiData(T reference) {
        aktualni = prvni;
        if (aktualni == null) {
            return false;
        }
        do {
            if (aktualni.obsah==reference) {
                return true;
            } else {
                aktualni = aktualni.nasledovnik;
            }
        } while (aktualni != prvni);
        return false;
    }

    private class Prvek<E> {

        private Prvek<E> predchudce = null;
        private Prvek<E> nasledovnik = null;
        private E obsah;

        public Prvek(E obsah) {
            this.obsah = obsah;
        }

        public Prvek<E> getPredchudce() {
            return predchudce;
        }

        public void setPredchudce(Prvek<E> predchudce) {
            this.predchudce = predchudce;
        }

        public Prvek<E> getNasledovnik() {
            return nasledovnik;
        }

        public void setNasledovnik(Prvek<E> nasledovnik) {
            this.nasledovnik = nasledovnik;
        }

        public E getObsah() {
            return obsah;
        }

        public void setObsah(E obsah) {
            this.obsah = obsah;
        }

        @Override
        public String toString() {
            return "Prvek{" + "predchudce=" + predchudce.obsah + ", nasledovnik=" + nasledovnik.obsah + ", obsah=" + obsah + '}';
        }

    }

//    public static void main(String[] args) {
//        AbstrDoubleList a = new AbstrDoubleList();
//        System.out.println(a);
//        System.out.println(a.vytvorIterator().hasNext());
//        System.out.println(a.jePrazdy());
//        a.vlozPrvni(new Integer(1));
//        a.vlozPrvni(new Integer(2));
//        a.vlozPrvni(new Integer(3));
//        a.vlozPrvni(new Integer(4));
//        System.out.println(a.vytvorIterator().hasNext());
//        a.vlozPosledni(new Integer(5));
//
//        System.out.println("--------------");
//        for (Object object : a) {
//            System.out.println(object);
//        }
//
//        System.out.println(a.jePrazdy());
//
//        System.out.println(a);
//        a.odeberAktualni();
//        System.out.println("aktualni");
//        System.out.println(a);
//        System.out.println("--------------");
//        for (Object object : a) {
//            System.out.println(object);
//        }
//        System.out.println("--------------");
//        System.out.println(a);
//        System.out.println("naslesnik");
//        a.odeberNaslednika();
//        for (Object object : a) {
//            System.out.println(object);
//        }
//        System.out.println("--------------");
//        System.out.println(a);
//        System.out.println("predchudce");
//        a.odeberPredchudce();
//        for (Object object : a) {
//            System.out.println(object);
//        }
//        
//       
//    }
}
