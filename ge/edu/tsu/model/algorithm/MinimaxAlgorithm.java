package ge.edu.tsu.model.algorithm;

import ge.edu.tsu.model.heuristicfunctions.HeuristicFunction;
import ge.edu.tsu.model.possibletables.PossibleTable;
import ge.edu.tsu.model.table.Table;
import java.awt.Point;
import java.util.ArrayList;

public class MinimaxAlgorithm extends AIAlgorithm {

    public MinimaxAlgorithm() {
    }

    public MinimaxAlgorithm(int maxDepth) {
        super(maxDepth);
    }
    
    @Override
    public void reload() {
        
    }

    /**
     * ალგორითმი მინიმაქსი. შემოკლებული ალგორითმი გარკვეულ სიღმემდე. ალგორითმი
     * შემოივლის ყველა სასურველ წიბოს და გადმოცემული ცხრილში დააფიქსირებს
     * საუკეთესო სვლას.
     *
     * @param table გადმოცეული ცხრილი
     * @param currDepth ცხრილის სიღრმე
     * @return საუკეთესო განვითარების ევრისტიკული ფუნქციის მნიშვნელობა
     */
    @Override
    public double algorithm(Table table, int currDepth, PossibleTable possibleTable, HeuristicFunction heuristicFunction) {
        byte x = (byte) currDepth % 2 == 0 ? (byte) 1 : (byte) 2;                // მოთამაშის ინდექსის დათვლა
        if (currDepth == maxDepth) {
            table.setH(heuristicFunction.heuristic(x, table));
            return table.getH();
        }
        ArrayList<Table> arr = possibleTable.getTables(table, x);
        Table ans = arr.get(0);
        double d = algorithm(ans, currDepth + 1, possibleTable, heuristicFunction);
        if (x == (byte) 1) {
            for (Table t : arr) {
                double k = algorithm(t, currDepth + 1, possibleTable, heuristicFunction);
                if (k > d) {
                    ans = t;
                    d = k;
                }
            }
        } else {
            for (Table t : arr) {
                double k = algorithm(t, currDepth + 1, possibleTable, heuristicFunction);
                if (k < d) {
                    ans = t;
                    d = k;
                }
            }
        }
        table.setBestTurn(new Point(ans.getLastTurn()));
        table.setH(d);
        return table.getH();
    }

}
