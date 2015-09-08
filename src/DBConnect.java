import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Date;


public class DBConnect implements Runnable{
    private GUIMain gui;
    public DBConnect(GUIMain gui)
    {
        this.gui = gui;
    }
    public void run()
    {
        try {
            readTag();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Deklarerer db connect
    public static final String JDBC_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:jtds:sqlserver://172.18.150.30:1433/SANLEGG";
    public static final String JDBC_USER = "sa";
    public static final String JDBC_PASSWORD = "fox";

    private Connection connection = null;
    private int teller = 0;



    //Åpner kopling mot database
    public boolean openDB() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(5);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        } catch (Exception e) {
            gui.txtInfoPane.setText("Kunne ikke kople seg til databasen: \n\n Exeption: " + e);
            //gui.setLblStatusDBConnect("Kunne ikke kople seg til databasen -> Exeption: " + e);
            //e.printStackTrace();
        }
        return !connection.isClosed();
    }

    //Lukker databasetilkopling
    public void closeDB() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    private void readTag() throws SQLException {

        //Åpner kopling mot PI database
        if (connection == null)
        try {
            gui.txtInfoPane.setText("Prøver å kople seg til databasen");
            openDB();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        PiConnect piCon = new PiConnect();
        String returnVerdi = "";
        String produkt = "";
        String dato_s = "";
        String dato = "";

        //Prosessverdier som skal hentes fra historian
        String Rastoff = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.REAKTOR_REG_4018FT010_MEAS";
        String Svovel = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Svovelsystem.AW5101.SVOVEL_REG_14FT519_MEAS";
        String Molar = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.MolarBeregning";
        String Mstat580 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU580_MSTAT";
        String Mstat581 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU581_MSTAT";
        String Mstat292 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU292_MSTAT";
        String Mstat007 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.S0YLEBU_MTR_6532HU007_MSTAT";
        String Mstat274 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_FKJ_MTR_22HU274_MSTAT";
        String Mstat505 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_22HU505_MSTAT";
        String Mstat013 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_4022HU013_MSTAT";
        String Mstat307 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT307_MEAS";
        String Mstat306 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT306_MEAS";
        String Mstat309 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT309_MEAS";
        String Temp102 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_REG_22TT102_MEAS";
        String Vstat157 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.VTL_INND_VLV_4024GSH157_CIN";
        String Vstat156 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024GSH156_CIN";
        String Vstat155 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024HSV155_COUT";
        String Temp304 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_REG_22TE304_MEAS";
        String Vstat117 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_VLV_22GSH117_CIN";

        String valueRastoff = "";
        String valueSvovel = "";
        String valueMolar = "";
        String valueMstat580 = "";
        String valueMstat581 = "";
        String valueMstat292 = "";
        String valueMstat007 = "";
        String valueMstat274 = "";
        String valueMstat505 = "";
        String valueMstat013 = "";
        String valueMstat307 = "";
        String valueMstat306 = "";
        String valueMstat309 = "";
        String valueTemp102 = "";
        String valueVstat157 = "";
        String valueVstat156 = "";
        String valueVstat155 = "";
        String valueTemp304 = "";
        String valueVstat117 = "";


        //Sql spørring som henter ut produkt fra Ungernet med timestampet
        String SQL = "SELECT * FROM UfacidProdusert where dato > '14.07.2015'";
        String SQLCount = "SELECT COUNT(*) FROM UfacidProdusert where dato > '14.07.2015'";
        String SQL2 = "SELECT * FROM UngerolProdusert where dato > '14.07.2015'";
        String SQLCount2 = "SELECT COUNT(*) FROM UngerolProdusert where dato > '14.07.2015'";
        //System.out.println(SQL);
        Statement statementCount = null;
        //******Statement statCount = null;
        try {
            statementCount = connection.createStatement();
            gui.txtInfoPane.append("\n" + statementCount.executeQuery(SQLCount).toString());
            gui.txtInfoPane.append("\n" + statementCount.executeQuery(SQLCount2).toString());

            //*****statCount = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println(SQL);
        Statement statement = null;
        //******Statement statCount = null;
        try {
            statement = connection.createStatement();
            //*****statCount = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        //****ResultSet resultSetCount = null;
        try {
            resultSet = statement.executeQuery(SQL);
            //***resultSetCount = statCount.executeQuery(SQLCount)

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Henter ut data fra Ufacid spørring Ungernet
        try {
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("value"));
                produkt = resultSet.getString("Produkt");
                dato_s = resultSet.getString("Dato");



                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dato_s);
                    dato = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Henter ut tag fra pi respektivt med tidsstempel
                valueRastoff = piCon.readTagHist(Rastoff, dato_s);
                valueSvovel = piCon.readTagHist(Svovel, dato_s);
                valueMolar = piCon.readTagHist(Molar, dato_s);
                valueMstat580 = piCon.readTagHist(Mstat580, dato_s);
                valueMstat581 = piCon.readTagHist(Mstat581, dato_s);
                valueMstat292 = piCon.readTagHist(Mstat292, dato_s);
                valueMstat007 = piCon.readTagHist(Mstat007, dato_s);
                valueMstat274 = piCon.readTagHist(Mstat274, dato_s);
                valueMstat505 = piCon.readTagHist(Mstat505, dato_s);
                valueMstat013 = piCon.readTagHist(Mstat013, dato_s);
                valueMstat307 = piCon.readTagHist(Mstat307, dato_s);
                valueMstat306 = piCon.readTagHist(Mstat306, dato_s);
                valueMstat309 = piCon.readTagHist(Mstat309, dato_s);
                valueTemp102 = piCon.readTagHist(Temp102, dato_s);
                valueVstat157 = piCon.readTagHist(Vstat157, dato_s);
                valueVstat156 = piCon.readTagHist(Vstat156, dato_s);
                valueVstat155 = piCon.readTagHist(Vstat155, dato_s);
                valueTemp304 = piCon.readTagHist(Temp304, dato_s);
                valueVstat117 = piCon.readTagHist(Vstat117, dato_s);

                //Skriver verdiene til fil
                try {
                    writeToFile(
                            produkt,
                            valueRastoff,
                            valueSvovel,
                            valueMolar,
                            valueMstat580,
                            valueMstat581,
                            valueMstat292,
                            valueMstat007,
                            valueMstat274,
                            valueMstat505,
                            valueMstat013,
                            valueMstat307,
                            valueMstat306,
                            valueMstat309,
                            valueTemp102,
                            valueVstat157,
                            valueVstat156,
                            valueVstat155,
                            valueTemp304,
                            valueVstat117);

                            gui.txtInfoPane.append("\n\n" + produkt+
                            valueRastoff+
                            valueSvovel+
                            valueMolar+
                            valueMstat580+
                            valueMstat581+
                            valueMstat292+
                            valueMstat007+
                            valueMstat274+
                            valueMstat505+
                            valueMstat013+
                            valueMstat307+
                            valueMstat306+
                            valueMstat309+
                            valueTemp102+
                            valueVstat157+
                            valueVstat156+
                            valueVstat155+
                            valueTemp304+
                            valueVstat117);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement2 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet2 = null;
        try {
            resultSet2 = statement.executeQuery(SQL2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Henter ut data fra Ungerol spørring Ungernet
        try {
            while (resultSet2.next()) {
                //System.out.println(resultSet.getString("value"));
                produkt = resultSet2.getString("Produkt");
                dato_s = resultSet2.getString("Dato");

                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dato_s);
                    dato = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Henter ut tag fra pi respektivt med tidsstempel
                valueRastoff = piCon.readTagHist(Rastoff, dato_s);
                valueSvovel = piCon.readTagHist(Svovel, dato_s);
                valueMolar = piCon.readTagHist(Molar, dato_s);
                valueMstat580 = piCon.readTagHist(Mstat580, dato_s);
                valueMstat581 = piCon.readTagHist(Mstat581, dato_s);
                valueMstat292 = piCon.readTagHist(Mstat292, dato_s);
                valueMstat007 = piCon.readTagHist(Mstat007, dato_s);
                valueMstat274 = piCon.readTagHist(Mstat274, dato_s);
                valueMstat505 = piCon.readTagHist(Mstat505, dato_s);
                valueMstat013 = piCon.readTagHist(Mstat013, dato_s);
                valueMstat307 = piCon.readTagHist(Mstat307, dato_s);
                valueMstat306 = piCon.readTagHist(Mstat306, dato_s);
                valueMstat309 = piCon.readTagHist(Mstat309, dato_s);
                valueTemp102 = piCon.readTagHist(Temp102, dato_s);
                valueVstat157 = piCon.readTagHist(Vstat157, dato_s);
                valueVstat156 = piCon.readTagHist(Vstat156, dato_s);
                valueVstat155 = piCon.readTagHist(Vstat155, dato_s);
                valueTemp304 = piCon.readTagHist(Temp304, dato_s);
                valueVstat117 = piCon.readTagHist(Vstat117, dato_s);

                //Skriver verdiene til fil
                try {
                    writeToFile(
                            produkt,
                            valueRastoff,
                            valueSvovel,
                            valueMolar,
                            valueMstat580,
                            valueMstat581,
                            valueMstat292,
                            valueMstat007,
                            valueMstat274,
                            valueMstat505,
                            valueMstat013,
                            valueMstat307,
                            valueMstat306,
                            valueMstat309,
                            valueTemp102,
                            valueVstat157,
                            valueVstat156,
                            valueVstat155,
                            valueTemp304,
                            valueVstat117);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Lukker database connect
        try {
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Totalt: " + teller + "linjer");
        //return returnVerdi;



    }

    //Script for skriving til fil
    private void writeToFile(String tag1, String tag2, String tag3, String tag4, String tag5,
                             String tag6, String tag7, String tag8, String tag9, String tag10, String tag11, String tag12,
                             String tag13, String tag14, String tag15, String tag16, String tag17, String tag18, String tag19, String produkt) throws IOException
    {
        teller++;
        FileWriter write = new FileWriter("c:/TrainingSet.arff", true);
        PrintWriter print_line = new PrintWriter(write);

        //Skriver produktnavn og prosessverdier ned til fil

        print_line.println(tag1 + ", " + tag2 + ", " + tag3 + ", " + tag4 + ", " + tag5 + ", " + tag6 + ", " + tag7
                + ", " + tag8 + ", " + tag9 + ", " + tag10 + ", " + tag11 + ", " + tag12 + ", " + tag13 + ", " + tag14 + ", " + tag15
                + ", " + tag16 + ", " + tag17 + ", " + tag18 + ", " + tag19 + ", " + produkt);

        print_line.close();


    }

    public void writeToDB(String tag, double value, String timeStamp) throws SQLException {
        if (connection == null)
            openDB();

        //String newTimeStamp = reformatDateTime(timeStamp);
        String SQL = "INSERT INTO [LINKEDPI].[piarchive]..[picomp] (tag, time, value, flags) " +
                "VALUES('" + tag + "','" + timeStamp + "'," + value + ", 'Q')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL);
        //System.out.println("Wrote: " + SQL + "\n");
    }

    public String reformatDateTime(String date) {
        // Reformat datetime
        String reformatDateTime;
        java.util.Date datetime = null;
        try {
            datetime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reformatDateTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(datetime);

        return reformatDateTime;
    }
    private void readTagLoop(Date datoSet) throws SQLException {

        //Åpner kopling mot PI database
        if (connection == null)
            try {
                gui.txtInfoPane.setText("Prøver å kople seg til databasen");
                openDB();
            } catch (SQLException e) {
                e.printStackTrace();

            }

        PiConnect piCon = new PiConnect();
        String returnVerdi = "";
        String produkt = "";
        String dato_s = "";
        String dato = "";

        //Prosessverdier som skal hentes fra historian
        String Rastoff = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.REAKTOR_REG_4018FT010_MEAS";
        String Svovel = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Svovelsystem.AW5101.SVOVEL_REG_14FT519_MEAS";
        String Molar = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.MolarBeregning";
        String Mstat580 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU580_MSTAT";
        String Mstat581 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU581_MSTAT";
        String Mstat292 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU292_MSTAT";
        String Mstat007 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.S0YLEBU_MTR_6532HU007_MSTAT";
        String Mstat274 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_FKJ_MTR_22HU274_MSTAT";
        String Mstat505 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_22HU505_MSTAT";
        String Mstat013 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_4022HU013_MSTAT";
        String Mstat307 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT307_MEAS";
        String Mstat306 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT306_MEAS";
        String Mstat309 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT309_MEAS";
        String Temp102 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_REG_22TT102_MEAS";
        String Vstat157 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.VTL_INND_VLV_4024GSH157_CIN";
        String Vstat156 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024GSH156_CIN";
        String Vstat155 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024HSV155_COUT";
        String Temp304 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_REG_22TE304_MEAS";
        String Vstat117 = "HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_VLV_22GSH117_CIN";

        String valueRastoff = "";
        String valueSvovel = "";
        String valueMolar = "";
        String valueMstat580 = "";
        String valueMstat581 = "";
        String valueMstat292 = "";
        String valueMstat007 = "";
        String valueMstat274 = "";
        String valueMstat505 = "";
        String valueMstat013 = "";
        String valueMstat307 = "";
        String valueMstat306 = "";
        String valueMstat309 = "";
        String valueTemp102 = "";
        String valueVstat157 = "";
        String valueVstat156 = "";
        String valueVstat155 = "";
        String valueTemp304 = "";
        String valueVstat117 = "";


        //Sql spørring som henter ut produkt fra Ungernet med timestampet
        String SQL = "SELECT * FROM UfacidProdusert where dato > " + datoSet;
        String SQLCount = "SELECT COUNT(*) FROM UfacidProdusert where dato > " + datoSet;
        String SQL2 = "SELECT * FROM UngerolProdusert where dato > " + datoSet;
        String SQLCount2 = "SELECT COUNT(*) FROM UngerolProdusert where dato > " + datoSet;

        gui.txtInfoPane.append("\nAntall linjer fra Ufacid: " + SQLCount);
        gui.txtInfoPane.append("\nAntall linjer fra Ungerol: " + SQLCount);

        //System.out.println(SQL);
        Statement statement = null;
        //******Statement statCount = null;
        try {
            statement = connection.createStatement();
            //*****statCount = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        //****ResultSet resultSetCount = null;
        try {
            resultSet = statement.executeQuery(SQL);
            //***resultSetCount = statCount.executeQuery(SQLCount)

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Henter ut data fra Ufacid spørring Ungernet
        try {
            while (resultSet.next()) {
                //System.out.println(resultSet.getString("value"));
                produkt = resultSet.getString("Produkt");
                dato_s = resultSet.getString("Dato");



                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dato_s);
                    dato = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Henter ut tag fra pi respektivt med tidsstempel
                valueRastoff = piCon.readTagHist(Rastoff, dato_s);
                valueSvovel = piCon.readTagHist(Svovel, dato_s);
                valueMolar = piCon.readTagHist(Molar, dato_s);
                valueMstat580 = piCon.readTagHist(Mstat580, dato_s);
                valueMstat581 = piCon.readTagHist(Mstat581, dato_s);
                valueMstat292 = piCon.readTagHist(Mstat292, dato_s);
                valueMstat007 = piCon.readTagHist(Mstat007, dato_s);
                valueMstat274 = piCon.readTagHist(Mstat274, dato_s);
                valueMstat505 = piCon.readTagHist(Mstat505, dato_s);
                valueMstat013 = piCon.readTagHist(Mstat013, dato_s);
                valueMstat307 = piCon.readTagHist(Mstat307, dato_s);
                valueMstat306 = piCon.readTagHist(Mstat306, dato_s);
                valueMstat309 = piCon.readTagHist(Mstat309, dato_s);
                valueTemp102 = piCon.readTagHist(Temp102, dato_s);
                valueVstat157 = piCon.readTagHist(Vstat157, dato_s);
                valueVstat156 = piCon.readTagHist(Vstat156, dato_s);
                valueVstat155 = piCon.readTagHist(Vstat155, dato_s);
                valueTemp304 = piCon.readTagHist(Temp304, dato_s);
                valueVstat117 = piCon.readTagHist(Vstat117, dato_s);

                //Skriver verdiene til fil
                try {
                    writeToFile(
                            valueRastoff,
                            valueSvovel,
                            valueMolar,
                            valueMstat580,
                            valueMstat581,
                            valueMstat292,
                            valueMstat007,
                            valueMstat274,
                            valueMstat505,
                            valueMstat013,
                            valueMstat307,
                            valueMstat306,
                            valueMstat309,
                            valueTemp102,
                            valueVstat157,
                            valueVstat156,
                            valueVstat155,
                            valueTemp304,
                            valueVstat117,
                            produkt);
                    gui.txtInfoPane.append("\nLinje skrevet til fil: \n" +valueRastoff+valueSvovel+
                            valueMolar+
                            valueMstat580+
                            valueMstat581+
                            valueMstat292+
                            valueMstat007+
                            valueMstat274+
                            valueMstat505+
                            valueMstat013+
                            valueMstat307+
                            valueMstat306+
                            valueMstat309+
                            valueTemp102+
                            valueVstat157+
                            valueVstat156+
                            valueVstat155+
                            valueTemp304+
                            valueVstat117+
                            produkt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement2 = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet2 = null;
        try {
            resultSet2 = statement.executeQuery(SQL2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Henter ut data fra Ungerol spørring Ungernet
        try {
            while (resultSet2.next()) {
                //System.out.println(resultSet.getString("value"));
                produkt = resultSet2.getString("Produkt");
                dato_s = resultSet2.getString("Dato");

                try {
                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dato_s);
                    dato = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Henter ut tag fra pi respektivt med tidsstempel
                valueRastoff = piCon.readTagHist(Rastoff, dato_s);
                valueSvovel = piCon.readTagHist(Svovel, dato_s);
                valueMolar = piCon.readTagHist(Molar, dato_s);
                valueMstat580 = piCon.readTagHist(Mstat580, dato_s);
                valueMstat581 = piCon.readTagHist(Mstat581, dato_s);
                valueMstat292 = piCon.readTagHist(Mstat292, dato_s);
                valueMstat007 = piCon.readTagHist(Mstat007, dato_s);
                valueMstat274 = piCon.readTagHist(Mstat274, dato_s);
                valueMstat505 = piCon.readTagHist(Mstat505, dato_s);
                valueMstat013 = piCon.readTagHist(Mstat013, dato_s);
                valueMstat307 = piCon.readTagHist(Mstat307, dato_s);
                valueMstat306 = piCon.readTagHist(Mstat306, dato_s);
                valueMstat309 = piCon.readTagHist(Mstat309, dato_s);
                valueTemp102 = piCon.readTagHist(Temp102, dato_s);
                valueVstat157 = piCon.readTagHist(Vstat157, dato_s);
                valueVstat156 = piCon.readTagHist(Vstat156, dato_s);
                valueVstat155 = piCon.readTagHist(Vstat155, dato_s);
                valueTemp304 = piCon.readTagHist(Temp304, dato_s);
                valueVstat117 = piCon.readTagHist(Vstat117, dato_s);

                //Skriver verdiene til fil
                try {
                    writeToFile(
                            produkt,
                            valueRastoff,
                            valueSvovel,
                            valueMolar,
                            valueMstat580,
                            valueMstat581,
                            valueMstat292,
                            valueMstat007,
                            valueMstat274,
                            valueMstat505,
                            valueMstat013,
                            valueMstat307,
                            valueMstat306,
                            valueMstat309,
                            valueTemp102,
                            valueVstat157,
                            valueVstat156,
                            valueVstat155,
                            valueTemp304,
                            valueVstat117);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Lukker database connect
        try {
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Totalt: " + teller + "linjer");
        //return returnVerdi;



    }

}
