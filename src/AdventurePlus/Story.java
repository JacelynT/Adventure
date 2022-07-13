package AdventurePlus;

import package2.Monster_Goblin;
import package2.SuperMonster;
import package2.Weapon_Knife;
import package2.Weapon_LongSword;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster;

    boolean silverRing = false;

    public Story(Game game, UI ui, VisibilityManager vm) {
        this.game = game;
        this.ui = ui;
        this.vm = vm;
    }

    public void defaultSetup() {
        player.hp = 15;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);
        silverRing = false;
    }

    public void selectPosition(String nextPosition) {
        switch (nextPosition) {
            case "townGate":
                townGate();
                break;
            case "talkGuard":
                talkGuard();
                break;
            case "attackGuard":
                attackGuard();
                break;
            case "crossRoad":
                crossRoad();
                break;
            case "north":
                north();
                break;
            case "east":
                east();
                break;
            case "west":
                west();
                break;
            case "fight":
                fight();
                break;
            case "playerAttack":
                playerAttack();
                break;
            case "monsterAttack":
                monsterAttack();
                break;
            case "winFight":
                winFight();
                break;
            case "townEntrance":
                townEntrance();
                break;
            case "deathScreen":
                deathScreen();
                break;
            case "goToStart":
                vm.showTitleScreen(); defaultSetup();
                break;
        }
    }

    public void townGate() {
        ui.mainTextArea.setText("You find yourself outside the gate of a small town. At the gate is a burly guard.\n\nWhat do you do?");
        ui.choice1.setText("Talk to the guard.");
        ui.choice2.setText("Attack the guard.");
        ui.choice3.setText("Turn and leave.");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGuard";
        game.nextPosition2 = "attackGuard";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGuard() {
        if (silverRing) {
            ui.mainTextArea.setText("Guard: Oh, you defeated the Goblin! Thanks so much! It's been terrorizing our town for weeks. I guess it'll be okay to let you in.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townEntrance";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            ui.mainTextArea.setText("Guard: Hello stranger. I've never seen your face around here before. Sorry, but I can't let any strangers into town.");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void attackGuard() {
        player.hp = player.hp - 3;
        ui.hpNumberLabel.setText("" + player.hp);
        if (player.hp <= 0) {
            deathScreen();
        } else {
            ui.mainTextArea.setText("Hey, don't be stupid!\n\nThe guard fought back and hit you hard.\n(You receive 3 points of damage.)");
            ui.choice1.setText(">");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "townGate";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void crossRoad() {
        ui.mainTextArea.setText("You find yourself at a crossroad. If you go south you will return to town.\n\nWhich way would you like to go?");
        ui.choice1.setText("Go north.");
        ui.choice2.setText("Go east.");
        ui.choice3.setText("Go west.");
        ui.choice4.setText("Go south.");

        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "west";
        game.nextPosition4 = "townGate";
    }

    public void north() {
        String amountRestoredMessage;
        if(player.hp == player.hpMax) {
            amountRestoredMessage = "(Your hit points are already full.)";
        } else if (player.hp == (player.hpMax) - 1) {
            player.hp = player.hp + 1;
            ui.hpNumberLabel.setText("" + player.hp);
            amountRestoredMessage = "(Your hit points are restored by 1.)";
        } else {
            player.hp = player.hp + 2;
            ui.hpNumberLabel.setText("" + player.hp);
            amountRestoredMessage = "(Your hit points are restored by 2.)";
        }

        ui.mainTextArea.setText("You find a smooth blue river ahead. You take a refreshing drink of water and rest.\n\n" + amountRestoredMessage);
        ui.choice1.setText("Go south.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void east() {
        if (player.currentWeapon.name.equals("Long Sword")) {
            ui.mainTextArea.setText("You walk up to a forest entrance. You don't find anything.");
        } else {
            ui.mainTextArea.setText("You walk up to a forest entrance. You find a Long Sword in the bushes!\n\n(You obtained a Long Sword.)");
            player.currentWeapon = new Weapon_LongSword();
            ui.weaponNameLabel.setText(player.currentWeapon.name);
        }
        ui.choice1.setText("Go west.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void west() {
        if (silverRing) {
            ui.mainTextArea.setText("You see the corpse of the slain Goblin on the ground.");
            ui.choice1.setText("Go east.");
            ui.choice2.setText("");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "crossRoad";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            monster = new Monster_Goblin();

            ui.mainTextArea.setText("You encounter an extremely fat and ugly " + monster.name + " snarling down at you!");
            ui.choice1.setText("Fight!");
            ui.choice2.setText("Run!");
            ui.choice3.setText("");
            ui.choice4.setText("");

            game.nextPosition1 = "fight";
            game.nextPosition2 = "crossRoad";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void fight() {
        ui.hpNumberLabel.setText("" + player.hp);

        ui.mainTextArea.setText(monster.name + " HP: " + monster.hp + "\n\nWhat do you do?");
        ui.choice1.setText("Attack!");
        ui.choice2.setText("Run!");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void playerAttack() {
        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        ui.mainTextArea.setText("You attacked the " + monster.name + " and caused " + playerDamage + " damage.");

        monster.hp = monster.hp - playerDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (monster.hp <= 0) {
            game.nextPosition1 = "winFight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "monsterAttack";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void monsterAttack() {
        int monsterDamage = new java.util.Random().nextInt(monster.damage);

        ui.mainTextArea.setText("The " + monster.name + " attacked you and caused " + monsterDamage + " damage.");

        player.hp = player.hp - monsterDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if (player.hp <= 0) {
            game.nextPosition1 = "deathScreen";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        } else {
            game.nextPosition1 = "fight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
        }
    }

    public void winFight() {
        ui.mainTextArea.setText("You defeated the " + monster.name + "!\nThe " + monster.name + " dropped a ring!\n\n(You obtained a Silver Ring.)");
        silverRing = true;

        ui.choice1.setText("Return to crossroad.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void townEntrance() {
        ui.mainTextArea.setText("You enter into a bustling street at the north entrance to the city. You look up and see...\n\nTO BE CONTINUED");

        ui.choice1.setText("Return to main menu.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "goToStart";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void deathScreen() {
        ui.hpNumberLabel.setText("0");
        ui.mainTextArea.setText("You died, ya big dumb stupid idiot.\n\n<GAME OVER>");

        ui.choice1.setText("Return to main menu.");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "goToStart";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }
}
