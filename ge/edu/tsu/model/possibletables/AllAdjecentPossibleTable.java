package ge.edu.tsu.model.possibletables;

import ge.edu.tsu.model.table.Table;
import java.awt.Point;
import java.util.ArrayList;

public class AllAdjecentPossibleTable implements PossibleTable {

    private Point nextPossibleTurn;        // შემდეგი შესაძლო სვლა

    public AllAdjecentPossibleTable() {
        nextPossibleTurn = new Point(0, 0);
    }

    /**
     * მეთოდი ქმნის და აბრუნებს საკუთარი კლასის ობიექტს
     *
     * @return ახალი ობიექტი
     */
    @Override
    public PossibleTable getCurClass() {
        return new AllAdjecentPossibleTable();
    }

    /**
     * მეთოდი აბრუნებს ცხრილი ყველა შესაძლო მეზობელ განვითარებას, მეზობელ
     * განვითარებაში იგულისხმება ყველა ისეთი სვლა, რომელიც გაკეთდება უკვე
     * დადებული ქვის გვერდით.
     *
     * @param table ცხრილი რომლის განვითარებებიც დგინდება
     * @param x რიცხვი რომლითაც უდნა განვითარდეს ცხრილი - მოთამაშის ინდექსი
     * @return ყველა შეესაძლო განვითარების სია
     */
    @Override
    public ArrayList<Table> getTables(Table table, byte x) {
        byte[][] b = table.getTable();
        ArrayList<Table> tables = new ArrayList<Table>();
        // თუ ცხრილი ცარიელია არის მხოლოდ ერთი ვარიანტი და ესაა შუაში ჩამატება 
        if (table.getLastTurn() == null) {
            Table t = table.copyTable();
            t.makeTurn(b.length / 2, b.length / 2, x);
            tables.add(t);
            return tables;
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i][j] == 0 && hasAdjecent(b, i, j)) {
                    Table t = table.copyTable();
                    t.makeTurn(i, j, x);
                    tables.add(t);
                }
            }
        }
        return tables;
    }

    /**
     * მეთოდი აბრუნებს შემდეგ შესაძლო ცხრილს, რომელიც იქნება სადმე ემზობლად
     * ჩამატებული x თამაშით. თუ მეტი სვლა აღარ არის გაისვირს
     * NoMoreTableEception-ს.
     *
     * @param table ცხრილი სადაც უნდა მოხდეს ახალი სვლა
     * @param x მოთამაშის ინდექსი
     * @return ახალ ცხრილს
     * @throws NoMoreTableException გაისვრის თუ მეტი სვლა ვეღარ გაკეთდება
     */
    @Override
    public Table nextTable(Table table, byte x) throws NoMoreTableException {
        byte[][] b = table.getTable();
        int size = b.length;
        while (true) {
            if (nextPossibleTurn.y == size) {
                nextPossibleTurn.y = 0;
                nextPossibleTurn.x++;
            }
            if (nextPossibleTurn.x == size) {
                throw new NoMoreTableException("მეტი ნწ :D");
            }
            if (b[nextPossibleTurn.x][nextPossibleTurn.y] == 0 && hasAdjecent(b, nextPossibleTurn.x, nextPossibleTurn.y)) {
                Table t = table.copyTable();
                t.makeTurn(nextPossibleTurn.x, nextPossibleTurn.y, x);
                nextPossibleTurn.y++;
                return t;
            } else {
                nextPossibleTurn.y++;
            }
        }
    }

    /**
     * დგინდება გარკვეულ ადგილს გააჩნია თუ არა მეზობელი ქვა (უკვე დადებული)
     *
     * @param b ცხრილი
     * @param i ჰორ ინდექსი
     * @param j ვერ ინდექი
     * @return true - ყავს მეზობელი, false - არ ყავს
     */
    public boolean hasAdjecent(byte b[][], int i, int j) {
        if (i > 0) {
            if (b[i - 1][j] != 0) {
                return true;
            }
        }
        if (j > 0) {
            if (b[i][j - 1] != 0) {
                return true;
            }
        }
        if (i < b.length - 1) {
            if (b[i + 1][j] != 0) {
                return true;
            }
        }
        if (j < b.length - 1) {
            if (b[i][j + 1] != 0) {
                return true;
            }
        }
        if (i > 0 && j > 0) {
            if (b[i - 1][j - 1] != 0) {
                return true;
            }
        }
        if (i > 0 && j < b.length - 1) {
            if (b[i - 1][j + 1] != 0) {
                return true;
            }
        }
        if (i < b.length - 1 && j > 0) {
            if (b[i + 1][j - 1] != 0) {
                return true;
            }
        }
        if (i < b.length - 1 && j < b.length - 1) {
            if (b[i + 1][j + 1] != 0) {
                return true;
            }
        }

        return false;
    }

}
