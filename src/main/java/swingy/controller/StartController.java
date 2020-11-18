package swingy.controller;
import swingy.view.startgame.StartView;

public class StartController 
{
    private StartView view;

    public StartController(StartView view) {
        this.view = view;
    }

    public void onCreateHeroButtonPressed() {
        view.openCreateHero();
    }
}
