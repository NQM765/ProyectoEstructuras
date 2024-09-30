package ProyectoEstructuras2;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;



public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JTextField textField = new JTextField(20);
        textField.setBounds(50, 50, 200, 30);
        frame.add(textField);

        JButton button = new JButton("Submit");
        button.setBounds(50, 100, 200, 30);
        frame.add(button);

        Lista<Tarjeta> lista = new Lista<>();

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 150, 300, 200);
        frame.add(textArea);

        JTextField titleField = new JTextField(20);
        titleField.setBounds(50, 200, 200, 30);
        frame.add(titleField);

        JTextField tagField = new JTextField(20);
        tagField.setBounds(50, 250, 200, 30);
        frame.add(tagField);


button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleField.getText();
        String tag = tagField.getText();
        String data = textField.getText();

        // Create a new Tarjeta object with the input data
        Tarjeta tarjeta = new Tarjeta(title, data, tag, 0.0f);

        // Clear the text fields
        titleField.setText("");
        tagField.setText("");
        textField.setText("");

        // Add the Tarjeta object to the list
        lista.add(tarjeta);

        // Update the text area with the contents of the list
        StringBuilder listContent = new StringBuilder();
        for (Tarjeta t : lista) {
            listContent.append(t.getTitle()).append("\t")
                       .append(t.getTag()).append("\t")
                       .append(t.getData()).append("\n");
        }
        textArea.setText(listContent.toString());
    }
});
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
}
