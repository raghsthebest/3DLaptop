import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

public class TDOPP extends JPanel{

	int l;
	
	double[][] p;
	
	int count=0;
	
	int shift=0;
	
	double turn=(Math.PI)/4/23;;
	int angle;
	
	BufferedImage[] bi = new BufferedImage[10];
	int[] imghi =  new int[10];
	
	public TDOPP(){
		init();
	}
	
	public TDOPP(int l){
		this.l = l;
		init();
	}
	
	public void init(){
		
		try{
			bi[0] = ImageIO.read(getClass().getResource("laptopbottom2.png"));
			bi[1] = ImageIO.read(getClass().getResource("roundedrectangle3.png"));
			bi[2] = ImageIO.read(getClass().getResource("laptopscreen.png"));
			bi[3] = ImageIO.read(getClass().getResource("laptopbottomkeys2.png"));
			
			imghi[0] = 300;
			imghi[1] = 300;
			imghi[2] = 256;
			imghi[3] = 300;			
		}catch(Exception e){
			e.printStackTrace();
		}
		p = new double[4][90000];
		
		drawLaptop();
		
		//check(count);
		count=0;
		repaint();
		
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, l, l);
		
		
		
		g.setColor(Color.black);
		
		int[] pintx = ConvertToPointsX(p[0]);
		int[] pinty = ConvertToPointsY(p[1]);
		
		g.setColor(Color.LIGHT_GRAY);
		
		/*g.drawLine(0,300,600,300);
		g.drawLine(300,0,300,600);
		g.drawRect(20, 20, 560, 560);
		*/
		
		Font f = new Font("Calibri", 0, 11);
		g.setFont(f);;
		g.drawString("Press the Up and Down arrow keys to rotate around the x-axis", 5, 15);
		g.drawString("Press the Left and Right arrow keys to rotate around the y-axis", 5, 25);
		g.drawString("Hold Shift while pressing the Left and Right arrow keys to rotate around the z-axis", 5, 35);
		
		g.drawString("Raghav Kansal", 525,595);
		
		g.setColor(Color.red);
		g.setColor(Color.black);
		
		for(int i=0;i<p[0].length;i++){
		//for(int i=0;i<9148;i++){
			g.setColor(new Color((int) p[3][i]));
			g.fillRect(pintx[i], pinty[i], 1, 1);
		}
		
