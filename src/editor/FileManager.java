package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
                textEditor.currentFile = file;
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(textEditor, "Error opening file: " + ex.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);
                    writer.close();
                    textEditor.currentFile = file;
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving your file: " + ex.getMessage());
                }
            }
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile));
                textArea.write(writer);
                writer.close();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(textEditor, "Error saving file: " + ex.getMessage());
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        // پاک کردن محتوای موجود در JTextArea
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
