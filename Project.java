import java.util.Scanner;

abstract class Hero {
    private String name;
    private int level;
    private int strength;
    private int defence;
    private int intelligence;
    private int dexterity;
    private int agility;
    private int speed;

    public Hero(String name, int strength, int defence, int intelligence, int dexterity, int agility, int speed) {
        this.name = name;
        this.strength = strength;
        this.defence = defence;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.agility = agility;
        this.speed = speed;
        this.level = 1;}

    public void levelUp() {
        level=level+1;
    }

    public void distributeExperiencePoints(int points) {
        // Implementacja rozdzielania punktów doświadczenia na statystyki
        // Tutaj przyjmujemy, że punkty są równo rozdzielane na wszystkie statystyki
        int pointsPerStat = points / 6;
        strength += pointsPerStat;
        defence += pointsPerStat;
        intelligence += pointsPerStat;
        dexterity += pointsPerStat;
        agility += pointsPerStat;
        speed += pointsPerStat;}

    // Gettery

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public int getSpeed() {
        return speed;
    }}

// Konkretne klasy postaci
class Mage extends Hero {
    public Mage(String name) {
        super(name, 100, 100, 120, 105, 102, 100);
    }}

class Knight extends Hero {
    public Knight(String name) {
        super(name, 120, 110, 100, 105, 102, 105);
    }}

class Archer extends Hero {
    public Archer(String name) {
        super(name, 105, 105, 100, 120, 110, 105);
    }}

// Klasa reprezentująca grę
public class Project {
    private Scanner scanner;
    private Hero player;

    public Project() {
        scanner = new Scanner(System.in);}

    public void start() {
        System.out.println("Witaj w grze tekstowej!");
        createCharacter();
        playGame();
        endGame();}

    private void createCharacter() {
        System.out.println("Wybierz klasę postaci:");
        System.out.println("1. Mag");
        System.out.println("2. Rycerz");
        System.out.println("3. Łucznik");

        int choice = getNumericInput(1, 3);

        String name = Nick();

        switch (choice) {
            case 1:
                player = new Mage(name);
                break;
            case 2:
                player = new Knight(name);
                break;
            case 3:
                player = new Archer(name);
                break;}

        System.out.println("Stworzono postać: " + player.getName());
        System.out.println("Klasa: " + player.getClass().getSimpleName());
        System.out.println();}

    private void playGame() {
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Aktualny poziom: " + player.getLevel());
            System.out.println("Aktualne statystyki:");
            printPlayerStats();

            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Zdobądź doświadczenie");
            System.out.println("2. Zakończ grę");

            int choice = getNumericInput(1, 2);

            switch (choice) {
                case 1:
                    int experiencePoints = 10;
                    player.distributeExperiencePoints(experiencePoints);
                    player.levelUp();
                    System.out.println("Zdobycie " + experiencePoints + " punktów doświadczenia!");
                    System.out.println("Postać awansowała na poziom " + player.getLevel());
                    System.out.println();
                    break;
                case 2:
                    gameOver = true;
                    break;}}}

    private void endGame() {
        System.out.println("Koniec gry. Dziękujemy za grę!");
        scanner.close();}

    private int getNumericInput(int min, int max) {
        int choice;

        do {
            System.out.print("Wybierz opcję: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Nieprawidłowe dane. Podaj liczbę: ");
                scanner.next();}
            choice = scanner.nextInt();}
        while (choice < min || choice > max);

        scanner.nextLine();

        return choice;}

    private String Nick() {
        System.out.print("Podaj imię postaci: ");
        return scanner.nextLine();}

    private void printPlayerStats() {
        System.out.println("Siła: " + player.getStrength());
        System.out.println("Obrona: " + player.getDefence());
        System.out.println("Inteligencja: " + player.getIntelligence());
        System.out.println("Celność: " + player.getDexterity());
        System.out.println("Zręczność: " + player.getAgility());
        System.out.println("Szybkość: " + player.getSpeed());
        System.out.println();}

    public static void main(String[] args) {
        Project game = new Project();
        game.start();}}
