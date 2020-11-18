package swingy.view.startgame;
import swingy.Main;
import swingy.controller.StartController;
import swingy.view.player.CreatePlayerGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGUI extends JPanel implements StartView
{
    private JButton createHeroButton = new JButton("Start game");
    private StartController controller;

    @Override
    public void start()
    {
        controller = new StartController(this);
        buildUI();
    }

    private void buildUI() {
        Main.getFrame().setTitle("Swingy");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.add(createHeroButton, gbc);
        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateHeroButtonPressed();
            }
        });
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        new CreatePlayerGUI().start();
    }
}
