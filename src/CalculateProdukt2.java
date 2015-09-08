public class CalculateProdukt2 {

    //Her er verdiene og vektene fra neural network definert som statiske verdier

    public static double x1_step1_xoffset[] = {-0.271040469,-2.092832565,-0.234375,0.29296875,-0.384496212};

    public static double x1_step1_gain[] = { 0.00376356058769408,0.000560487023272718,0.00100120463540045,0.000159374418035908,0.00112421291627032 };
    public static double x1_step1_ymin = -1.0;

    //Layer 1
    public static double b1[] = {-4.0728975389879718,0.58179984655217376,1.4794880811726707,-0.61156288577028173,2.2131970677597921,-0.099310326126985163,-0.96991468062687358,-0.22676710170548403,2.7567718318576802,2.3347076869078687};

    public static double IW1_1[][] = {
            {5.9590850815019607, 3.2284590187325488, -0.096531299002858145, -1.7124101292033891, 1.277383180629188},

            {-2.5986559122541695, 0.55517190616911816, -1.3408991965985908, -1.5226725710788174, 0.53594599171584789},

            {-0.52742446649999419, 0.14383560787451696, 0.18811799000997054, 0.71478079360685576, -2.0030912058744845},

            {0.17236684136282521, -0.82202551921764522, 0.8798802887644176, 1.7332606978749916, -1.4988742877509769},

            {-3.3898472232059307, -1.218313688432231, 0.05291381836041225, 0.75453399807201449, -0.35648088110549481},

            {-1.0783278326320769, 1.2625443837780363, -1.4634639840418813, -0.75247834803987657, 1.4081800638140622},

            {1.2136419525908919, -0.44438905348329361, -0.94841675739290321, 3.0499066192599278, 1.2745656063577586},

            {2.9894867529528555, 1.7899723690392153, 2.9695409724275934, 0.67820855266905355, 2.6253998136996253},

            {-2.5289232941134854, -6.5123954538091269, 1.3841113306278572, 0.53495065962088451, -1.1259275689992201},

            {1.9838999974466014, -2.1475129531549353, 2.7005150287800941, 0.047413175021752296, 0.6782822154832383}};


    // Layer 2
    public static double b2 = 0.058147859841879757;
    public static double LW2_1[] = {1.6746295936377356, -2.0008248393508241, -1.2005609781423274, 1.0204363297815771, 2.385182817922149, 1.9017685998939184, -1.3737788723829076, -1.4592880497218421, -0.57582834541592109, 1.5196644599830822};

    //Output
    public static double y1_step1_ymin = -1.0;
    public static double y1_step1_gain = 2.5;
    public static double y1_step1_xoffset = 0.1;

    //Hovedfunksjon som kjører beregningen
    //public static void main(String[] args) {
    public double Utregn(double v1, double v2, double v3, double v4, double v5){
        // TODO Auto-generated method stub

        //Initaialisering av input verdier {Svovel, Rastoff,22FT307, 22FT306, 22FT309}
        //double[] x1 = {4380,330,-60,5345,1300,600,0.9,32.2};
        double[] x1 = {v1,v2,v3,v4,v5};


        //Denne matrisen skal være lik antallet hidden layers
        double[] xp1 = new double[x1.length];
        double[] IW1_1Xp1 = new double[b1.length];
        double[] sumTilTransig = new double[IW1_1.length];
        double[] a1 = new double[IW1_1.length];
        double a2 = 0;
        double y1 = 0;

        //Finner xp1
        xp1 = mapminmax_apply(x1,x1_step1_gain,x1_step1_xoffset,x1_step1_ymin);

        //Finner produktet IW1_1 * xp1
        IW1_1Xp1 = beregnMatrise(IW1_1, xp1);

        //Finner summen til b1 + IW1_1*xp1
        sumTilTransig = summerMatrise(b1, IW1_1Xp1);


        //Finner så a1 (a1 = tansig_apply(repmat(b1,1,Q) + IW1_1*xp1)
        a1 = tansig_apply(sumTilTransig);

        //Finner så a2(b2 + LW2_1 * a1)
        a2 = finnA2(b2, LW2_1, a1);

        //Finner tilslutt den predikterte verdien
        y1 = mapminmax_reverse(a2, y1_step1_gain, y1_step1_xoffset, y1_step1_ymin);

        return y1;
    }

    //Funksjon for mapminmax_apply
    static double[] mapminmax_apply(double[] x,double[] settings_gain, double[] settings_xoffset,double settings_ymin)
    {
        //Initialiserer return verdien
        double ret[] = new double[x.length];

        //Beregner først offset
        for (int i=0; i<x.length; i++)
        {
            ret[i] = x[i] - settings_xoffset[i];
            ret[i] = ret[i] * settings_gain[i];
            ret[i] = ret[i] + settings_ymin;
        }

        return ret;
    }

    //Funksjon for å multiplisere matriser
    static double[] beregnMatrise(double m1[][], double m2[])
    {
        double M3[] = new double[m1.length];
        double sum = 0;

        for(int c=0; c<m1.length; c++)
        {
            for (int d=0; d<m2.length; d++) {
                sum = sum + m1[c][d] * m2[d];

            }
            M3[c] = sum;
            sum = 0;

        }
        return M3;

    }

    //Funksjon for å finn a2 verdien
    static double finnA2(double b2, double LW2_1[], double a1[])
    {
        double sum = 0;

        for (int i=0; i<b1.length; i++)
        {
            sum = sum + LW2_1[i] * a1[i];
        }
        return sum + b2;
    }

    //Funskjon for å summere matriser
    static double[] summerMatrise(double m1[], double m2[])
    {
        double M3[] = new double[m1.length];
        for(int i=0; i< M3.length; i++)
        {
            M3[i] = m1[i] + m2[i];
        }
        return M3;
    }

    //Funksjon for å finne a1
    static double[] tansig_apply(double m1[])
    {
        double ret[] = new double[m1.length];
        double expFunc = 0;

        for (int i=0; i<m1.length; i++)
        {
            expFunc = (-2) * m1[i];
            //a = 2 ./ (1 + exp(-2*n)) - 1;
            ret[i] = 2/(1 + Math.exp(expFunc))-1;
        }
        return ret;
    }

    //Funksjon for å beregne den predikterte verdien
    static double mapminmax_reverse(double y, double settings_gain, double settings_xoffset,double settings_ymin)
    {
        double ret = 0;

        ret = 	y - settings_ymin;
        ret = ret / settings_gain;
        ret = ret + settings_xoffset;

        return ret;
    }
}