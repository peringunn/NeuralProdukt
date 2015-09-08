import java.io.*;

public class ReadWekaRules implements Runnable {
    private GUIMain gui;
    public ReadWekaRules(GUIMain gui)
    {
        this.gui = gui;
    }

    @Override
    public void run()
    {

        double count = 0, count2=0,countBuffer=0,countBuffer2=0,countLine=0,countLine2=0;
        String lineNumber = "";
        int lineNumberInt = 0;
        int lineNumberInt2 = 0;
        String filename = "C://Users//Per-Olav//Documents//MLSjekkProduksjon//Weka_Results//Jrip.txt";
        //String filePath = "C:\\Users\\allen\\Desktop\\TestText.txt";
        BufferedReader br;
        String inputSearch = "rules:"; //For å fjerne alt over reglene
        String inputSearch2 = "Rules"; //For å fjerne all tekst under reglene
        String line = "";
        String content = "public class rulesOfWeka{\n\tpublic void estimer()\n{\n";

        //Tømmer infopanel for tekst.
        gui.txtInfoPane.setText("");

        //Finner linje nummer for teksten over reglene
        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while((line = br.readLine()) != null)
                {
                    countLine++;
                    //System.out.println(line);
                    String[] words = line.split(" ");

                    for (String word : words) {
                        if (word.equals(inputSearch)) {
                            count++;
                            countBuffer++;
                        }
                    }

                    if(countBuffer > 0)
                    {
                        countBuffer = 0;
                        lineNumber += countLine + ",";
                        lineNumberInt += countLine;
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //System.out.println("Times found at--"+count);
        //System.out.println("Word found at--"+lineNumber);

        //Finner linje nummer for teksten under reglene
        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while((line = br.readLine()) != null)
                {
                    countLine2++;
                    //System.out.println(line);
                    String[] words = line.split(" ");

                    for (String word : words) {
                        if (word.equals(inputSearch2)) {
                            count2++;
                            countBuffer2++;
                        }
                    }

                    if(countBuffer2 > 0)
                    {
                        countBuffer2 = 0;
                        //lineNumber2 += countLine2 + ",";
                        lineNumberInt2 += countLine2;
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
        String content = null;

        File file = new File(filename); //for ex foo.txt
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        content.equals("rules");

        content = content.replaceAll("and", "&&");
        //gui.txtInfoPane.setText(content);
        content = content.replaceAll("Organsik mengde", "Organisk");
        content = content.replaceAll("40HU580", "M40HU580");
        content = content.replaceAll("40HU581", "M40HU581");
        content = content.replaceAll("40HU292", "M40HU292");
        content = content.replaceAll("6032HU007", "M6032HU007");
        content = content.replaceAll("22HU274", "M22HU274");
        content = content.replaceAll("22HU505", "M22HU505");
        content = content.replaceAll("22HU013", "M22HU013");
        content = content.replaceAll("22FT307", "M22FT307");
        content = content.replaceAll("22FT306", "M22FT306");
        content = content.replaceAll("22FT309", "M22FT309");
        content = content.replaceAll("22TT102", "M22TT102");
        content = content.replaceAll("24GSH157", "M24GSH157");
        content = content.replaceAll("24GSH156", "M24GSH156");
        content = content.replaceAll("24HCV155", "M24HCV155");
        content = content.replaceAll("22TE304", "M22TE304");
        content = content.replaceAll("22HSV117", "M22HSV117");
        content = content.replaceAll(" = ", " == ");
        content = content.replaceAll(" => ", " ");
        //gui.txtInfoPane.setText(content);
        //gui.txtInfoPane.append(lineNumber);
        //gui.txtInfoPane.append("" + count);
        */

        //Fjerner de første linjene
        try {
            br = new BufferedReader(new FileReader(filename));
            int lineCounter = 0;
            int test = lineNumberInt2;


            try {
                int lineKontroller = 0;
                while((line = br.readLine()) != null)
                {

                    lineCounter++;
                    if (lineCounter > lineNumberInt+2 && lineCounter < lineNumberInt2-1)
                    {
                        lineKontroller++;
                        //gui.txtInfoPane.append(line + "\n");
                        if (lineKontroller == 1)
                        content = content + "if (" + line + "\n";
                        else if (lineCounter == (lineNumberInt2-2))
                            content = content + "else //" + line + "\n";
                        else
                            content = content + "else if (" + line + "\n";
                    }

                }
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        content = content.replaceAll("and", "&&");
        //gui.txtInfoPane.setText(content);
        content = content.replaceAll("Organsik mengde", "Organisk");
        content = content.replaceAll("40HU580", "M40HU580");
        content = content.replaceAll("40HU581", "M40HU581");
        content = content.replaceAll("40HU292", "M40HU292");
        content = content.replaceAll("6032HU007", "M6032HU007");
        content = content.replaceAll("22HU274", "M22HU274");
        content = content.replaceAll("22HU505", "M22HU505");
        content = content.replaceAll("22HU013", "M22HU013");
        content = content.replaceAll("22FT307", "M22FT307");
        content = content.replaceAll("22FT306", "M22FT306");
        content = content.replaceAll("22FT309", "M22FT309");
        content = content.replaceAll("22TT102", "M22TT102");
        content = content.replaceAll("24GSH157", "M24GSH157");
        content = content.replaceAll("24GSH156", "M24GSH156");
        content = content.replaceAll("24HCV155", "M24HCV155");
        content = content.replaceAll("22TE304", "M22TE304");
        content = content.replaceAll("22HSV117", "M22HSV117");
        content = content.replaceAll(" = ", " == ");
        content = content.replaceAll(" => ", " ");
        content = content.replaceAll("Produkt", ") \n result'");
        //gui.txtInfoPane.setText(content);


        //Loop for fjerning av endeslutten i hver regel
        int teller=0;
        StringBuilder checkString = new StringBuilder(content);
        //checkString = content.toString();


        for (int i =0; i<= checkString.length()-1; i++)
        {
            //String compare = "" + content.charAt(i);
            String compare = "" + checkString.charAt(i);
            if(compare.equals("("))
            {
                //Søker for å finne / i nærheten
                //dersom vi får treff er vi på rett sted og kan slette endingen
                for(int y=0;y<=10;y++)
                {
                    String compare2 = "";
                    if ((i+y)< checkString.length())
                    {
                        compare2 = "" + checkString.charAt(i + y);
                    }
                    else
                    {
                        compare2 = "";
                    }
                    if(compare2.equals("/"))
                    {
                        //Må finne posisjon til slutt parantesen
                        for(int u=0;u<15; u++ )
                        {
                            if(i > 8160)
                                System.out.print("Stop");

                            String compare3 = "";
                            if((i+y+u) < checkString.length())
                            {
                                compare3 = "" + checkString.charAt(i + y + u);
                            }
                            else compare3="";



                                if (compare3.equals(")"))
                            {

                                //Fjerner slutten på linjen
                                for(int x=0;x<(u+y);x++)
                                {
                                    checkString.deleteCharAt(i + ((u+y)-x));
                                    System.out.println("i = " + i + " y = " + y + " u = " + u);
                                    System.out.println("");
                                }

                            }

                        }
                        checkString.deleteCharAt(i);
                        teller++;
                    }
                }
            }
        }
        System.out.print("h");
        content = checkString.toString();
        gui.txtInfoPane.append(content);
        gui.txtInfoPane.append("}\n}");




    }

}