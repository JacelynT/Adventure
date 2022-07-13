package AdventurePlus;

public class VisibilityManager {

    UI ui;

    public VisibilityManager(UI ui){
        this.ui = ui;
    }

    public void showTitleScreen(){

        //HIDE THE GAME SCREEN
        ui.playerPanel.setVisible(false);
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);

        //SHOW THE TITLE SCREEN
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);
    }

    public void titleToTown() {

        //HIDE THE TITLE SCREEN
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);

        //SHOW THE GAME SCREEN
        ui.playerPanel.setVisible(true);
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
    }

}
