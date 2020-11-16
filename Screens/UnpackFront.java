package Screens;

import Application.Constants;
import Application.Dimensions;
import Logic.Packer;
import Logic.Unpacker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UnpackFront {
    public UnpackFront() {
        JFrame windowTitle = new JFrame(Constants.unpackerWindowTitle);
        JLabel headerLabel = new JLabel();
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setText(Constants.homeScreenTitle);
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setFont(new Font(Constants.homeScreenTitleFont, Font.BOLD, Constants.homeScreenTitleFontSize));
        headerLabel.setBounds(Dimensions.headerLabelBoundX, Dimensions.headerLabelBoundY, headerLabel.getPreferredSize().width, Dimensions.headerLabelHeight);

        JButton btnSubmit = new JButton(Constants.buttonSubmit);
        btnSubmit.setBounds(Dimensions.unpackerSubmitButtonBoundX, Dimensions.unpackerSubmitButtonBoundY, Dimensions.unpackerSubmitButtonWidth, Dimensions.unpackerSubmitButtonHeight);
        JButton btnBack = new JButton(Constants.buttonBack);
        btnBack.setBounds(Dimensions.unpackerBackButtonBoundX, Dimensions.unpackerBackButtonBoundY, Dimensions.unpackerBackButtonWidth, Dimensions.unpackerBackButtonHeight);

        JLabel packedFileName = new JLabel(Constants.unpackerInputLabel);
        packedFileName.setBounds(Dimensions.unpackerInputLabelBoundX, Dimensions.unpackerInputLabelBoundY, Dimensions.unpackerInputLabelWidth, Dimensions.unpackerInputLabelHeight);

        JTextField tfPackedFile = new JTextField();
        tfPackedFile.setBounds(Dimensions.unpackerFileNameTextFieldBoundX, Dimensions.unpackerFileNameTextFieldBoundY, Dimensions.unpackerFileNameTextFieldWidth, Dimensions.unpackerFileNameTextFieldHeight);

        windowTitle.add(headerLabel);
        windowTitle.add(packedFileName);
        windowTitle.add(btnSubmit);
        windowTitle.add(tfPackedFile);
        windowTitle.add(btnBack);

        windowTitle.setSize(Dimensions.windowTitleWidth, Dimensions.windowTitleHeight);
        windowTitle.setLayout(null);
        windowTitle.setVisible(true);
        windowTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                String fileName = tfPackedFile.getText();
                if (isFileExists(fileName) == true) {
                    new Unpacker(fileName);
                    new HomeScreen();
                    windowTitle.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "No such file in the directory", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                HomeScreen obj2 = new HomeScreen();
                windowTitle.setVisible(false);
            }
        });
    }

    public boolean isFileExists(String fileName) {
        String path = System.getProperty("user.dir");
        File directory = new File(path);
        File[] fileArray = directory.listFiles();
        String[] fileNameArray = new String[fileArray.length];

        for (int i = 0; i < fileArray.length; i++) {
            fileNameArray[i] = fileArray[i].getName();
        }
        for (String f : fileNameArray) {
            if (fileName.equals(f)) {
                return true;
            }
        }
        return false;
    }
}