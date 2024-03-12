import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.List;

public class Summary extends JPanel implements KeyListener {
    private JLabel lblSummaryTtl;
    private JTextArea txtSummaryDesc;
    private JScrollPane scroll;
 

    public Summary() {     
       
        lblSummaryTtl = new JLabel("Professional Summary");
        lblSummaryTtl.setFont(new Font("Consolas", Font.PLAIN, 20));
        lblSummaryTtl.setBounds(20, 15, 230, 20);

        txtSummaryDesc = new JTextArea("",0,0);
        // txtSummaryDesc.setBounds(40, 70, 400, 100);        
        txtSummaryDesc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // txtSummaryDesc.setLineWrap(true);
        // txtSummaryDesc.setWrapStyleWord(true);   
       
        scroll = new JScrollPane(txtSummaryDesc);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(40, 70, 400, 100);        

        txtSummaryDesc.addKeyListener(null);
        add(lblSummaryTtl);       
        add(scroll);

        setLayout(null);
    }

    public List<String> getSummaryDesc(){ 
            List<String> lines = new ArrayList<>();
        for(String line : txtSummaryDesc.getText().split("\\n"))
            lines.add(line);
        return lines;
    }

   

    @Override
    public void keyTyped(KeyEvent e) { // TODO Auto-generated method stub

     }

    @Override
    public void keyPressed(KeyEvent e) { // TODO Auto-generated method stub
     }

    @Override
    public void keyReleased(KeyEvent e) { // TODO Auto-generated method stub
     }

}
