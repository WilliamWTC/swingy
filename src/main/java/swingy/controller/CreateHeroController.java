package swingy.controller;
import swingy.controller.HeroValidationException;
import swingy.model.Game;
import swingy.model.character.Hero;
import swingy.model.character.HeroFactory;
import swingy.view.player.CreatePlayer;

public class CreateHeroController
{
    private CreatePlayer view;
    private Game game;

    public CreateHeroController(CreatePlayer view)
    {
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreateButtonPressed(String name, String heroClass)
    {
        Hero hero;
        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | HeroValidationException e) {
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }

        game.initGame(hero);
        view.openGame();
    }
}
