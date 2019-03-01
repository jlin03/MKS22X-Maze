import java.util.*;
import java.io.*;

public class Maze {

  private char[][] maze;
  private boolean animate;
  private int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};
  private int m;

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
    maze = new char[lines.size()][lines.get(0).length()];
    for(int r = 0; r < lines.size();r++) {
      for(int c = 0; c < lines.get(r).length();c++) {
        maze[r][c] = lines.get(r).charAt(c);
        if((maze[r][c] == 'S' && hasS) || (maze[r][c] == 'E' && hasE)) {
          throw new IllegalStateException();
        }
        if(maze[r][c] == 'E' && !hasE) {
          hasE = true;
        }
        if(maze[r][c] == 'S' && !hasS) {
          hasS = true;
        }
      }
    }
  }

  public void setAnimate(boolean a) {
    animate = a;
  }

  private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

  public void clearTerminal(){
    System.out.println("\033[2J\033[1;1H");
  }

  public int solve() {
    int[][] pos = new int[1][2];
    for(int r = 0; r < maze.length;r++) {
      for(int c = 0; c < maze[r].length;c++) {
        if(maze[r][c] == 'S') {
          pos[0][0] = r;
          pos[0][1] = c;
        }
      }
    }
    m = -1;
    solveH(pos[0][0],pos[0][1],1);
    return m;
  }

  private boolean solveH(int r, int c, int move){
    if(maze[r][c] == 'E') {
      m = move;
      return true;
    }
    if(maze[r][c] != '#' && maze[r][c] != '.' && maze[r][c] != '@') {
      maze[r][c] = '@';
      for(int i = 0; i < 4; i++) {
        if(solveH(r+moves[i][0],c+moves[i][1],move+1)) {
          return true;
        }
      }
      maze[r][c] = '.';
    }

    if(animate){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }
    return false;
  }


  public String toString(){
    String out = "";
    for(int r = 0; r < maze.length;r++) {
      for(int c = 0; c < maze[r].length;c++) {
        out += maze[r][c];
      }
      if(r < maze.length - 1) {
        out += "\n";
      }
    }
    return out;
  }






}
