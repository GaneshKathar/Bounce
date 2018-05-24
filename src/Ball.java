import java.applet.*;
import java.awt.*;
import java.awt.event.*; 

public class Ball
{
	private double g=15;
	private double el=0.65;
	private double dt=0.2;
	private double fl=0.95;
	private int x=0;
	private int y=425;
	private int r=20;
	private double dx=0;
	private double dy=0;
	private double gdy=-100;
	boolean gameover=false;

	public Ball(){}
	
	public Ball(int i,int j)
	{
		x=i;
		y=j;
	}
	
	public int getR(){return r;}

	public int getX(){return x;}
	public void setX(int x){this.x=x;} 
	
	public int getY(){return y;}
	public void setY(int y){this.y=y;} 
	
	public double getDx(){return dx;}
	public void setDx(double x){dx=x;}
	
	public double getDy(){return dy;}
	public void setDy(double y){dy=y;}
	
	public double getG(){return g;}
	public void setG(double x){g=x;}

	public double getGdy(){return gdy;}
	public void setGdy(double y){gdy=y;}

	public void update(StartingPoint3 sp)
	{
			if(x+dx> sp.getWidth()-r-1)
			{
				x=sp.getWidth()-r-1;
				dx=-dx;
			}
			else if(x+dx < 0+r)
			{
				x=0+r;
				dx=-dx;
			}
			else
			{
				x+=dx;
			}
			if (y== sp.getHeight()-r-1)
			{
				//dx*=fl;
				if(Math.abs(dx)<0.8)
					dx=0;
			}
			 
			if(y> sp.getHeight()-r-1)
			{
				y=sp.getHeight()-r-1;
				//dy=dy*el;
				dy=gdy;
			}
			
			else
			{
				dy=dy+g*dt;
				y+=dy*dt+0.5*g*dt*dt;
			}
			if(y>=sp.getHeight()-r-1)
			{	
				gameover=true;
				y=sp.getHeight()+50;
				
			}
			else
			{
				gameover=false;
			}
				
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x-r,y-r,r*2,r*2);
	}
	
	public void moveRight()
	{
		if(dx+1<20)
			dx=+3;
	}
	
	public void moveLeft()
	{
		if(dx-1>-20)
			dx=-3 ;
	}
	public boolean isGameOver()
	{
		return gameover;
	}
}