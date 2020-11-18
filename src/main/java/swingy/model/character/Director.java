package swingy.model.character;

public class Director {

    private static HeroBuilder buildNew(String name) {
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setLevel(0);
        builder.setExperience(0);
        return builder;
    }

    public static Hero createGod(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(99);
        builder.setDefense(99);
        builder.setHitPoints(100);
        builder.setHeroClass("GOD");
        return builder.getHero();
    }

    public static Hero createAlien(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(70);
        builder.setDefense(40);
        builder.setHitPoints(80);
        builder.setHeroClass("ALIEN");
        return builder.getHero();
    }

    public static Hero createMortal(String name) {
        HeroBuilder builder = buildNew(name);
        builder.setAttack(42);
        builder.setDefense(24);
        builder.setHitPoints(110);
        builder.setHeroClass("MORTAL");
        return builder.getHero();
    }
}
