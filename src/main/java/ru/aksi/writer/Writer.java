package ru.aksi.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ru.aksi.Database;
import ru.aksi.model.CurrencyExchange;
import ru.aksi.repository.CurrencyExchangeRepositorySqliteImpl;

import java.io.*;

public class Writer {
    public static void writeCSVToFile(File file) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(CurrencyExchange.class).withUseHeader(true);
            ObjectWriter myObjectWriter = mapper.writer(schema);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
            OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            myObjectWriter.writeValue(writerOutputStream, CurrencyExchangeRepositorySqliteImpl.getInstance().findAll());
            Database.getInstance().closeConnection();
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e);
        }
    }
    public static void writeJSONToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(CurrencyExchangeRepositorySqliteImpl.getInstance().findAll());
            Database.getInstance().closeConnection();
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error exporting to JSON: " + e);
        }
    }
}
