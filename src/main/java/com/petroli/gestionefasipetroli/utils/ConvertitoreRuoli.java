package com.petroli.gestionefasipetroli.utils;

public class ConvertitoreRuoli {


    // 0 = Amministratore
    public static String RUOLO_AMMINISTRATORE = "AMMINISTRATORE";

    // 1 = Utente con permessi per ....... da decidere
    public static String RUOLO_UNO = "UNO";





    public static String RUOLO_NON_DEFINITO = "RUOLO-NON-DEFINITO";

    public static String convertiinruolo(int ruolo){
        switch (ruolo){
            case 0: return RUOLO_AMMINISTRATORE;
            case 1: return RUOLO_UNO;
        }

        return RUOLO_NON_DEFINITO;
    }


}
