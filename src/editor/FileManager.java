package editor;

import javax.swing.*;
import java.io.*;
import java.awt.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);  // خواندن محتویات فایل و نمایش آن در JTextArea
                reader.close();
                textEditor.currentFile = file;  // ذخیره مسیر فایل در currentFile
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(textEditor, "Error opening file: " + ex.getMessage());  // نمایش پیام خطا
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {  // اگر currentFile مقدار ندارد، کاربر باید مسیر ذخیره را انتخاب کند
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);  // نوشتن محتوای متن در فایل
                    writer.close();
                    textEditor.currentFile = file;  // ذخیره مسیر فایل در currentFile
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(textEditor, "Error saving your file: " + ex.getMessage());  // نمایش پیام خطا
                }
            }
        } else {  // اگر currentFile مقدار دارد، مستقیماً روی همان فایل ذخیره می‌شود
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile));
                textArea.write(writer);  // نوشتن محتوای متن در فایل موجود
                writer.close();
            }
            catch (IOException ex) {  // رفع مشکل عدم تعریف ex در بلاک catch
                JOptionPane.showMessageDialog(textEditor, "Error saving file: " + ex.getMessage());  // نمایش پیام خطا
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");  // پاک کردن محتوای موجود در JTextArea
        textEditor.currentFile = null;  // ریست کردن فایل فعلی
    }
}
