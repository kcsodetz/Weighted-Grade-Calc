import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Ken Sodetz on 12/14/2016.
 */
public class CalculateGUI extends Frame implements ActionListener {

    //container for the content
    private Container coontentPane;

    //buttons
    private JButton calculate;
    private JTextField input;
    private JTextArea output;
    private JScrollPane scrollPane;

    public CalculateGUI(){
        this.setTitle("Weighted Grade Calculator");

        addElements();
    }

    private void addElements(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
