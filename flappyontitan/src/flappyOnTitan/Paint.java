package flappyOnTitan;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Paint extends JPanel {
    int x = 0;
    int y = -80;
    private ImageIcon bghome = new ImageIcon(this.getClass().getResource("background.jpg"));
    private ImageIcon actor = new ImageIcon(this.getClass().getResource("actor2.png"));
    private ImageIcon actor2 = new ImageIcon(this.getClass().getResource("actor3.png"));
    private ImageIcon start = new ImageIcon(this.getClass().getResource("start3.png"));
    public JButton btStart = new JButton(start);


    public Paint() {
        setLayout(null);
        add(btStart);
        btStart.setBounds(330,650,150,90);
        add(btStart);
    }

    public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(bghome.getImage(),0,0,1024,800,this);
            g.drawImage(actor.getImage(), 300, 200, 250, 400, this);
            g.drawImage(actor2.getImage(), 550, 400, 238, 256, this);
	}
}

