import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    public test() {
        setTitle("Panel on Top Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main content pane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Create a text area for the top
        JTextArea textArea = new JTextArea("This is the text area at the top.");
        textArea.setEditable(false); // Make it non-editable
        contentPane.add(textArea, BorderLayout.NORTH);

        // Create a label
        JLabel label = new JLabel("This is the content pane below the text area.");
        contentPane.add(label, BorderLayout.CENTER);

        setContentPane(contentPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new test();
        });
    }
}
