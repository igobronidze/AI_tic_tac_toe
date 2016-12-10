package ge.edu.tsu.model.algorithm;

import ge.edu.tsu.model.heuristicfunctions.HeuristicFunction;
import ge.edu.tsu.model.possibletables.PossibleTable;
import ge.edu.tsu.model.table.Table;

public abstract class AIAlgorithm {

    protected int maxDepth;                               // მაქსიმუმი სიღრმე

    public AIAlgorithm() {
    }

    public AIAlgorithm(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public abstract double algorithm(Table table, int currDepth, PossibleTable possibleTable, HeuristicFunction heuristicFunction);

    public abstract void reload();
    
}
