//import com.sun.javafx.scene.layout.region.Margins;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        GUIMain gui = new GUIMain();



/*
        //Valg for hva programmet skal gjøre
        Integer valg = 0;


        PiConnect piCon = new PiConnect();
        DBConnect dbCon = new DBConnect();
        CalculateProdukt prod = new CalculateProdukt();
        CalculateProdukt2 prod2 = new CalculateProdukt2();
        JRipWeka prod3 = new JRipWeka();
        ReadWekaRules readRules = new ReadWekaRules();
        System.out.println(readRules.readFile("C://Users//Per-Olav//Documents//MLSjekkProduksjon//Weka_Results//Jrip.txt"));

        //Printer ut heading til bruker
        System.out.print  ("***********************************************************************");
        System.out.println("*                                                                      *");
        System.out.println("*           Estimering av produktkjøring i S-avdeling                  *");
        System.out.println("*                                                                      *");
        System.out.println("*  Versjon: 1.1     Made by Per-Olav Hansen                            *");
        System.out.println("************************************************************************");
        System.out.println("");
        System.out.println("Henter prosessverdier fra historisk database og kalkurerer");
        System.out.println("Venligst vent ....");
        System.out.println("");
        //GetFile hentFil = new GetFile();

        //hentFil.readFile();


        //Hent ut timestamp now
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        String timeStmp = currentTimestamp.toString();


        String Rastoff = "";
        String Svovel = "";
        String Molar = "";
        String Mstat580 = "";
        String Mstat581 = "";
        String Mstat292 = "";
        String Mstat007 = "";
        String Mstat274 = "";
        String Mstat505 = "";
        String Mstat013 = "";
        String Mstat307 = "";
        String Mstat306 = "";
        String Mstat309 = "";
        String Temp102 = "";
        String Vstat157 = "";
        String Vstat156 = "";
        String Vstat155 = "";
        String Temp304 = "";
        String Vstat117 = "";

        if (valg==0) {
            String test = dbCon.readTag("");
        }

        Rastoff = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.REAKTOR_REG_4018FT010_MEAS");
        Svovel = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Svovelsystem.AW5101.SVOVEL_REG_14FT519_MEAS");
        Molar = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.MolarBeregning");
        Mstat580 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU580_MSTAT");
        Mstat581 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU581_MSTAT");
        Mstat292 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU292_MSTAT");
        Mstat007 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.S0YLEBU_MTR_6532HU007_MSTAT");
        Mstat274 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_FKJ_MTR_22HU274_MSTAT");
        Mstat505 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_22HU505_MSTAT");
        Mstat013 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_4022HU013_MSTAT");
        Mstat307 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT307_MEAS");
        Mstat306 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT306_MEAS");
        Mstat309 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT309_MEAS");
        Temp102 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_REG_22TT102_MEAS");
        Vstat157 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.VTL_INND_VLV_4024GSH157_CIN");
        Vstat156 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024GSH156_CIN");
        Vstat155 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024HSV155_COUT");
        Temp304 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_REG_22TE304_MEAS");
        Vstat117 = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_VLV_22GSH117_CIN");


        double RastoffD= Double.parseDouble(Rastoff);
        double SvovelD = Double.parseDouble(Svovel);
        double MolarD = Double.parseDouble(Molar);
        double Mstat580D = Double.parseDouble(Mstat580);
        double Mstat581D = Double.parseDouble(Mstat581);
        double Mstat292D = Double.parseDouble(Mstat292);
        double Mstat007D = Double.parseDouble(Mstat007);
        double Mstat274D = Double.parseDouble(Mstat274);
        double Mstat505D = Double.parseDouble(Mstat505);
        double Mstat013D = Double.parseDouble(Mstat013);
        double Mstat307D = Double.parseDouble(Mstat307);
        double Mstat306D = Double.parseDouble(Mstat306);
        double Mstat309D = Double.parseDouble(Mstat309);
        double Temp102D = Double.parseDouble(Temp102);
        double Vstat157D = Double.parseDouble(Vstat157);
        double Vstat156D = Double.parseDouble(Vstat156);
        double Vstat155D = Double.parseDouble(Vstat155);
        double Temp304D = Double.parseDouble(Temp304);
        double Vstat117D = Double.parseDouble(Vstat117);

        if(valg==1) {
            //Bruker neural net for utregning av verdi {Rastoff, Svovel, Duggpunkt, luftsvovel, luftkonv, luftso3, molar, molarspt}
            double ProdEst = prod.Utregn(SvovelD, RastoffD, MolarD, Mstat580D, Mstat581D, Mstat292D, Mstat007D, Mstat274D, Mstat505D, Mstat013D, Mstat307D, Mstat306D, Mstat309D, Temp102D, Vstat157D, Vstat156D, Vstat155D, Temp304D, Vstat117D);
            double ProdEst2 = prod2.Utregn(SvovelD, RastoffD, Mstat307D, Mstat306D, Mstat309D);
            String ProdEst3 = prod3.Utregn(SvovelD, RastoffD, MolarD, Mstat580D, Mstat581D, Mstat292D, Mstat007D, Mstat274D, Mstat505D, Mstat013D, Mstat307D, Mstat306D, Mstat309D, Temp102D, Vstat157D, Vstat156D, Vstat155D, Temp304D, Vstat117D);

            System.out.println("De estimerte utregnede faktorene er: ");
            System.out.println("Forste: " + ProdEst);
            System.out.println("Andre: " + ProdEst2);
            System.out.println("Tredje (Weka Jrip: " + ProdEst3);
            System.out.println("");

            //Definerer en array for å bruk til å beregne produkt treff til nermeste faktor innenfor produktet
            double prodEstValue[] = new double[16];
            String prodName[] = new String[16];

            //Definerer produkt i henhold til faktorer
            prodName[0] = "Ufacid K";
            prodName[1] = "Ungerol N2-28";
            prodName[2] = "Ufacid TPB";
            prodName[3] = "Ufapore GP3";
            prodName[4] = "Ufapol SLS 30";
            prodName[5] = "Ufapol EPO 7";
            prodName[6] = "Ufarol NA Tep 70";
            prodName[7] = "Ufarol CT 70 NP";
            prodName[8] = "Ufarol NA 70";
            prodName[9] = "Ufapor GP 413";
            prodName[10] = "Ungerol N2-70";
            prodName[11] = "Ufapol EPN 2";
            prodName[12] = "Ungerol LES 370";
            prodName[13] = "Ungerol N2-70 C";
            prodName[14] = "Ufarol AM 70";
            prodName[15] = "Ufapore AM GP";


/*
            double increment = 0.05;
            double tempHolder = 10;
            double tempHolder2 = 10;

            int prodArrayValue = 0;
            int prodArrayValue2 = 0;

            for (int i = 0; i < 16; i++) {
                double tempAvvik = 0;
                double tempAvvik2 = 0;
                increment = increment + 0.05;

                prodEstValue[i] = increment;
                tempAvvik = Math.abs(prodEstValue[i] - ProdEst);
                tempAvvik2 = Math.abs(prodEstValue[i] - ProdEst2);

                if (tempAvvik < tempHolder) {
                    tempHolder = tempAvvik;
                    prodArrayValue = i;
                    //System.out.println();
                }
                if (tempAvvik2 < tempHolder2) {
                    tempHolder2 = tempAvvik2;
                    prodArrayValue2 = i;
                    //System.out.println();
                }
                //System.out.println();

            }

            //Skriver ut produktnavn med respektive faktorer
            java.text.DecimalFormat df = new DecimalFormat("#.##");

            System.out.println("Produktnavn med faktor");
            for (int i = 0; i < 16; i++) {
                System.out.println("Produktnavn : " + prodName[i] + " Faktor: " + df.format(prodEstValue[i]));
            }
            System.out.println("");
            System.out.println("Lister ut prosessverdiene");
            System.out.println("Svovel: " + SvovelD);
            System.out.println("Rastoff: " + RastoffD);
            System.out.println("Molar: " + MolarD);
            System.out.println("Mstat 580: " + Mstat580D);
            System.out.println("Mstat 581: " + Mstat581D);
            System.out.println("Mstat 292: " + Mstat292D);
            System.out.println("Mstat 007: " + Mstat007D);
            System.out.println("Mstat 274: " + Mstat274D);
            System.out.println("Mstat 505: " + Mstat505D);
            System.out.println("Mstat 013: " + Mstat013D);
            System.out.println("Mengde 307: " + Mstat307D);
            System.out.println("Mengde 306: " + Mstat306D);
            System.out.println("Mengde 309: " + Mstat309D);
            System.out.println("Temperatur 102: " + Temp102D);
            System.out.println("Ventil 157: " + Vstat157D);
            System.out.println("Ventil 156: " + Vstat156D);
            System.out.println("Ventil 155: " + Vstat155D);
            System.out.println("Temperatur 304: " + Temp304D);
            System.out.println("Ventil 117: " + Vstat117D);

            System.out.println("");
            System.out.println("Anlegget produserer mest sansynlig (Neural net med 19 inputs): " + prodName[prodArrayValue]);
            System.out.println("Anlegget produserer mest sansynlig (Neural net med 5 inputs): " + prodName[prodArrayValue2]);
            System.out.println("");
            System.out.println("Anlegget produserer mest sansynlig kalkulert med");
            System.out.println("Weka Jrip rules med 19 inputs: " + ProdEst3);
            System.out.println();


            //piCon.writeToDB("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.NT_Estimert_Neural", NTEst, timeStmp);
            //piCon.writeToDB("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.Fri_Estimert_Neural", FriEst, timeStmp);
            //piCon.writeToDB("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.Aktiv_Estimert_Neural", AktivEst, timeStmp);

        }

*/
    }
}
