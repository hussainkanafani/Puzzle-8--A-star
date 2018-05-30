package model;

import java.util.Arrays;

import model.PuzzleGame.action;

public class State implements Comparable<State> {

	private State parentNode;
	private Integer[][] gameBoard;
	private int totalCost;
	private action takenAction;

	public State() {
		setParentNode(null);
		gameBoard = new Integer[3][3];
		totalCost = 0;
	}

	public static int calculateNodeDepth(State state) {
		int depth = 0;
		while (state.getParentNode() != null) {
			depth++;
			state = state.getParentNode();
		}
		return depth;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Arrays.deepHashCode(this.getGameBoard());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final State other = (State) obj;
		if (!Arrays.deepEquals(this.getGameBoard(), other.getGameBoard())) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(State pg) {
		if (this.totalCost > pg.totalCost)
			return 1;
		else if (this.totalCost == pg.totalCost)
			return 0;
		return -1;
	}

	public Integer[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Integer[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public action getTakenAction() {
		return takenAction;
	}

	public void setTakenAction(action takenAction) {
		this.takenAction = takenAction;
	}

	public State getParentNode() {
		return parentNode;
	}

	public void setParentNode(State parentNode) {
		this.parentNode = parentNode;
	}

}
