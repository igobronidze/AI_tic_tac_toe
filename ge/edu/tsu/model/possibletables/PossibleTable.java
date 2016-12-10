package ge.edu.tsu.model.possibletables;

import ge.edu.tsu.model.table.Table;
import java.util.ArrayList;

public interface PossibleTable {

    ArrayList<Table> getTables(Table table, byte x);

    Table nextTable(Table table, byte x) throws NoMoreTableException;

    PossibleTable getCurClass();

}
