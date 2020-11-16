package Screens;


import Application.Constants;
import Application.Dimensions;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JLabel;
import java.awt.Dimension;

public class HomeScreen {
    public HomeScreen() {
        initView();
    }

    private void initView() {
        JLabel headerLabel = new JLabel();
        JLabel helperText = new JLabel();
        Dimension dimension;
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setText(Constants.homeScreenTitle);
        headerLabel.setForeground(Color.BLUE);
        headerLabel.setFont(new Font(Constants.homeScreenTitleFont, Font.BOLD, Constants.homeScreenTitleFontSize));

        helperText.setText(Constants.helperText);
        helperText.setForeground(Color.BLACK);

        dimension = headerLabel.getPreferredSize();
        headerLabel.setBounds(Dimensions.headerLabelBoundX, Dimensions.headerLabelBoundY, dimension.width, Dimensions.headerLabelHeight);

        dimension = helperText.getPreferredSize();
        helperText.setBounds(Dimensions.helperLabelBoundX, Dimensions.helperLabelBoundY, dimension.width, Dimensions.helperLabelHeight);

        JFrame windowTitle = new JFrame(Constants.windowScreenTitle);
        //windowTitle.setLocationRelativeTo(null);
        windowTitle.setLocation(dimension.width / 2 - windowTitle.getSize().width / 2, dimension.height / 2 - windowTitle.getSize().height / 2);
        JButton btnPackFiles = new JButton(Constants.buttonPackTitle);
        btnPackFiles.setBounds(Dimensions.packButtonBoundX, Dimensions.packButtonBoundY, Dimensions.packButtonWidth, Dimensions.packButtonHeight);
        JButton btnUnpackFiles = new JButton(Constants.buttonUnpackTitle);
        btnUnpackFiles.setBounds(Dimensions.unpackButtonBoundX, Dimensions.unpackButtonBoundY, Dimensions.unpackButtonWidth, Dimensions.unpackButtonHeight);

        windowTitle.add(headerLabel);
        windowTitle.add(helperText);
        windowTitle.add(btnPackFiles);
        windowTitle.add(btnUnpackFiles);
        windowTitle.setSize(Dimensions.windowTitleWidth, Dimensions.windowTitleHeight);
        windowTitle.setLayout(null);
        windowTitle.setVisible(true);
        windowTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPackFiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                new PackFront();
                windowTitle.setVisible(false);
            }
        });

        btnUnpackFiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                new UnpackFront();
                windowTitle.setVisible(false);
            }
        });

    }
}