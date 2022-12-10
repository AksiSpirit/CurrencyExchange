package ru.aksi.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import ru.aksi.Database;
import ru.aksi.http.Downloader;
import ru.aksi.model.CurrencyExchange;
import ru.aksi.repository.CurrencyExchangeRepositorySqliteImpl;
import ru.aksi.writer.Writer;

import java.io.File;
import java.time.LocalDate;

public class CurrencyExchangeController {
    private ObservableList<CurrencyExchange> currencies = FXCollections.observableArrayList();

    @FXML
    private TableView<CurrencyExchange> currencyTable;
    @FXML
    private TableColumn<CurrencyExchange, Integer> idColumn;
    @FXML
    private TableColumn<CurrencyExchange, String> codeColumn;
    @FXML
    private TableColumn<CurrencyExchange, String> nameColumn;
    @FXML
    private TableColumn<CurrencyExchange, Double> nominalColumn;
    @FXML
    private TableColumn<CurrencyExchange, Integer> valueColumn;
    @FXML
    private TableColumn<CurrencyExchange, LocalDate> dateColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Integer>("id"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, String>("currencyCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, String>("currencyName"));
        nominalColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Double>("nominal"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, Integer>("value"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<CurrencyExchange, LocalDate>("date"));

        updateTableData();
    }

    private void updateTableData() {
        currencies.setAll(CurrencyExchangeRepositorySqliteImpl.getInstance().findAll());
        Database.getInstance().closeConnection();
        currencyTable.setItems(currencies);
    }

    @FXML
    private void onUpdateButtonClick() {
        Downloader.loadDataFromAPI();
        updateTableData();
    }

    @FXML
    private void onSaveCSVButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as CSV");
        fileChooser.setInitialFileName("CurrencyExchange.csv");
        File file = fileChooser.showSaveDialog(CurrencyExchangeApplication.getWindow());
        Writer.writeCSVToFile(file);
    }

    @FXML
    private void onSaveJSONButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as JSON");
        fileChooser.setInitialFileName("CurrencyExchange.json");
        File file = fileChooser.showSaveDialog(CurrencyExchangeApplication.getWindow());
        Writer.writeJSONToFile(file);
    }
}