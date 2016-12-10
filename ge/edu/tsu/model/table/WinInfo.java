package ge.edu.tsu.model.table;

import java.awt.Point;

public class WinInfo {

    private String winner;           // გამარჯვებული, X ან 0s
    private Point start;             // მოგებული მიყოლების საწყისი
    private WinType winType;         // მოგების ტიპი (ჰორ, ვერ, დიაგ1, დიაგ2)

    public WinInfo() {
    }

    public WinInfo(String winner, Point start, WinType winType) {
        this.winner = winner;
        this.start = start;
        this.winType = winType;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public WinType getWinType() {
        return winType;
    }

    public void setWinType(WinType winType) {
        this.winType = winType;
    }

    @Override
    public String toString() {
        return "WinInfo{" + "winner=" + winner + ", start=" + start + ", winType=" + winType + '}';
    }

}
