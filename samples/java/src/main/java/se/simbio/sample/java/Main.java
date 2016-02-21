package se.simbio.sample.java;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import se.simbio.bitkointlin.Bitkointlin;
import se.simbio.bitkointlin.http.fuel.HttpClientImplFuel;
import se.simbio.bitkointlin.model.Info;

public class Main {

    /**
     * You need to setup your bitcoin environment and set below the correct data
     * more info: https://bitcoin.org/en/developer-guide
     */
    static final Bitkointlin bitkointlin = new Bitkointlin(
            "your_user",
            "your_password",
            "http://127.0.0.1:8332/",
            new HttpClientImplFuel());

    static JFrame frame = new JFrame("Bitkointlin");
    static JDialog dialog = new JDialog();

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        addButton("Get Balance", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBalance();
            }
        });
        addButton("Get BestBlockHash", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getBestBlockHash();
            }
        });
        addButton("Get Difficulty", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getDifficulty();
            }
        });
        addButton("Get GetInfo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getInfo();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void addButton(String method, ActionListener listener) {
        JButton button = new JButton(method);
        button.addActionListener(listener);
        frame.add(button);
    }

    static void showDialog(String method) {
        dialog = new JDialog(frame, method, false);
        dialog.setLocationRelativeTo(frame);
        dialog.add(new JTextField(method + " called, waiting..."));
        dialog.pack();
        dialog.setVisible(true);
    }

    static void showSuccess(String method, String message) {
        dialog.dispose();
        JOptionPane.showMessageDialog(frame, message, method + " success", JOptionPane.INFORMATION_MESSAGE);
    }

    static void showError(String method, String message) {
        dialog.dispose();
        JOptionPane.showMessageDialog(frame, message, method + " error", JOptionPane.ERROR_MESSAGE);
    }

    static void getBalance() {
        final String method = "getBalance";
        showDialog(method);
        bitkointlin.getBalance(
                new Function1<Double, Unit>() {
                    @Override
                    public Unit invoke(Double balance) {
                        showSuccess(method, "Balance: " + balance);
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        showError(method, error);
                        return null;
                    }
                });
    }

    static void getBestBlockHash() {
        final String method = "getBestBlockHash";
        showDialog(method);
        bitkointlin.getBestBlockHash(
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String bestBlockHash) {
                        showSuccess(method, "Best Block Hash: " + bestBlockHash);
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        showError(method, error);
                        return null;
                    }
                });
    }

    static void getDifficulty() {
        final String method = "getDifficulty";
        showDialog(method);
        bitkointlin.getDifficulty(
                new Function1<Double, Unit>() {
                    @Override
                    public Unit invoke(Double difficulty) {
                        showSuccess(method, "Difficulty: " + difficulty);
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        showError(method, error);
                        return null;
                    }
                });
    }

    static void getInfo() {
        final String method = "getInfo";
        bitkointlin.getInfo(
                new Function1<Info, Unit>() {
                    @Override
                    public Unit invoke(Info info) {
                        showSuccess(method, "Info: \n\t" +
                                "- Version: " + info.getVersion() + "\n\t" +
                                "- Protocol Version: " + info.getProtocolVersion() + "\n\t" +
                                "- Wallet Version: " + info.getWalletVersion() + "\n\t" +
                                "- Balance: " + info.getBalance() + "\n\t" +
                                "- Blocks: " + info.getBlocks() + "\n\t" +
                                "- Time Offset: " + info.getTimeOffset() + "\n\t" +
                                "- Connections: " + info.getConnections() + "\n\t" +
                                "- Proxy: " + info.getProxy() + "\n\t" +
                                "- Difficulty: " + info.getDifficulty() + "\n\t" +
                                "- Test Net: " + info.getTestNet() + "\n\t" +
                                "- Key Pool Oldest: " + info.getKeyPoolOldest() + "\n\t" +
                                "- Key Pool Size: " + info.getKeyPoolSize() + "\n\t" +
                                "- Pay Tx Fee: " + info.getPayTxFee() + "\n\t" +
                                "- Relay Fee: " + info.getRelayFee() + "\n\t" +
                                "- Errors: " + info.getErrors() + "\n\t");
                        return null;
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(String error) {
                        showError(method, error);
                        return null;
                    }
                });
    }

}
