package utils;

import java.util.ArrayList;

import buildings.Edge;
import logic.GamePlay;
import logic.Player;

public class getTotalOwners {
	public static int fromSideEdges(ArrayList<Edge> sideEdges) {
		int owners = 0;
		GamePlay instance = GamePlay.getInstance();
		Player currentPlayer = instance.getAllPlayers().get(instance.getCurrentPlayer());
		for (Edge edge : sideEdges) {
			if (edge.getOwner() != null && edge.getOwner().equals(currentPlayer) == false) {
				owners += 1;
			}
		}
		return owners;
	}
}
