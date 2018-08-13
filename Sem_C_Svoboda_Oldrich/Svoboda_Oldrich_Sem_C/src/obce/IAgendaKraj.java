/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

import bvs.ETypProhl;
import java.util.Iterator;

/**
 *
 * @author Oldřich
 */
public interface IAgendaKraj {

    void Najdi(IKlic obec);

    void Vloz(IObec obec);

    void Odeber();

    Iterator<IKlic> VytvorIterátor(ETypProhl typ);//    vrací iterátor tabulky 

    IObec ZjistiSousedniObec(ETyp t);  //vrací sousední obec od vyhledané obce

    IObec Generuj(); //   umožnuje generovat jednotlivé obce

    int importData(String soubor);

    void ulozDataCsv(String soubor);

    String[] vratNazvyKraju();

    void vytvorKraj(String kraj);

    IObec getPosledni();
}
