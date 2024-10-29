import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import org.json.simple.parser.ParseException;

import java.util.Collections;


public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
    private JPanel controlPanel;
    private JPanel controlPanel2;
    private static JTextArea linkInput; //typing area
    private static JTextArea output;
    private int WIDTH=800;
    private int HEIGHT=700;


    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#40E0D0"));
        UIManager.put("Button.arc", 20); // Round buttons
        UIManager.put("Label.arc", 20);
        UIManager.put("Component.focusColor", Color.CYAN); // Cyan focus color
        UIManager.put("Panel.background", Color.DARK_GRAY); // Dark gray
        UIManager.put("TextField.focusedBackground", "#40E0D0");
        UIManager.put("ScrollBar.thumb", new Color(0x40E0D0)); // Turquoise for thumb

        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();


    }

    private void prepareGUI() {
        mainFrame = new JFrame("pokedex");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 1));

        controlPanel2 = new JPanel();
        controlPanel2.setLayout(new BorderLayout());

        linkInput = new JTextArea();
        linkInput.setBounds(50, 5, WIDTH-100, 100);







        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        output = new JTextArea();
        output.setBounds(50, 1, WIDTH-100, 100);
        output.setLineWrap(true);  // Wrap lines
        output.setWrapStyleWord(true);  //  Wrap at word boundaries

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        JButton button1 = new JButton("Search");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        button1.setActionCommand("Start");
        button1.addActionListener(this);

        JLabel label1 = new JLabel("Link", JLabel.CENTER);
        //JLabel image = new JLabel(imageIcon);
        okButton.setActionCommand("OK");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

       /* mainFrame.add(button1);
        mainFrame.add(button2);
        mainFrame.add(button3);
        mainFrame.add(button4);*/
        mainFrame.add(controlPanel, BorderLayout.NORTH);
        mainFrame.add(controlPanel2);

        //controlPanel.add(label4);
        controlPanel.add(label1);
        controlPanel.add(linkInput);

        controlPanel.add(button1, BorderLayout.EAST);

        controlPanel2.add(scrollPane);




        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Start".equals(e.getActionCommand())) {
            output.setText("");
            try {
                ReadJson read = new ReadJson();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

        }
    }
    public static void errorMessage() {
        output.append("Your URL is invalid. Please check for typos and try again."+ "\n");
        output.append("Tip: ensure that your URL begins with https://www...");
    }
    public static void writeToOutput(String outputParameter) {
        output.append(outputParameter+ "\n");
        System.out.println(outputParameter);
    }

    public static String getLinkInput() {
        return linkInput.getText();
    }


}