package swingy.model.character;

public abstract class HeroFactory {

    public static Hero newHero(String name, String heroClass) {
        switch (heroClass.toUpperCase()) {
            case "GOD":
                return Director.createGod(name);
            case "ALIEN":
                return Director.createAlien(name);
            case "MORTAL":
                return Director.createMortal(name);
            default:
                throw new IllegalArgumentException("Invalid hero class: " + heroClass);
        }
    }
}
