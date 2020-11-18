package swingy.view.player;
import swingy.Main;
import swingy.controller.CreateHeroController;
import swingy.view.gameplay.GameConsole;
import java.util.Scanner;

public class CreatePlayerConsole implements CreatePlayer
{
    private CreateHeroController controller;

    @Override
    public void start()
    {
        controller = new CreateHeroController(this);
        getUserInput();
    }

    @Override
    public void getUserInput()
    {
        Scanner scanner = Main.getScanner();

        System.out.println("\n## Insert a name for your character:");
        System.out.println();
        String name = scanner.nextLine();
        System.out.println("\n## Available character classes ##");
        System.out.println("\nClass  |  attack  |   defense | hp\n" +
                "----------------------------------\n" +
                "God        99          99      100\n" +
                "Alien      70          40      80\n" +
                "Mortal     42          24      110\n" +
                "\n## Choose a class for your character:\n");
        String heroClass = scanner.nextLine();

        System.out.println("\n## Type in 'PLAY' to create character & start game\n");
        while (scanner.hasNext())
        {
            String input = scanner.nextLine();

            if ("play".equalsIgnoreCase(input))
            {
                controller.onCreateButtonPressed(name, heroClass);
                break;
            } else {
                System.out.println("\n-- Use only the available commands!! --\n");
            }
        }
    }

    @Override
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    @Override
    public void openGame()
    {
        new GameConsole().start();
    }
}
