package ge.edu.tsu.model.heuristicfunctions;

/**
 * ღია და დახურული მიმდევრობის ევრისტიკული ფუნქციის კოეფიციენტები
 *
 * @author sg
 */
public class UseOpenAndCloseCoeficient {

    public double coefOpen4;
    public double coefClose4;
    public double coefOpen3;
    public double coefClose3;
    public double coefOpen2;
    public double coefClose2;

    private double deffencePercentage;  // დაცვის დონე (პროცენტულად)

    public UseOpenAndCloseCoeficient(double coefOpen4, double coefClose4, double coefOpen3, double coefClose3, double coefOpen2, double coefClose2, double deffencePercentage) {
        this.coefOpen4 = coefOpen4;
        this.coefClose4 = coefClose4;
        this.coefOpen3 = coefOpen3;
        this.coefClose3 = coefClose3;
        this.coefOpen2 = coefOpen2;
        this.coefClose2 = coefClose2;
        this.deffencePercentage = deffencePercentage;
    }

    /**
     * დაკვირვებით მიღებული ერთ-ერთი საუკეთესო კოეფიციენტები
     */
    public static final UseOpenAndCloseCoeficient coeficient
            = new UseOpenAndCloseCoeficient(1000, 200, 170, 100, 70, 20, 50);

    public double getCoefOpen4() {
        return coefOpen4;
    }

    public double getCoefClose4() {
        return coefClose4;
    }

    public double getCoefOpen3() {
        return coefOpen3;
    }

    public double getCoefClose3() {
        return coefClose3;
    }

    public double getCoefOpen2() {
        return coefOpen2;
    }

    public double getCoefClose2() {
        return coefClose2;
    }

    public static UseOpenAndCloseCoeficient getCoeficient() {
        return coeficient;
    }

    public double getDeffencePercentage() {
        return deffencePercentage;
    }

    public void setDeffencePercentage(double deffencePercentage) {
        this.deffencePercentage = deffencePercentage;
    }

}
