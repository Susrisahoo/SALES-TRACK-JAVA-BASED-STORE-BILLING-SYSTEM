import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ex extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JScrollPane scrollPane;

    public ex() {
        setTitle("Billing System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewItem();
            }
        });

        addNewItem(); // Initial item

        add(scrollPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    private void addNewItem() {
        JPanel newItemPanel = new JPanel();
        newItemPanel.setLayout(new FlowLayout());

        JLabel itemLabel = new JLabel("Item:");
        JTextField itemTextField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField(5);

        newItemPanel.add(itemLabel);
        newItemPanel.add(itemTextField);
        newItemPanel.add(quantityLabel);
        newItemPanel.add(quantityTextField);

        mainPanel.add(newItemPanel);

        // Repaint and revalidate to reflect the changes
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ex().setVisible(true);
            }
        });
    }
}
