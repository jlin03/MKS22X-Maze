import java.util.*;
import java.io.*;

public class Maze {

  private char[][]maze;
  private boolean animate;

  public Maze(String filename) throws FileNotFoundException{
    animate = false;
    boolean hasE = false;
    boolean hasS = false;
    ArrayList<String> lines = new ArrayList<String>();
    File f = new File(filename);
    Scanner scan = new Scanner(f);
    while(scan.hasNextLine()) {
      lines.add(scan.nextLine());
    }
    for(int r = 0; r < lines.size();r++) {
      for(int c = 0; c < lines.get(r).length();c++) {
        maze[r][c] = lines.get(r).charAt(c);
        if(maze[r][c] == 'E' && !hasE) {
          hasE = true;
        }
        if(maze[r][c] == 'S' && !hasS) {
          hasS = true;
        }
        if((maze[r][c] == 'S' && hasS) || (maze[r][c] == 'E' && hasE)) {
          throw new IllegalStateException();
        }
      }
    }
  }

  public void setAnimate(boolean a) {
    animate = a;
  }

  public void clearTerminal(){
    System.out.println("\033[2J\033[1;1H");
  }

  







}
