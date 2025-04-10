package metropolia.patricsc.otp.dblocalization;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> languageSelector;

    @FXML
    private TextField tfNewTitle;
    @FXML
    private TextField tfNewTranslation;

    @FXML
    private Button btnSaveTitle;

    @FXML
    private ListView<String> employeeList;

    private static final String DB_url = "jdbc:mysql://localhost:3306/db_localize";
    private static final String DB_username = "root";
    private static final String DB_password = "";

    private static final String[] langs = {"English", "Spanish", "French", "Chinese"};

    @FXML
    private void initialize() {
        languageSelector.getItems().addAll(langs);
        languageSelector.setValue(langs[0]);
        languageSelector.setOnAction(event -> changeLanguage());
        changeLanguage();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void changeLanguage() {
        String languageCode = getLanguageCode(languageSelector.getValue());
        fetchLocalizedData(languageCode);
    }

    private String getLanguageCode(String language) {
        return switch(language) {
            case "Spanish" -> "es";
            case "French" -> "fr";
            case "Chinese" -> "CN";
            default -> "en";
        };
    }

    private void fetchLocalizedData(String languageCode) {
        String query = "SELECT Key_name, translation_text from translations where Language_code=?";
        try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, languageCode);
            ResultSet rs = stmt.executeQuery();

            employeeList.getItems().clear();
            while (rs.next()) {
                String keyName = rs.getString("key_name");
                String translationText = rs.getString("translation_text");
                employeeList.getItems().add(keyName + ": " + translationText);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void saveNewTitle() {
        String newTitle = tfNewTitle.getText();
        String newTranslation = tfNewTranslation.getText();
        String currentLanguageCode = getLanguageCode(languageSelector.getValue());
        String query = "INSERT INTO translations (Key_name, Language_code, translation_text) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, currentLanguageCode);
            stmt.setString(3, newTranslation);

            stmt.executeUpdate();

            tfNewTitle.setText("");
            tfNewTranslation.setText("");
            fetchLocalizedData(currentLanguageCode);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}