package ge.edu.tsu.model.algorithm;

import ge.edu.tsu.model.heuristicfunctions.HeuristicFunction;
import ge.edu.tsu.model.possibletables.NoMoreTableException;
import ge.edu.tsu.model.possibletables.PossibleTable;
import ge.edu.tsu.model.table.Table;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * კლასი და მისი ფუნქცია არ მუშაობს. იდეა არის ალფა-ბეტა ალგორითმის გამოყენება.
 *
 * @author sg
 */
public class AlphaBetaAlgorithm extends AIAlgorithm {

    private HashMap<Integer, Double> currAns = new HashMap<Integer, Double>();

    public AlphaBetaAlgorithm() {
    }

    public AlphaBetaAlgorithm(int maxDepth) {
        super(maxDepth);
    }

    @Override
    public void reload() {
        currAns = new HashMap<>();
    }

    @Override
    public double algorithm(Table table, int currDepth, PossibleTable possibleTable, HeuristicFunction heuristicFunction) {

        byte x = (byte) currDepth % 2 == 0 ? (byte) 1 : (byte) 2;
        if (currDepth == maxDepth) {
            table.setH(heuristicFunction.heuristic(x, table));
            return table.getH();
        }
        PossibleTable p = possibleTable.getCurClass();
        Table ans = null;
        try {
            ans = p.nextTable(table, x);
        } catch (NoMoreTableException ex) {
            System.out.println(ex.getMessage());
        }
        double d = algorithm(ans, currDepth + 1, p, heuristicFunction);
        Table t = null;
        while (true) {
            try {
                /*
                if (currAns.containsKey(currDepth) && currDepth != 0) {
                    
                    if (x == (byte) 1) {
                        if (d >= currAns.get(currDepth)) {
                            System.out.println("შეწყდა მეტობის გამო!");
                            break;
                        }
                    } else if (d <= currAns.get(currDepth)) {
                        System.out.println("შეწყდა ნაკლებობის გამო!");
                        break;
                    }
                    
                }*/
                t = p.nextTable(table, x);
                double k = algorithm(t, currDepth + 1, p, heuristicFunction);
                
                if (x == (byte) 1) {
                    if (k > d) {
                        ans = t;
                        d = k;
                    }
                } else {
                    if (k < d) {
                        ans = t;
                        d = k;
                    }
                }
                System.out.println(t.getLastTurn() + " " + currDepth + " " + d + " " + currAns.get(currDepth));
                 
            } catch (NoMoreTableException ex) {
                System.out.println("დამთავრდა ყველა!");
                break;
            }
        }
        
        table.setBestTurn(new Point(ans.getLastTurn()));
        table.setH(d);
        /*
        if (currAns.containsKey(currDepth)) {
            if (x == (byte) 1) {
                if (d < currAns.get(currDepth)) {
                    currAns.replace(currDepth, d);
                }
            } else {
                if (d > currAns.get(currDepth)) {
                    currAns.replace(currDepth, d);
                }
            }
        } else {
            currAns.put(currDepth, d);
        }
         */
        return table.getH();

        //return ABalgorithm(table, currDepth, possibleTable, heuristicFunction, maxDepth, maxDepth)
    }

}
