package swingy.view.gameplay;
import swingy.model.Game;
import swingy.Coord;

public interface GameView 
{
    void start();
    void printMap(boolean[][] map, Coord heroCoord);
    void update(Game game);
    void gameFinished();
    void showMessage(String message);
    void getVillainCollisionInput();
}
