package graphics;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import animals.*;
import mobility.Point;

public class ZooPanel extends JPanel {
    private ArrayList<Animal> curr_animals;
    private ArrayList<Animal> del_animals;
    private Image backgroundImage;

    public ZooPanel() {
        this.curr_animals = new ArrayList<>();
        this.del_animals = new ArrayList<>();
        this.setLayout(new BorderLayout());
        loadBackgroundImage();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/competitionBackground.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAnimalPositions() {
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int[] competition = new int[3];
        Arrays.fill(competition, 0);

        for (Animal animal : curr_animals) {
            if (animal instanceof Alligator) {
                if (((Alligator) animal).getCompType().equals("Land"))
                    animal.setPosition(new Point(0, 0));
                else {
                    animal.setPosition(new Point(panelWidth / 12, panelHeight / 9 + ((panelHeight - 20) * 2 / 9 * (competition[1]))));
                    competition[1]++;
                }
            }
            else if (animal instanceof TerrestrialAnimal)
                animal.setPosition(new Point(0, 0));
            else if (animal instanceof WaterAnimal) {
                animal.setPosition(new Point(panelWidth / 12, panelHeight / 9 + ((panelHeight - 20) * 2 / 9 * (competition[1]))));
                competition[1]++;
            }
            else if (animal instanceof AirAnimal) {
                animal.setPosition(new Point(panelWidth / 12, (panelWidth - 10) * 2 / 9 * competition[2]));
                competition[2]++;
            }
        }
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        for (Animal animal : curr_animals) {
            animal.drawObject(g);
        }
    }

    public void addAnimal(Animal animal) {
        curr_animals.add(animal);
        repaint();
    }

    public void clearAnimals() {
        del_animals.addAll(curr_animals);
        curr_animals.clear();
        repaint();
    }

    public void feedAnimals(int energy) {
        for (Animal animal : curr_animals) {
            animal.eat(energy);
        }
    }

    public void showInfo() {
        String[] columnNames = {"Animal", "Competition", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
        Object[][] data = new Object[curr_animals.size() + del_animals.size()][7];

        if (!curr_animals.isEmpty())
            insertData(data, curr_animals);
        if (!del_animals.isEmpty())
            insertData(data, del_animals);
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); // Animal
        columnModel.getColumn(1).setPreferredWidth(100); // Category
        columnModel.getColumn(2).setPreferredWidth(100); // Type
        columnModel.getColumn(3).setPreferredWidth(100); // Speed
        columnModel.getColumn(4).setPreferredWidth(150); // Energy Amount
        columnModel.getColumn(5).setPreferredWidth(150); // Distance
        columnModel.getColumn(6).setPreferredWidth(200); // Energy Consumption

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.GRAY);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Animal Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void insertData(Object[][] data, ArrayList<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            data[i][0] = animal.getName(); // Assuming getName() exists in Animal
            data[i][1] = getCategory(animal); // Assuming getCategory() returns Air, Water, or Terrestrial
            data[i][2] = animal.getClass().getSimpleName(); // Assuming the type is the class name
            data[i][3] = animal.getSpeed(); // Assuming getSpeed() exists in Animal
            data[i][4] = animal.getEnergyAmount(); // Assuming getEnergyAmount() exists in Animal
            data[i][5] = animal.getTotalDistance(); // Using getTotalDistance() from Mobile
            data[i][6] = animal.getEnergyConsumption(); // Assuming getEnergyConsumption() exists in Animal
        }
    }

    private String getCategory(Animal animal) {
        if (animal instanceof Alligator)
            return "Terres/Water";
        if (animal instanceof AirAnimal) {
            return "Air";
        } else if (animal instanceof WaterAnimal) {
            return "Water";
        } else {
            return "Terrestrial";
        }
    }
}
