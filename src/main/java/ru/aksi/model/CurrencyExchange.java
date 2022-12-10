package ru.aksi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.aksi.writer.util.LocalDateSerializer;

import java.time.LocalDate;

@JsonPropertyOrder({"id", "value", "nominal", "currencyName", "currencyCode", "date"})
public class CurrencyExchange {
    @JsonProperty("id")
    private int id;
    @JsonProperty("value")
    private double value;
    @JsonProperty("nominal")
    private int nominal;
    @JsonProperty("currencyName")
    private String currencyName;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonProperty("date")
    private LocalDate date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CurrencyExchange() {
    }

    public CurrencyExchange(int id, double value, int nominal, String currencyName, String currencyCode, LocalDate date) {
        this.id = id;
        this.value = value;
        this.nominal = nominal;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.date = date;
    }
}
