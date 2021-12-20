package maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	
    	//current cell falls outside of grid
    	if (x < 0 || x > maze.getNCols()-1 || y < 0 || y > maze.getNRows()-1) {
    		return false;
    	}
    	//cell doesn't have NON_BACKGROUND
    	if (maze.getColor(x,y)!=NON_BACKGROUND) {
    		return false;
    	}
    	//exit cell = (getColumn()-1,getRow()-1)
    	//current cell is exit cell
    	if (x==(maze.getNCols()-1) && y==(maze.getNRows()-1)) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	//current cell is valid
    	maze.recolor(x,y, PATH);
    	boolean path1 = findMazePath(x+1, y); //go right one
    	boolean path2 = findMazePath(x, y+1); //go down one
    	boolean path3 = findMazePath(x, y-1); //go left one
    	boolean path4 = findMazePath(x-1, y); //go up one
    	
    	//checking for valid paths
    	if (path1 == true) {
    		return true;
    	} else if (path2 == true) {
    		return true;
    	} else if (path3 == true) {
    		return true;
    	} else if (path4 == true) {
    		return true;
    	} else {
    		maze.recolor(x, y, TEMPORARY);
    		return false;
    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    /**
     * modifies findMazePath to return all solutions to the maze in an ArrayList of cells
     * if more than one path to exit, or no path to exit, all cells should stay NON_BACKGROUND
     * @param x The x-coordinate of the current point
     * @param y The y-coordinate of the current point
     * @return list of all the solutions to the maze
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0,result, trace);
    	return result;
    }
    
    /**
     * helper function for findAllMazePaths
     * change current cell color to PATH and push current cell onto trace 
     * when exit cell reached, all contents of trace are placed in result
     * if non-valid path found, backtrack while popping trace and restoring color of current cell
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @param result list of successful paths recorded up to now
     * @param trace trace of current path being explored
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	//current cell falls outside of the grid
    	if (x < 0 || x > maze.getNCols()-1 || y < 0 || y > maze.getNRows()-1) {
    		return;
    	}
    	//current cell doesn't have NON_BACKGROUND
    	if (maze.getColor(x,y)!=NON_BACKGROUND) {
    		return;
    	}
    	//exit cell = (getColumn()-1,getRow()-1)
    	//current cell is exit cell
    	if (x==(maze.getNCols()-1) && y==(maze.getNRows()-1)) {
    		trace.push(new PairInt(x,y));
    		ArrayList<PairInt> temp = new ArrayList<>();
    		temp.addAll(trace);
    		result.add(temp);
    		trace.pop();
    		return;
    	}
    	//current cell is valid
    	maze.recolor(x,y, PATH);
    	trace.push(new PairInt(x,y));
    	findMazePathStackBased(x+1,y,result,trace); //go right one
    	findMazePathStackBased(x,y+1,result,trace); //go down one
    	findMazePathStackBased(x,y-1,result,trace); //go left one
    	findMazePathStackBased(x-1,y,result,trace); //go up one
    	
    	//backtracking, restoring explored path, and popping trace
    	if (maze.getColor(x, y)==PATH) {
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    	}
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    /**
     * returns shortest path in the list of paths
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return returns the shortest path as an array of PairInts
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<PairInt> result = new ArrayList<>();
    	ArrayList<ArrayList<PairInt>> paths = findAllMazePaths(0,0);
    	
    	//check to see if there is a valid path
    	if (findMazePath()==true) {
    		//if there is a valid path, find shortest path
    		int lengthMin = Integer.MAX_VALUE;
	    	ArrayList<PairInt> currentPath = new ArrayList<>();
	    	
	    	//go through all paths calculating length
	    	for (int i=0; i<paths.size(); i++) {
	    		currentPath = paths.get(i);
	    		int currentPathLength = 0;
	    		for (int j=0; j<currentPath.size(); j++) {
	    			currentPathLength++;
	    		}
	    		//if new smallest path change value of lengthMin and path that is the result
	    		if (currentPathLength<lengthMin) {
	    			lengthMin = currentPathLength;
	    			result = currentPath;
	    		}
	    	}
    	}
    	//set maze back to state before findMazePath() was run
    	maze.recolor(PATH, NON_BACKGROUND);
		maze.recolor(TEMPORARY, NON_BACKGROUND);
    	return result;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
