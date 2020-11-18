package swingy.view.player;
import swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.gameplay.GameGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlayerGUI extends JPanel implements CreatePlayer
{
    private JLabel heroNameLabel = new JLabel("Insert name:");
    private JTextField heroNameField = new JTextField(10);
    private JButton createHeroButton = new JButton("Create");
    private String[] heroClasses = {"God", "Alien", "Mortal"};
    private JLabel heroClass = new JLabel("Class:");
    private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
    private JEditorPane infoPane = new JEditorPane();
    private CreateHeroController controller;

    @Override
    public void start()
    {
        controller = new CreateHeroController(this);
        buildUI();
    }

    private void buildUI()
    {
        Main.getFrame().setTitle("Create");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        this.add(createHeroPanel, gbc);

        JPanel classPannel = new JPanel();
        classPannel.add(heroClass);
        classesComboBox.setSelectedIndex(0);
        classPannel.add(classesComboBox);
        classPannel.setVisible(true);
        this.add(classPannel, gbc);

        infoPane.setEditable(false);
        infoPane.setFont(new Font("monospaced", Font.PLAIN, 12));
        infoPane.setText("         attack  defense   hp\n" +
                "God        99      99      100\n" +
                "Alien      70      40      80\n" +
                "Mortal     42      24      110\n");
        infoPane.setPreferredSize(new Dimension(200, 120));
        infoPane.setMinimumSize(new Dimension(200, 120));
        this.add(infoPane, gbc);

        this.add(createHeroButton, gbc);
        this.setVisible(true);

        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.onCreateButtonPressed(heroNameField.getText(), (String) classesComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void getUserInput() {}

    @Override
    public void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void openGame()
    {
        this.setVisible(false);
        new GameGUI().start();
    }
}
