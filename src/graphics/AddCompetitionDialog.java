package graphics;
import javax.swing.*;
import java.awt.*;

public class AddCompetitionDialog extends JDialog {
    private JComboBox<String> type;
    private JButton done;
    private JButton cancel;

    public AddCompetitionDialog(int[] competitions, JFrame owner){
        super(owner,"Add competition", true);
        this.setLocationRelativeTo(owner);

        type = new JComboBox<>(new String[]{"land", "water", "air"});
        done = new JButton("Done");
        cancel = new JButton("Cancel");

        this.setLayout(new GridLayout(2, 2));
        done.addActionListener(e -> {
            String chosen = String.valueOf(type.getSelectedItem());
            try {
                if (chosen.equalsIgnoreCase("land")) {
                    if (competitions[0] == -1) {
                        competitions[0] = 0;
                        dispose();
                    }
                    else
                        throw new IllegalArgumentException("land");
                } else if (chosen.equalsIgnoreCase("water")) {
                    if (competitions[1] == -1) {
                        competitions[1] = 0;
                        dispose();
                    }
                    else
                        throw new IllegalArgumentException("water");
                } else {
                    if (competitions[2] == -1) {
                        competitions[2] = 0;
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

        this.add(new JLabel("Choose type"));
        this.add(type);
        this.add(done);
        this.add(cancel);
        this.setSize(350, 200);
        this.setVisible(true);
    }
}
