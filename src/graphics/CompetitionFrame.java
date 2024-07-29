package graphics;
import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionFrame extends JFrame {
    private List<Animal> animals;
    private int[] compatition;
    private final JButton addC;
    private final JButton addA;
    private final JButton clear;
    private final JButton eat;
    private final JButton info;
    private final JButton exit;
    private BufferedImage image;
    private CompetitionFrame frame;
    private ZooPanel zooPanel;

    public CompetitionFrame() throws IOException {
        super("Competition");
        this.frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        animals = new ArrayList<>();
        compatition = new int[3];
        Arrays.fill(compatition, -1);

        zooPanel = new ZooPanel();
        this.add(zooPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem helpItem = new JMenuItem("Help");

        fileMenu.add(exitItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);

        addC = new JButton("Add Competition");
        addA = new JButton("Add Animal");
        clear = new JButton("Clear");
        eat = new JButton("Eat");
        info = new JButton("Info");
        exit = new JButton("Exit");


        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        final JPanel buttonsPanel = new JPanel();
        GroupLayout layout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(layout);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CompetitionFrame.this, "Home Work 2\nGUI", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        zooPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // Recalculate the positions of the animals on the board
                zooPanel.updateAnimalPositions();
            }
        });

        addC.addActionListener(e -> {
            AddCompetitionDialog compFrame = new AddCompetitionDialog(compatition, this);
        });
        addA.addActionListener(e -> {
            AddAnimalDialog newAnimal = new AddAnimalDialog(CompetitionFrame.this, zooPanel, compatition);
        });
        clear.addActionListener(e -> {
            zooPanel.clearAnimals();
            Arrays.fill(compatition, -1);
        });
        eat.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(zooPanel, "Enter energy amount:", "feed", JOptionPane.INFORMATION_MESSAGE);
            if (input != null) {
                try {
                    int energy = Integer.parseInt(input);
                    zooPanel.feedAnimals(energy);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CompetitionFrame.this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zooPanel.showInfo();
            }
        });
        exit.addActionListener(e -> {
            System.exit(0);
        });

        // Set up GroupLayout properties
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Arrange components horizontally and vertically in the buttons panel
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(addC)
                        .addComponent(addA)
                        .addComponent(clear)
                        .addComponent(eat)
                        .addComponent(info)
                        .addComponent(exit)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addC)
                        .addComponent(addA)
                        .addComponent(clear)
                        .addComponent(eat)
                        .addComponent(info)
                        .addComponent(exit)
        );

        this.add(buttonsPanel);
        this.pack();
        this.setSize(800, 500);
        setLocation(100, 100);
        this.setVisible(true);
    }

    public static void main(String[] arg) throws IOException {
        CompetitionFrame init = new CompetitionFrame();
        init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
