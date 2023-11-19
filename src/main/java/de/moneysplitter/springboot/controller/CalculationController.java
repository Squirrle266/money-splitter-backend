package de.moneysplitter.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.moneysplitter.springboot.currency.CalculationRequest;
import de.moneysplitter.springboot.currency.CalculationResult;
import de.moneysplitter.springboot.currency.DenominationDifference;
import de.moneysplitter.springboot.currency.CalculationResponse;
import de.moneysplitter.springboot.currency.DenominationResult;
import de.moneysplitter.springboot.service.CalculationService;

@RestController
public class CalculationController {

    @Autowired
    private CalculationService currencyService;

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculateDenomination(@RequestBody CalculationRequest request) {
        double amount = request.getAmount();
        List<DenominationResult> previousResult = request.getPreviousResult();
        double[] banknotesAndCoins = request.getBanknotesAndCoins();

        // Stückelung und Differenz berechnen
        CalculationResult calculationResult = currencyService.calculateDenomination(amount, previousResult, banknotesAndCoins);
        List<DenominationResult> result = calculationResult.getResult();
        List<DenominationDifference> difference = calculationResult.getDifference();

        // Antwort erstellen
        CalculationResponse response = new CalculationResponse(result, difference);

        return ResponseEntity.ok(response);
    }
}
