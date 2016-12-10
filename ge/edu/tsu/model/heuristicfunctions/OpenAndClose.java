package ge.edu.tsu.model.heuristicfunctions;

import ge.edu.tsu.model.table.Table;
import java.awt.Point;

/**
 * კლასი ინახავს ინფორმაციას გარკვეულ ცხრილში კარგი პოზიციების რაოდენობას ეს
 * პოზიციები შეიძლება იყოს ღია და ცაკეტილი 2,3,4 და 5
 *
 * @author sg
 */
public class OpenAndClose {

    // ღია და დახურული მიმდევრობები
    private int open1;
    private int close1;
    private int open2;
    private int close2;
    private int open3;
    private int close3;
    private int open4;
    private int close4;
    public int clop5;

    public OpenAndClose() {
    }

    public OpenAndClose(int open1, int close1, int open2, int close2, int open3, int close3, int open4, int close4) {
        this.open1 = open1;
        this.close1 = close1;
        this.open2 = open2;
        this.close2 = close2;
        this.open3 = open3;
        this.close3 = close3;
        this.open4 = open4;
        this.close4 = close4;
    }

    public int getOpen1() {
        return open1;
    }

    public void setOpen1(int open1) {
        this.open1 = open1;
    }

    public int getClose1() {
        return close1;
    }

    public void setClose1(int close1) {
        this.close1 = close1;
    }

    public int getOpen2() {
        return open2;
    }

    public void setOpen2(int open2) {
        this.open2 = open2;
    }

    public int getClose2() {
        return close2;
    }

    public void setClose2(int close2) {
        this.close2 = close2;
    }

    public int getOpen3() {
        return open3;
    }

    public void setOpen3(int open3) {
        this.open3 = open3;
    }

    public int getClose3() {
        return close3;
    }

    public void setClose3(int close3) {
        this.close3 = close3;
    }

    public int getOpen4() {
        return open4;
    }

    public void setOpen4(int open4) {
        this.open4 = open4;
    }

    public int getClose4() {
        return close4;
    }

    public void setClose4(int close4) {
        this.close4 = close4;
    }

    public int getClop5() {
        return clop5;
    }

    public void setClop5(int clop5) {
        this.clop5 = this.clop5 + clop5;
    }

