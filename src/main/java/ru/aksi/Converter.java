package ru.aksi;

import ru.aksi.dto.Valute;
import ru.aksi.model.CurrencyExchange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
    public static CurrencyExchange valuteToCurrency(Valute valute, String date) {
        CurrencyExchange currency = new CurrencyExchange();
        currency.setValue(valute.getValue());
        currency.setNominal(valute.getNominal());
        currency.setCurrencyName(valute.getName());
        currency.setCurrencyCode(valute.getCharCode());
        currency.setDate(LocalDate.parse(date, formatter));
        return currency;
    }
}
