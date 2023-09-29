import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double accountBalance;

    public BankAccount(double initialBalance) {
        accountBalance = initialBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void depositMoney(double amount) {
        accountBalance += amount;
    }

    public boolean withdrawMoney(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            return true;
        }
        return false;
    }
}

public class ATMApp extends JFrame {
    private BankAccount userBankAccount;

    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;

    public ATMApp(BankAccount account) {
        this.userBankAccount = account;

        setTitle("ATM Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + userBankAccount.getAccountBalance());
        amountField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");

        add(balanceLabel);
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(checkBalanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (userBankAccount.withdrawMoney(amount)) {
                    balanceLabel.setText("Balance: $" + userBankAccount.getAccountBalance());
                    JOptionPane.showMessageDialog(null, "Withdrawal successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                }
                amountField.setText("");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                userBankAccount.depositMoney(amount);
                balanceLabel.setText("Balance: $" + userBankAccount.getAccountBalance());
                JOptionPane.showMessageDialog(null, "Deposit successful.");
                amountField.setText("");
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Balance: $" + userBankAccount.getAccountBalance());
            }
        });
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMApp(userAccount).setVisible(true);
            }
        });
    }
}
