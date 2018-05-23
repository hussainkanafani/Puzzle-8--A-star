package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import model.PuzzleGame.action;

public class AStarPlayer extends Player{

	
	@Override
	public List<action> solve(PuzzleGame game) {			
		PriorityQueue<PuzzleGame> priorityQueue= new PriorityQueue<>();
		List<action> actions= new ArrayList<action>();
		Set<PuzzleGame> visited = new HashSet<>();
		visited.add(game);
		priorityQueue.add(game);
		PuzzleGame state= new PuzzleGame();    
		state=priorityQueue.poll();
		while(!state.isSolution(state.getGameBoard())){
        	action[] possibleActions= state.getPossibleActions(state.getGameBoard());
    		  for(action item:possibleActions){    			  
    			  PuzzleGame temp= new PuzzleGame();
    			  temp.setGameBoard(state.computeAction(item, state.getGameBoard()));
    			  temp.setTotalCost(state.getTotalCost()+state.getHeuristicValue(temp.getGameBoard()));    			  
    			  temp.getLog().addElement(item);
    			  temp.setParentNode(state);
    			  if(!visited.contains(temp)) {
    				  priorityQueue.add(temp);   		
    				  visited.add(temp);
    			  }
    		  }
                state=priorityQueue.poll();                
            }
		
		while(state.getParentNode()!=null) {
			actions.add(0, state.getLog().get(0));
			state=state.getParentNode();					
		}
		
		return actions;
	}

	
	
}
