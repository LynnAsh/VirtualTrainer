package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {
    private String name;
    private Pet pet = new Pet();

    @FXML private TextField petName;

    @FXML private Button startBt;
    @FXML private Button plus1;
    @FXML private Button plus10;
    @FXML private Button plus100;
    @FXML private Button itemshopBt;
    @FXML private Button piercingBt;
    @FXML private Button bowBt;
    @FXML private Button necklaceBt;
    @FXML private Button crownBt;

    @FXML private ImageView borderImg;
    @FXML private ImageView panda;
    @FXML private ImageView crown;
    @FXML private ImageView necklace;
    @FXML private ImageView bow;
    @FXML private ImageView piercing;

    @FXML private Pane startPage;
    @FXML private Pane menuBox;
    @FXML private Pane itemShop;
    @FXML private Pane upgradeShop;
    @FXML private Pane cosmeticsPage;
    @FXML private Pane barPane;
    @FXML private Pane pointsPane;

    @FXML private Label pointsLabel;
    @FXML private Label ppcLabel;
    @FXML private Label nameTag;

    public void startClick(MouseEvent event) {
        name = petName.getText();

        if (petName.isVisible() == false) {
            petName.setVisible(true);
        } else if (!name.isEmpty()) {
            pet.setName(name);
            showMain();
        }
    }

    public void showMain() {
        startPage.setVisible(false);
        barPane.setVisible(true);
        panda.setVisible(true);
        borderImg.setVisible(true);
        pointsPane.setVisible(true);
        menuBox.setVisible(true);
        nameTag.setText(pet.getName());
    }

    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    public void pandaClick(MouseEvent event) {
        panda.setFitHeight(250);
        panda.setFitWidth(375);
        
        pet.onClick();
        updateLabels();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                panda.setFitHeight(275);
                panda.setFitWidth(400);
            }
        }, 50);        
    }

    public void itemshopShow(MouseEvent event) throws IOException {
        menuBox.setVisible(false);
        itemShop.setVisible(true);
    }

    public void backButton(ActionEvent event) {
        itemShop.setVisible(false);
        upgradeShop.setVisible(false);
        cosmeticsPage.setVisible(false);
        menuBox.setVisible(true);

        updateLabels();
    }

    public void upgradePage(ActionEvent event) {
        menuBox.setVisible(false);
        upgradeShop.setVisible(true);
    }

    public void cosmeticsPage(ActionEvent event) {
        menuBox.setVisible(false);
        cosmeticsPage.setVisible(true);
    }

    public void setCosmetic(ActionEvent event) {
        if (event.getSource() == piercingBt && pet.getPoints() > 50000 && !pet.getCosmetic("piercing")) {
            piercing();
            pet.setPoints(-50000);
            updateLabels();
        } else if (event.getSource() == bowBt && pet.getPoints() > 200000 && !pet.getCosmetic("bow")) {
            bow();
            pet.setPoints(-200000);
            updateLabels();
        } else if (event.getSource() == necklaceBt && pet.getPoints() > 1000000 && !pet.getCosmetic("necklace")) {
            necklace();
            pet.setPoints(-1000000);
            updateLabels();
        } else if (event.getSource() == crownBt && pet.getPoints() > 20000000 && !pet.getCosmetic("crown")) {
            crown();
            pet.setPoints(-10000000);
            updateLabels();
        }
    }

    private void piercing() {
        pet.setCosmetic("piercing");
        piercing.setVisible(true);
    }

    private void bow() {
        pet.setCosmetic("bow");
        bow.setVisible(true);
    }

    private void necklace() {
        pet.setCosmetic("necklace");
        necklace.setVisible(true);
    }

    private void crown() {
        pet.setCosmetic("crown");
        crown.setVisible(true);
    }

    public void ppc(ActionEvent event) {
        if (event.getSource() == plus1 && pet.getPoints() >= 600) {
            pet.setppc(1);
            pet.setPoints(-600);
        } else if (event.getSource() == plus10 && pet.getPoints() >= 6000) {
            pet.setppc(10);
            pet.setPoints(-6000);
        } else if (event.getSource() == plus100 && pet.getPoints() >= 600000) {
            pet.setppc(100);
            pet.setPoints(-600000);
        }

        updateLabels();
    }

    public void morePointsI(ActionEvent event) {
        pet.setmppc1(0);
        updateLabels();
    }

    public void morePointsII(ActionEvent event) {
        pet.setmppc2(0);
        updateLabels();
    }

    public void saveGame(ActionEvent event) throws IOException {
        /*
         * savegame.txt
         * String (name)
         * int (points)
         * int (ppc)
         * int (mppc1)
         * int (mppc2)
         * String (hasPiercing)
         * String (hasBow)
         * String (hasNecklace)
         * String (hasCrown)
         */

        try {
            File file = new File("savegame.txt");
            FileWriter writer = new FileWriter(file);
            PrintWriter pw = new PrintWriter(writer);
            pw.printf("%s %d %d %d %d %b %b %b %b".formatted(pet.getName(), pet.getPoints(), pet.getppc(), pet.getmppc1(), pet.getmppc2(),
                        pet.getCosmetic("piercing"), pet.getCosmetic("bow"), pet.getCosmetic("necklace"), pet.getCosmetic("crown")));
            pw.close();

        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }

    public void updateLabels() {
        pointsLabel.setText("Points = " + pet.getPoints());
        ppcLabel.setText("Points Per Click = " + pet.getppc());
    }

    public void continueGame(ActionEvent event) throws IOException {
        String line = "";
        File file = new File("savegame.txt");
        Scanner scnr = new Scanner(file);
        while(scnr.hasNextLine()) {
            line = scnr.nextLine();
        }
        scnr.close();

        System.out.println(line);
        String[] data = line.split(" ");

        pet.setName(data[0]);
        pet.setPoints(Integer.parseInt(data[1]));
        pet.setppc(Integer.parseInt(data[2]));
        pet.setmppc1(Integer.parseInt(data[3]));
        pet.setmppc2(Integer.parseInt(data[4]));
        boolean hasPiercing = Boolean.parseBoolean(data[5]);
        boolean hasBow = Boolean.parseBoolean(data[6]);
        boolean hasNecklace = Boolean.parseBoolean(data[7]);
        boolean hasCrown = Boolean.parseBoolean(data[8]);

        if (hasPiercing) {
            piercing();
        } if (hasBow) {
            bow();
        } if (hasNecklace) {
            necklace();
        } if (hasCrown) {
            crown();
        }

        updateLabels();

        showMain();
    }
}
