package flappyOnTitan;

import static flappyOnTitan.FlappyOnTitan.flappyOnTitan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class setbuttom extends JFrame implements ActionListener {
    Paint home = new Paint();
    
    public setbuttom(){
        this.setSize(828,1050);
	this.add(home);
        home.btStart.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== home.btStart){
            this.setLocationRelativeTo(null);
            this.dispose();
            flappyOnTitan = new FlappyOnTitan();
        }
    }
}


