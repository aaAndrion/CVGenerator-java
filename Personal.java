import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.*;

public class Personal extends JPanel implements KeyListener {
    private JTextField txtFirstName, txtLastName, txtEmail, txtPhoneNum;
    private JLabel lblPersonal, lblFirstName, lblLastName, lblEmail, lblPhoneNum;

    private static final String FONTSTYLE = "Berlin Sans FB";

    public Personal() {
        lblPersonal = new JLabel("Personal Details");
        lblPersonal.setFont(new Font("Consolas", Font.PLAIN, 20));
        lblPersonal.setBounds(20, 15, 180, 20);

        lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblFirstName.setBounds(40, 35, 75, 20);

        lblLastName = new JLabel("Last Name");
        lblLastName.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblLastName.setBounds(40, 65, 75, 20);

        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblEmail.setBounds(74, 95, 40, 20);

        lblPhoneNum = new JLabel("Phone Number");
        lblPhoneNum.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblPhoneNum.setBounds(15, 125, 105, 20);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(120, 38, 150, 20);

        txtLastName = new JTextField();
        txtLastName.setBounds(120, 68, 150, 20);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 98, 150, 20);

        txtPhoneNum = new JTextField();
        txtPhoneNum.setBounds(120, 128, 150, 20);

        // Add Labels into this panel
        add(lblPersonal);
        add(lblFirstName);
        add(lblLastName);
        add(lblEmail);
        add(lblPhoneNum);

        // Add Text Fields into this Panel
        add(txtFirstName);
        add(txtLastName);
        add(txtEmail);
        add(txtPhoneNum);

        // Add Key listners to this panel
        txtFirstName.addKeyListener(this);
        txtLastName.addKeyListener(this);
        txtEmail.addKeyListener(this);
        txtPhoneNum.addKeyListener(this);

        setLayout(null);

    }

    public void errorMessage(String txt) { JOptionPane.showMessageDialog(null, txt); }

    private static String capitalizeFully(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Arrays.stream(str.split("\\s+")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public String getNames() { return getFirstName() + " " + getLastName(); }

    private String getFirstName() { return capitalizeFully(txtFirstName.getText()); }

    private String getLastName() { return capitalizeFully(txtLastName.getText()); }

    public String getEmail() {
        return capitalizeFully(txtEmail.getText());

    }

    public String getPhoneNum() { return txtPhoneNum.getText(); }

    public boolean emailValildation() {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matchEmail = pattern.matcher(getEmail());

        if (this.getEmail().isEmpty()) {
            errorMessage("Email Field is empty");

            return true;
        }

        else if (!matchEmail.matches()) {
            errorMessage("wrong email format");
            return true;

        }
        else {

            return false;
        }

    }

    public boolean pValidation() {
        if (this.getFirstName().isEmpty()) {
            errorMessage("First name Field is empty");
            return false;
        }
        else if (this.getLastName().isEmpty()) {
            errorMessage("Last name Field is empty");
            return false;
        }
        else if (emailValildation()) {

            return false;
        }
        else if (this.getPhoneNum().isEmpty()) {
            errorMessage("Phone Number Field is empty");
            return false;
        }
        else {
            return true;

        }

    }

  

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(txtPhoneNum)) {
            if (!(Character.isDigit(e.getKeyChar()))) {
                e.consume();
            }
        }

        

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}
