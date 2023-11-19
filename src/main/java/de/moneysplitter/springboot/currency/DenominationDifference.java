package de.moneysplitter.springboot.currency;

public class DenominationDifference {
    private int countDiff;
    private double banknotesAndCoins;

    // Konstruktor, Getter und Setter

    public DenominationDifference(int countDiff, double banknotesAndCoins) {
        this.countDiff = countDiff;
        this.banknotesAndCoins = banknotesAndCoins;
    }

    public int getCountDiff() {
        return countDiff;
    }
    public void setCountDiff(int countDiff) {
        this.countDiff = countDiff;
    }

    public double getBanknotesAndCoins() {
        return banknotesAndCoins;
    }
    public void setBanknotesAndCoins(double banknotesAndCoins) {
        this.banknotesAndCoins = banknotesAndCoins;
    }
}