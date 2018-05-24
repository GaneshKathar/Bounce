import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;

/*<applet code="StartingPoint3.java"
  height=800
  width=800>
  </Applet>
 */
public class StartingPoint3 extends Applet implements Runnable, KeyListener,MouseListener
{
	
	Image i;
	Graphics dg;
	Ball b1;
	Platform []p;
	Platform []q;
	int gap=200;
	Item it;
	Image img;
	URL url;
	int score=0;
	double cityX=0;
	double cityDX=3;
	Thread th;
	String str1;
	int flag=1;
	int flag1=1;
	boolean go=false;
	public void init() 
	{	
		
		addKeyListener(this);
		addMouseListener(this);
		try 
		
		{
			url=getDocumentBase();
		}
		catch(Exception e){}
		
		setSize(800,800);
		img=getImage(url,"image/img2.jpg");
			
	}	
	public void start()
	{
		
				
		
		p=new Platform[8];
		q=new Platform[4];
		b1=new Ball();
		it=new Item(350,450);
		
		score=0;
		Random r=new Random();
		int x,y,x1,y1;
		for(int i=0;i<p.length;i++)
		{
			x=gap*i;
			if(i<4)
				y=450;
			else
				y=getHeight()-40-r.nextInt(350);
			p[i]=new Platform(x,y);
		}
		
		
		
	th=new Thread(this);
		//th.start();
	}
	public void run()
	{
		while(true)
		{
			
			go=b1.isGameOver();
			if(cityX < (-9000 ))
				cityX=0;
			else
				cityX -=cityDX;
			b1.update(this);
			
			it.update(this,b1);
			if(it.getCN())
			{
				Random ra=new Random();
				it=new Item(getWidth()+ra.nextInt(800),getHeight()-100-ra.nextInt(600));
			}
			it.setCN(false);
			if(go==false)
				score=1+score;
			

			for (int i=0;i<p.length;i++) {
				p[i].update(this,b1);	
			}

			repaint();
			try
			{
				Thread.sleep(17);	
			}
			catch(Exception e){System.out.println("run1");}
		}

	}
	public void destroy(){}
	public void stop(){}
	public void update(Graphics g)
	{
		if(i==null)	
		{
			i= createImage(this.getSize().width,this.getSize().height);
			dg = i.getGraphics();
		}
		dg.setColor(getBackground());
		dg.fillRect(0,0,this.getSize().width,this.getSize().height);
		dg.setColor(getForeground());
		paint(dg);
		g.drawImage(i,0, 0,this);	

	}
	public void paint(Graphics g)
	{
				
				
				
				//g.setColor(new Color(15,77,147));
				//g.fillRect(0,0,getWidth(),getHeight());
				g.drawImage(img,(int)cityX,0,this);
				g.drawImage(img,(int)cityX+9000,0,this);
				b1.paint(g);
				it.paint(g);
				for (int i=0;i<p.length;i++) 
					p[i].paint(g);	
				
				
				Font f=new Font("Serif",Font.BOLD,32);
				g.setFont(f);
				g.setColor(Color.RED);
				String d=Integer.toString(score);
				g.drawString(d,getWidth()-150,50);			
				str1="START";
				if(flag==1)
				{	
					g.drawRect(350, 325, 110 ,25);
					g.drawString(str1, 350, 350);
				}
				if(go==true )
				{
					g.drawRect(300 ,325,200,30);
					g.drawString("GAME OVER",300 ,350 );
					g.drawRect(300 ,380,200,30);
					g.drawString("TRY AGAIN",310 ,405 );
					
				}
					
	}
	
	public void keyPressed(KeyEvent e)
	{
		
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT :
				b1.moveRight();
				break;
			case KeyEvent.VK_LEFT :
				b1.moveLeft();
				break;
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void setGap(int i)
	{
		gap=i;

	}
	public int getGap()
	{
		return gap;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x=arg0.getX();
		int y=arg0.getY();
		if(x>=350 && x<=460 && y>=325 && y<=350 && flag==1)
		{
		
			th.start();
			flag=0;
		}
		if(x>=300 && x<=500 && y>=380 && y<=410)
		{
		
			go=false;
			start();
			score=0;
			
			
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}