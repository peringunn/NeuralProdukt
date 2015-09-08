/**
 * Created by Per-Olav on 29.07.2015.
 */
public class GetPrValues implements Runnable{
    private GUIMain gui;
    public GetPrValues(GUIMain gui)
    {
        this.gui = gui;
    }
    public void run()
    {
        try {
            gui.txtInfoPane.setText("Prøver å hente ut prosessverdier!");
            GetProsessValues values = new GetProsessValues();
            String getValues[][] = values.getValues();

            //Skriver ut resultatet i textarea boksen
            for (int i = 0; i <= getValues.length - 1; i++) {
                gui.txtInfoPane.append(getValues[0][i] + "\t");
                gui.txtInfoPane.append(getValues[1][i]);
                gui.txtInfoPane.append("\n");
            }
        } catch (Exception e)
        {
            gui.txtInfoPane.setText("Klarte ikke å hente verdier fra PI-Databasen");
            gui.txtInfoPane.append("\n\nExeption: " + e);
        }
    }

}
