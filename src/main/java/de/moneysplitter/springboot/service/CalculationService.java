package de.moneysplitter.springboot.service;

import org.springframework.stereotype.Service;

import de.moneysplitter.springboot.currency.CalculationResult;
import de.moneysplitter.springboot.currency.DenominationDifference;
import de.moneysplitter.springboot.currency.DenominationResult;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculationService {

    private final Object lock = new Object();

    private List<DenominationResult> previousResultBackend = Collections.emptyList();

    // Berechnung der Stückelung
    public CalculationResult calculateDenomination(double amount, List<DenominationResult> previousResult, double[] banknotesAndCoins) {
        List<DenominationResult> result = new ArrayList<>();

        // Umrechnung in Cent, um Rundungsfehler zu vermeiden
        int amountInCent = (int) (amount * 100);

        // Durchlaufe alle Scheine/Münzen und ziehe diese vom Betrag ab
        for (double banknoteOrCoin : banknotesAndCoins) {
            int count = amountInCent / (int) (banknoteOrCoin * 100);
            if (count > 0) {
                result.add(new DenominationResult(banknoteOrCoin, count));
                amountInCent %= (int) (banknoteOrCoin * 100);
            }
        }

        List<DenominationDifference> difference = calculateDifference(result, previousResult);

        // thread-safe access, damit die Aktualisierung vollständig ausgeführt wird
        synchronized (lock) {
            previousResultBackend = new ArrayList<>(result);
        }

        return new CalculationResult(result, difference);
    }

    // Berechnung der Differenz
    public List<DenominationDifference> calculateDifference(List<DenominationResult> currentResult, List<DenominationResult> previousResult) {
        
        // Nimm die letzte Stückelung, falls vorhanden
        if (previousResult != null){
            previousResultBackend = previousResult;
        } else if (previousResultBackend == null) {
            return new ArrayList<>();
        }

        // Nimm nur die Scheine/Münzen der vorherigen und aktuellen Stückelung
        Set<Double> displayedBanknotesAndCoins = Stream.concat(
                currentResult.stream().map(DenominationResult::getBanknotesAndCoins),
                previousResultBackend.stream().map(DenominationResult::getBanknotesAndCoins))
                .collect(Collectors.toSet());

        List<DenominationDifference> difference = new ArrayList<>();

        // Berechne die Differenz zwischen den Scheinen/Münzen der vorherigen und aktuellen Stückelung 
        for (double banknoteOrCoin : displayedBanknotesAndCoins) {
            DenominationResult current = currentResult.stream()
                    .filter(item -> item.getBanknotesAndCoins() == banknoteOrCoin)
                    .findFirst()
                    .orElse(new DenominationResult(banknoteOrCoin, 0));

            DenominationResult previous = previousResultBackend.stream()
                    .filter(item -> item.getBanknotesAndCoins() == banknoteOrCoin)
                    .findFirst()
                    .orElse(new DenominationResult(banknoteOrCoin, 0));

            int countDiff = current.getCount() - previous.getCount();
            difference.add(new DenominationDifference(countDiff, banknoteOrCoin));
        }

        // Sortiere die Differenzen in absteigender Reihenfolge nach den Scheinen/Münzen
        List<DenominationDifference> sortedDifference = difference.stream()
            .sorted(Comparator.comparingDouble(DenominationDifference::getBanknotesAndCoins).reversed())
            .collect(Collectors.toList());

        return sortedDifference;
    }
}
