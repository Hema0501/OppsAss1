import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VirtualizedTableApp extends Application {

    public static class Student {
        private final String name;
        private final int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Student Table - Virtualized");

        // Your name and register number
        Label userInfo = new Label("Name: Hemapriya R    Register No: 2117240070115");
        userInfo.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        TableView<Student> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student, String> nameCol = new TableColumn<>("Student Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        tableView.getColumns().addAll(nameCol, ageCol);

        ObservableList<Student> data = FXCollections.observableArrayList();

        // Sample student names
        String[] names = {
            "Aarav", "Diya", "Rohan", "Meera", "Karthik", "Sneha", "Vikram", "Anjali",
            "Rahul", "Priya", "Arjun", "Neha", "Siddharth", "Ishita", "Manoj", "Pooja"
        };

        // Generate 100,000 students with realistic names
        for (int i = 0; i < 100_000; i++) {
            String name = names[i % names.length] + " " + (char)('A' + (i % 26)) + ".";
            int age = 18 + (i % 5); // Age between 18–22
            data.add(new Student(name, age));
        }

        tableView.setItems(data);

        VBox root = new VBox(10, userInfo, tableView);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
