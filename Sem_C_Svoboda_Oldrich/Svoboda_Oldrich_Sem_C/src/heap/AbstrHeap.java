/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Oldřich
 */
public class AbstrHeap<E> implements IAbstrHeap<E> {

    private Comparator<E> comp;
    private E[] pole;
    private int kapacita;
    private int obsazeno;
    private int krokKapacity;

    public AbstrHeap(Comparator<E> comp) {
        this.krokKapacity = 5;
        this.comp = comp;
        this.pole = (E[]) new Object[10];
        this.kapacita = 10;
        this.obsazeno = 0;
    }

    public AbstrHeap(Comparator<E> comp, int kapacita) {
        this.krokKapacity = 5;
        this.comp = comp;
        this.pole = (E[]) new Object[kapacita];
        this.kapacita = kapacita;
        this.obsazeno = 0;
        this.krokKapacity = (kapacita + 1) / 2;
    }

    @Override
    public void vybuduj(Iterator iterator, Comparator<E> comparator) {
        zrus();
        while (iterator.hasNext()) {
            E prvek = (E) iterator.next();
            if (obsazeno < kapacita) {
                pole[obsazeno] = prvek;
                obsazeno++;
            } else {
                zvetsiKapacitu();
                pole[obsazeno] = prvek;
                obsazeno++;
            }
        }
        reorganizuj(comparator);
    }

    private void zvetsiKapacitu() {
        zmenKapacitu(krokKapacity);
        krokKapacity = (kapacita + 1) / 2;
    }

    private void zmenKapacitu(int zmena) {
        if (zmena > 0) {
            kapacita = kapacita + zmena;
            E[] novePole = (E[]) new Object[kapacita];
            for (int i = 0; i < pole.length; i++) {
                novePole[i] = pole[i];
            }
            pole = novePole;
        } else if (zmena < 0) {
            kapacita = kapacita + zmena;
            E[] novePole = (E[]) new Object[kapacita];
            for (int i = 0; i < novePole.length; i++) {
                novePole[i] = pole[i];
            }
            pole = novePole;
        }
    }

    @Override
    public void reorganizuj(Comparator<E> komparator) {
        comp = komparator;
        for (int index = (obsazeno / 2) - 1; index >= 0; index--) {
            zaradSmerumDolu(index);
        }
    }

    public E odeberMax() {
        if (obsazeno == 0) {
            return null;
        }
        int index = 0;
        E vrat = pole[index];
        pole[index] = pole[obsazeno - 1];
        obsazeno--;

        zaradSmerumDolu(index);

        return vrat;
    }

    private void zaradSmerumDolu(int index) {
        int iSyna;
        while (indexSyna(index) < obsazeno) {
            if (indexSyna(index) + 1 >= obsazeno) {
                iSyna = indexSyna(index);
            } else if (comp.compare(pole[indexSyna(index)], pole[indexSyna(index) + 1]) > 0) {
                iSyna = indexSyna(index);
            } else {
                iSyna = indexSyna(index) + 1;
            }
            if (comp.compare(pole[index], pole[iSyna]) < 0) {
                prohod(index, iSyna);
                index = iSyna;
            } else {
                break;
            }
        }
    }

    @Override
    public void vloz(E prvek) {
        if (obsazeno < kapacita) {
            pole[obsazeno] = prvek;
            obsazeno++;
            zaradSmeremNahoru(obsazeno - 1);

        } else {
            zvetsiKapacitu();
            pole[obsazeno] = prvek;
            obsazeno++;
            zaradSmeremNahoru(obsazeno - 1);
        }

    }

    private void zaradSmeremNahoru(int indexSyna) {
        int indexOtce;
        while ((indexOtce = indexOtce(indexSyna)) >= 0) {
            if (comp.compare(pole[indexOtce], pole[indexSyna]) < 0) {
                prohod(indexOtce, indexSyna);
                indexSyna = indexOtce;
            } else {
                break;
            }
        }
    }

    private void prohod(int index1, int index2) {
        E pom = pole[index1];
        pole[index1] = pole[index2];
        pole[index2] = pom;
    }

    private int indexOtce(int index) {
        return ((index + 1) / 2) - 1;
    }

    private int indexSyna(int index) {
        return (index + 1) * 2 - 1;
    }

    @Override
    public void zrus() {
        this.pole = (E[]) new Object[10];
        this.kapacita = 10;
        this.obsazeno = 0;
    }

    @Override
    public E zpristupniMax() {
        return pole[0];
    }

    @Override
    public Comparator<E> getComp() {
        return comp;
    }

    @Override
    public void vypis() {
        System.out.println(vrat());
    }

    @Override
    public String vrat() {
        String vrat = "";
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            vrat += it.next().toString() + '\n';
        }
        return vrat;
    }

    @Override
    public int getKapacita() {
        return kapacita;
    }

    @Override
    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    @Override
    public int getObsazeno() {
        return obsazeno;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            
            int posledni = -1;

            @Override
            public boolean hasNext() {
                return posledni + 1 < obsazeno;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                posledni++;
                return pole[posledni];
            }
        };
    }

    @Override
    public boolean jePrazdny() {
        return obsazeno <= 0;
    }
    public static void main(String[] args) {
        System.out.println(1/2);
    }
}
