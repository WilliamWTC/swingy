package swingy.controller;
import swingy.model.Game;
import swingy.model.character.Armor;
import swingy.model.character.Artifact;
import swingy.model.character.Helm;
import swingy.model.character.Weapon;
import swingy.model.character.Hero;
import swingy.model.character.Villain;
import swingy.Coord;
import swingy.view.gameplay.GameView;
import java.util.Random;

public class GameController
{
    private GameView view;
    private Game game;
    private Coord previousPosition;

    public GameController(GameView view)
    {
        this.view = view;
        game = Game.getInstance();
        previousPosition = new Coord(0, 0);
    }

    public void onStart() {
        view.update(game);
    }

    public void onPrintMap() {
        view.printMap(game.getMap(), game.getHeroCoord());
        view.update(game);
    }

    public void onMove(String direction) {
        int x = game.getHeroCoord().getX();
        int y = game.getHeroCoord().getY();
        previousPosition.setX(x);
        previousPosition.setY(y);

        switch (direction.toUpperCase()) {
            case "UP":
                y--;
                break;
            case "RIGHT":
                x++;
                break;
            case "DOWN":
                y++;
                break;
            case "LEFT":
                x--;
                break;
        }

        if (x < 0 || y < 0 || x >= game.getMapSize() || y >= game.getMapSize()) {
            winGame();
            return;
        }

        game.getHeroCoord().setX(x);
        game.getHeroCoord().setY(y);

        if (game.getMap()[y][x]) {
            villainCollision();
        }

        if (game.getHero().getHitPoints() > 0)
            view.update(game);
    }

    private void winGame() {
        view.showMessage("You win! You earned " + game.getMapSize() * 100 + "xp!");
        addExperience(game.getMapSize() * 100);
        view.gameFinished();
    }

    private void villainCollision() {
        view.getVillainCollisionInput();
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            view.showMessage("Successfully evaded the Vader!");
            game.getHeroCoord().setX(previousPosition.getX());
            game.getHeroCoord().setY(previousPosition.getY());
        } else {
            view.showMessage("c'mon, just fight him!");
            onFight();
        }
    }

    private void setArtifact(Artifact artifact) {
        if (artifact != null) {
            if (artifact instanceof Weapon) {
                if (game.getHero().getWeapon() == null) {
                    game.getHero().equipWeapon((Weapon) artifact);
                    view.showMessage("You equipped new weapon: " + artifact);
                }
            } else if (artifact instanceof Helm) {
                if (game.getHero().getHelm() == null) {
                    game.getHero().equipHelm((Helm) artifact);
                    view.showMessage("You equipped new helm: " + artifact);
                }
            } else if (artifact instanceof Armor) {
                if (game.getHero().getArmor() == null) {
                    game.getHero().equipArmor((Armor) artifact);
                    view.showMessage("You equipped new armor: " + artifact);
                }
            }
        }
    }

    public void onFight() {
        Villain villain = game.generateVillain();
        int xp = game.fightResult(villain);

        if (xp >= 0) {
            view.showMessage("You win! " + xp + "xp gained.");
            addExperience(xp);
            game.getMap()[game.getHeroCoord().getY()][game.getHeroCoord().getX()] = false;
            setArtifact(villain.getArtifact());
        } else {
            view.gameFinished();
        }
    }

    private void addExperience(int addXP) {
        int level = game.getHero().getLevel();
        game.getHero().addExperience(addXP);
        if (level != game.getHero().getLevel())
            view.showMessage("Level UP!\nHP, attack and defense were increased!");
    }
}
