package swingy.view.gameplay;
import swingy.Main;
import swingy.controller.GameController;
import swingy.model.Game;
import swingy.Coord;
import java.util.Scanner;

public class GameConsole implements GameView
{
    private GameController controller;

    @Override
    public void start() {
        controller = new GameController(this);
        controller.onStart();
    }

    @Override
    public void gameFinished(){}
    
    @Override
    public void update(Game game)
    {
        System.out.println("\n--------- STATS ---------");
        System.out.println(game.getHero().toString() +
                "Position: " + "(" + game.getHeroCoord().getX() +
                "," + game.getHeroCoord().getY() + ")");
        System.out.println("------------------------\n");
        getUserInput();
    }

    private void getUserInput()
    {
        Scanner scanner = Main.getScanner();
        System.out.println("... Type in a command from game options below ...");
        System.out.println("To Move: 'UP', 'RIGHT', 'DOWN' or 'LEFT'\nMAP: to view your position\n");
        while (scanner.hasNext())
        {
            String input = scanner.nextLine();

            if ("map".equalsIgnoreCase(input))
            {
                controller.onPrintMap();
                break;
            } else if ("up".equalsIgnoreCase(input) ||
                    "right".equalsIgnoreCase(input) ||
                    "down".equalsIgnoreCase(input) ||
                    "left".equalsIgnoreCase(input)) {
                controller.onMove(input);
                break;
            } else {
                System.out.println("\n-- Use only the available commands!! --\n");
            }
        }
    }

    @Override
    public void printMap(boolean[][] map, Coord heroCoord)
    {
        System.out.printf("\nMAP %dx%d", map.length, map.length);
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (heroCoord.getX() == j && heroCoord.getY() == i)
                    System.out.print("@ ");
                else if (map[i][j])
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    @Override
    public void showMessage(String message)
    {
        System.out.println(message);
    }

    @Override
    public void getVillainCollisionInput()
    {
        Scanner scanner = Main.getScanner();
        System.out.println();
        System.out.println("-- This position is occupied by Darth Vader. Your options:");
        System.out.println("1) FIGHT ");
        System.out.println("2) RUN ");
        System.out.println("## Type in FIGHT or RUN. \n");
        while (scanner.hasNext())
        {
            String input = scanner.nextLine();

            if ("fight".equalsIgnoreCase(input)) {
                controller.onFight();
                break;
            } else if ("run".equalsIgnoreCase(input)) {
                controller.onRun();
                break;
            } else {
                System.out.println("\n-- Use only the available commands!! --\n");
            }
        }
    }
}
