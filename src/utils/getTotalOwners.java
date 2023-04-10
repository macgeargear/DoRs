package utils;

import java.util.ArrayList;

import buildings.Edge;

public class getTotalOwners {
	public static int fromSideEdges(ArrayList<Edge> sideEdges) {
		int owners = 0;
		for (Edge edge : sideEdges) {
			if (edge.getOwner() != null) {
				owners += 1;
			}
		}
		return owners;
	}
}
