import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Creates Login Page GUI. This method will run by Event Dispatch Thread(EDT).
 */
public class Login extends JComponent implements Runnable {

    JButton loginButton;
    JButton deleteAccountButton;
    JButton createAccountButton;
    JButton editButton;

    JTextField userText;
    JTextField passwordText;
    JLabel userLabel;
    JLabel passwordLabel;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                if (userText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Do not leave username field blank", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (passwordText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Do not leave password field blank", "Error", JOptionPane.ERROR_MESSAGE);
                }

                Login loginObj = new Login();
                boolean user = loginObj.alreadyUser(userText.getText(), passwordText.getText());
                String[] options = {"Teacher", "Student"};
                if (user) {
                    int identity = JOptionPane.showOptionDialog(null, "Student or Teacher?", "Identify yourself", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                }

            }
            if (e.getSource() == deleteAccountButton) {
                Login loginObj1 = new Login();
                boolean a = loginObj1.alreadyUSer(userText.getText(), passwordText.getText());
                if (a) {
                    loginObj1.deleteUser(userText.getText(), passwordText.getText());
                    JOptionPane.showMessageDialog(null, "Account is successfully deleted!", "Thank you for using our program", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == editButton) {
                Login loginObj2 = new Login();
                boolean b = loginObj2.alreadyUser(userText.getText(), passwordText.getText());
                if (b) {
                    String newUserName = JOptionPane.showInputDialog(null,"What would you like your new username to be?", "New username", JOptionPane.INFORMATION_MESSAGE);
                    loginObj2.editUsername(userText.getText(), newUserName);
                    String newPassword = JOptionPane.showInputDialog(null, "What would you like your new password to be?", "New P");
                    loginObj2.editPassword(newUserName, newPassword);
                    flag2 = 1;
                    JOptionPane.showMessageDialog(null, "Account is successfully edited!");
                }
            }
            if (e.getSource() == createAccountButton) {
                String username = JOptionPane.showInputDialog(null, "What would you like your username to be?", "Create username", JOptionPane.PLAIN_MESSAGE);
                String password = JOptionPane.showInputDialog(null, "What would you like your password to be?", "Create password", JOptionPane.PLAIN_MESSAGE);
                Login loginObj = new Login();

                String login = loginObj.addUsername(username);
                if (!login.equals("This username already exists, please try again")) {
                    loginObj.addPassword(password);
                    JOptionPane.showMessageDialog(null, "Your account is successfully created!");
                    break;
                }
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Login());

    }

    public void run() {
        JOptionPane.showMessageDialog(null, "Please do not enter .txt when entering the file names of quizzes, courses, " +
                "and submissions", "How to use our quiz bank", JOptionPane.INFORMATION_MESSAGE);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        frame.setTitle("Welcome to our Quiz Bank!");
        frame.setSize(420,420);
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        // end creating frame

        //Container content = frame.getContentPane();
        //content.setLayout(new BorderLayout());
        loginButton = new JButton("Login");
        loginButton.setBounds(250, 200, 75, 25);
        panel.add(loginButton);
        loginButton.addActionListener(actionListener);
        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setBounds(10, 300, 130, 25);
        panel.add(deleteAccountButton);
        deleteAccountButton.addActionListener(actionListener);
        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(145, 300, 130, 25);
        panel.add(createAccountButton);
        createAccountButton.addActionListener(actionListener);
        editButton = new JButton("Edit Account");
        editButton.setBounds(280, 300, 130, 25);
        panel.add(editButton);

        userText = new JTextField(20);
        userText.setBounds(125, 100, 200, 25);
        panel.add(userText);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(125, 150, 200, 25);
        panel.add(passwordText);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(50, 100, 75, 25);
        panel.add(userLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(50, 150, 75, 25);
        panel.add(passwordLabel);

        //content.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }
}
