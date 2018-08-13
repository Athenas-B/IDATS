/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Oldřich
 */
public interface IAbstrHeap<E> extends Iterable<E> {

    Comparator<E> getComp();

    int getKapacita();

    int getObsazeno();

    E odeberMax();

    void reorganizuj(Comparator<E> komparator);

    void setKapacita(int kapacita);

    boolean jePrazdny();

    void vloz(E prvek);

    String vrat();

    void vybuduj(Iterator iterator, Comparator<E> comparator);

    void vypis();

    E zpristupniMax();

    void zrus();

}
