import javax.swing.*;
import java.awt.event.*;

public class TDOMain extends JFrame implements MouseListener, MouseMotionListener, KeyListener{	
	
	int width = 600, height = 623;
	TDOPP p;
	
	public static void main(String args[]){
		new TDOMain();
	}
	
	public TDOMain(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle("RK 3D Laptop");
        
        p = new TDOPP(width);
        add(p);

        setResizable(true);
        setVisible(true);
        
        Action updateCursorAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	p.forever();
            }
        };
        
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        p.addKeyListener(this);

        new Timer(100, updateCursorAction).start();
	}

	public void mouseClicked(MouseEvent me) {
		//p.mouseDown(me.getX(), me.getY());
	}

	public void mouseEntered(MouseEvent me) {
		
	}

	public void mouseExited(MouseEvent me) {
		
	}

	public void mousePressed(MouseEvent me) {
		
	}

	public void mouseReleased(MouseEvent me) {
		
	}
	
	public void mouseDragged(MouseEvent me) {
		
	}

	public void mouseMoved(MouseEvent me) {
		//p.mouseMoved(me.getX(), me.getY());
	}

	public void keyPressed(KeyEvent ke) {
		p.keyDown(ke.getKeyCode());
	}

	public void keyReleased(KeyEvent ke) {
		p.keyUp(ke.getKeyCode())
;	}

	public void keyTyped(KeyEvent ke) {
		
	}
	

}
