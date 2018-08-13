/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

import java.io.Serializable;

/**
 *
 * @author Oldřich
 */
public interface IObyvatele extends Serializable{

    int importData(String soubor);
    /*–  provede  import  dat  z datového  souboru 
     kraje.csv,  kde  číslo  kraje  odpovídá  indexu  pole.  Návratová  hodnota  přestavuje  počet 
     úspěšně načtených záznamů.*/

    void vlozObec(IObec obec, enumPozice pozice, int kraj);/* - vloží novou  obec  do  seznamu  obcí  na  příslušnou  pozici  (první,  poslední,  předchůdce, 
     následník), v odpovídajícím kraji*/


    IObec zpristupniObec(enumPozice pozice, int kraj);/*  -  zpřístupní 
     obec  z požadované  pozice  (první,  poslední,  předchůdce,  následník,  aktuální), 
     v odpovídajícím kraji*/


    IObec odeberObec(enumPozice pozice, int kraj);/*  -  odebere  obec
     z požadované  pozice (první, poslední, předchůdce, následník,  aktuální), v odpovídajícím 
     kraji*/


    float zjistiPrumer(int kraj);/*  –  zjistí  průměrný  počet  obyvatel  v kraji, 
     pokud je hodnota kraje rovna nule, pak je průměr spočítán pro všechny kraje.*/


    void zobrazObce(int kraj);/*  –  pomocí  iterátoru  provede  výpis  obcí  v daném 
     kraji, pokud je hodnota kraje rovna nule, pak jsou vypsány všechny kraje.*/


    void zobrazObceNadPrumer(int kraj);    /*  –  pomocí  iterátoru  provede  výpis 
     obcí,  které mají v daném kraji nadprůměrný počet obyvatel. Pokud je hodnota kraje rovna 
     nule, pak je průměr spočítán pro všechny kraje.*/


    void zrus(int kraj);/* – zruší všechny obce v kraji.  Pokud je hodnota kraje rovna 
     nule, pak zruší všechny obce.*/


    public String vratObceNadPrumer(int kraj);

    public String vratObce(int kraj);

    void vytvorKraj(String kraj);

    String[] vratNazvyKraju();

    void ulozDataCsv(String soubor);

}
