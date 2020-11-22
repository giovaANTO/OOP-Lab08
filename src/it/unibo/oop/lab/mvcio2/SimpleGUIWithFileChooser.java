package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    private final Controller applicationController = new Controller();

    public SimpleGUIWithFileChooser() {
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        this.frame.setLocationByPlatform(true);
        /*
         * Set exit on close as default option on the window closing 
         */
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JTextArea txtArea = new JTextArea();
        mainPanel.add(txtArea, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                applicationController.writeOnFile(txtArea.getText());
            }
        });
        mainPanel.add(saveButton, BorderLayout.SOUTH);

        final JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        final JTextField fileField = new JTextField();
        fileField.setEditable(false);
        final JButton browseButton = new JButton("Browse...");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                final int result = fileChooser.showDialog(frame, "Ok");
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final String newPath = fileChooser.getSelectedFile().getPath();
                        applicationController.setAsCurrentFile(fileChooser.getSelectedFile().getPath());
                        fileField.setText(newPath);
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                default:
                    JOptionPane.showMessageDialog(frame, "Error loading file");
                    break;
                }
            }
        });
        filePanel.add(fileField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.LINE_END);
        mainPanel.add(filePanel, BorderLayout.NORTH);
        this.frame.add(mainPanel);
    }

    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
