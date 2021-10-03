package pl.darbean.WarhammerServer.model.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Profession {

    APTEKARKA("Aptekarka",Klass.UCZENI),
    CZARODZIEJ("Czarodziej", Klass.UCZENI),
    INZYNIER("Inżynier", Klass.UCZENI),
    KAPLAN("Kapłan", Klass.UCZENI),
    MEDYCZKA("Medyczka", Klass.UCZENI),
    MNISZKA("Mniszka", Klass.UCZENI),
    PRAWNICZKA("Prawniczka", Klass.UCZENI),
    UCZONY("Uczony", Klass.UCZENI),

    AGITATOR("Agitator", Klass.MIESZCZANIE),
    KUPIEC("Kupiec", Klass.MIESZCZANIE),
    MIESZCZKA("Mieszczka", Klass.MIESZCZANIE),
    RZEMIESLNICZKA("Rzemieślniczka", Klass.MIESZCZANIE),
    STRAZNIK("Strażnik", Klass.MIESZCZANIE),
    SZCZUROLAP("Szczurołap", Klass.MIESZCZANIE),
    SLEDCZY("Śledczy", Klass.MIESZCZANIE),
    ZEBRAK("Żebrak", Klass.MIESZCZANIE),

    ARTYSTKA("Artystka", Klass.DWOREZANIE),
    DORADCA("Doradca", Klass.DWOREZANIE),
    NAMIESTNIK("Namiestnik", Klass.DWOREZANIE),
    POSEL("Poseł", Klass.DWOREZANIE),
    SLUZACA("Służąca", Klass.DWOREZANIE),
    SZLACHCIC("Szlachcic", Klass.DWOREZANIE),
    SZPIEG("Szpieg", Klass.DWOREZANIE),
    ZWADZCA("Zwadźca", Klass.DWOREZANIE),

    CHLOPKA("Chłopka", Klass.POSPOLSTWO),
    GORNIK("Górnik", Klass.POSPOLSTWO),
    GUSLARZ("Guślarz", Klass.POSPOLSTWO),
    LOWCZYNI("Łowczyni", Klass.POSPOLSTWO),
    MISTYCZKA("Mistyczka", Klass.POSPOLSTWO),
    ZARZADCA("Zarządca", Klass.POSPOLSTWO),
    ZIELARKA("Zielarka", Klass.POSPOLSTWO),
    ZWIADOWCA("Zwiadowca", Klass.POSPOLSTWO),

    BICZOWNIK("Biczownik", Klass.WEDROWCY),
    DOMOKRAZCA("Domokrążca", Klass.WEDROWCY),
    KUGLARKA("Kuglarka", Klass.WEDROWCY),
    LOWCA_CZAROWNIC("Łowca czarownic", Klass.WEDROWCY),
    LOWCA_NAGROD("Łowca nagród", Klass.WEDROWCY),
    POSLANIEC("Posłaniec", Klass.WEDROWCY),
    WOZNICA("Woźnica", Klass.WEDROWCY),

    DOKER("Doker", Klass.WODNIACY),
    FLISAK("Flisak", Klass.WODNIACY),
    PILOTKA_RZECZNA("Pilotka rzeczna", Klass.WODNIACY),
    PIRAT("Pirat rzeczny", Klass.WODNIACY),
    PRZEMYTNIK("Przemytnik", Klass.WODNIACY),
    PRZEWOZNIK("Przewoźnik", Klass.WODNIACY),
    STRAZNIK_RZECZNY("Strażnik rzeczny", Klass.WODNIACY),
    ZEGLARZ("Żeglarz", Klass.WODNIACY),

    BANITA("Banita", Klass.LOTRY),
    CZAROWNICA("Czarownica", Klass.LOTRY),
    HIENA_CMENTARNA("Hiena cmentarna", Klass.LOTRY),
    PASER("Paser", Klass.LOTRY),
    RAJFUR("Rajfur", Klass.LOTRY),
    RAKIETIERKA("Rakietierka", Klass.LOTRY),
    SZARLATAN("Szarlatan", Klass.LOTRY),
    ZLODZIEJ("Złodziej", Klass.LOTRY),

    GLADIATOR("Gladiator", Klass.WOJOWNICY),
    KAPLAN_BITEWNY("Kapłan bitewny", Klass.WOJOWNICY),
    KAWALERZYSTA("Kawalerzysta", Klass.WOJOWNICY),
    OCHRONIARZ("Ochroniarz", Klass.WOJOWNICY),
    OPRYCH("Oprych", Klass.WOJOWNICY),
    RYCERZ("Rycerz", Klass.WOJOWNICY),
    ZABOJCA("Zabójca", Klass.WOJOWNICY),
    ZOLNIERZ("Żołnierz", Klass.WOJOWNICY);


    static Map<String, Profession> map = new LinkedHashMap<>();
    private Klass klasa;
    private String label;

    static{
        for (Profession profession : Profession.values()) {
            map.put(profession.getLabel(), profession);
        }
    }

    Profession(String label, Klass klasa) {
        this.label = label;
        this.klasa=klasa;
    }

    public static String[] getSpinnerOptions(){
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        return list.toArray(new String[0]);
    }

    public static Profession getProfessionByName(String name){
        return map.get(name);
    }



    public Klass getKlasa() {
        return klasa;
    }

    public void setKlasa(Klass klasa) {
        this.klasa = klasa;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
