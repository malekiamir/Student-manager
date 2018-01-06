package manager.student;
/*
    Amir Maleki
*/
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FormController {
    @FXML private TextField stId;
    @FXML private TextField name;
    @FXML private TextField familyName;
    @FXML private TextField score;
    @FXML private TextField passed;
    @FXML private TextField trend;
    @FXML private Button submit;
    @FXML private Button show;
    private final String sourceFile = "students.json";
    private final String sourceFileName = "students_sorted_by_name.json";
    private final String sourceFileId = "students_sorted_by_id.json";
    ArrayList <Student>studentArraySortedByName = new ArrayList<>();
    ArrayList <Student>studentArraySortedById = new ArrayList<>();
    
    public Student stringToPerson(String text) {
        Gson gson = new Gson();
        return gson.fromJson(text, Student.class);
    }

    public String personToString(Student s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }
    
    public void save() {
        File file = new File(sourceFile);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Student s;
            String str;
            while((str = reader.readLine()) != null) {
                s = stringToPerson(str);
                studentArraySortedByName.add(s);
                studentArraySortedById.add(s);
            }
        } catch (IOException ex) {
            Logger.getLogger("can not access the file");
        }
        
        Collections.sort(studentArraySortedByName, (Student o1, Student o2) -> {
            for (int i = 0; i < o1.name.length() - 1 && i < o2.name.length() - 1; i++) {
                if (o1.name.charAt(i) > o2.name.charAt(i)) {
                    return 1;
                } else if (o1.name.charAt(i) < o2.name.charAt(i)) {
                    return -1;
                }
            }
            if (o1.name.length() == o2.name.length())
                return 0;
            else if (o1.name.length() > o2.name.length())
                return 1;
            else if (o1.name.length() < o2.name.length())
                return -1;
            return 0;
        });
        
        Collections.sort(studentArraySortedById, (Student o1, Student o2) -> {
            for (int i = 0; i < o1.stId.length() - 1 && i < o2.stId.length() - 1; i++) {
                if (o1.stId.charAt(i) > o2.stId.charAt(i)) {
                    return 1;
                } else if (o1.stId.charAt(i) < o2.stId.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        });
        File fileSortedName = new File(sourceFileName);
        fileSortedName.delete();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSortedName, true))) {
            writer.write("مرتب شده بر اساس نام");
            writer.newLine();
            for (Student s : studentArraySortedByName) {
                writer.write(personToString(s));
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger("can not access the file");
        }
        
        File fileSortedId = new File(sourceFileId);
        fileSortedId.delete();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileSortedId, true))) {
            writer.write("مرتب شده بر اساس شماره دانشجویی");
            writer.newLine();
            for (Student s : studentArraySortedById) {
                writer.write(personToString(s));
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger("can not access the file");
        }
            
    }
    
    public void initManager(final FormManager formManager) {
        submit.setOnAction((ActionEvent event) -> {
            File file = new File(sourceFile);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                Student s = new Student(stId.getText(), name.getText()
                        , familyName.getText(), Double.parseDouble(score.getText())
                        , Integer.parseInt(passed.getText()), trend.getText());
                studentArraySortedByName.add(s);
                studentArraySortedById.add(s);                
                writer.append(personToString(s));
                writer.newLine();
                formManager.authenticated(s.toStringP()
                        + "  با موفقیت ثبت شد");
                save();
            } catch (IOException ex) {
                Logger.getLogger("can not access the file");
            } catch (NumberFormatException ex) {
                formManager.authenticated("لطفا فرم را با دقت پر نمایید");
                Logger.getLogger("not valid inputs");
            }
            
        });
        show.setOnAction((ActionEvent event) -> {
            ProcessBuilder pb1 = new ProcessBuilder("Notepad.exe", sourceFileName);
            ProcessBuilder pb2 = new ProcessBuilder("Notepad.exe", sourceFileId);
            try {
                pb1.start();
            } catch (IOException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                pb2.start();
            } catch (IOException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
