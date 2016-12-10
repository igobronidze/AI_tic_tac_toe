package ge.edu.tsu.model.heuristicfunctions;

import ge.edu.tsu.model.table.Table;

public interface HeuristicFunction {

    double heuristic(int x, Table t);

    UseOpenAndCloseCoeficient getCoeficient();

}
