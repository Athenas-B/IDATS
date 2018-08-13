/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifo;

import java.util.Iterator;

/**
 *
 * @author Oldřich
 */
public interface IIFO<E>{

    void zrus();        //zrušení celé fronty/zásobníku

    boolean jePrazdny();    //test prázdnosti 

    void vloz(E data);    //vloží prvek do zásobníku/fronty

    E odeber();      //odebere prvek ze zásobníku/fronty

    Iterator<E> vytvorIterator();
}
