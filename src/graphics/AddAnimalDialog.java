package graphics;
import animals.*;
import mobility.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddAnimalDialog extends JDialog {
    private JComboBox<String> animalTypeComboBox;
    private JTextField nameField, weightField, speedField, energyPerMeter, breedField, lengthField, altitudeOfFlightField, wingspanField, familyField, foodTypeField, areaOfLiving;
    private JComboBox<String> genderComboBox, toxicityComboBox, waterTypeComboBox, imageComboBox, compType, group;
    private JPanel dynamicPanel, groupaPanel;
    private JCheckBox castratedCheckBox;
    private int[] competition;
    private int[] tournament;

    public AddAnimalDialog(Frame owner, ZooPanel zooPanel, int[] competition, int[] tournamentType) {
        super(owner, "Add Animal", true);
        this.setSize(470, 450);
        this.setLocationRelativeTo(owner); // Open in the center of the screen

        this.competition = competition;
        this.tournament = tournamentType;

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Competition type Type:"), gbc);

        gbc.gridx = 1;
        compType = new JComboBox<>(new String[]{"Land", "Water", "Air"});
        mainPanel.add(compType, gbc);

        groupaPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(groupaPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Animal Type:"), gbc);

        gbc.gridx = 1;
        animalTypeComboBox = new JComboBox<>();
        mainPanel.add(animalTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Gender:"), gbc);

        gbc.gridx = 1;
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        mainPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Weight:"), gbc);

        gbc.gridx = 1;
        weightField = new JTextField(20);
        mainPanel.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Speed:"), gbc);

        gbc.gridx = 1;
        speedField = new JTextField(20);
        mainPanel.add(speedField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(new JLabel("Energy per meter:"), gbc);

        gbc.gridx = 1;
        energyPerMeter = new JTextField(20);
        mainPanel.add(energyPerMeter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        mainPanel.add(new JLabel("Image:"), gbc);

        gbc.gridx = 1;
        imageComboBox = new JComboBox<>();
        mainPanel.add(imageComboBox, gbc);

        dynamicPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        mainPanel.add(dynamicPanel, gbc);

        updateAnimalTypeComboBox();
        updateImageComboBox((String) Objects.requireNonNull(animalTypeComboBox.getSelectedItem()));
        updateDynamicFields();

        animalTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAnimalType = (String) animalTypeComboBox.getSelectedItem();
                if (selectedAnimalType != null) {
                    updateImageComboBox(selectedAnimalType);
                    updateDynamicFields();
                }
            }
        });

        compType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCompType = (String) compType.getSelectedItem();
                if (selectedCompType != null) {
                    updateAnimalTypeComboBox();
                    updateDynamicFields();
                    //updateImageComboBox();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(e -> {
            Animal animal = createAnimal(zooPanel);
            if (animal != null) {
                zooPanel.addAnimal(animal, (String)compType.getSelectedItem());
            }
            this.dispose();
        });

        cancelButton.addActionListener(e -> this.dispose());

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        updateAnimalTypeComboBox();
        updateDynamicFields();
        updateImageComboBox((String) Objects.requireNonNull(animalTypeComboBox.getSelectedItem()));
    }

    private Animal createAnimal(ZooPanel zooPanel) {
        String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();
        String competitionType = (String) compType.getSelectedItem();
        try {
            // checking if there is an existing competition and if there is, then also checking if there is an open space in the competition
            switch (competitionType) {
                case "Land" -> {
                    if (competition[0] == -1)
                        throw new RuntimeException("There's no Land competition available");
                    else
                        competition[0]++;
                }
                case "Water" -> {  // water competition - maximum of 4 participants
                    if (competition[1] == -1)
                        throw new RuntimeException("There's no water competition available");
                    else if (competition[1] == 4)
                        throw new RuntimeException("There's no open place in the competition");
                    else
                        competition[1]++;
                }
                case "Air" -> {  // air competition - maximum of 5 participant
                    if (competition[2] == -1)
                        throw new RuntimeException("There's no air competition available");
                    else if (competition[2] == 5)
                        throw new RuntimeException("There's no open space in the competition");
                    else
                        competition[2]++;
                }
            }

            // checking that the selected animal can participate in the selected competition type
            if (selectedAnimal.equals("Alligator") && competitionType.equals("Air"))
                throw new RuntimeException("Alligator can't be in an air competition");
            else if ((selectedAnimal.equals("Cat") || selectedAnimal.equals("Dog") || selectedAnimal.equals("Snake")) && !competitionType.equals("Land"))
                throw new RuntimeException(selectedAnimal + " can only be in a land competition");
            else if ((selectedAnimal.equals("Eagle") || selectedAnimal.equals("Pigeon")) && !competitionType.equals("Air"))
                throw new RuntimeException(selectedAnimal + " can only be in an air competition");
            else if ((selectedAnimal.equals("Dolphin") || selectedAnimal.equals("Whale")) && !competitionType.equals("Water"))
                throw new RuntimeException(selectedAnimal + " can only be in a water competition");

            String name = nameField.getText();
            Animal.Gender gender = Animal.Gender.valueOf((String) genderComboBox.getSelectedItem());
            double weight = Double.parseDouble(weightField.getText());
            double speed = Double.parseDouble(speedField.getText());
            int nrgPerMeter = Integer.parseInt(energyPerMeter.getText());
            String imageName = (String) imageComboBox.getSelectedItem();

            switch (selectedAnimal) {
                case "Cat":
                    boolean castrated = castratedCheckBox.isSelected(); // or true, depending on the UI input for castrated
                    return new Cat(name, gender, weight, speed, null, castrated, 100, nrgPerMeter, imageName);
                case "Dog":
                    return new Dog(name, gender, weight, speed, null, breedField.getText(), 100, nrgPerMeter, imageName);
                case "Eagle":
                    double altitude = Double.parseDouble(altitudeOfFlightField.getText());
                    return new Eagle(name, gender, weight, speed, null, new Point(zooPanel.getWidth() / 12, (zooPanel.getHeight() - 10) * 2 / 9 * (competition[2] - 1)), Double.parseDouble(wingspanField.getText()), altitude, 100, nrgPerMeter, imageName);
                case "Dolphin":
                    Dolphin.WaterType waterType = Dolphin.WaterType.valueOf((String) waterTypeComboBox.getSelectedItem());
                    return new Dolphin(name, gender, weight, speed, null, new Point(zooPanel.getWidth() / 12, zooPanel.getHeight() / 9 + ((zooPanel.getHeight() - 20) * 2 / 9 * (competition[1] - 1))), waterType, 100, nrgPerMeter, imageName);
                case "Alligator":
                    if(competitionType.equals("Land"))
                        return new Alligator(name, gender, weight, speed, null, new Point(0, 0), areaOfLiving.getText(), 100, nrgPerMeter, imageName, competitionType);
                    else
                        return new Alligator(name, gender, weight, speed, null, new Point(zooPanel.getWidth() / 12, 40 + ((zooPanel.getHeight() - 20) * 2 / 9 * (competition[1] - 1))), areaOfLiving.getText(), 100, nrgPerMeter, imageName, competitionType);
                case "Snake":
                    double length = Double.parseDouble(lengthField.getText());
                    Snake.Toxicity poisonous = Snake.Toxicity.valueOf((String) toxicityComboBox.getSelectedItem());
                    return new Snake(name, gender, weight, speed, null, poisonous, length, 100, nrgPerMeter, imageName);
                case "Pigeon":
                    return new Pigeon(name, gender, weight, speed, null, new Point(zooPanel.getWidth() / 12, (zooPanel.getHeight() - 10) * 2 / 9 * (competition[2] - 1)), Double.parseDouble(wingspanField.getText()), familyField.getText(), 100, nrgPerMeter, imageName);
                case "Whale":
                    return new Whale(name, gender, weight, speed, null, new Point(zooPanel.getWidth() / 12, zooPanel.getHeight() / 9 + ((zooPanel.getHeight() - 20) * 2 / 9 * (competition[1] - 1))), foodTypeField.getText(), 100, nrgPerMeter, imageName);
                default:
                    return null;
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    private void updateDynamicFields() {
        dynamicPanel.removeAll();
        String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        switch (selectedAnimal) {
            case "Cat":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Castrated:"), gbc);
                gbc.gridx = 1;
                castratedCheckBox = new JCheckBox();
                dynamicPanel.add(castratedCheckBox, gbc);
                break;
            case "Dog":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Breed:"), gbc);
                gbc.gridx = 1;
                breedField = new JTextField(20);
                dynamicPanel.add(breedField, gbc);
                break;
            case "Eagle":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Altitude of Flight:"), gbc);
                gbc.gridx = 1;
                altitudeOfFlightField = new JTextField(20);
                dynamicPanel.add(altitudeOfFlightField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Wingspan:"), gbc);
                gbc.gridx = 1;
                wingspanField = new JTextField(20);
                dynamicPanel.add(wingspanField, gbc);
                break;
            case "Dolphin":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Water Type:"), gbc);
                gbc.gridx = 1;
                waterTypeComboBox = new JComboBox<>(new String[]{"Sea", "Sweet"});
                dynamicPanel.add(waterTypeComboBox, gbc);
                break;
            case "Alligator":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Area of living:"), gbc);
                gbc.gridx = 1;
                areaOfLiving = new JTextField(20);
                dynamicPanel.add(areaOfLiving, gbc);
                break;
            case "Snake":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Poisonous:"), gbc);
                gbc.gridx = 1;
                toxicityComboBox = new JComboBox<>(new String[]{"Low", "Mid", "High"});
                dynamicPanel.add(toxicityComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Length:"), gbc);
                gbc.gridx = 1;
                lengthField = new JTextField(20);
                dynamicPanel.add(lengthField, gbc);
                break;
            case "Pigeon":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Family:"), gbc);
                gbc.gridx = 1;
                familyField = new JTextField(20);
                dynamicPanel.add(familyField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                dynamicPanel.add(new JLabel("Wingspan:"), gbc);
                gbc.gridx = 1;
                wingspanField = new JTextField(20);
                dynamicPanel.add(wingspanField, gbc);
                break;
            case "Whale":
                gbc.gridx = 0;
                gbc.gridy = 0;
                dynamicPanel.add(new JLabel("Food Type:"), gbc);
                gbc.gridx = 1;
                foodTypeField = new JTextField(20);
                dynamicPanel.add(foodTypeField, gbc);
                break;
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }

    private void updateAnimalTypeComboBox() {
        animalTypeComboBox.removeAllItems();
        groupaPanel.removeAll();
        String selectedComp = (String) compType.getSelectedItem();
        String[] animalTypes = new String[]{};

        if (selectedComp != null) {
            switch (selectedComp) {
                case "Land":
                    animalTypes = new String[]{"Cat", "Dog", "Snake", "Alligator"};
                    if(tournament[0] >= 1)
                        updateGroupsPanel(0, tournament);
                    break;
                case "Water":
                    animalTypes = new String[]{"Dolphin", "Whale", "Alligator"};
                    if(tournament[1] >= 1)
                        updateGroupsPanel(1, tournament);
                    break;
                case "Air":
                    animalTypes = new String[]{"Eagle", "Pigeon"};
                    if(tournament[2] >= 1)
                        updateGroupsPanel(2, tournament);
                    break;
                default:
                    animalTypes = new String[]{};
            }
        }

        for (String animalType : animalTypes) {
            animalTypeComboBox.addItem(animalType);
        }
        updateImageComboBox((String) animalTypeComboBox.getSelectedItem());

        //animalTypeComboBox.revalidate();
        //animalTypeComboBox.repaint();
    }

    private void updateGroupsPanel(int type, int[] tournament){
        groupaPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        groupaPanel.add(new JLabel("Choose group:"), gbc);
        gbc.gridx = 1;
        group = new JComboBox<>();
        group.addItem("new group");
        for (int i = 1; i <= tournament[0]; i++)
            group.addItem(String.valueOf(i));
        groupaPanel.add(group, gbc);
    }

    private void updateImageComboBox(String selectedAnimal) {
        imageComboBox.removeAllItems();
        //String selectedAnimal = (String) animalTypeComboBox.getSelectedItem();
        String[] images = new String[]{};

        switch (selectedAnimal) {
            case "Cat":
                images = new String[]{"cat1", "cat2"};
                break;
            case "Dog":
                images = new String[]{"dog1", "dog2", "dog3", "dog4","dog5", "dog6"};
                break;
            case "Eagle":
                images = new String[]{"eagle1", "eagle2", "eagle3"};
                break;
            case "Dolphin":
                images = new String[]{"dolphin1", "dolphin2", "dolphin3"};
                break;
            case "Alligator":
                images = new String[]{"alligator1", "alligator2", "alligator3"};
                break;
            case "Snake":
                images = new String[]{"snake1", "snake2", "snake3"};
                break;
            case "Pigeon":
                images = new String[]{"pigeon"};
                break;
            case "Whale":
                images = new String[]{"whale", "whale2"};
                break;
            default:
                images = new String[]{};
                break;
        }

        for (String image : images) {
            imageComboBox.addItem(image);
        }
        //imageComboBox.revalidate();
        //imageComboBox.repaint();
    }
}