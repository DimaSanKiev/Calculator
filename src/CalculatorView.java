import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    // Declaring fields
    private JTextField display;
    private static final Font BOLD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

    // Variables for calculator's state
    private boolean startNumber = true;                         // expecting number, not operation
    private String prevOperation = "=";                         // previous operation
    private CalculatorEngine engine = new CalculatorEngine();   // reference to CalculatorEngine

    public CalculatorView() {

        // Window settings
        Dimension size = new Dimension(320, 300);
        setPreferredSize(size);
        setResizable(false);

        // Display field
        display = new JTextField("0", 18);
        display.setFont(BOLD_FONT);
        display.setHorizontalAlignment(JTextField.RIGHT);

        // Operations panel with "+", "-", "*" and "/" buttons
        ActionListener operationListener = new OperationListener();
        JPanel operationPanel = new JPanel();
        String[] operationPanelNames = new String[]{"+", "-", "*", "/"};
        operationPanel.setLayout(new GridLayout(2, 2, 2, 2));
        for (String operationPanelName: operationPanelNames) {
            JButton button = new JButton(operationPanelName);
            operationPanel.add(button);
            button.addActionListener(operationListener);
        }

        // Operations panel with "C" and "=" buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 1, 2, 2));
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(new ClearKeyListener());
        controlPanel.add(clearButton);
        JButton equalButton = new JButton("=");
        equalButton.addActionListener(operationListener);
        controlPanel.add(equalButton);


        // Buttons panel
        JPanel buttonPanel = new JPanel();
        ActionListener numberListener = new NumberKeyListener();
        String[] buttonPanelNames = new String[]{"7", "8", "9", "4", "5", "6", "1", "2", "3", " ", "0", " "};
        buttonPanel.setLayout(new GridLayout(4, 3, 2, 2));
        for (String buttonPanelName : buttonPanelNames) {
            JButton button = new JButton(buttonPanelName);
            if (buttonPanelName.equals(" ")) {
                button.setEnabled(false);
            }
            button.addActionListener(numberListener);
            buttonPanel.add(button);
        }

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(display, BorderLayout.NORTH);
        mainPanel.add(operationPanel, BorderLayout.EAST);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Window build
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private void actionClear() {
        startNumber = true;
        display.setText("0");
        prevOperation = "=";
        engine.equal("0");
    }

    class OperationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (startNumber) {
                actionClear();
                display.setText("ERROR - wrong operation");
            } else {
                startNumber = true;
                try {
                    String displayText = display.getText();
                    switch (prevOperation) {
                        case "=":
                            engine.equal(displayText);
                            break;
                        case "+":
                            engine.add(displayText);
                            break;
                        case "-":
                            engine.subtract(displayText);
                            break;
                        case "/":
                            engine.divide(displayText);
                            break;
                        case "*":
                            engine.multiply(displayText);
                            break;
                    }
                    display.setText("" + engine.getTotalString());
                } catch (NumberFormatException ex) {
                    actionClear();
                }
                prevOperation = e.getActionCommand();
            }
        }
    }

    class NumberKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String digit = e.getActionCommand();
            if (startNumber) {
                display.setText(digit);
                startNumber = false;
            } else {
                display.setText(display.getText() + digit);
            }
        }
    }

    class ClearKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            actionClear();
        }
    }

}
