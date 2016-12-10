package ge.edu.tsu.model.possibletables;

import ge.edu.tsu.model.table.Table;
import java.awt.Point;
import java.util.ArrayList;

public class AllPossibleTable implements PossibleTable {

    private Point nextPossibleTurn;        // შემდეგი შესაძლო სვლა

    public AllPossibleTable() {
        nextPossibleTurn = new Point(0, 0);
    }

    /**
     * მეთოდი აბრუნებს ცხრილის ყველა შესაძლო განვითარებას. ყველა შესაძლო
     * განვითარებაში იგულისხმება ნებისმიერი ადგილი ცხრილში, სადაც წერია 0.
     *
     * @param table ცხრილი რომლის განვითარებებიც დგინდება
     * @param x რიცხვი რომლითაც უდნა განვითარდეს ცხრილი - მოთამაშის ინდექსი
     * @return ყველა შეესაძლო განვითარების სია
     */
    @Override
    public ArrayList<Table> getTables(Table table, byte x) {
        byte[][] b = table.getTable();
        ArrayList<Table> tables = new ArrayList<Table>();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i][j] == 0) {
                    Table t = table.copyTable();
                    t.makeTurn(i, j, x);
                    tables.add(t);
                }
            }
        }
        return tables;
    }

    @Override
    public Table nextTable(Table table, byte x) {
        return null;
    }

    /**
     * მეთოდი ქმნის და აბრუნებს საკუთარი კლასის ობიექტს
     *
     * @return ახალი ობიექტი
     */
    @Override
    public PossibleTable getCurClass() {
        return new AllPossibleTable();
    }

}
