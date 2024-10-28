import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI extends JFrame {
    private Transaction transferObject;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;
    private JLabel showAllData, statusLabel;
    private JButton showAllButton, depositButton, withdrawButton, transferButton, balanceButton;
    private JTextField accDeposit, accWithdraw, acc1Transfer, acc2Transfer, depositInput, withdrawInput, transferAmount,
            accBalance;

    public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setLayout(new BorderLayout());

        globalAccounts = accounts;
        sbAllData = new StringBuilder();

        showAllData = new JLabel();
        statusLabel = new JLabel("Status: Ready");

        showAllButton = new JButton("Show All");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        balanceButton = new JButton("Check Balance");

        accDeposit = new JTextField("Account Number for Deposit", 15);
        accWithdraw = new JTextField("Account Number for Withdraw", 15);
        acc1Transfer = new JTextField("From Account Number", 15);
        acc2Transfer = new JTextField("To Account Number", 15);
        depositInput = new JTextField("Amount to Deposit", 15);
        withdrawInput = new JTextField("Amount to Withdraw", 15);
        transferAmount = new JTextField("Amount to Transfer", 15);
        accBalance = new JTextField("Account Number for Balance", 15);

        addPlaceholder(accDeposit, "Account Number for Deposit");
        addPlaceholder(accWithdraw, "Account Number for Withdraw");
        addPlaceholder(acc1Transfer, "From Account Number");
        addPlaceholder(acc2Transfer, "To Account Number");
        addPlaceholder(depositInput, "Amount to Deposit");
        addPlaceholder(withdrawInput, "Amount to Withdraw");
        addPlaceholder(transferAmount, "Amount to Transfer");
        addPlaceholder(accBalance, "Account Number for Balance");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Banking System v1.0"));
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(showAllButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(depositButton, gbc);

        gbc.gridx = 1;
        panel.add(accDeposit, gbc);

        gbc.gridx = 2;
        panel.add(depositInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(withdrawButton, gbc);

        gbc.gridx = 1;
        panel.add(accWithdraw, gbc);

        gbc.gridx = 2;
        panel.add(withdrawInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(transferButton, gbc);

        gbc.gridx = 1;
        panel.add(acc1Transfer, gbc);

        gbc.gridx = 2;
        panel.add(acc2Transfer, gbc);

        gbc.gridx = 3;
        panel.add(transferAmount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(balanceButton, gbc);
        gbc.gridx = 1;
        panel.add(accBalance, gbc);

        add(new JScrollPane(showAllData), BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.SOUTH);

        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
        balanceButton.addActionListener(handler);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setVisible(true);

        updateAllData();
    }

    private void updateAllData() {
        throw new UnsupportedOperationException("Unimplemented method 'updateAllData'");
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showAllButton) {
                updateAllData();
                statusLabel.setText("Status: Showing all accounts");
            } else if (e.getSource() == depositButton) {
                try {
                    int accountNumber = Integer.parseInt(accDeposit.getText());
                    int amount = Integer.parseInt(depositInput.getText());
                    for (Account account : globalAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            account.deposit(amount);
                            updateAllData();
                            statusLabel.setText("Status: Deposit successful");
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Status: Invalid input for deposit");
                }
            } else if (e.getSource() == withdrawButton) {
                try {
                    int accountNumber = Integer.parseInt(accWithdraw.getText());
                    int amount = Integer.parseInt(withdrawInput.getText());
                    for (Account account : globalAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            account.withdraw(amount);
                            updateAllData();
                            statusLabel.setText("Status: Withdrawal successful");
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Status: Invalid input for withdrawal");
                }
            } else if (e.getSource() == transferButton) {
                try {
                    int acc1 = Integer.parseInt(acc1Transfer.getText());
                    int acc2 = Integer.parseInt(acc2Transfer.getText());
                    int amount = Integer.parseInt(transferAmount.getText());
                    Account fromAccount = null, toAccount = null;
                    for (Account account : globalAccounts) {
                        if (account.getAccountNumber() == acc1) {
                            fromAccount = account;
                        } else if (account.getAccountNumber() == acc2) {
                            toAccount = account;
                        }
                    }
                    if (fromAccount != null && toAccount != null) {
                        transferObject = new Transaction();
                        transferObject.transfer(fromAccount, toAccount, amount);
                        updateAllData();
                        statusLabel.setText("Status: Transfer successful");
                    } else {
                        statusLabel.setText("Status: Invalid account numbers for transfer");
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Status: Invalid input for transfer");
                }
            } else if (e.getSource() == balanceButton) {
                try {
                    int accountNumber = Integer.parseInt(accBalance.getText());
                    for (Account account : globalAccounts) {
                        if (account.getAccountNumber() == accountNumber) {
                            statusLabel.setText(
                                    "Status: Balance for account " + accountNumber + " is " + account.getBalance());
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("Status: Invalid input for balance check");
                }
            }
        }

        private void updateAllData() {
            sbAllData.setLength(0);
            for (Account account : globalAccounts) {
                sbAllData.append("Account Number: ").append(account.getAccountNumber())
                        .append(", First Name: ").append(account.getFirstName())
                        .append(", Last Name: ").append(account.getLastName())
                        .append(", Balance: ").append(account.getBalance())
                        .append("\n");
            }
            showAllData.setText("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
        }
    }

    public static void main(String[] args) {
        String file = "/Users/tokhirjonobidov/Desktop/2428279/Accounts.csv";
        ReadAccounts readAccounts = new ReadAccounts(file);

        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountList = readAccounts.getAccounts();
        LinkedList<Integer> balanceList = readAccounts.getBalances();

        LinkedList<Account> accounts = new LinkedList<>();
        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(lastNames.get(i), firstNames.get(i), accountList.get(i), balanceList.get(i)));
        }

        new GUI(accounts);
    }
}