    /**
     * მეთოდი იკვლევს ცხრილს და ადგენს რამდენია შიგნით ღია 2,3,4 და დახურული
     * 2,3,4 გამოიყენება დინამიკური ალგორითმი, შეფასების დრო O(n^2)
     *
     * @param table გამოსაკვლევი ცხრილი
     * @param x რომელი მოტამაშისთვისაც ხდება გამოკვლევა
     * @return აბრუნებს მიღებულ შედეგს 2,3,4 ღია და დახურულის რაოდენობა
     */
    public static OpenAndClose getOpenAndClose(Table table, int x) {
        byte[][] b = table.getTable();
        int size = b.length;
        OpenAndClose[][] horizontal = new OpenAndClose[size][size];
        OpenAndClose[][] vertical = new OpenAndClose[size][size];
        OpenAndClose[][] diagonal1 = new OpenAndClose[size][size];
        OpenAndClose[][] diagonal2 = new OpenAndClose[size][size];
        OpenAndClose ans = new OpenAndClose();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                horizontal[i][j] = new OpenAndClose();
                vertical[i][j] = new OpenAndClose();
                diagonal1[i][j] = new OpenAndClose();
                diagonal2[i][j] = new OpenAndClose();
                if (b[i][j] != x) {
                    continue;
                }
                // (i,j)-ს  ვერტიკალური შევსება
                if (i > 0) {
                    vertical[i][j].setClose2(vertical[i - 1][j].getClose1());
                    vertical[i][j].setClose3(vertical[i - 1][j].getClose2());
                    vertical[i][j].setClose4(vertical[i - 1][j].getClose3());
                    vertical[i][j].setOpen2(vertical[i - 1][j].getOpen1());
                    vertical[i][j].setOpen3(vertical[i - 1][j].getOpen2());
                    vertical[i][j].setOpen4(vertical[i - 1][j].getOpen3());
                    vertical[i][j].setClop5(vertical[i - 1][j].getClose4() + vertical[i - 1][j].getOpen4());
                    if (b[i - 1][j] == 0) {
                        vertical[i][j].setOpen1(1);
                    } else if (b[i - 1][j] != x) {
                        vertical[i][j].setClose1(1);
                    }
                } else {
                    vertical[i][j].setClose1(1);
                }
                // (i,j)-ს  ჰორიზონტალური შევსება
                if (j > 0) {
                    horizontal[i][j].setClose2(horizontal[i][j - 1].getClose1());
                    horizontal[i][j].setClose3(horizontal[i][j - 1].getClose2());
                    horizontal[i][j].setClose4(horizontal[i][j - 1].getClose3());
                    horizontal[i][j].setOpen2(horizontal[i][j - 1].getOpen1());
                    horizontal[i][j].setOpen3(horizontal[i][j - 1].getOpen2());
                    horizontal[i][j].setOpen4(horizontal[i][j - 1].getOpen3());
                    horizontal[i][j].setClop5(horizontal[i][j - 1].getClose4() + horizontal[i][j - 1].getOpen4());
                    if (b[i][j - 1] == 0) {
                        horizontal[i][j].setOpen1(1);
                    } else if (b[i][j - 1] != x) {
                        horizontal[i][j].setClose1(1);
                    }
                } else {
                    horizontal[i][j].setClose1(1);
                }
                // (i,j)-ს  მთავარ დიაგონალური შევსება
                if (i > 0 && j > 0) {
                    diagonal1[i][j].setClose2(diagonal1[i - 1][j - 1].getClose1());
                    diagonal1[i][j].setClose3(diagonal1[i - 1][j - 1].getClose2());
                    diagonal1[i][j].setClose4(diagonal1[i - 1][j - 1].getClose3());
                    diagonal1[i][j].setOpen2(diagonal1[i - 1][j - 1].getOpen1());
                    diagonal1[i][j].setOpen3(diagonal1[i - 1][j - 1].getOpen2());
                    diagonal1[i][j].setOpen4(diagonal1[i - 1][j - 1].getOpen3());
                    diagonal1[i][j].setClop5(diagonal1[i - 1][j - 1].getClose4() + diagonal1[i - 1][j - 1].getOpen4());
                    if (b[i - 1][j - 1] == 0) {
                        diagonal1[i][j].setOpen1(1);
                    } else if (b[i - 1][j - 1] != x) {
                        diagonal1[i][j].setClose1(1);
                    }
                } else {
                    diagonal1[i][j].setClose1(1);
                }
                // (i,j)-ს  დამხმარე დიაგონალური შევსება
                if (i > 0 && j < size - 1) {
                    diagonal2[i][j].setClose2(diagonal2[i - 1][j + 1].getClose1());
                    diagonal2[i][j].setClose3(diagonal2[i - 1][j + 1].getClose2());
                    diagonal2[i][j].setClose4(diagonal2[i - 1][j + 1].getClose3());
                    diagonal2[i][j].setOpen2(diagonal2[i - 1][j + 1].getOpen1());
                    diagonal2[i][j].setOpen3(diagonal2[i - 1][j + 1].getOpen2());
                    diagonal2[i][j].setOpen4(diagonal2[i - 1][j + 1].getOpen3());
                    diagonal2[i][j].setClop5(diagonal2[i - 1][j + 1].getClose4() + diagonal2[i - 1][j + 1].getOpen4());
                    if (b[i - 1][j + 1] == 0) {
                        diagonal2[i][j].setOpen1(1);
                    } else if (b[i - 1][j + 1] != x) {
                        diagonal2[i][j].setClose1(1);
                    }
                } else {
                    diagonal2[i][j].setClose1(1);
                }
                // ვერტიკალური ჩამატება
                if (i < size - 1) {
                    if (b[i + 1][j] == 0) {
                        ans.setOpen2(ans.getOpen2() + vertical[i][j].getOpen2());
                        ans.setOpen3(ans.getOpen3() + vertical[i][j].getOpen3());
                        ans.setOpen4(ans.getOpen4() + vertical[i][j].getOpen4());
                        ans.setClose2(ans.getClose2() + vertical[i][j].getClose2());
                        ans.setClose3(ans.getClose3() + vertical[i][j].getClose3());
                        ans.setClose4(ans.getClose4() + vertical[i][j].getClose4());
                    } else if (b[i + 1][j] != x) {
                        ans.setClose2(ans.getClose2() + vertical[i][j].getOpen2());
                        ans.setClose3(ans.getClose3() + vertical[i][j].getOpen3());
                        ans.setClose4(ans.getClose4() + vertical[i][j].getOpen4());
                    }
                } else {
                    ans.setClose2(ans.getClose2() + vertical[i][j].getOpen2());
                    ans.setClose3(ans.getClose3() + vertical[i][j].getOpen3());
                    ans.setClose4(ans.getClose4() + vertical[i][j].getOpen4());
                }
                ans.setClop5(vertical[i][j].getClop5());
                // ჰორიზონტალური ჩამატება
                if (j < size - 1) {
                    if (b[i][j + 1] == 0) {
                        ans.setOpen2(ans.getOpen2() + horizontal[i][j].getOpen2());
                        ans.setOpen3(ans.getOpen3() + horizontal[i][j].getOpen3());
                        ans.setOpen4(ans.getOpen4() + horizontal[i][j].getOpen4());
                        ans.setClose2(ans.getClose2() + horizontal[i][j].getClose2());
                        ans.setClose3(ans.getClose3() + horizontal[i][j].getClose3());
                        ans.setClose4(ans.getClose4() + horizontal[i][j].getClose4());
                    } else if (b[i][j + 1] != x) {
                        ans.setClose2(ans.getClose2() + horizontal[i][j].getOpen2());
                        ans.setClose3(ans.getClose3() + horizontal[i][j].getOpen3());
                        ans.setClose4(ans.getClose4() + horizontal[i][j].getOpen4());
                    }
                } else {
                    ans.setClose2(ans.getClose2() + horizontal[i][j].getOpen2());
                    ans.setClose3(ans.getClose3() + horizontal[i][j].getOpen3());
                    ans.setClose4(ans.getClose4() + horizontal[i][j].getOpen4());
                }
                ans.setClop5(horizontal[i][j].getClop5());
                // მთავარ დიაგონალური ჩამატება
                if (i < size - 1 && j < size - 1) {
                    if (b[i + 1][j + 1] == 0) {
                        ans.setOpen2(ans.getOpen2() + diagonal1[i][j].getOpen2());
                        ans.setOpen3(ans.getOpen3() + diagonal1[i][j].getOpen3());
                        ans.setOpen4(ans.getOpen4() + diagonal1[i][j].getOpen4());
                        ans.setClose2(ans.getClose2() + diagonal1[i][j].getClose2());
                        ans.setClose3(ans.getClose3() + diagonal1[i][j].getClose3());
                        ans.setClose4(ans.getClose4() + diagonal1[i][j].getClose4());
                    } else if (b[i + 1][j + 1] != x) {
                        ans.setClose2(ans.getClose2() + diagonal1[i][j].getOpen2());
                        ans.setClose3(ans.getClose3() + diagonal1[i][j].getOpen3());
                        ans.setClose4(ans.getClose4() + diagonal1[i][j].getOpen4());
                    }
                } else {
                    ans.setClose2(ans.getClose2() + diagonal1[i][j].getOpen2());
                    ans.setClose3(ans.getClose3() + diagonal1[i][j].getOpen3());
                    ans.setClose4(ans.getClose4() + diagonal1[i][j].getOpen4());
                }
                ans.setClop5(diagonal1[i][j].getClop5());
                // დამხმარე დიაგონალური ჩამატება
                if (i < size - 1 && j > 0) {
                    if (b[i + 1][j - 1] == 0) {
                        ans.setOpen2(ans.getOpen2() + diagonal2[i][j].getOpen2());
                        ans.setOpen3(ans.getOpen3() + diagonal2[i][j].getOpen3());
                        ans.setOpen4(ans.getOpen4() + diagonal2[i][j].getOpen4());
                        ans.setClose2(ans.getClose2() + diagonal2[i][j].getClose2());
                        ans.setClose3(ans.getClose3() + diagonal2[i][j].getClose3());
                        ans.setClose4(ans.getClose4() + diagonal2[i][j].getClose4());
                    } else if (b[i + 1][j - 1] != x) {
                        ans.setClose2(ans.getClose2() + diagonal2[i][j].getOpen2());
                        ans.setClose3(ans.getClose3() + diagonal2[i][j].getOpen3());
                        ans.setClose4(ans.getClose4() + diagonal2[i][j].getOpen4());
                    }
                } else {
                    ans.setClose2(ans.getClose2() + diagonal2[i][j].getOpen2());
                    ans.setClose3(ans.getClose3() + diagonal2[i][j].getOpen3());
                    ans.setClose4(ans.getClose4() + diagonal2[i][j].getOpen4());
                }
                ans.setClop5(diagonal2[i][j].getClop5());
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        String info = "";
        info += "2 - " + open2 + " " + close2 + System.lineSeparator();
        info += "3 - " + open3 + " " + close3 + System.lineSeparator();
        info += "4 - " + open4 + " " + close4 + System.lineSeparator();
        info += "5 - " + clop5;
        return info;
    }

}
