package de.moneysplitter.springboot.currency;

import java.util.List;

public class CalculationResult {
    private List<DenominationResult> result;
    private List<DenominationDifference> difference;

    // Konstruktor, Getter und Setter

    public CalculationResult(List<DenominationResult> result, List<DenominationDifference> difference) {
        this.result = result;
        this.difference = difference;
    }

    public List<DenominationResult> getResult() {
        return result;
    }
    public void setResult(List<DenominationResult> result) {
        this.result = result;
    }

    public List<DenominationDifference> getDifference() {
        return difference;
    }
    public void setDifference(List<DenominationDifference> difference) {
        this.difference = difference;
    }
}