package swingy.view.gameplay;
import swingy.Main;
import swingy.controller.GameController;
import swingy.model.Game;
import swingy.Coord;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JPanel implements GameView
{
    private String[] directions = {"Up", "Right", "Down", "Left"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton moveButton = new JButton("Move character");
    private JEditorPane infoPane = new JEditorPane();
    private GameController controller;

    @Override
    public void start()
    {
        controller = new GameController(this);
        buildUI();
        controller.onStart();
    }

    private void buildUI()
    {
        Main.getFrame().setTitle("Swingy");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        directionsComboBox.setSelectedIndex(0);
        this.add(directionsComboBox, gbc);
        this.add(moveButton, gbc);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        moveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onMove((String) directionsComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void printMap(boolean[][] map, Coord heroCoord)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("MAP %dx%d\n", map.length, map.length));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i)
                    stringBuilder.append("! ");
                else if (map[i][j])
                    stringBuilder.append("* ");
                else
                    stringBuilder.append(". ");
            }
            stringBuilder.append("\n");
        }
    }

    @Override
    public void update(Game game)
    {
        infoPane.setText(game.getHero().toString() +
                "Position: " + "(" + game.getHeroCoord().getX() +
                "," + game.getHeroCoord().getY() + ")");

        printMap(game.getMap(), game.getHeroCoord());
    }

    @Override
    public void gameFinished()
    {
        Main.hideFrame();
        Main.getFrame().dispose();
    }

    @Override
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void getVillainCollisionInput()
    {
        Object options[] = {"Fight", "Run"};

        int result = JOptionPane.showOptionDialog(Main.getFrame(),
                "This position is occupied by Darth Vader!",
                "Fight or run?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (result == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.onRun();
    }
}
