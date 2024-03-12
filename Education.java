import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Education extends JPanel implements ActionListener {
    private JLabel lblEducation, lblDegree, lblCity, lblSchool, lblStartDate, lblEndDate, lblDescription;
    private JTextField txtDegree, txtCity, txtSchool;
    private JComboBox<String> cboStartMonth, cboEndMonth;
    private JComboBox<Integer> cboStartYear, cboEndYear;
    private JTextArea txtDescription;
    
    private JScrollPane scroll;
    private JButton btnAddUpdate, btnEdit, btnDelete;

    int value;

    List<EduDetails> list = new LinkedList<EduDetails>();

    private static final String FONTSTYLE = "Berlin Sans FB";

    public Education() {

        String[] months = {
                "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November",
                "December"
        };
        Integer[] years = new Integer[62];
        int sartYear = 1960;
        int endYear = 2022;

        for (int i = 0; i < 62; i++) {
            years[i] = sartYear;
            sartYear++;
            if (sartYear == endYear) {
                years[i] = endYear;
                break;
            }
        }

        lblEducation = new JLabel("Education");
        lblEducation.setFont(new Font("Consolas", Font.PLAIN, 20));
        lblEducation.setBounds(20, 15, 180, 20);

        lblDegree = new JLabel("Degree");
        lblDegree.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblDegree.setBounds(60, 35, 75, 20);

        lblCity = new JLabel("City/Town");
        lblCity.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblCity.setBounds(41, 65, 75, 20);

        lblSchool = new JLabel("School");
        lblSchool.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblSchool.setBounds(65, 95, 75, 20);

        lblStartDate = new JLabel("Start Date");
        lblStartDate.setFont(new Font(FONTSTYLE, Font.PLAIN, 14));
        lblStartDate.setBounds(40, 128, 75, 20);

        lblEndDate = new JLabel("End Date");
        lblEndDate.setFont(new Font(FONTSTYLE, Font.PLAIN, 14));
        lblEndDate.setBounds(245, 128, 75, 20);

        lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font(FONTSTYLE, Font.PLAIN, 15));
        lblDescription.setBounds(40, 185, 75, 20);

        txtDegree = new JTextField();
        txtDegree.setBounds(120, 38, 150, 20);

        txtCity = new JTextField();
        txtCity.setBounds(120, 68, 150, 20);

        txtSchool = new JTextField();
        txtSchool.setBounds(120, 98, 150, 20);

        cboStartMonth = new JComboBox<>(months);
        cboStartMonth.setBounds(40, 153, 95, 20);

        cboStartYear = new JComboBox<>(years);
        cboStartYear.setBounds(140, 153, 95, 20);

        cboEndMonth = new JComboBox<>(months);
        cboEndMonth.setBounds(245, 153, 95, 20);

        cboEndYear = new JComboBox<>(years);
        cboEndYear.setBounds(345, 153, 95, 20);
        txtDescription = new JTextArea("", 0, 0);
        
        txtDescription.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      

        scroll = new JScrollPane(txtDescription);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(40, 230, 400, 100);

        
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
        
        add(lblEducation);

        add(lblDegree);
        add(lblCity);
        add(lblSchool);
        add(lblStartDate);
        add(lblEndDate);
        add(lblDescription);

        add(txtDegree);
        add(txtCity);
        add(txtSchool);

        add(cboStartMonth);
        add(cboStartYear);
        add(cboEndMonth);
        add(cboEndYear);

        add(scroll);
        setLayout(null);

    }
    public List<EduDetails> getDetails() { return list; }

    public static String capitalizeFully(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Arrays.stream(str.split("\\s+")).map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public String getJobTitle() { return capitalizeFully(txtDegree.getText()); }

    public String getCityTown() { return capitalizeFully(txtCity.getText()); }

    public String getEmployer() { return capitalizeFully(txtSchool.getText()); }

    public String getStartMonth() { return (String) cboStartMonth.getSelectedItem(); }

    public String getStartYear() { return (Integer) cboStartYear.getSelectedItem() + ""; }

    public String getEndMonth() { return (String) cboEndMonth.getSelectedItem(); }

    public String getEndYear() { return (Integer) cboEndYear.getSelectedItem() + ""; }

    public List<String> getDesc() {
        List<String> lines = new LinkedList<>();
        for (String line : txtDescription.getText().split("\\n"))
            lines.add(line);
        return lines;
    }

    

    public boolean nextPressed(boolean bool){
        return bool;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAddUpdate)) {
            if (btnAddUpdate.getText().equalsIgnoreCase("Update")) {
                int i = 0;
                System.out.println("Updated!");
                btnAddUpdate.setText("Add");
                for (EduDetails eduDetails : list) {
                    i = list.indexOf(eduDetails);
                    if (i == value) {
                        System.out.println("should return list:" + i);

                        list.set(i,
                                new EduDetails(this.getJobTitle(), this.getCityTown(), this.getEmployer(), this.getStartMonth(),
                                        this.getStartYear(), this.getEndMonth(), this.getEndYear(), this.getDesc()));
                    

                        clearFields();
                    }
                }
                btnDelete.setEnabled(false);

            }
            else {
                    if (isFieldEmpty()) {
                    System.out.println("added");
                    add();

                    btnEdit.setEnabled(true);

                    clearFields();
                }
            }

        }
        if (e.getSource().equals(btnEdit)) {
            DefaultListModel<String> t1 = new DefaultListModel<String>();

            JList<String> jl = new JList<String>(t1);

            String hold = "";
            try {
                for (EduDetails degree : list) {

                    t1.addElement(degree.getDegree());
                }

                int option = JOptionPane.showConfirmDialog(null, jl, "Education: Edit", 0);
                if (option == JOptionPane.YES_OPTION) {
                    btnDelete.setEnabled(true);
                    value = jl.getSelectedIndex();

                    for (EduDetails eduDetails : list) {
                        if (list.indexOf(eduDetails) == value) {
                            setFields(eduDetails.getDegree(), eduDetails.getCityTown(), eduDetails.getSchool(),
                                    eduDetails.getStartMonth(), eduDetails.getStartYear(), eduDetails.getEndMonth(),
                                    eduDetails.getEndYear(), hold);

                            for (String desc : eduDetails.getDesc()) {
                                hold += desc + "\n";
                                setFields(eduDetails.getDegree(), eduDetails.getCityTown(), eduDetails.getSchool(),
                                        eduDetails.getStartMonth(), eduDetails.getStartYear(), eduDetails.getEndMonth(),
                                        eduDetails.getEndYear(), hold);
                            }

                        }

                    }
                    if (btnAddUpdate.getText().equalsIgnoreCase("Add"))
                        btnAddUpdate.setText("Update");

                }

            }
            catch (NullPointerException nullP) {
                nullP.printStackTrace();
            }
        }

        if (e.getSource().equals(btnDelete)) {
            for (EduDetails eduDetails : list) {
                if (list.indexOf(eduDetails) == value) {
                    System.out.println("should return");
                    list.remove(eduDetails);

                }
            }
            clearFields();
            btnDelete.setEnabled(false);
            btnAddUpdate.setText("Add");

        }
    }


    public void add(){
        list.add(new EduDetails(this.getJobTitle(), this.getCityTown(), this.getEmployer(), this.getStartMonth(),
                        this.getStartYear(), this.getEndMonth(), this.getEndYear(), this.getDesc()));
    }


    public boolean isFieldEmpty() {
        if (!this.getJobTitle().isEmpty() && !this.getCityTown().isEmpty() && !this.getEmployer().isEmpty() && !txtDescription.getText().isEmpty())            
                 
            return true;
        else
            return false;
    }

    public boolean edukValidation() {
        if (!list.isEmpty())
            return true;

        else {
            return false;
        }
    }

    public void enable() {
        btnAddUpdate.setEnabled(true);
        // btnEdit.setEnabled(true);
        // btnDelete.setEnabled(true);
        txtDegree.setEnabled(true);
        txtCity.setEnabled(true);
        txtSchool.setEnabled(true);
        cboStartMonth.setEnabled(true);
        cboStartYear.setEnabled(true);
        cboEndMonth.setEnabled(true);
        cboEndYear.setEnabled(true);
        txtDescription.setEnabled(true);

    }

    public void disable() {
        btnAddUpdate.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        txtDegree.setEnabled(false);
        txtCity.setEnabled(false);
        txtSchool.setEnabled(false);
        cboStartMonth.setEnabled(false);
        cboStartYear.setEnabled(false);
        cboEndMonth.setEnabled(false);
        cboEndYear.setEnabled(false);
        txtDescription.setEnabled(false);

    }

    public void setFields(String job, String city, String employer, String startMOnth, String startYear, String endMonth,
            String endYear, String desc) {

        txtDegree.setText(job);
        txtCity.setText(city);
        txtSchool.setText(employer);
        cboStartMonth.setSelectedItem(startMOnth);
        cboStartYear.setSelectedItem(startYear);
        cboEndMonth.setSelectedItem(endMonth);
        cboEndYear.setSelectedItem(endYear);
        txtDescription.setText(desc);

    }

    public void clearFields() {
        txtDegree.setText("");
        txtCity.setText("");
        txtSchool.setText("");
        cboStartMonth.setSelectedIndex(0);
        cboStartYear.setSelectedIndex(0);
        cboEndMonth.setSelectedIndex(0);
        cboEndYear.setSelectedIndex(0);
        txtDescription.setText("");
    }

}
