import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Item 
{
	private int x;
	private int y;
	private int r;
	private int dx;
	Graphics g;
	Boolean cn=false;

	public Item(int x,int y)
	{
		r=10;
		dx=-5;
		this.x=x;
		this.y=y;
	}
	public int getR(){return r;}

	public int getX(){return x;}
	public void setX(int x){this.x=x;} 
	
	public	Boolean getCN(){return cn;}
	public void setCN(Boolean x){this.cn=x;}
	
	public int getY(){return y;}
	public void setY(int y){this.y=y;} 

	public void update(StartingPoint3 sp,Ball b)
	{
		x=x+dx;
		checkForCollision(b);
		Random ra=new Random();
		if(x<0+r)
		{
			x=sp.getWidth()+ra.nextInt(800);
			y=sp.getHeight()-100-ra.nextInt(600);
		}

	
	}
	public void checkForCollision(Ball b)
	{
		int bx=b.getX();
		int by=b.getY();
		int br=b.getR();
		int a=(bx-x)*(bx-x);
		int o=(by-y)*(by-y);
		int c=(br+r)*(br+r);
		if(a+o<c)
		{
			x=0;
			y=0;
			cn=true;	
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.ORANGE);
		g.fillOval(x-r,y-r,r*2,r*2);
	}
}
