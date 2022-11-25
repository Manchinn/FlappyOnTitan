package flappyOnTitan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class FlappyOnTitan extends JPanel implements ActionListener,KeyListener
{

    public static FlappyOnTitan flappyOnTitan;
    public final int WIDTH = 828, HEIGHT = 1050;
    public Renderer renderer;
    public Rectangle Levi;
    public ArrayList<Rectangle> columns;
    public int ticks,score;
    public int mt;
    public boolean gameOver, started;
    public Random rand;



    FlappyOnTitan()
    {
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);
		renderer = new Renderer();
		rand = new Random();
		jframe.add(renderer);
		jframe.setTitle("Flappy On Titan");
		jframe.setDefaultCloseOperation
		(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);
   
		Levi = new Rectangle(WIDTH / 2 - 10, 
		HEIGHT / 2 - 10, 20, 20);
 
		columns = new ArrayList<>();
			addColumn(true);
			addColumn(true);
			addColumn(true);
			timer.start();
	}

    

	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 90 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 900, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.pink.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public void jump(){
		if (gameOver){
			Levi = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			mt = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}
		if (!started){
			started = true;
		}
		else if (!gameOver){
			if (mt > 0){
				mt = 0;
			}
			mt -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		int speed = 10;
		ticks++;
		if (started){
			for (int i = 0; i < columns.size(); i++){
                Rectangle column = columns.get(i);
				column.x -= speed;
			}
		if (ticks % 2 == 0 && mt < 15){
			mt += 2;
		}
		for (int i = 0; i < columns.size(); i++){
			Rectangle column = columns.get(i);
			if (column.x + column.width < 0){
			columns.remove(column);
				if (column.y == 0){
					addColumn(false);
				}
			}
		}
		Levi.y += mt;

		for (Rectangle column : columns){
			if(column.y == 0 && Levi.x + Levi.width / 5  > column.x + column.width / 2 - 10 && Levi.x + Levi.width / 2 < column.x + column.width / 2 + 10){
				score++;
			}
			if (column.intersects(Levi)){
				gameOver = true;
				if (Levi.x <= column.x){
					Levi.x = column.x - Levi.width;
				}
				else{
					if (column.y != 0){
						Levi.y = column.y - Levi.height;
					}
					else if (Levi.y < column.height){
						Levi.y = column.height;
					}
                }
            }
        }
		if (Levi.y > HEIGHT - 120 || Levi.y < 0){
			gameOver = true;
		}
		if (Levi.y + mt >= HEIGHT - 120){
            Levi.y = HEIGHT - 120 - Levi.height;
            gameOver = true;
		}
        }       
            renderer.repaint();
	}
        
	public void repaint(Graphics g)
        {
            g.setColor(Color.black.darker());
            g.fillRect(Levi.x, Levi.y, Levi.width, Levi.height);

            for(Rectangle column : columns)
            {
               paintColumn(g,column);
            }
            g.setColor(Color.white);
            g.setFont(new Font("Ariel", 1, 100));
            if (!started)
            {
                g.drawString("Press to start!", 75, HEIGHT / 2 - 50);
            }
            if (gameOver)
            {
                g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
            }
            if (!gameOver && started)
            {
                g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
            }
	}

	public static void main(String[] args)
	{
		JFrame frame = new setbuttom();
		frame.setSize(828,1050);
		frame.setTitle("Flappy On Titan");
		frame.setDefaultCloseOperation
		(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                jump();
            }
	}
}