package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import model.PuzzleGame.action;

public class AStarPlayer extends Player {

	/**
	 * class AStarPlayer implements A* Algorithm to solve the Puzzle-8 problem
	 * 
	 * g(n) is the depth of the state in the solution tree
	 * h(n) is the heuristic function written in PuzzleGame class
	 * totalCost of a state n equals to g(n)+ h(n)
	 * */
	
	@Override
	public List<action> solve(PuzzleGame game) {
		State state = new State();
		state.setGameBoard(game.getGameBoard());
		PriorityQueue<State> priorityQueue = new PriorityQueue<>();
		List<action> actions = new ArrayList<action>();
		Set<State> visited = new HashSet<>();
		visited.add(state);
		priorityQueue.add(state);
		state = priorityQueue.poll();
		while (!game.isSolution(state.getGameBoard())) {
			state.setTotalCost(State.calculateNodeDepth(state));
			// get the possible actions in this step
			action[] possibleActions = game.getPossibleActions(state.getGameBoard());
			for (action item : possibleActions) {
				State nextPossibleState = new State();
				nextPossibleState.setGameBoard(game.computeAction(item, state.getGameBoard()));
				nextPossibleState.setTotalCost(state.getTotalCost() + game.getHeuristicValue(nextPossibleState.getGameBoard()));
				nextPossibleState.setTakenAction(item);
				nextPossibleState.setParentNode(state);
				if (!visited.contains(nextPossibleState)) {
					priorityQueue.add(nextPossibleState);
					visited.add(nextPossibleState);
				}
			}
			state = priorityQueue.poll();
		}

		while (state.getParentNode() != null) {
			actions.add(0, state.getTakenAction());
			state = state.getParentNode();
		}
		System.out.println("number of total actions: " + actions.size());
		System.out.println("performed actions: " + actions);

		return actions;
	}

	

}
