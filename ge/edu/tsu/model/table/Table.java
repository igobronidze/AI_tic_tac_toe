package ge.edu.tsu.model.table;

import java.awt.Point;

public class Table implements TableInterface {

    private byte[][] table;                    // ცრილი, 0 - ცარიელი, 1 - პირველი, 2 - მეორე
    private Point lastTurn = null;             // უკანასკნელი სვლა ცხრილში
    private Point bestTurn = null;             // საუკეთესო სვლა ამ მომენტში
    private double h;                          // ევრისტიკული ფუნქციის მნიშვნელობა
    Table parent;                              // მშობელი ცხრილი

    /**
     * უპარამეტრო კონსტრუქტორი, აკეთებს 20X20 ცხრილს და ავსებს 0-თ
     */
    public Table() {
        table = new byte[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                table[i][j] = 0;
            }
        }
    }

    /**
     * პარამეტრიანი კონსტრუქტორი
     *
     * @param size ცხრილის ზომა
     */
    public Table(int size) {
        table = new byte[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = 0;
            }
        }
    }

    /**
     * სვლის გაკეთება. მეთოდი ადგენს შეიძება თუ არა გარკვეულ ადგილზე გაკეთდეს
     * სვლა დადაებითი პასუხის შეთხვევაში აკეტებეს ამ ცვლილებას და თავის თავში
     * აღნიშნავს უკანასკნელად გაკეთებულ სვლას, რომელიც იქნება ის რაც ეხლა
     * გაკეთდა.
     *
     * @param i ცხრილის ჰორიზონტალური კოორდინატი
     * @param j ცხრილის ვერტიკალური კოორდინატი
     * @param x რიცხვი რომლითაც უნდა შევსოს ცხრილი (1 - პირველი მოთამაშე, 2 -
     * მეორე)
     * @return true - თუ სხვლა გაკეთდა, false - თუ აღნიშნულ ადგილზე სვლა უკვე
     * გაკეთებული იყო
     */
    @Override
    public boolean makeTurn(int i, int j, byte x) {
        if (table[i][j] == 0) {
            table[i][j] = x;
            lastTurn = new Point(i, j);
            return true;
        } else {
            return false;
        }
    }

    /**
     * მეთოდი ადგენს დაფაზე არის თუ არა მოგებული (5 ჰორიზონტალური, ვერტიკალური
     * ან დიაგონალური მიყოლებული ქვა)
     *
     * @return აბრუნებს ინფორმაციას მოგების შესახებ - ვინ მოიგო(X ან 0), სად
     * არის მოგების საწყისი წერტილი და მოგების ტიპი(ჰორ, ვერ, დიაგ1 ან დიაგ2)
     */
    @Override
    public WinInfo win() {
        WinInfo info = null;
        for (int i = 0; i <= table.length - 5; i++) {
            for (int j = 0; j <= table.length - 5; j++) {
                // ჰრიზონტალური შემოწმება
                int s = 0;
                for (int k = i; k < i + 5; k++) {
                    if (table[k][j] == table[i][j] && table[i][j] != 0) {
                        s++;
                    }
                }
                if (s == 5) {
                    info = new WinInfo(table[i][j] == 1 ? "X" : "0", new Point(i, j), WinType.VERTICAL);
                    return info;
                }

                // ვერტიკალური შემოწმება
                s = 0;
                for (int k = j; k < j + 5; k++) {
                    if (table[i][k] == table[i][j] && table[i][j] != 0) {
                        s++;
                    }
                }
                if (s == 5) {
                    info = new WinInfo(table[i][j] == 1 ? "X" : "0", new Point(i, j), WinType.HORIZONTAL);
                    return info;
                }

                // მტავარი დიაგონალი
                s = 0;
                for (int k = 0; k < 5; k++) {
                    if (table[i + k][j + k] == table[i][j] && table[i][j] != 0) {
                        s++;
                    }
                }
                if (s == 5) {
                    info = new WinInfo(table[i][j] == 1 ? "X" : "0", new Point(i, j), WinType.DIAGONAL1);
                    return info;
                }
            }
        }
        for (int i = 4; i < table.length; i++) {
            for (int j = 0; j <= table.length - 5; j++) {
                int s = 0;
                for (int k = 0; k < 5; k++) {
                    if (table[i - k][j + k] == table[i][j] && table[i][j] != 0) {
                        s++;
                    }
                }
                if (s == 5) {
                    info = new WinInfo(table[i][j] == 1 ? "X" : "0", new Point(i, j), WinType.DIAGONAL2);
                    return info;
                }
            }
        }

        return info;
    }

    /**
     * მეთოდი ქმნის ახალ ცხრილს, რომელიც შინაგანად იქნება ზუსტად იგივე, თუმცა
     * განსხვავებულ მისამართზე
     *
     * @return დაკოპირებული (მისამრთ შეცვლილი) ცხრილი
     */
    @Override
    public Table copyTable() {
        Table table = new Table(this.table.length);
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                table.getTable()[i][j] = this.table[i][j];
            }
        }
        table.setParent(this);
        return table;
    }

    @Override
    public String toString() {
        String info = "";
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                info += table[i][j] + " ";
            }
            info += System.lineSeparator();
        }
        return info;
    }

    public byte[][] getTable() {
        return table;
    }

    public void setTable(byte[][] table) {
        this.table = table;
    }

    public Point getLastTurn() {
        return lastTurn;
    }

    public void setLastTurn(Point lastTurn) {
        this.lastTurn = lastTurn;
    }

    public Point getBestTurn() {
        return bestTurn;
    }

    public void setBestTurn(Point bestTurn) {
        this.bestTurn = bestTurn;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Table getParent() {
        return parent;
    }

    public void setParent(Table parent) {
        this.parent = parent;
    }

}
