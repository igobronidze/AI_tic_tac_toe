package ge.edu.tsu.model.heuristicfunctions;

import ge.edu.tsu.model.table.Table;

public class UseOpenAndCloseHeuristic implements HeuristicFunction {

    private UseOpenAndCloseCoeficient coeficient;     // ევრისტიკული ალგორითმი კოეფიციენტები

    public UseOpenAndCloseHeuristic() {
        coeficient = UseOpenAndCloseCoeficient.coeficient;
    }

    public UseOpenAndCloseHeuristic(UseOpenAndCloseCoeficient coeficient) {
        this.coeficient = coeficient;
    }

    /**
     * ევრისტიკული ფუნქცია ფორმულა - h(t,x) = +inf თუ x მოიგებს აუცილებლად, -inf
     * თუ x წააგებს აუცილებლად წინააღმდეგ შემთხვევაში h(n) = 1-დან 4-ს ჩათვლით
     * coefI * (openI(მოთ) * deff - openI(მოწ) * deff)
     *
     * @param x მოთამაშე
     * @param t ცხრილი
     * @return ევრისტიკული ფუნქციის მნიშვნელობა
     */
    @Override
    public double heuristic(int x, Table t) {
        OpenAndClose oc1 = OpenAndClose.getOpenAndClose(t, 1);
        OpenAndClose oc2 = OpenAndClose.getOpenAndClose(t, 2);
        if (oc1.getClop5() > 0 && oc2.getClop5() > 0) {
            return heuristic(x, t.getParent());
        }
        if (oc1.getClop5() > 0) {
            return Double.MAX_VALUE;
        }
        if (oc2.getClop5() > 0) {
            return -Double.MAX_VALUE;
        }
        if (x == 1) {
            if (oc1.getOpen4() > 0 || oc1.getClose4() > 0) {
                return Integer.MAX_VALUE;
            }
            if (oc2.getOpen4() > 0) {
                return -Integer.MAX_VALUE;
            }
        } else {
            if (oc2.getOpen4() > 0 || oc2.getClose4() > 0) {
                return -Integer.MAX_VALUE;
            }
            if (oc1.getOpen4() > 0) {
                return Integer.MAX_VALUE;
            }
        }

        double offencepercentage = 100 - coeficient.getDeffencePercentage();
        double defencePercentage = coeficient.getDeffencePercentage();
        return coeficient.getCoefClose4() * (oc1.getClose4() * offencepercentage - oc2.getClose4() * defencePercentage)
                + coeficient.getCoefOpen3() * (oc1.getOpen3() * offencepercentage - oc2.getOpen3() * defencePercentage)
                + coeficient.getCoefClose3() * (oc1.getClose3() * offencepercentage - oc2.getClose3() * defencePercentage)
                + coeficient.getCoefOpen2() * (oc1.getOpen2() * offencepercentage - oc2.getOpen2() * defencePercentage)
                + coeficient.getCoefClose2() * (oc1.getClose2() * offencepercentage - oc2.getClose2() * defencePercentage);
    }

    @Override
    public UseOpenAndCloseCoeficient getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(UseOpenAndCloseCoeficient coeficient) {
        this.coeficient = coeficient;
    }

}
