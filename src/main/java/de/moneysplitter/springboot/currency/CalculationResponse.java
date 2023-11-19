package de.moneysplitter.springboot.currency;

import java.util.List;

public class CalculationResponse {
    private List<DenominationResult> currentResult;
    private List<DenominationDifference> difference;

    public CalculationResponse(List<DenominationResult> currentResult, List<DenominationDifference> difference) {
        this.currentResult = currentResult;
        this.difference = difference;
    }

    public List<DenominationResult> getCurrentResult() {
        return currentResult;
    }

    public List<DenominationDifference> getDifference() {
        return difference;
    }

    public void setCurrentResult(List<DenominationResult> currentResult) {
        this.currentResult = currentResult;
    }

    public void setDifference(List<DenominationDifference> difference) {
        this.difference = difference;
    }
}