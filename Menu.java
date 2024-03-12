import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;

public class Menu extends JFrame implements ActionListener{
    private JComboBox<String> cbochoice;
    private JButton btnOk;
    String userHomeDir = System.getProperty("user.home");

    File folder = new File(userHomeDir + "\\Desktop\\CVs\\");
    File[] listOfFiles = folder.listFiles();

    String choice[] = {
            "Show History", "Create Resume", "Exit"
    };

    public Menu(){

        cbochoice = new JComboBox<String>(choice);
        cbochoice.setBounds(70, 50, 130, 20);

        btnOk = new JButton("OK");
        btnOk.setBounds(95, 100, 70, 20);

        btnOk.addActionListener(this);

        add(cbochoice);
        add(btnOk);


        setTitle("CV Menu");
        
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    


    @Override
    public void actionPerformed(ActionEvent e) { // TODO Auto-generated method stub
        int i = cbochoice.getSelectedIndex();

        if(i == 0){
            showHistory();
        }

        if(i == 1){
            try {
                new CVG();                
                setVisible(false);
                if (folder.mkdir()) {
                    System.out.println("Folder does not exist. Creating a new one");
                }
                else {
                    System.out.println("Folder Alreaedy exist!");
                }
            }
            catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if(i == 2){
            System.exit(0);
        }
     }

     private void showHistory() {
        List<FileDetails> list = new LinkedList<FileDetails>();

        boolean isOpen = false;

       

        int value = 0;

        DefaultListModel<String> t1 = new DefaultListModel<String>();
        JList<String> jl = new JList<String>(t1);

      

     

        for (File file : listOfFiles) {

            if (file.isFile()) {
                list.add(new FileDetails(file.getName(), file.getAbsolutePath()));
            }

        }

        for (FileDetails cv : list) {
            t1.addElement(cv.getFile());

        }
        int option = JOptionPane.showConfirmDialog(null, jl, "Show History", 0);
            if (option == JOptionPane.YES_OPTION) {
                value = jl.getSelectedIndex();
                for (FileDetails cv : list) {
                    if (list.indexOf(cv) == value) {
                        if (Desktop.isDesktopSupported()) {
                            File myFile = new File(cv.getPath());
                            try {
                                Desktop.getDesktop().open(myFile);
                                
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                           
       
    }
}
                }
            }
        }
    }

