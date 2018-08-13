/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obce;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import doubleList.AbstrDoubleList;
import doubleList.IAbstrDoubleList;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oldřich
 */
public class Obyvatele implements IObyvatele {

    //private static final long serialVersionUID = 1L;
    private IAbstrDoubleList<IObec>[] poleKraju = null;
    private String[] nazvyKraju = null;

    @Override
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
                obec.setPocetZen(Integer.valueOf(radek[2]));
                obec.setPocetMuzu(Integer.valueOf(radek[2]));
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
                poleKraju[indexKraje.get(i) - 1].vlozPosledni(nacteno.get(i));
                nazvyKraju[indexKraje.get(i) - 1] = nazevKraje.get(i);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Obyvatele.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Obyvatele.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pocitadlo;
    }

    @Override
    public void vlozObec(IObec obec, enumPozice pozice, int kraj) {
        if (poleKraju == null || kraj <= 0 || kraj > poleKraju.length) {
            return;
        } else {
            switch (pozice) {
                case PRVNI:
                    poleKraju[kraj - 1].vlozPrvni(obec);
                    break;
                case POSLEDNI:
                    poleKraju[kraj - 1].vlozPosledni(obec);
                    break;
                case NASLEDNIK:
                    poleKraju[kraj - 1].vlozNaslednika(obec);
                    break;
                case PREDCHUDCE:
                    poleKraju[kraj - 1].vlozPredchudce(obec);
                    break;
            }
        }
    }

    @Override
    public IObec zpristupniObec(enumPozice pozice, int kraj) {
        if (poleKraju == null || kraj <= 0 || kraj > poleKraju.length) {
            return null;
        } else {
            switch (pozice) {
                case PRVNI:
                    return poleKraju[kraj - 1].zpristupniPrvni();
                case POSLEDNI:
                    return poleKraju[kraj - 1].zpristupniPosledni();
                case NASLEDNIK:
                    return poleKraju[kraj - 1].zpristupniNaslednika();
                case PREDCHUDCE:
                    return poleKraju[kraj - 1].zpristupniPredchudce();
                case AKTUALNI:
                    return poleKraju[kraj - 1].zpristupniAktualni();
                default:
                    return null;
            }
        }
    }

    @Override
    public IObec odeberObec(enumPozice pozice, int kraj) {
        if (poleKraju == null || kraj <= 0 || kraj > poleKraju.length) {
            return null;
        } else {
            switch (pozice) {
                case PRVNI:
                    return poleKraju[kraj - 1].odeberPrvni();
                case POSLEDNI:
                    return poleKraju[kraj - 1].odeberPosledni();
                case NASLEDNIK:
                    return poleKraju[kraj - 1].odeberNaslednika();
                case PREDCHUDCE:
                    return poleKraju[kraj - 1].odeberPredchudce();
                case AKTUALNI:
                    return poleKraju[kraj - 1].odeberAktualni();
                default:
                    return null;
            }
        }
    }

    @Override
    public float zjistiPrumer(int kraj) {
        if (poleKraju == null || kraj < 0 || kraj > poleKraju.length) {
            return 0;
        } else {
            int pocet = 0;
            float prumer = 0;
            if (kraj == 0) {
                for (IAbstrDoubleList<IObec> list : poleKraju) {
                    for (IObec obec : list) {
                        prumer += obec.getCelkem();
                        pocet++;
                    }
                }
            } else {
                for (IObec obec : poleKraju[kraj - 1]) {
                    prumer += obec.getCelkem();
                    pocet++;
                }
            }
            prumer /= pocet;
            return prumer;
        }
    }

    @Override
    public void zobrazObce(int kraj) {
        if (poleKraju == null || kraj < 0 || kraj > poleKraju.length) {
            return;
        } else {
            if (kraj == 0) {
                for (int i = 0; i < poleKraju.length; i++) {
                    System.out.println("\n\t" + nazvyKraju[i] + ":");
                    for (IObec obec : poleKraju[i]) {
                        System.out.println(obec);
                    }
                }
            } else {
                System.out.println("\n\t" + nazvyKraju[kraj - 1] + ":");
                for (IObec obec : poleKraju[kraj - 1]) {
                    System.out.println(obec);
                }
            }
        }
    }

    @Override
    public String vratObce(int kraj) {
        if (poleKraju == null || kraj < 0 || kraj > poleKraju.length) {
            return "";
        } else {
            String vrat = "";
            if (kraj == 0) {
                for (int i = 0; i < poleKraju.length; i++) {
                    vrat += "\n\t" + nazvyKraju[i] + ":\n";
                    for (IObec obec : poleKraju[i]) {
                        vrat += obec + "\n";
                    }
                }
            } else {
                vrat += "\n\t" + nazvyKraju[kraj - 1] + ":\n";
                for (IObec obec : poleKraju[kraj - 1]) {
                    vrat += obec + "\n";
                }
            }
            return vrat;
        }
    }

    @Override
    public void zobrazObceNadPrumer(int kraj) {
        if (poleKraju == null || kraj < 0 || kraj > poleKraju.length) {
            return;
        } else {
            System.out.print("Obce nad prumer ");
            if (kraj == 0) {
                float prumer = zjistiPrumer(kraj);
                System.out.println(prumer + " :");
                for (IAbstrDoubleList<IObec> list : poleKraju) {
                    for (IObec obec : list) {
                        if (obec.getCelkem() > prumer) {
                            System.out.println(obec);
                        }
                    }
                }
            } else {
                float prumer = zjistiPrumer(kraj);
                for (IObec obec : poleKraju[kraj - 1]) {
                    if (obec.getCelkem() > prumer) {
                        System.out.println(obec);
                    }
                }
            }
        }
    }

    @Override
    public String vratObceNadPrumer(int kraj) {
        if (poleKraju == null || kraj < 0 || kraj > poleKraju.length) {
            return "";
        } else {
            String vrat = "Obce nad prumer ";
            if (kraj == 0) {
                float prumer = zjistiPrumer(kraj);
                vrat += prumer + " : \n";
                for (int i = 0; i < poleKraju.length; i++) {
                    vrat += "\n\t" + nazvyKraju[i] + ":\n";
                    for (IObec obec : poleKraju[i]) {
                        if (obec.getCelkem() > prumer) {
                            vrat += obec + "\n";
                        }
                    }
                }
            } else {
                vrat += "\n\t" + nazvyKraju[kraj - 1] + ":\n";
                float prumer = zjistiPrumer(kraj);
                for (IObec obec : poleKraju[kraj - 1]) {
                    if (obec.getCelkem() > prumer) {
                        vrat += obec + "\n";
                    }
                }
            }
            return vrat;
        }
    }

    @Override
    public void zrus(int kraj) {
        if (kraj == 0 || poleKraju == null) {
            poleKraju = null;
            nazvyKraju = null;
        } else if (kraj > 0 && kraj <= poleKraju.length) {
            IAbstrDoubleList<IObec> novePole[] = new AbstrDoubleList[poleKraju.length - 1];
            String noveNazvy[] = new String[nazvyKraju.length - 1];
            int citac = 0;
            for (int i = 0; i < poleKraju.length; i++) {
                if (i == kraj - 1) {
                    continue;
                }
                noveNazvy[citac] = nazvyKraju[i];
                novePole[citac] = poleKraju[i];
                citac++;
            }
            poleKraju = novePole;
            nazvyKraju = noveNazvy;
        }

    }

//    public static void main(String[] args) {
//        Obyvatele o = new Obyvatele();
//        o.importData("kraje.csv");
//        o.zobrazObce(0);
//    }
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
    public String[] vratNazvyKraju() {
        return nazvyKraju;
    }

    @Override
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
            Logger.getLogger(Obyvatele.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
