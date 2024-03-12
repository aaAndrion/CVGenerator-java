import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CVG extends JFrame implements ActionListener, WindowListener {
    private int currentCard = 1;

    String userHomeDir = System.getProperty("user.home");

    private JButton btnPrevious, btnNext, btnSave;
    private JPanel cPanel, btnPanel;

    private CardLayout card;

    Experience experience;

    Education education;
    Personal personal;
    Skills skills;
    Summary summary;

    CV cv;

    boolean dtlsPersonal, dtlsExp, dtlsEdu, dtlsSkill, dtlsSumm;

    public CVG() throws IOException {

        // Holds the the thing yehe
        cPanel = new JPanel();

        // Holds the Card containers
        card = new CardLayout();

        // Sets the Layout container of the panel to card
        cPanel.setLayout(card);

        // initialize classes
        personal = new Personal();
        experience = new Experience();
        education = new Education();
        skills = new Skills();
        summary = new Summary();

        cv = new CV();

        // Card containers
        cPanel.add(personal, "1");
        cPanel.add(experience, "2");
        cPanel.add(education, "3");
        cPanel.add(skills, "4");
        cPanel.add(summary, "5");

        // panel that holds buttons
        btnPanel = new JPanel();

        btnPrevious = new JButton("Previous");
        btnNext = new JButton("Next");
        btnSave = new JButton("save");

        btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add buttons to btnPanel
        btnPanel.add(btnPrevious).setEnabled(false);
        ;
        btnPanel.add(btnNext);
        btnPanel.add(btnSave).setEnabled(false);

        add(cPanel);
        add(btnPanel, BorderLayout.SOUTH);

        addWindowListener(this);

        btnPrevious.addActionListener(this);
        btnNext.addActionListener(this);
        btnSave.addActionListener(this);

        setTitle("CV Generator");
        setSize(350, 250);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException { new Menu(); }

    // @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrevious) {
            if (currentCard > 1) {
                currentCard--;
                card.show(cPanel, "" + currentCard);

            }
            if (currentCard == 1) {

                setSize(350, 250);
                setLocationRelativeTo(null);

                btnPrevious.setEnabled(false);
            }
            if (currentCard == 2 || currentCard == 3) {
                setSize(500, 500);

                setLocationRelativeTo(null);

            }
            if (currentCard == 4) {
                setSize(500, 250);
                setLocationRelativeTo(null);

            }

            if (currentCard != 5)
                btnNext.setEnabled(true);
            btnSave.setEnabled(false);
        }

        if (e.getSource() == btnNext) {
            if (currentCard < 5) {
                currentCard++;
                card.show(cPanel, "" + currentCard);
            }
            if (currentCard != 1) {
                setSize(500, 500);
                setLocationRelativeTo(null);

                btnPrevious.setEnabled(true);
            }
            if (currentCard == 4) {
                setSize(500, 250);
                setLocationRelativeTo(null);

            }

            if (currentCard == 2) {
                if (personal.pValidation()) {
                    dtlsPersonal = true;
                }
            }

            if (currentCard == 5) {
                btnNext.setEnabled(false);
                btnSave.setEnabled(true);
            }

        }
        if (e.getSource() == btnSave) {
            try {
                if (personal.pValidation()) {

                    cv.personal(personal.getNames(), personal.getEmail(), personal.getPhoneNum());
                    dtlsPersonal = true;

                }
                if (experience.getChkbox()) {
                    cv.expTitle();
                    if (experience.wrkValidation()) {
                        for (ExpDetails expDetails : experience.getDetails()) {

                            cv.experience(expDetails.getJobTitle(), expDetails.getCityTown(), expDetails.getEmployer(),
                                    expDetails.getStartMoYe(), expDetails.getEndMoye(), expDetails.getDesc());

                            cv.setExpY(80);

                            cv.setEduY(100);
                            cv.setEduTitle(100);

                            cv.setSkillY(100);
                            cv.setSkillTitle(100);

                        }
                        dtlsExp = true;

                    }

                }
                else {
                    dtlsExp = true;
                }

                if (education.edukValidation()) {
                    for (EduDetails eduDetails : education.getDetails()) {

                        cv.education(eduDetails.getDegree(), eduDetails.getCityTown(), eduDetails.getSchool(),
                                eduDetails.getStartMoYe(), eduDetails.getEndMoye(), eduDetails.getDesc());

                        cv.setEduY(80);

                        cv.setSkillY(100);
                        cv.setSkillTitle(100);
                    }
                    dtlsEdu = true;

                }
                if (skills.skillValidation()) {
                    for (SkillDetails skillDetails : skills.getDetails()) {
                        cv.skill(skillDetails.getSkill(), skillDetails.getLevel());

                        cv.setSkillY(50);
                    }
                    dtlsSkill = true;

                }

                if (!summary.getSummaryDesc().isEmpty()) {
                    for (String text : summary.getSummaryDesc()) {
                        cv.summary(text);
                        cv.setSummY(15);
                    }
                    dtlsSumm = true;

                }

                if (dtlsPersonal && dtlsExp && dtlsEdu && dtlsSumm) {
                    int filecount = 0;

                    cv.eduTitle();
                    cv.skillTitle();
                    File folder = new File(userHomeDir + "\\Desktop\\CVs\\");
                    File[] listOfFiles = folder.listFiles();
                    for (File file : listOfFiles) {
                        int temp = listOfFiles.length;
                        if (file.exists()) {
                            filecount++;
                            if (filecount == temp) {

                                String path = userHomeDir + "\\Desktop\\CVs\\CV" + filecount + ".pdf";

                                cv.setPath(path);
                            }
                        }
                    }
                    cv.save();
                    dispose();
                    new Menu().setVisible(true);

                }

            }
            catch (IOException e1) {

                e1.printStackTrace();
            }

        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            cv.close();
            new Menu().setVisible(true);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }
}
