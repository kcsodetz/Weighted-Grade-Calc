import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Ken Sodetz on 12/14/2016.
 */
public class CalculateGUI extends Frame implements ActionListener { //TODO implement GUI

    private Container getContentPane;
    //buttons
    private JButton calculate;
    private JTextField input;
    private JTextArea output;
    private JScrollPane scrollPane;

    public CalculateGUI(){
        this.setTitle("Weighted Grade Calculator");
        //this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addElements();
    }

    private void addElements(){ //TODO finish method

        JPanel contentPanel, buttonPanel;

        Container contentPane = this.getContentPane;
        contentPane.setLayout(new BorderLayout());

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder("Input"));

        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder());

        contentPanel.add(buttonPanel, BorderLayout.NORTH);

        calculate = new JButton("Calculate");
        calculate.addActionListener(this);

        contentPane.add(contentPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(calculate, BorderLayout.SOUTH);

    }

    public void createContentPanel(){ //TODO finish method

    }

    @Override
    public void actionPerformed(ActionEvent e) { //TODO finish action listener
        String str;


    }

    public static void main(String[] args) { //TODO finish main
        CalculateGUI window = new CalculateGUI();
        window.pack();
        window.setVisible(true);
    }
}
