package ru.aksi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import ru.aksi.dto.util.DoubleDeserializer;

public class Valute {
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "NumCode", isAttribute = true)
    private int numCode;
    @JacksonXmlProperty(localName = "CharCode", isAttribute = true)
    private String charCode;
    @JacksonXmlProperty(localName = "Nominal", isAttribute = true)
    private int nominal;
    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;
    @JacksonXmlProperty(localName = "Value", isAttribute = true)
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double value;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Valute() {
    }

    public Valute(String id, int numCode, String charCode, int nominal, String name, double value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }
}
