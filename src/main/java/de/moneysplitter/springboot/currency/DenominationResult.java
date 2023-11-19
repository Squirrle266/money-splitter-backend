package de.moneysplitter.springboot.currency;

public class DenominationResult {
    private double banknotesAndCoins;
    private int count;

    // Konstruktor, Getter und Setter

    public DenominationResult() {}
    public DenominationResult(double banknotesAndCoins, int count) {
        this.banknotesAndCoins = banknotesAndCoins;
        this.count = count;
    }

    public double getBanknotesAndCoins() {
        return banknotesAndCoins;
    }
    public void setBanknotesAndCoins(double banknotesAndCoins) {
        this.banknotesAndCoins = banknotesAndCoins;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}