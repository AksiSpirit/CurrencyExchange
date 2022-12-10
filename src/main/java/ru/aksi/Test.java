package ru.aksi;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;
import ru.aksi.dto.ValCurs;
import ru.aksi.dto.Valute;
import ru.aksi.http.CbrService;
import ru.aksi.model.CurrencyExchange;
import ru.aksi.repository.CurrencyExchangeRepositorySqliteImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("https://www.cbr.ru")
                .setConverter(new JacksonConverter(new XmlMapper()))
                .build();

        CbrService cbrService = retrofit.create(CbrService.class);

        String date = "05/04/2021";
        ValCurs exchange = cbrService.getExchange(date);
        List<Valute> valuteList = exchange.getValuteList();

        CurrencyExchangeRepositorySqliteImpl repository = CurrencyExchangeRepositorySqliteImpl.getInstance();
        repository.deleteAll();
        for (Valute valute: valuteList) {
            CurrencyExchange currency = Converter.valuteToCurrency(valute, date);
            repository.insert(currency);
        }
        Database.getInstance().closeConnection();
    }
}
