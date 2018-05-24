import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Platform
{
	int dx;
	int x,y,w,h,t;
	public Platform()
	{
		dx=-5;
		x=300;
		y=300;
		w=120;
		h=40;
		t=x;

	}
	public Platform(int i,int j)
	{
		dx=-5;
		x=i;
		t=i;
		y=j;
		w=120;
		h=40;

	}
	public void update(StartingPoint3 sp,Ball b)
	{	
		x=x+dx;
		checkForCollision(b);
		if(x<0-w)
		{
			Random r= new Random();
			x=((sp.getGap()+w)*8)-300-sp.getWidth();//+r.nextInt(300);
			y=sp.getHeight()-40-r.nextInt(400);
		}
	}
	public void checkForCollision(Ball b)
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int radius=b.getR();
		if(ballY+radius > y && ballY+radius < y+h)
		{
			if(ballX > x && ballX < x+w)
			{
				Double n=b.getGdy();
				b.setY(y-radius);
				b.setDy(n);
			}
		}

	}
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(x,y,w,h);
	}
}