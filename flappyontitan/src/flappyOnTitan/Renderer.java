package flappyOnTitan;

import java.awt.Graphics;
import java.io.Serial;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
	@Serial
	private static final long serialVersionUID = 1L;
        private ImageIcon bghome = new ImageIcon(this.getClass().getResource("background.jpg"));

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
                g.drawImage(bghome.getImage(),0,0,1024,800,this);
		FlappyOnTitan.flappyOnTitan.repaint(g);
	}
}