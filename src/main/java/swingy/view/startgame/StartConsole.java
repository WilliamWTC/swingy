package swingy.view.startgame;
import swingy.Main;
import swingy.controller.StartController;
import swingy.view.player.CreatePlayerConsole;
import java.util.Scanner;

public class StartConsole implements StartView
{
    private StartController controller;

    @Override
    public void start() {
        controller = new StartController(this);
        System.out.println("\n-- SWINGY ON CONSOLE --\n");
        Scanner scanner = Main.getScanner();
        System.out.println("## Type in 'CREATE' to make a character.");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("create".equalsIgnoreCase(input)) {
                controller.onCreateHeroButtonPressed();
                break;
            } else {
                System.out.println("\n-- Use only the available commands!! --\n");
            }
        }
    }

    @Override
    public void openCreateHero() {
        new CreatePlayerConsole().start();
    }
}
