import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Skills extends JPanel implements ActionListener {
    private JLabel lblSkillsTTL, lblSkill;
    private JTextField txtSkill;
    private JButton btnAddUpdate, btnEdit, btnDelete;

    private JComboBox<String> cboLevel;
    private static final String FONTSTYLE = "Berlin Sans FB";

    List<SkillDetails> list = new LinkedList<SkillDetails>();

    int value;

    public Skills() {
        String[] level = {
                "Expert", "Experience", "Skillful", "Intermediate", "Begineer"
        };

        lblSkillsTTL = new JLabel("Skills");
        lblSkillsTTL.setFont(new Font("Consolas", Font.PLAIN, 20));
        lblSkillsTTL.setBounds(20, 15, 180, 20);

        lblSkill = new JLabel("Skill");
        lblSkill.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblSkill.setBounds(40, 35, 75, 20);

        txtSkill = new JTextField();
        txtSkill.setBounds(70, 35, 150, 20);

        cboLevel = new JComboBox<>(level);
        cboLevel.setBounds(240, 35, 100, 20);

        btnAddUpdate = new JButton("Add");
        btnAddUpdate.setBounds(370, 15, 80, 30);
        btnAddUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(370, 55, 80, 30);
        btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(370, 95, 80, 30);
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnAddUpdate.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);

        add(btnAddUpdate);
        add(btnEdit).setEnabled(false);
        add(btnDelete).setEnabled(false);

        add(lblSkillsTTL);
        add(lblSkill);
        add(txtSkill);

        add(cboLevel);

        setLayout(null);
    }

    public List<SkillDetails> getDetails() { return list; }


    public static String capitalizeFully(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Arrays.stream(str.split("\\s+")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
    

    public String getSkill() { return capitalizeFully(txtSkill.getText()); }

    public String getLevel() { return (String) cboLevel.getSelectedItem(); }

    @Override
    public void actionPerformed(ActionEvent e) { // TODO Auto-generated method stub
        if (e.getSource().equals(btnAddUpdate)) {
            if (btnAddUpdate.getText().equalsIgnoreCase("Update")) {
                int i = 0;
                System.out.println("Updated!");
                btnAddUpdate.setText("Add");
                for (SkillDetails skillDetails : list) {
                    i = list.indexOf(skillDetails);
                    if (i == value) {
                        System.out.println("should return list:" + i);

                        list.set(i, new SkillDetails(this.getSkill(), this.getLevel()));

                        txtSkill.setText("");
                    }
                }
                btnDelete.setEnabled(false);

            }
            else {
                if (isFieldEmpty()) {
                    System.out.println("added");
                    list.add(new SkillDetails(this.getSkill(), this.getLevel()));

                    btnEdit.setEnabled(true);

                    txtSkill.setText("");

                }
            }

        }

        if (e.getSource().equals(btnEdit)) {
            DefaultListModel<String> t1 = new DefaultListModel<String>();

            JList<String> jl = new JList<String>(t1);

            try {
                for (SkillDetails skill : list) {

                    t1.addElement(skill.getSkill());
                }

                int option = JOptionPane.showConfirmDialog(null, jl, "Skill: Edit", 0);
                if (option == JOptionPane.YES_OPTION) {
                    btnDelete.setEnabled(true);
                    value = jl.getSelectedIndex();

                    for (SkillDetails skillDetails : list) {
                        if (list.indexOf(skillDetails) == value) {
                            setFields(skillDetails.getSkill(), skillDetails.getLevel());

                        }
                        if (btnAddUpdate.getText().equalsIgnoreCase("Add"))
                            btnAddUpdate.setText("Update");

                    }

                }
            }
            catch (NullPointerException nullP) {
                nullP.printStackTrace();
            }

        }

        if (e.getSource().equals(btnDelete)) {
            for (SkillDetails skillDetails : list) {
                if (list.indexOf(skillDetails) == value) {
                    System.out.println("should return");
                    list.remove(skillDetails);

                }
            }
            txtSkill.setText("");

            btnDelete.setEnabled(false);
            btnAddUpdate.setText("Add");

        }
    }

    public boolean isFieldEmpty() {
        if (!this.getSkill().isEmpty() && !this.getLevel().isEmpty())

            return true;
        else
            return false;
    }

    public boolean skillValidation() {
        if (!list.isEmpty())
            return true;

        else {
            return false;
        }
    }

    public void setFields(String skill, String level) {

        txtSkill.setText(skill);
        cboLevel.setSelectedItem(level);

    }

}