		//check(count);
		//count++;
		
	}
	
	public void drawLaptop(){
		for(int i=0;i<bi[0].getHeight();i++){
			for(int j=0;j<bi[0].getWidth();j++){
				if(bi[0].getRGB(i, j)!=-1){
					p[0][count] = i-l/2;
					p[1][count] = -imghi[0]/2+10-20;
					p[2][count] = -((l-j)-l/2)-l/2+imghi[0]/2;
					p[3][count] = Color.black.getRGB();
					count++;
					for(int k=1;k<19;k++){
						if(bi[0].getRGB(i, j)==Color.red.getRGB()){
							p[0][count] = i-l/2;
							p[1][count] = -imghi[0]/2+10-k;
							p[2][count] = -((l-j)-l/2)-l/2+imghi[0]/2;
							p[3][count] = Color.black.getRGB();
							count++;
						}
					}
				}
			}
		}
		
		//check(count);
		
		for(int i=0;i<bi[3].getHeight();i++){
			for(int j=0;j<bi[3].getWidth();j++){
				if(bi[3].getRGB(i, j)!=-1){
					p[0][count] = i-l/2;
					p[1][count] = -imghi[3]/2+10;
					p[2][count] = -((l-j)-l/2)-l/2+imghi[3]/2;
					p[3][count] = Color.black.getRGB();
					count++;
				}
			}
		}
		
		//check(count);
		
		for(int i=0;i<bi[1].getHeight();i++){
			for(int j=0;j<bi[1].getWidth();j++){
				if(bi[1].getRGB(i, j)!=-1){
					for(int k=0;k<2;k++){
						p[0][count] = i-l/2;
						p[1][count] = l-j-l/2-10;
						p[2][count] = -l/2-k*5;
						if(bi[1].getRGB(i, j)==Color.red.getRGB()){
							p[3][count] = Color.black.getRGB();
						}else{
							p[3][count] = bi[1].getRGB(i, j);
						}
						Color ct = new Color(0,0,64);
						p[3][count] = ct.getRGB();
						count++;
					}
					for(int k=1;k<4;k++){
						if(bi[1].getRGB(i, j)==Color.red.getRGB()){
							p[0][count] = i-l/2;
							p[1][count] = l-j-l/2-20;
							p[2][count] = -l/2-k;
							p[3][count] = Color.black.getRGB();
							count++;
						}
					}
				}
			}
		}
		
		//check(count);
		
		for(int i=0;i<bi[2].getHeight();i++){
			for(int j=0;j<bi[2].getWidth();j++){
				if(bi[2].getRGB(i, j)!=-1){
					p[0][count] = i-l/2;
					p[1][count] = l-j-l/2-10;
					p[2][count] = -l/2;
					p[3][count] = Color.black.getRGB();
					count++;
				}
			}
		}
		
		//check(count);
		
	}

	
	public int[] ConvertToPointsX(double[] ds)
	{
		int[] is = new int[ds.length];
	    for(int i=0;i<ds.length;i++){
	    	is[i] = (int) (ds[i]*(((p[2][i]+l/4)/4+3*(l/2)/4)/(l/2))+l/2);
	    	//is[i] = (int) (ds[i]/(p[2][i]/512))+l/2;
	    }
	    return is;
	}
	
	public int[] ConvertToPointsY(double[] ds)
	{
		int[] is = new int[ds.length];
	    for(int i=0;i<ds.length;i++){
	    	is[i] = (int) (l*0.5 - ds[i]*(((p[2][i]+l/4)/4+3*(l/2)/4)/(l/2)));
	    	//is[i] = (int) (l/2 - ds[i]/(p[2][i]/512));
	    }
	    return is;
	}
	
	public void forever(){
		
	}
	
	public void check(double t){
		System.out.println(t);
	}
	
	public void check(String s){
		System.out.println(s);
	}
	
	public void keyDown(int k){
		if(k==37){
			if(shift==1){
				for(int i=0;i<p[0].length;i++){
					double temp = p[0][i];
					p[0][i] = p[0][i]*Math.cos(turn) - p[1][i]*Math.sin(turn);
					p[1][i] = temp*Math.sin(turn) + p[1][i]*Math.cos(turn);
				}
				repaint();
			}else{
				for(int i=0;i<p[0].length;i++){
					double temp = p[0][i];
					p[0][i] = p[0][i]*Math.cos(-turn) + p[2][i]*Math.sin(-turn);
					p[2][i] = -temp*Math.sin(-turn) + p[2][i]*Math.cos(-turn);
				}
				repaint();
			}
		}
		if(k==39){
			if(shift==1){
				for(int i=0;i<p[0].length;i++){
					double temp = p[0][i];
					p[0][i] = p[0][i]*Math.cos(-turn) - p[1][i]*Math.sin(-turn);
					p[1][i] = temp*Math.sin(-turn) + p[1][i]*Math.cos(-turn);
				}
				repaint();
			}else{
				for(int i=0;i<p[0].length;i++){
					double temp = p[0][i];
					p[0][i] = p[0][i]*Math.cos(turn) + p[2][i]*Math.sin(turn);
					p[2][i] = -temp*Math.sin(turn) + p[2][i]*Math.cos(turn);
				}
				repaint();
			}
		}
		
		if(k==38){
			for(int i=0;i<p[0].length;i++){
				double temp = p[1][i];
				p[1][i] = p[1][i]*Math.cos(-turn) - p[2][i]*Math.sin(-turn);
				p[2][i] = temp*Math.sin(-turn) + p[2][i]*Math.cos(-turn);
			}
			repaint();
		}
		if(k==40){
			for(int i=0;i<p[0].length;i++){
				double temp = p[1][i];
				p[1][i] = p[1][i]*Math.cos(turn) - p[2][i]*Math.sin(turn);
				p[2][i] = temp*Math.sin(turn) + p[2][i]*Math.cos(turn);
			}
			repaint();
		}
		if(k==16){
			shift=1;
		}
		//check(k);
	}
	
	public void keyUp(int k){
		if(k==16){
			shift=0;
		}
	}
	
	
}
