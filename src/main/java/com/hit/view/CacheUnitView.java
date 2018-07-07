package com.hit.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Observable;


public class CacheUnitView extends Observable implements View {
    private final String statisticsRequest = "{\"headers\":{\"action\":\"STATISTICS\"}}";

    private JFrame mainFrame;
    private JPanel buttonsPanel;
    private JButton loadRequestButton;
    private JButton showStatisticsButton;
    private JPanel statisticsContentPanel;
    private JTextArea statisticsContentTextArea;
    private JFileChooser fileChooser;
    private JScrollPane statisticsContentScroll;

    /**
     * Start the view component
     */
    @Override
    public void start() {
        initializeGUI();
        activateGUI();
    }

    /**
     * Update the UI Data accordingly
     * @param t - The service response
     * @param <T> - Type of t
     */
    @Override
    public <T> void updateUIData(T t) {
        if (t != null) {
            String statisticsTextContent = t.toString();
            statisticsTextContent = statisticsTextContent.replaceAll(".EndLine.", "\n");
            statisticsContentTextArea.setText(statisticsTextContent);
        }
    }

    /**
     * Initialize the GUI Components
     */
    private void initializeGUI() {
        initializeButtonsPanel();
        initializeStatisticsContentArea();
        initializeMainFrame();
    }

    /**
     * Activate the GUI
     */
    private void activateGUI() {
        mainFrame.setVisible(true);
    }

    /**
     * Initialize the main frame and it's content
     */
    private void initializeMainFrame() {
        mainFrame = new JFrame("CacheUnitGUI");

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(450, 300);
        mainFrame.getContentPane().add(BorderLayout.NORTH, buttonsPanel);
        mainFrame.getContentPane().add(BorderLayout.CENTER, statisticsContentPanel);
    }

    /**
     * Initialize Buttons and ButtonsPanel
     */
    private void initializeButtonsPanel() {
        buttonsPanel = new JPanel();

        //init loadRequestButton
        loadRequestButton = new JButton("Load Request");

        loadRequestButton.setIcon(new ImageIcon("src/main/resources/folder_icon.ico"));
        loadRequestButton.setPreferredSize(new Dimension(200, 50));
        loadRequestButton.addActionListener(e -> {
            fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    String request = new String(Files.readAllBytes(fileChooser.getSelectedFile().toPath()));
                    request = request.replaceAll(System.lineSeparator(), "");
                    setChanged();
                    notifyObservers(request);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    updateUIData("ERROR - unable to read from file.\nPlease select a text file contains a json format!");
                }
            }
        });

        //init showStatisticsButton
        showStatisticsButton = new JButton("Get Statistics");

        showStatisticsButton.setIcon(new ImageIcon("src/main/resources/statistics_icon.ico"));
        showStatisticsButton.setPreferredSize(new Dimension(200, 50));
        showStatisticsButton.addActionListener(e -> sendStatisticsRequest());

        //add the buttons to the panel
        buttonsPanel.add(loadRequestButton);
        buttonsPanel.add(showStatisticsButton);
    }

    /**
     * Initialize the statistics content JtextArea and JScrollPane
     */
    private void initializeStatisticsContentArea() {
    	//init statisticsContentPanel
    	statisticsContentPanel = new JPanel();
    	statisticsContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    	statisticsContentPanel.setLayout(new BorderLayout(0, 0));
        
    	//init statisticsContentTextArea
        statisticsContentTextArea = new JTextArea();

        statisticsContentTextArea.setLineWrap(true);
        statisticsContentTextArea.setEditable(false);
        statisticsContentTextArea.setSize(new Dimension(400, 400));
        statisticsContentPanel.add(statisticsContentTextArea);
        
        //init the textArea scroll pane
        statisticsContentScroll = new JScrollPane (statisticsContentTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        statisticsContentPanel.add(statisticsContentScroll);
    }

    /**
     * Send a request to get the statistics from the service
     */
    private void sendStatisticsRequest() {
        setChanged();
        notifyObservers(statisticsRequest);
    }
}
