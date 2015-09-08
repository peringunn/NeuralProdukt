import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by Per-Olav on 19.08.2015.
 */
public class GUITagBrowse extends JFrame{
    private JButton søkButton;
    private JTextField textField1;
    private JTextArea textAreaResultat;
    private JComboBox comboBox1;
    private JPanel rootPanel;
    private JButton cmdAvslutt;

    public GUITagBrowse()
    {
        super("Tag browser");
        setContentPane(rootPanel);
        setSize(1200, 800);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        cmdAvslutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
}
