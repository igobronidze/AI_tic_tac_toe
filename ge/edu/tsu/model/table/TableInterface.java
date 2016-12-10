package ge.edu.tsu.model.table;

public interface TableInterface {

    boolean makeTurn(int i, int j, byte x);

    WinInfo win();

    Table copyTable();

}
