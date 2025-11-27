package app;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import service.RecommendationService;
import model.Fault;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {

    @FXML private TextField symptomField;
    @FXML private ListView<String> resultsList;
    @FXML private Menu languageMenu;

    private ResourceBundle bundle;
    private final RecommendationService service = new RecommendationService();

    @FXML
    public void initialize() { setLanguage("mk"); }

    private void setLanguage(String lang) {
        bundle = ResourceBundle.getBundle("i18n.messages", new Locale(lang));
        symptomField.setPromptText(bundle.getString("label.symptom"));
        languageMenu.setText(bundle.getString("menu.language"));
    }

    @FXML private void setMacedonian() { setLanguage("mk"); }
    @FXML private void setEnglish() { setLanguage("en"); }
    @FXML private void setAlbanian() { setLanguage("al"); }

    @FXML
    private void onSearch() {
        String symptom = symptomField.getText();
        resultsList.getItems().clear();
        try {
            List<Fault> faults = service.getPossibleFaults(symptom);
            if (faults.isEmpty()) resultsList.getItems().add("Нема резултати / No results");
            else {
                for (Fault f : faults)
                    resultsList.getItems().add(f.getName() + " - " + f.getSolution());
            }
        } catch (SQLException e) {
            resultsList.getItems().add("Error: " + e.getMessage());
        }
    }
}
