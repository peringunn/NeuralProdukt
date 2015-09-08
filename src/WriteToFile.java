import java.io.*;

/**
 * Created by Per-Olav on 08.08.2015.
 */
public class WriteToFile {
    public void writeToFile(String tag1, String tag2, String tag3, String tag4, String tag5,
                             String tag6, String tag7, String tag8, String tag9, String tag10, String tag11, String tag12,
                             String tag13, String tag14, String tag15, String tag16, String tag17, String tag18, String tag19, String produkt) throws IOException
    {
        //teller++;

        FileWriter write = new FileWriter("c:/Estimate.arff", true);
        PrintWriter print_line = new PrintWriter(write);

        //Dersom teller er en lages en heading i filen med navn


        //Skriver produktnavn og prosessverdier ned til fil
        print_line.println( tag1 + ", " + tag2 + ", " + tag3 + ", " + tag4 + ", " + tag5 + ", " + tag6 + ", " + tag7
                + ", " + tag8 + ", " + tag9 + ", " + tag10 + ", " + tag11 + ", " + tag12 + ", " + tag13 + ", " + tag14 + ", " + tag15
                + ", " + tag16 + ", " + tag17 + ", " + tag18 + ", " + tag19 + ", " + produkt);

        print_line.close();

        //Skriver ut oppdatering til konsol
        //System.out.print(teller + "* ");
        //if(teller == 200 || teller == 400 || teller == 600 || teller == 800 || teller == 1000 || teller == 1200 || teller == 1400)
        //    System.out.println("");


    }
    public void writeLine(String line, int brake) throws IOException
    {
        FileWriter write = new FileWriter("c:/Estimate.arff", true);
        PrintWriter print_line = new PrintWriter(write);
        if(brake==0)
        {
            print_line.print(line);
        }
        else
        {
            print_line.println(line);
        }

        print_line.close();

    }
    public void createFile()
    {
        try {
            PrintWriter writer = new PrintWriter("c:/Estimate.arff", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
