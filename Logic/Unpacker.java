package Logic;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Unpacker {
    public FileOutputStream outstream = null;
    public boolean flag = false;
    JFrame frame = new JFrame();

    public Unpacker(String src) {

        flag = unpackFile(src);
        if (flag == true) {
            JOptionPane.showMessageDialog(frame, "Files Unpacked successfully");
        } else {
            JOptionPane.showMessageDialog(frame, "Files not Unpacked successfully");
        }
    }

    public boolean unpackFile(String FilePath) {
        try {
            FileInputStream instream = new FileInputStream(FilePath);

            byte Header[] = new byte[100];
            int ret = 0;

            while ((instream.read(Header, 0, 100)) > 0) {
                String str = new String(Header);

                String ext = str.substring(str.lastIndexOf("/"));

                ext = ext.substring(1);

                String words[] = ext.split("\\s");
                String name = words[0];
                int size = Integer.parseInt(words[1]);

                byte arr[] = new byte[size];
                ret = instream.read(arr, 0, size);

                if (ret != 0) {

                    FileOutputStream fout = new FileOutputStream(name);
                    fout.write(arr, 0, size);
                    flag = true;
                }
            }
        } catch (Exception obj) {
        }
        return flag;
    }
}