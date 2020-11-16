package Screens;

import Application.Constants;
import Application.Dimensions;
import Logic.Packer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PackFront {
    public PackFront() {
        initView();
    }

    private void initView() {
        JFrame windowTitle = new JFrame(Constants.packerWindowTitle);
        JLabel headerLabel = new JLabel();
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setText(Constants.homeScreenTitle);
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setFont(new Font(Constants.homeScreenTitleFont, Font.BOLD, Constants.homeScreenTitleFontSize));
        headerLabel.setBounds(Dimensions.headerLabelBoundX, Dimensions.headerLabelBoundY, headerLabel.getPreferredSize().width, Dimensions.headerLabelHeight);

        JButton btnSubmit = new JButton(Constants.buttonSubmit);
        btnSubmit.setBounds(Dimensions.packerSubmitButtonBoundX, Dimensions.packerSubmitButtonBoundY, Dimensions.packerSubmitButtonWidth, Dimensions.packerSubmitButtonHeight);
        JButton btnBack = new JButton(Constants.buttonBack);
        btnBack.setBounds(Dimensions.packerBackButtonBoundX, Dimensions.packerBackButtonBoundY, Dimensions.packerBackButtonWidth, Dimensions.packerBackButtonHeight);


        JLabel directoryInputLabel = new JLabel(Constants.packerInputLabel);
        directoryInputLabel.setBounds(Dimensions.packerInputLabelBoundX, Dimensions.packerInputLabelBoundY, Dimensions.packerInputLabelWidth, Dimensions.packerInputLabelHeight);

        JTextField directoryField = new JTextField();
        directoryField.setBounds(Dimensions.inputTextFieldBoundX, Dimensions.inputTextFieldBoundY, Dimensions.inputTextFieldWidth, Dimensions.inputTextFieldHeight);
        JLabel newFileName = new JLabel(Constants.packerNewFileName);
        newFileName.setBounds(Dimensions.packerFileNameBoundX, Dimensions.packerFileNameBoundY, Dimensions.packerFileNameWidth, Dimensions.packerFileNameHeight);

        JTextField tfNewFile = new JTextField();
        tfNewFile.setBounds(Dimensions.fileNameTextFieldBoundX, Dimensions.fileNameTextFieldBoundY, Dimensions.fileNameTextFieldWidth, Dimensions.fileNameTextFieldHeight);

        windowTitle.add(directoryInputLabel);
        windowTitle.add(btnSubmit);
        windowTitle.add(directoryField);
        windowTitle.add(newFileName);
        windowTitle.add(tfNewFile);
        windowTitle.add(btnBack);
        windowTitle.add(headerLabel);

        windowTitle.setSize(Dimensions.windowTitleWidth, Dimensions.windowTitleHeight);
        windowTitle.setLayout(null);
        windowTitle.setVisible(true);
        windowTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                String fileName = tfNewFile.getText();
                if (!isFileExists(fileName)) {
                    new Packer(directoryField.getText(), fileName);
                    new HomeScreen();
                    windowTitle.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "File name already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                new HomeScreen();
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