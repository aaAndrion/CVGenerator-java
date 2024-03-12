import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Color;

public class CV {
    int filecount = 0;

    String userHomeDir = System.getProperty("user.home");
    String path = userHomeDir + "\\Desktop\\CVs\\CV" + filecount + ".pdf";

    PDDocument cvDoc = new PDDocument();
    PDPage firstpage = new PDPage();
    PDPageContentStream contentStream;
    PDImageXObject pdPerson, pdTele, pdMail;

    File fileSerifBold;
    File fileSerifSemiBold;

    PDFont serifBold;
    PDFont serifSemiBold;

    Experience experience;
    Education education;
    Personal personal;
    Skills skills;
    Summary summary;

    // int expX = 170;
    int expTitle = 165;
    int expY = 180;

    int eduTitle = 165;
    int eduY = 180;

    int skillTitle = 165;
    int skillY = 180;

    // int summX = 170;
    int summY = 45;

    String eduDesc;

    public CV() throws IOException {
        cvDoc.addPage(firstpage);

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.OVERWRITE, true, true);
        contentStream.setNonStrokingColor(new Color(63, 101, 146));
        contentStream.addRect(0, firstpage.getTrimBox().getHeight(), 150, -1000);
        contentStream.fill();
        contentStream.close();

        icons();

    }

    public void setDescEdu(String str) { this.eduDesc = str; }

    public void setPath(String path) { this.path = path; }

    public void setSummY(int y) { this.summY += y; }

    // public void setExpX(int x) { this.expX *= x; }

    public void setExpY(int y) { this.expY += y; }

    public void setEduY(int y) { this.eduY += y; }

    public void setEduTitle(int y) { this.eduTitle += y; }

    public void setSkillY(int y) { this.skillY += y; }

    public void setSkillTitle(int y) { this.skillTitle += y; }

    public void close() throws IOException { cvDoc.close(); }

    public void expTitle() throws IOException {
        load();

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(serifBold, 12);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(170, firstpage.getTrimBox().getHeight() - 165);
        contentStream.showText("WORK EXPERIENCE");
        contentStream.newLine();
        contentStream.endText();
        contentStream.close();
    }

    public void eduTitle() throws IOException {
        load();

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(serifBold, 12);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(170, firstpage.getTrimBox().getHeight() - eduTitle);
        contentStream.showText("Education and Qualifications");
        contentStream.newLine();
        contentStream.endText();
        contentStream.close();
    }

    public void skillTitle() throws IOException {
        load();

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(serifBold, 12);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(170, firstpage.getTrimBox().getHeight() - skillTitle);
        contentStream.showText("Skills");
        contentStream.newLine();
        contentStream.endText();
        contentStream.close();
    }

    public void stream() throws IOException {
        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);
    }

    public void save() throws IOException {
        cvDoc.save(path);

        Logger logger = Logger.getLogger(CV.class.getName());
        logger.log(Level.INFO, "CV has been Created");

    }

    private void load() throws IOException {
        fileSerifBold = new File(userHomeDir + "\\Documents\\ShynLabCVG\\Fonts\\OpenSans-Bold.ttf");
        fileSerifSemiBold = new File(userHomeDir + "\\Documents\\ShynLabCVG\\Fonts\\OpenSans-Semibold.ttf");

        serifBold = PDType0Font.load(cvDoc, fileSerifBold);
        serifSemiBold = PDType0Font.load(cvDoc, fileSerifSemiBold);
    }

    public void icons() throws IOException {

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        pdPerson = PDImageXObject.createFromFile(userHomeDir + "\\Documents\\ShynLabCVG\\icons\\user.png", cvDoc);
        contentStream.drawImage(pdPerson, 14, 745, 10, 10);

        pdMail = PDImageXObject.createFromFile(userHomeDir + "\\Documents\\ShynLabCVG\\icons\\mail.png", cvDoc);
        contentStream.drawImage(pdMail, 14, 706, 10, 10);

        pdTele = PDImageXObject.createFromFile(userHomeDir + "\\Documents\\ShynLabCVG\\icons\\phone.png", cvDoc);
        contentStream.drawImage(pdTele, 14, 667, 10, 10);

        contentStream.close();
    }

    public void personal(String name, String email, String pNumber) throws IOException {
        personal = new Personal();

        load();

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.white);
        contentStream.setFont(serifBold, 14);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(15, firstpage.getTrimBox().getHeight() - 25);
        contentStream.showText("Personal");
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(16.0f);
        contentStream.setFont(serifBold, 10);
        contentStream.newLineAtOffset(30, firstpage.getTrimBox().getHeight() - 45);
        contentStream.showText("Name");
        contentStream.newLine();

        contentStream.setFont(serifSemiBold, 10);
        contentStream.showText(name);
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(serifBold, 10);
        contentStream.newLineAtOffset(30, firstpage.getTrimBox().getHeight() - 85);
        contentStream.showText("Email");
        contentStream.newLine();

        contentStream.setFont(serifSemiBold, 10);
        contentStream.showText(email);
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(serifBold, 10);
        contentStream.newLineAtOffset(30, firstpage.getTrimBox().getHeight() - 125);
        contentStream.showText("Phone NUmber");
        contentStream.newLine();
        contentStream.setFont(serifSemiBold, 10);
        contentStream.showText(pNumber);
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.setFont(serifBold, 18);
        contentStream.newLineAtOffset(170, firstpage.getTrimBox().getHeight() - 25);
        contentStream.showText(name);
        contentStream.endText();

        contentStream.close();

    }

    public void experience(String jobTitle, String city, String employer, String startMoYe, String endMoYe,
            List<String> description) throws IOException {
        load();
        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.gray);
        contentStream.setFont(serifSemiBold, 10);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(470, firstpage.getTrimBox().getHeight() - expY);
        contentStream.showText(startMoYe + " - " + endMoYe);
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.newLineAtOffset(175, firstpage.getTrimBox().getHeight() - expY);
        contentStream.setFont(serifBold, 10);
        contentStream.showText(jobTitle);
        contentStream.newLine();
        contentStream.setFont(serifSemiBold, 9);
        contentStream.showText(employer + ", " + city);
        contentStream.newLine();
        contentStream.setFont(serifSemiBold, 8);
        for (String txt : description) {

            contentStream.showText(txt.trim());
            contentStream.newLine();

        }

        contentStream.endText();

        contentStream.close();
    }

    public void education(String degree, String city, String school, String startMoye, String endMoye, List<String> description)
            throws IOException {
        load();
        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.gray);
        contentStream.setFont(serifSemiBold, 10);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(470, firstpage.getTrimBox().getHeight() - eduY);
        contentStream.showText(startMoye + " - " + endMoye);
        contentStream.newLine();
        contentStream.endText();

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.black);
        contentStream.newLineAtOffset(175, firstpage.getTrimBox().getHeight() - eduY);
        contentStream.setFont(serifBold, 10);
        contentStream.showText(degree);
        contentStream.newLine();
        contentStream.setFont(serifSemiBold, 9);
        contentStream.showText(school + ", " + city);
        contentStream.newLine();
        contentStream.setFont(serifSemiBold, 8);

        for (String txt : description) {

            contentStream.showText(txt.trim());
            contentStream.newLine();

        }

        contentStream.endText();

        contentStream.close();
    }

    public void skill(String skill, String skillLevel) throws IOException {
        load();
        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(serifBold, 10);
        contentStream.setLeading(16.0f);
        contentStream.newLineAtOffset(175, firstpage.getTrimBox().getHeight() - skillY);
        contentStream.showText(skill + "                     " + skillLevel);
        contentStream.newLine();

        contentStream.endText();

        contentStream.close();
    }

    public void summary(String summary) throws IOException {
        load();

        contentStream = new PDPageContentStream(cvDoc, firstpage, PDPageContentStream.AppendMode.APPEND, true, true);

        contentStream.beginText();
        contentStream.setFont(serifBold, 10);
        contentStream.setLeading(16.0f);

        contentStream.newLineAtOffset(170, firstpage.getTrimBox().getHeight() - summY);

        contentStream.showText(summary);
        contentStream.endText();

        contentStream.close();

    }

}
