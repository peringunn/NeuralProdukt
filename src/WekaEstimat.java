public class WekaEstimat implements Runnable{
    private GUIMain gui;
    public WekaEstimat(GUIMain gui)
    {
        this.gui = gui;
    }
    public void run()
    {

        //Kjører estimatprogram
        Utregn();
    }



    //Hovedfunksjon som kjører beregningen
    /*public String Utregn(double Svovel, double Organsik, double MR, double M40HU580, double M40HU581D,
                         double M40HU292, double M6032HU007, double M22HU274, double M22HU505, double M22HU013,
                         double M22FT307, double M22FT306, double M22FT309, double M22TT102, double M24GSH157,
                         double M24GSH156, double M24HCV155, double M22TE304, double M22HSV117 ){*/
    public void Utregn(){

        try{
            gui.txtInfoPane.setText("Prøver å estimere produkt!");
            GetProsessValues values = new GetProsessValues();
            String getValues[][] = values.getValues();



        //Henter prosessverdiene fra PI Historian og definerer de til tag navn
        double Svovel, Organsik, MR, M40HU580, M40HU581D, M40HU292, M6032HU007, M22HU274, M22HU505, M22HU013;
        double M22FT307, M22FT306, M22FT309, M22TT102, M24GSH157, M24GSH156, M24HCV155, M22TE304, M22HSV117;

        //Definerer variabel navn til array
        Svovel = Double.parseDouble(getValues[1][0]);
        Organsik = Double.parseDouble(getValues[1][1]);
        MR = Double.parseDouble(getValues[1][2]);
        M40HU580 = Double.parseDouble(getValues[1][3]);
        M40HU581D = Double.parseDouble(getValues[1][4]);
        M40HU292 = Double.parseDouble(getValues[1][5]);
        M6032HU007 = Double.parseDouble(getValues[1][6]);
        M22HU274 = Double.parseDouble(getValues[1][7]);
        M22HU505 = Double.parseDouble(getValues[1][8]);
        M22HU013 = Double.parseDouble(getValues[1][9]);
        M22FT307 = Double.parseDouble(getValues[1][10]);
        M22FT306 = Double.parseDouble(getValues[1][11]);
        M22FT309 = Double.parseDouble(getValues[1][12]);
        M22TT102 = Double.parseDouble(getValues[1][13]);
        M24GSH157 = Double.parseDouble(getValues[1][14]);
        M24GSH156 = Double.parseDouble(getValues[1][15]);
        M24HCV155 = Double.parseDouble(getValues[1][16]);
        M22TE304 = Double.parseDouble(getValues[1][17]);
        M22HSV117 = Double.parseDouble(getValues[1][18]);

        //Deklarerer return verdi
        String result = "";

        //Kjører if setninger fra rules Weka
        if ((M22TE304 >= 45.763924) && (Organsik >= 2859.835205) && (Organsik <= 2943.718262) && (Organsik <= 2896.49585) && (M22TT102 >= 43.449059))
            result = "ufapolEPN2";
        else if ((M22TE304 >= 45.763924) && (Organsik >= 2915.557373) && (Organsik <= 2943.720703) && (M22FT307 >= 0.041008) && (Organsik >= 2938.027588))
            result = "ufapolEPN2";
        else if ((M22TE304 >= 60.611675) && (M40HU292 >= 1))
            result = "ufapolEPO7";
        else if ((M22TE304 >= 58.164917) && (Organsik >= 3110.63916) && (M22TT102 >= 49.893654))
            result = "ufapolEPO7";
        else if ((M22HU505 >= 1) && (M22FT307 >= 898.062256) && (M22TT102 >= 66.367935) && (M22TT102 <= 69.474686))
            result = "ufaporeAMGP";
        else if ((M22TE304 >= 43.872906) && (Organsik >= 2441.884277) && (Organsik <= 2626.649902) && (M22TE304 <= 58.476982) && (M22TE304 >= 57.296444))
            result ="UFAPORE GP 413";
        else if ((M22TE304 >= 53.567657) && (Organsik>= 3022.002197) && (Organsik <= 3125.623047))
            result = "UFAPORE GP 3";
        else if ((M22TE304 >= 54.073906) && (M40HU292 >= 1) && (M22TE304 >= 58.727001))
            result = "UFAPORE GP 3";
        else if ((M22TE304 >= 54.408562) && (M22FT307 <= 3.862045) && (Organsik >= 2981.39624))
            result = "UFAPORE GP 3";
        else if ((M22FT307 >= 1020.597717) && (M22HU274 >= 1) && (M22TT102 <= 66.886429) && (M22TT102 >= 63.195236))
            result = "UNGEROL LES 3-70";
        else if ((M24GSH157 >= 1) && (M22TT102 <= 65.855453) && (Organsik >= 2938.194092) && (M22TT102 >= 63.761414))
            result = "UNGEROL LES 3-70";
        else if ((M24GSH157 >= 1) && (Organsik >= 3293.330078) && (Organsik <= 3349.224854))
            result = "UNGEROL LES 3-70";
        else if ((M22TE304 >= 45.855534) && (Organsik >= 2724.595947) && (M22FT307 <= 0.199111) && (M22TE304 >= 52.486385))
            result = "UNGEROL N 2-28";
        else if ((M22TE304 >= 45.855534) && (Organsik >= 2573.182861) && (M22FT307 <= 1.514328) && (M22FT309 <= -0.12534) && (M22TE304 >= 50.526382))
            result = "UNGEROL N 2-28";
        else if ((M22TE304 >= 42.746174) && (Organsik >= 2676.692139) && (M22TT102 >= 48.223873) && (M22TT102 <= 53.771549) && (Organsik <= 2949.215576))
            result = "UNGEROL N 2-28";
        else if ((M22FT307 <= 300.825531) && (Organsik >= 2482.510254) && (Organsik <= 2659.762695) && (M22TE304 <= 31.003323) && (M22TT102 <= 54.064728))
            result = "UFACID TPB";
        else if ((M22FT307 <= 300.825531) && (Organsik >= 2472.671387) && (Organsik <= 2659.762695) && (Organsik >= 2614.831787))
            result = "UFACID TPB";
        else if ((M22FT307 <= 0.304739) && (M22FT306 == 1.7578125) && (Organsik >= 2464.173828) && (M22TT102 >= 70.265411))
            result = "UFACID TPB";
        else if ((M22HU505 >= 1) && (M22TT102 <= 69.898315) && (M22TT102 >= 66.886856) && (M22FT307 <= 801.537415))
            result = "UFAROL AM 70";
        else if ((M22HU505 >= 1) && (M22TT102 <= 68.866257) && (M22FT307 <= 839.270691) && (M22TT102 >= 63.941372))
            result = "UFAROL AM 70";
        else if ((M24GSH156 >= 1) && (M22TT102 <= 85.922058) && (Organsik >= 2962.129639) && (M22HU274 >= 1) && (M22TT102 <= 77.667313))
            result = "UFAROL AM 70";
        else if ((M24GSH156 >= 1) && (M22TT102 <= 86.357803) && (M22FT309 >= -0.139153) && (M22TT102 <= 72.58149) && (M22TT102 >= 70.704849))
            result = "UFAROL AM 70";
        else if ((M40HU580 >= 1) && (M22TT102 >= 84.288651) && (M22TT102 <= 89.724358) && (M22FT307 <= 751.130432) && (M22TT102 <= 87.524918) && (M22FT309 >= -0.171161))
            result = "UFAROL NA 70";
        else if ((M40HU580 >= 1) && (M22TT102 >= 84.288651) && (M22TT102 <= 90.691841) && (M22FT307 <= 750.648804) && (Organsik <= 2428.862793) && (Organsik >= 2349.568115))
            result = "UFAROL NA 70";
        else if ((M40HU580 >= 1) && (M22TT102 >= 83.859596) && (M22TT102 <= 93.280128) && (M22FT309 >= -0.09536))
            result = "UFAROL NA 70";
        else if ((M40HU580 >= 1) && (M22FT307 >= 709.575501) && (M22TT102 <= 93.62384) && (M22TE304 >= 34.994862))
            result = "UFAROL NA 70";
        else if ((M22TE304 >= 43.633392) && (M40HU580 >= 1) && (Organsik <= 2410.279541) && (M22TE304 <= 54.579201))
            result = "UFAPOL SLS 30";
        else if ((M22TE304 >= 44.630699) && (M22FT307 <= 2.961055) && (M22HU013 >= 1))
            result = "UFAPOL SLS 30";
        else if ((M22TE304 >= 43.36277) && (M22FT307 <= 1.180817) && (M22TT102 >= 54.064495) && (M22TT102 <= 68.695976))
            result = "UFAPOL SLS 30";
        else if ((M40HU580 >= 1) && (M22TT102 >= 90.151283) && (M22TT102 >= 93.298859))
            result = "UFAROL NA-TEP 70";
        else if ((M40HU580 >= 1) && (M22TT102 >= 89.759071) && (Organsik <= 2385.401611))
            result = "UFAROL NA-TEP 70";
        else if ((M40HU580 >= 1) && (M22TT102 >= 86.106323) && (M22TT102 >= 90.151283) && (Organsik >= 2403.17627))
            result = "UFAROL NA-TEP 70";
        else if ((M22TT102 >= 78.004288) && (M6032HU007 <= 0) && (M22FT307 >= 757.963928) && (Organsik <= 2420.541016) && (Organsik <= 2399.450195) && (M22FT307 <= 778.51886))
            result = "UFAROL NA-TEP 70";
        else if ((M24GSH157 >= 1) && (M22TE304 <= 30.067636) && (Organsik <= 2933.996094) && (M22FT307 >= 900.684265) && (M22TT102 <= 70.563362) && (M22TT102 >= 66.497017) && (Organsik >= 2924.151855))
            result = "UNGEROL N2-70 C";
        else if ((M24GSH157 >= 1) && (M22TE304 <= 30.245462) && (M22FT309 >= -0.17698) && (M22TE304 >= 26.866438))
            result = "UNGEROL N2-70 C";
        else if ((M24GSH157 >= 1) && (M22TE304 <= 26.313406))
            result = "UNGEROL N2-70 C";
        else if ((M24GSH157 >= 1) && (M22FT309 <= -0.188901) && (M22TT102 <= 69.482697) && (Organsik <= 2947.275635))
            result = "UNGEROL N2-70 C";
        else if ((M22HU274 >= 1) && (M24GSH157 >= 1) && (M22FT309 >= -0.17095) && (M22TE304 <= 32.276211))
            result = "UNGEROL  N 2-70";
        else if ((M22HU274 >= 1) && (M24GSH157 >= 1) && (M22TE304 <= 35.954273) && (M22TE304 >= 31.625433))
            result = "UNGEROL  N 2-70";
        else if ((M22HU013 >= 1) && (M22HU274 >= 1) && (Organsik <= 2867.310547))
            result = "UNGEROL  N 2-70";
        else if ((M22HU013 >= 1) && (M22TT102 <= 84.832138) && (M22FT307 <= 784.261169) && (M22TT102 >= 75.825317))
            result = "UNGEROL  N 2-70";
        else if ((M22FT307 >= 739.638184) && (Organsik >= 2887.93335) && (M22HU505 >= 1) && (M22TT102 <= 71.337563) && (M22TE304 >= 23.585833))
            result = "UNGEROL  N 2-70";
        else if ((M22HU013 >= 1) && (M24GSH157 >= 1) && (M22TE304 <= 27.585995))
            result = "UNGEROL  N 2-70";
        else if ((M22TT102 >= 80.698998) && (Organsik >= 2584.728271) && (M22TT102 >= 89.567261) && (Organsik >= 2616.665039))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (M22FT307 >= 763.616699) && (M22FT309 <= -0.140038) && (M22TE304 >= 28.226431))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (M22TE304 <= 26.736885) && (Organsik >= 2533.944336) && (Organsik <= 2555.865234))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (M22TE304 >= 32.105) && (M22FT307 <= 750.667297))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (M22FT307 >= 760.390686) && (M22TE304 >= 32.506168))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (Organsik >= 2585.484131) && (Organsik <= 2627.422607) && (M22FT309 <= -0.213711))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (M22FT307 >= 768.321411) && (M22TT102 >= 90.146599) && (M22FT307 >= 797.33374))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 80.698998) && (Organsik >= 2579.594482) && (M22TE304 <= 27.831177) && (M22TE304 >= 25.165422) && (M22FT309 >= -0.111936) && (M22TT102 <= 88.040337))
            result = "UFAROL CT 70 NP";
        else if ((M22TT102 >= 86.33181) && (Organsik >= 2565.588623) && (M22FT306 == 1.7578125))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 82.333015) && (Organsik >= 2565.884521) && (Organsik <= 2589.105957))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 82.453087) && (Organsik >= 2487.021484) && (M22FT307 >= 757.727173) && (M22FT309 <= -0.148512))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 82.333015) && (M22FT309 >= -0.132727) && (Organsik >= 2555.004639) && (M22FT307 >= 770.325256))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 77.354202) && (Organsik >= 2466.70459) && (Organsik >= 2629.618164) && (Organsik <= 2666.729248))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 75.402611) && (M22TT102 >= 87.990036) && (M22FT307 <= 740.687439) && (M22FT309 >= -0.125406))
            result = "UFAROL CT 70A";
        else if ((M22TT102 >= 77.842514) && (M22TT102 >= 87.569962) && (M24GSH156 <= 0))
            result = "UFAROL CT 70A";
        else
            result = "UFACID K";

       //return result;

        } catch (Exception e)
        {
            gui.txtInfoPane.setText("Klarte ikke å få tilgang til PI-Databasen");
        }

    }









}