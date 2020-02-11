
package game_0f_life;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MINCI
 */
public class game_0f_life extends Applet implements KeyListener {

    int r = 20;
    int len = 400;
    int grid[][] = new int[20][20];
    int grid2[][] = new int[20][20];

    public game_0f_life() {
        
        addKeyListener(this);
        
    }

    @Override
    public void init() {
        
        setSize(len, len);
        grid[8][8] = 1;
        grid[8][9] = 1;
        grid[9][9] = 1;
        grid[10][9] = 1;
        grid[9][7] = 1;

    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.lightGray);
        int p = 0;
        for (int i = 0; i <= r; i++) {
            g.drawLine(0, p, len, p);
            g.drawLine(p, 0, p, len);
            p += 20;
        }

        int c = 20;
        g.setColor(Color.black);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (grid[i][j] == 1) {
                    int x = i * c;
                    int y = j * c;
                    g.fillRoundRect(x, y, c, c, 1, 1);
                }
            }
        }

    }
    
    

    public void numofneighbour(int[][] gridd, int r) throws InterruptedException {
        int neighbour[][] = new int[20][20];
        for (int p = 0; p < r ; p++) {
            for (int o = 0; o < r; o++) {
                
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        aliveNeighbours += gridd[Math.floorMod((p+i), r)][Math.floorMod((o+j), r)];
                        //we've used floormod function because in java % operation just calculates remainder
                        
                    }
                }

                aliveNeighbours -= gridd[p][o];

                
                // Cell is lonely and dies 
                if ((gridd[p][o] == 1) && (aliveNeighbours < 2)) {
                    neighbour[p][o] = 0;
                } // Cell dies due to over population 
                else if ((gridd[p][o] == 1) && (aliveNeighbours > 3)) {
                    neighbour[p][o] = 0;
                } // A new cell is born 
                
                else if ((gridd[p][o] == 0) && (aliveNeighbours == 3)) {
                    neighbour[p][o] = 1;
                } // Remains the same 
                else {
                    neighbour[p][o] = gridd[p][o];
                }
                
            }

        }
        grid = neighbour;

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    
        
   }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    int key = ke.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
             
        try {
            numofneighbour(grid, r);
            repaint();
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(game_0f_life.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    
    
    }

}
