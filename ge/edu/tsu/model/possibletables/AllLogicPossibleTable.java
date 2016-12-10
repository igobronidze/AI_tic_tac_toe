package ge.edu.tsu.model.possibletables;

import ge.edu.tsu.model.table.Table;
import java.awt.Point;
import java.util.ArrayList;

/**
 * კლასი არ არის დამთავრებული. იდეა არის ლოგიკური სვლის ცხრილების დაბრუნბა, ანუ
 * ისეთი სვლები როცა ახალი სვლა დგება ძველი ქვის გვერდით ან ერთის გამოტოვებით
 */
public class AllLogicPossibleTable implements PossibleTable {

    private Point nextPossibleTurn;        // შემდეგი შესაძლო სვლა

    public AllLogicPossibleTable() {
        nextPossibleTurn = new Point(0, 0);
    }

    @Override
    public ArrayList<Table> getTables(Table table, byte x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PossibleTable getCurClass() {
        return new AllLogicPossibleTable();
    }

    @Override
    public Table nextTable(Table table, byte x) {
        return null;
    }

}
