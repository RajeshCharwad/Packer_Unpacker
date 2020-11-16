package Logic;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Packer {
    public FileOutputStream outstream = null;
    public boolean flag = false;

    public Packer(String FolderName, String FileName) {
        try {
            new File(FileName);
            outstream = new FileOutputStream(FileName);

            TravelDirectory(FolderName);
        } catch (Exception obj) {
            System.out.println(obj);
        }
    }

    public void TravelDirectory(String path) {
        File directoryPath = new File(path);
        File arr[] = directoryPath.listFiles();
        JFrame frame = new JFrame();

        for (File filename : arr) {

            if (filename.getName().endsWith(".txt")) {
                flag = PackFile(filename.getAbsolutePath());
            }
        }

        if (flag == true) {
            JOptionPane.showMessageDialog(frame, "Files packed successfully");
        } else {
            JOptionPane.showMessageDialog(frame, "Files not packed successfully", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean PackFile(String FilePath) {
        byte Header[] = new byte[100];
        byte Buffer[] = new byte[1024];
        int length = 0;
        int data = 0;

        FileInputStream istream = null;

        File fobj = new File(FilePath);

        String temp = FilePath + " " + fobj.length();

        for (int i = temp.length(); i < 100; i++) {
            temp = temp + " ";
        }

        Header = temp.getBytes();
        try {
            istream = new FileInputStream(FilePath);

            outstream.write(Header, 0, Header.length);

            while ((length = istream.read(Buffer)) > 0) {
                outstream.write(Buffer, 0, length);
            }

            if (length != 0) {
                flag = true;
            }

            istream.close();
        } catch (Exception obj) {
        }

        return flag;

    }
}