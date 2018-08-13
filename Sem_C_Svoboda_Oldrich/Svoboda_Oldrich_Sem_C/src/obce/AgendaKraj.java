/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

import au.com.bytecode.opencsv.CSVReader;
import bvs.*;
import doubleList.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oldřich
 */
public class AgendaKraj implements IAgendaKraj {

    private IAbstrBVS strom;
    private IAbstrDoubleList<IObec>[] poleKraju = null;
    private String[] nazvyKraju = null;
    private IObec posledni = null;

    public AgendaKraj() {
        strom = new AbstrBVS();
    }

    @Override
    public void Najdi(IKlic klic) {///
        posledni = (IObec) strom.najdi(klic);
        //System.out.println(posledni.getKraj());
        if (posledni == null) {
            return;
        }
        if (!(poleKraju.length > posledni.getKraj() - 1)) {
            throw new IndexOutOfBoundsException("Kraj neexistuje!");
        }
        poleKraju[posledni.getKraj() - 1].najdiData(posledni);
    }

    @Override
    public void Vloz(IObec obec) {
        if (!(poleKraju.length > obec.getKraj() - 1)) {
            throw new IndexOutOfBoundsException("Kraj neexistuje!");
        }
        strom.vloz(obec);
        poleKraju[obec.getKraj() - 1].vlozPosledni(obec);
    }

    @Override
    public void Odeber() {
        if (posledni == null) {
            return;
        }
        strom.odeber(posledni);
        poleKraju[posledni.getKraj() - 1].najdiData(posledni);

        //IObec dalsi = poleKraju[posledni.getKraj() - 1].zpristupniNaslednika();
        //poleKraju[posledni.getKraj() - 1].najdiData(posledni);
        poleKraju[posledni.getKraj() - 1].odeberAktualni();

        posledni = null;
//        if (posledni == dalsi) {
//            posledni = null;        //lze zmenit 
//        } else {
//            posledni = dalsi;
//        }

    }

    @Override
    public IObec ZjistiSousedniObec(ETyp t) {
        if (posledni == null) {
            return null;
        }
        //poleKraju[posledni.getKraj() - 1].najdiData(posledni);
        switch (t) {
            case AKTUALNI:
                return poleKraju[posledni.getKraj() - 1].zpristupniAktualni();
            case NASLEDNIK:
                return poleKraju[posledni.getKraj() - 1].zpristupniNaslednika();
            case PREDCHUDCE:
                return poleKraju[posledni.getKraj() - 1].zpristupniPredchudce();

        }
        return null;
    }

    @Override
    public IObec Generuj() {
        return Generator.generujObec(poleKraju.length);
    }

    @Override
    public Iterator<IKlic> VytvorIterátor(ETypProhl typ) {
        return strom.vytvorIterator(typ);
    }

    public int importData(String soubor) {
        int pocitadlo = 0;
        try {
            CSVReader csv = new CSVReader(new FileReader(soubor), ';');
            String[] radek;
            ArrayList<Integer> indexKraje = new ArrayList<>();
            ArrayList<String> nazevKraje = new ArrayList<>();
            ArrayList<IObec> nacteno = new ArrayList<>();
            int pocetKraju = 0;
            while ((radek = csv.readNext()) != null) {
                IObec obec = new Obec(Integer.valueOf(radek[2]), radek[3], Integer.valueOf(radek[6]));
                obec.setPocetZen(Integer.valueOf(radek[4]));
                obec.setPocetMuzu(Integer.valueOf(radek[5]));
                obec.setKraj(Integer.valueOf(radek[0]));//uprava
                indexKraje.add(Integer.valueOf(radek[0]));
                nazevKraje.add(radek[1]);
                if (Integer.valueOf(radek[0]) > pocetKraju) {
                    pocetKraju = Integer.valueOf(radek[0]);
                }
                nacteno.add(obec);
                pocitadlo++;

                //System.out.println(kraje.get(pocitadlo - 1));
                //System.out.println(obec);
            }
            csv.close();

            this.poleKraju = new IAbstrDoubleList[pocetKraju];
            this.nazvyKraju = new String[pocetKraju];
            for (int i = 0; i < poleKraju.length; i++) {
                poleKraju[i] = new AbstrDoubleList<>();
            }
            for (int i = 0; i < pocitadlo; i++) {
                //nacteno.get(i).setKraj(indexKraje.get(i));                  //úprava od minule
                poleKraju[indexKraje.get(i) - 1].vlozPosledni(nacteno.get(i));
                nazvyKraju[indexKraje.get(i) - 1] = nazevKraje.get(i);
                strom.vloz(nacteno.get(i)); //uprava
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AgendaKraj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgendaKraj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pocitadlo;
    }

    public void ulozDataCsv(String soubor) {
        BufferedWriter csv = null;
        try {
            csv = new BufferedWriter(new FileWriter(soubor));
            for (int i = 0; i < poleKraju.length; i++) {
                for (IObec obec : poleKraju[i]) {
                    csv.write(i + 1 + ";");
                    csv.write(nazvyKraju[i] + ";");
                    csv.write(obec.getPsc() + ";");
                    csv.write(obec.getObec() + ";");
                    csv.write(obec.getPocetMuzu() + ";");
                    csv.write(obec.getPocetZen() + ";");
                    csv.write(obec.getCelkem() + "");
                    //csv.write(System.getProperty("line.separator"));
                    csv.newLine();
                    //csv.write("\n\r");
                }
            }
            csv.close();

        } catch (IOException ex) {
            Logger.getLogger(AgendaKraj.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public String[] vratNazvyKraju() {
        return nazvyKraju;
    }

    @Override
    public void vytvorKraj(String kraj) {
        if (!kraj.isEmpty()) {

            if (nazvyKraju != null) {
                IAbstrDoubleList<IObec> novePole[] = new AbstrDoubleList[poleKraju.length + 1];
                String noveNazvy[] = new String[nazvyKraju.length + 1];
                for (int i = 0; i < poleKraju.length; i++) {
                    novePole[i] = poleKraju[i];
                    noveNazvy[i] = nazvyKraju[i];
                }
                novePole[novePole.length - 1] = new AbstrDoubleList<>();
                noveNazvy[noveNazvy.length - 1] = kraj;

                poleKraju = novePole;
                nazvyKraju = noveNazvy;
            } else {
                poleKraju = new AbstrDoubleList[1];
                nazvyKraju = new String[1];
                poleKraju[0] = new AbstrDoubleList<>();
                nazvyKraju[0] = kraj;
            }
        }
    }

    @Override
    public IObec getPosledni() {
        return posledni;
    }

}
