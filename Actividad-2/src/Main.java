import javax.swing.*;

public class Main extends JFrame {

    private JTextField textField1;
    private JButton agregarButton;

    public Main() {
        setTitle("Todo App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }
}
