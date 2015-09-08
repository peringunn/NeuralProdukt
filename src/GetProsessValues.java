import java.sql.SQLException;


/**
 * Created by Per-Olav on 14.07.2015.
 */
public class GetProsessValues {

    public String[][] getValues() {

        //Deklarere kopling mot PI database
        PiConnect piCon = new PiConnect();
        String[][] sendValue = new String[19][19];

        //setProgress.progressBar1.setValue(50);

        //Henter prosessverdiene fra PI database


            //Definerer navnet til taggene i matrise
            sendValue[0][0] = "Rastoff";
            sendValue[0][1] = "Svovel";
            sendValue[0][2] = "Molar";
            sendValue[0][3] = "Mstat580";
            sendValue[0][4] = "Mstat581";
            sendValue[0][5] = "Mstat292";
            sendValue[0][6] = "Mstat007";
            sendValue[0][7] = "Mstat274";
            sendValue[0][8] = "Mstat505";
            sendValue[0][9] = "Mstat013";
            sendValue[0][10] = "Mstat307";
            sendValue[0][11] = "Mstat306";
            sendValue[0][12] = "Mstat309";
            sendValue[0][13] = "Temp102";
            sendValue[0][14] = "Vstat157";
            sendValue[0][15] = "Vstat156";
            sendValue[0][16] = "Vstat155";
            sendValue[0][17] = "Temp304";
            sendValue[0][18] = "Vstat117";

            //Henter ut verdiene til taggene fra PI historian
            try{
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.REAKTOR_REG_4018FT010_MEAS") == null)
                    sendValue[1][0] = "0";
                else
                    sendValue[1][0] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.REAKTOR_REG_4018FT010_MEAS");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Svovelsystem.AW5101.SVOVEL_REG_14FT519_MEAS")== null)
                    sendValue[1][1] = "0";
                else
                    sendValue[1][1] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Svovelsystem.AW5101.SVOVEL_REG_14FT519_MEAS");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.MolarBeregning") == null)
                    sendValue[1][2] = "0";
                else
                    sendValue[1][2] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Fodesystem_Raastoff.MolarBeregning");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU580_MSTAT") == null)
                    sendValue[1][3] = "0";
                else
                    sendValue[1][3] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU580_MSTAT");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU581_MSTAT") == null)
                    sendValue[1][4] = "0";
                else
                    sendValue[1][4] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU581_MSTAT");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU292_MSTAT") == null)
                    sendValue[1][5] = "0";
                else
                    sendValue[1][5] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.TANKER_MTR_40HU292_MSTAT");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.S0YLEBU_MTR_6532HU007_MSTAT") == null)
                    sendValue[1][6] = "0";
                else
                    sendValue[1][6] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Raastofflagring.S0YLEBU_MTR_6532HU007_MSTAT");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_FKJ_MTR_22HU274_MSTAT") == null)
                    sendValue[1][7] = "0";
                else
                    sendValue[1][7] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_FKJ_MTR_22HU274_MSTAT");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_22HU505_MSTAT") == null)
                    sendValue[1][8] = "0";
                else
                    sendValue[1][8] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_22HU505_MSTAT");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_4022HU013_MSTAT") == null)
                    sendValue[1][9] = "0";
                else
                    sendValue[1][9] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_MTR_4022HU013_MSTAT");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT307_MEAS") == null)
                    sendValue[1][10] = "0";
                else
                    sendValue[1][10] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT307_MEAS");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT306_MEAS") == null)
                    sendValue[1][11] = "0";
                else
                    sendValue[1][11] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT306_MEAS");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT309_MEAS") == null)
                    sendValue[1][12] = "0";
                else
                    sendValue[1][12] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_USP_REG_22FT309_MEAS");
                if(piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_REG_22TT102_MEAS") == null)
                    sendValue[1][13] = "0";
                else
                    sendValue[1][13] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_REG_22TT102_MEAS");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.VTL_INND_VLV_4024GSH157_CIN") == null)
                    sendValue[1][14] = "0";
                else
                    sendValue[1][14] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.VTL_INND_VLV_4024GSH157_CIN");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024GSH156_CIN") == null)
                    sendValue[1][15] = "0";
                else
                    sendValue[1][15] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024GSH156_CIN");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024HSV155_COUT") == null)
                    sendValue[1][16] = "0";
                else
                    sendValue[1][16] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Utlufting_inndamping.UTL_INND_VLV_4024HSV155_COUT");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_REG_22TE304_MEAS") == null)
                    sendValue[1][17] = "0";
                else
                    sendValue[1][17] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT_REG_22TE304_MEAS");
                if (piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_VLV_22GSH117_CIN") == null)
                    sendValue[1][18] = "0";
                else
                    sendValue[1][18] = piCon.readTag("HMI_Unger.HMI_Server:HMI_Server:S_AVD.Noytraliseringssystem.N0YT2_VLV_22GSH117_CIN");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sendValue;

    }


}
