package graphics;
import javax.swing.*;
import java.awt.*;

public class AddCompetitionDialog extends JDialog {
    private JComboBox<String> tournament;
    private JComboBox<String> type;
    private JButton done;
    private JButton cancel;

    public AddCompetitionDialog(int[] competitions, int[] tournamentType, JFrame owner){
        super(owner,"Add competition", true);
        this.setLocationRelativeTo(owner);

        tournament = new JComboBox<>(new String[]{"courier", "regular"});
        type = new JComboBox<>(new String[]{"land", "water", "air"});
        done = new JButton("Done");
        cancel = new JButton("Cancel");

        this.setLayout(new GridLayout(3, 2));
        done.addActionListener(e -> {
            String chosenTournament = String.valueOf(tournament.getSelectedItem());
            int temp = chosenTournament.equalsIgnoreCase("courier") ? 1 : 0; // if the tournament type is courier the element is 1 or higher, if is regular the element is 0
            String chosenRace = String.valueOf(type.getSelectedItem());
            try {
                if (chosenRace.equalsIgnoreCase("land")) {
                    if (competitions[0] == -1) {
                        competitions[0] = 0;
                        tournamentType[0] = temp;
                        dispose();
                    }
                    else
                        throw new IllegalArgumentException("land");
                } else if (chosenRace.equalsIgnoreCase("water")) {
                    if (competitions[1] == -1) {
                        competitions[1] = 0;
                        tournamentType[1] = temp;
                        dispose();
                    }
                    else
                        throw new IllegalArgumentException("water");
                } else {
                    if (competitions[2] == -1) {
                        competitions[2] = 0;
                        tournamentType[2] = temp;
                        dispose();
                    }
                    else
                        throw new IllegalArgumentException("air");
                }
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(this, "There is already existing " + ex.getMessage() + " competition.", "Existing competition", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancel.addActionListener(e -> {
            dispose();
        });

        this.add(new JLabel("Choose tournament type"));
        this.add(tournament);
        this.add(new JLabel("Choose landscape type"));
        this.add(type);
        this.add(done);
        this.add(cancel);
        this.setSize(350, 200);
        this.setVisible(true);
    }
}
