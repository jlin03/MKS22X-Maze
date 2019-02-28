import java.util.*;
import java.io.*;

public class Maze {

  private char[][]maze;
  private boolean animate;
  private int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};

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



  private int solveH(int r, int c, int move){
    if(maze[r][c] == 'E') {
      return move;
    }
    if(maze[r][c] != '#' && maze[r][c] != '.' && maze[r][c] != '@') {
      maze[r][c] == '@';
      for(int i = 0; i < 4; i++) {
        solveH(r+moves[i][0],c+moves[i][1],move+1);
      }
      maze[r][c] == '.';
    }

    if(animate){
        clearTerminal();
        System.out.println(this);
        wait(20);
    }

    return -1;
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
