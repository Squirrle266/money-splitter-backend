package de.moneysplitter.springboot.currency;

import java.util.List;

public class CalculationRequest {
    private double amount;
    private List<DenominationResult> previousResult;
    private double[] banknotesAndCoins;

    // Konstruktor, Getter und Setter

    public CalculationRequest() {}
    public CalculationRequest(double amount, List<DenominationResult> previousResult, double[] banknotesAndCoins) {
        this.amount = amount;
        this.previousResult = previousResult;
        this.banknotesAndCoins = banknotesAndCoins;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<DenominationResult> getPreviousResult() {
        return previousResult;
    }
    public void setPreviousResult(List<DenominationResult> previousResult) {
        this.previousResult = previousResult;
    }

    public double[] getBanknotesAndCoins() {
        return banknotesAndCoins;
    }
    public void setBanknotesAndCoins(double[] banknotesAndCoins) {
        this.banknotesAndCoins = banknotesAndCoins;
    }
}