package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static sample.Main.*;

public class Main extends Application {

    public static String savegame;

    public static int bark;
    public static int wood;
    public static int carbon;
    public static int magma;
    public static int firedust;
    public static int lifedust;
    public static int soulserum;
    public static int mortalpowder;
    public static int megapowder;
    public static int ultrapowder;
    public static int infinitumpowder;
    public static int gold;
    public static int sapphire;
    public static int nightstone;
    public static int energyserum;
    public static int xenonpowder;
    public static int neonpowder;
    public static int concentratedoganesson;
    public static int energyelixir;
    public static int purificationstone;
    public static int maximuscrystals;
    public static int eternalpearls;

    public static int health = 100;
    public static int strength = 10;
    static Text hp = new Text("Health: " + health);

    public static int x = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int y = Toolkit.getDefaultToolkit().getScreenSize().height;
    static Group root = new Group();
    static ImageView room = new ImageView();
    static Scene scene = new Scene(root, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
    static ImageView mainchar = new ImageView();
    static Rectangle door = new Rectangle(10, 50);
    static ImageView chest = new ImageView();
    static Stage dungeonstage = new Stage();
    static Stage deathstage = new Stage();

    static ImageView died = new ImageView();
    static Button playbtn2 = new Button("Respawn");
    static Rectangle diedbg = new Rectangle(x, y);

    public static Line fire = new Line();
    public static Line strike = new Line();

    static Rectangle chestmenubg = new Rectangle();
    static Text chesttitle = new Text("Chest Contents");
    static Button chestexit = new Button("Exit");
    static String chestitem;
    static Text chestitemdisp = new Text("xX Item");
    static int chestnum = 0;
    static boolean chestgen = false;
    static Button chestcollect = new Button("Collect");

    static Rectangle invbg = new Rectangle();
    static Button exitinv = new Button();
    static Text invtitle = new Text("Inventory");
    static Text sworddisplay = new Text("No equipped sword (somehow.)");
    static Text hmatsdisplay = new Text("No health materials.");
    static Text smatsdisplay = new Text("No strength materials.");

    static Button craftbtn = new Button("Craft Potion");
    static TextField cf1 = new TextField("Bark");
    static TextField cf2 = new TextField("Wood");
    static TextField cf3 = new TextField("Carbon");
    static TextField cf4 = new TextField("Magma");
    static TextField cf5 = new TextField("Fire Dust");
    static TextField cf6 = new TextField("Life Dust");
    static TextField cf7 = new TextField("Soul Serum");
    static TextField cf8 = new TextField("Mortal Powder");
    static TextField cf9 = new TextField("Mega Powder");
    static TextField cf10 = new TextField("Ultra Powder");
    static TextField cf11 = new TextField("Infinitum Powder");
    static TextField cf12 = new TextField("Gold");
    static TextField cf13 = new TextField("Sapphire");
    static TextField cf14 = new TextField("Nightstone");
    static TextField cf15 = new TextField("Energy Serum");
    static TextField cf16 = new TextField("Xenon Powder");
    static TextField cf17 = new TextField("Neon Powder");
    static TextField cf18 = new TextField("Concentrated Oganesson");
    static TextField cf19 = new TextField("Energy Elixir");
    static TextField cf20 = new TextField("Purification Stone");
    static TextField cf21 = new TextField("Maximus Crystals");
    static TextField cf22 = new TextField("Eternal Pearls");
    
    static int healthincrease;
    static int strengthincrease;

    public static ImageView enemy = new ImageView();
    static String enemyname;
    static int enemyhp;
    static int enemydmg;
    static Text enbox = new Text(enemyname);
    static Text enemyhb = new Text("Enemy Health: " + enemyhp);
    static Timer t;

    @Override
    public void start(Stage primaryStage) throws Exception{
        load();

        primaryStage.setTitle("Dungeon");
        scene.setFill(javafx.scene.paint.Color.rgb(100, 100, 100));
        primaryStage.setFullScreen(true);

        mainchar.setFitHeight(50);
        mainchar.setFitWidth(50);
        mainchar.setX(x / 2);
        mainchar.setY(y / 2);

        fire.setStartX(enemy.getX());
        fire.setStartY(enemy.getY());
        fire.setStartX(mainchar.getX());
        fire.setStartY(mainchar.getY());
        fire.setFill(Color.RED);
        fire.setStrokeWidth(0);

        chestmenubg.setFill(Color.rgb(25, 25, 25));
        chestmenubg.setWidth(600);
        chestmenubg.setHeight(600);
        chestmenubg.setX((x / 2) - 300);
        chestmenubg.setY((y / 2) - 300);
        chestmenubg.setVisible(false);

        chesttitle.setFill(Color.WHITE);
        chesttitle.setX(x / 2 - 50);
        chesttitle.setY(chestmenubg.getY() + 25);
        chesttitle.setVisible(false);

        chestitemdisp.setFill(Color.WHITE);
        chestitemdisp.setX(x / 2 - 50);
        chestitemdisp.setY(chestmenubg.getY() + 100);
        chestitemdisp.setVisible(false);

        chestcollect.setLayoutX(chestmenubg.getX());
        chestcollect.setLayoutY(chestmenubg.getY() + 500);
        chestcollect.setPrefWidth(600);
        chestcollect.setPrefHeight(100);
        chestcollect.setVisible(false);

        chestcollect.setOnAction(event -> {
            if (chestitem == "Bark"){
                bark += chestnum;
            } else if (chestitem == "Wood"){
                wood += chestnum;
            } else if (chestitem == "Carbon"){
                carbon += chestnum;
            } else if (chestitem == "Magma"){
                magma += chestnum;
            } else if (chestitem == "Fire Dust"){
                firedust += chestnum;
            } else if (chestitem == "Life Dust"){
                lifedust += chestnum;
            } else if (chestitem == "Soul Serum"){
                soulserum += chestnum;
            } else if (chestitem == "Mortal Powder"){
                mortalpowder += chestnum;
            } else if (chestitem == "Mega Powder"){
                megapowder += chestnum;
            } else if (chestitem == "Ultra Powder"){
                ultrapowder += chestnum;
            } else if (chestitem == "Infinitum Powder"){
                infinitumpowder += chestnum;
            } else if (chestitem == "Gold"){
                gold += chestnum;
            } else if (chestitem == "Sapphire"){
                sapphire += chestnum;
            } else if (chestitem == "Nightstone"){
                nightstone += chestnum;
            } else if (chestitem == "Energy Serum"){
                energyserum += chestnum;
            } else if (chestitem == "Xenon Powder"){
                xenonpowder += chestnum;
            } else if (chestitem == "Neon Powder"){
                neonpowder += chestnum;
            } else if (chestitem == "Concentrated Oganesson"){
                concentratedoganesson += chestnum;
            } else if (chestitem == "Energy Elixir"){
                energyelixir += chestnum;
            } else if (chestitem == "Purification Stone"){
                purificationstone += chestnum;
            } else if (chestitem == "Maximus Crystals"){
                maximuscrystals += chestnum;
            } else if (chestitem == "Eternal Pearls"){
                eternalpearls += chestnum;
            }

            save();
            chestnum = 0;
            chestexit.fire();
        });

        chestexit.setLayoutX(chestmenubg.getX());
        chestexit.setLayoutY(chestmenubg.getY());
        chestexit.setVisible(false);

        chestexit.setOnAction(event -> {
            chestexit.setVisible(false);
            chesttitle.setVisible(false);
            chestmenubg.setVisible(false);
            chestitemdisp.setVisible(false);
            chestcollect.setVisible(false);
        });

        invbg.setHeight(y);
        invbg.setWidth(x);
        invbg.setFill(Color.rgb(150,150,150));
        invbg.setVisible(false);

        exitinv.setText("Exit");
        exitinv.setLayoutY(y - 50);
        exitinv.setPrefHeight(50);
        exitinv.setPrefWidth(x);
        exitinv.setVisible(false);

        exitinv.setOnAction(event -> {
            invbg.setVisible(false);
            exitinv.setVisible(false);
            invtitle.setVisible(false);
            sworddisplay.setVisible(false);
            hmatsdisplay.setVisible(false);
            smatsdisplay.setVisible(false);
        });

        invtitle.setX((x/2) - 50);
        invtitle.setY(50);
        invtitle.setFont(Font.font("Copperplate", FontWeight.BOLD, 25));
        invtitle.setFill(Color.WHITE);
        invtitle.setVisible(false);

        sworddisplay.setY(invtitle.getY() + 50);
        hmatsdisplay.setY(invtitle.getY() + 50);
        smatsdisplay.setY(invtitle.getY() + 50);

        hmatsdisplay.setText("Bark: " + bark + "\nWood: " + wood + "\nCarbon: " + carbon + "\nMagma: " + magma + "\nFire Dust: " + firedust + "\nLife Dust: " + "\nSoul Serum: " + soulserum + "\nMortal Powder: " + mortalpowder + "\nMega Powder: " + megapowder + "\nUltra Powder: " + ultrapowder + "\nInfinitum Powder: " + infinitumpowder);
        smatsdisplay.setText("Gold: " + gold + "\nSapphire: " + sapphire + "\nNightstone: " + nightstone + "\nEnergy Serum: " + energyserum + "\nXenon Powder: " + xenonpowder + "\nNeon Powder: " + neonpowder + "\nConcentrated Oganesson: " + concentratedoganesson + "\nEnergy Elixir: " + energyelixir + "\nPurification Stone: " + purificationstone + "\nMaximus Crystals: " + maximuscrystals + "\nEternal Pearls: " + eternalpearls);

        sworddisplay.setX(x / 2);
        hmatsdisplay.setX(x / 4);
        smatsdisplay.setX((x / 4) * 3);
        sworddisplay.setTextAlignment(TextAlignment.CENTER);
        hmatsdisplay.setTextAlignment(TextAlignment.CENTER);
        smatsdisplay.setTextAlignment(TextAlignment.CENTER);
        sworddisplay.setFill(Color.GREEN);
        smatsdisplay.setFill(Color.WHITE);
        hmatsdisplay.setFill(Color.LIGHTBLUE);
        sworddisplay.setVisible(false);
        hmatsdisplay.setVisible(false);
        smatsdisplay.setVisible(false);

        cf1.setLayoutX(50);
        cf2.setLayoutX(50);
        cf3.setLayoutX(50);
        cf4.setLayoutX(50);
        cf5.setLayoutX(50);
        cf6.setLayoutX(50);
        cf7.setLayoutX(50);
        cf8.setLayoutX(50);
        cf9.setLayoutX(50);
        cf10.setLayoutX(50);
        cf11.setLayoutX(50);
        cf12.setLayoutX(50);
        cf13.setLayoutX(50);
        cf14.setLayoutX(50);
        cf15.setLayoutX(50);
        cf16.setLayoutX(50);
        cf17.setLayoutX(50);
        cf18.setLayoutX(50);
        cf19.setLayoutX(50);
        cf20.setLayoutX(50);
        cf21.setLayoutX(50);
        cf22.setLayoutX(50);

        cf1.setLayoutY(100);
        cf2.setLayoutY(125);
        cf3.setLayoutY(150);
        cf4.setLayoutY(175);
        cf5.setLayoutY(200);
        cf6.setLayoutY(225);
        cf7.setLayoutY(250);
        cf8.setLayoutY(275);
        cf9.setLayoutY(300);
        cf10.setLayoutY(325);
        cf11.setLayoutY(350);
        cf12.setLayoutY(375);
        cf13.setLayoutY(400);
        cf14.setLayoutY(425);
        cf15.setLayoutY(450);
        cf16.setLayoutY(475);
        cf17.setLayoutY(500);
        cf18.setLayoutY(525);
        cf19.setLayoutY(550);
        cf20.setLayoutY(575);
        cf21.setLayoutY(600);
        cf22.setLayoutY(625);

        cf1.setPrefWidth((x - 100));
        cf2.setPrefWidth((x - 100));
        cf3.setPrefWidth((x - 100));
        cf4.setPrefWidth((x - 100));
        cf5.setPrefWidth((x - 100));
        cf6.setPrefWidth((x - 100));
        cf7.setPrefWidth((x - 100));
        cf8.setPrefWidth((x - 100));
        cf9.setPrefWidth((x - 100));
        cf10.setPrefWidth((x - 100));
        cf11.setPrefWidth((x - 100));
        cf12.setPrefWidth((x - 100));
        cf13.setPrefWidth((x - 100));
        cf14.setPrefWidth((x - 100));
        cf15.setPrefWidth((x - 100));
        cf16.setPrefWidth((x - 100));
        cf17.setPrefWidth((x - 100));
        cf18.setPrefWidth((x - 100));
        cf19.setPrefWidth((x - 100));
        cf20.setPrefWidth((x - 100));
        cf21.setPrefWidth((x - 100));
        cf22.setPrefWidth((x - 100));
        
        cf1.setPrefHeight(20);
        cf2.setPrefHeight(20);
        cf3.setPrefHeight(20);
        cf4.setPrefHeight(20);
        cf5.setPrefHeight(20);
        cf6.setPrefHeight(20);
        cf7.setPrefHeight(20);
        cf8.setPrefHeight(20);
        cf9.setPrefHeight(20);
        cf10.setPrefHeight(20);
        cf11.setPrefHeight(20);
        cf12.setPrefHeight(20);
        cf13.setPrefHeight(20);
        cf14.setPrefHeight(20);
        cf15.setPrefHeight(20);
        cf16.setPrefHeight(20);
        cf17.setPrefHeight(20);
        cf18.setPrefHeight(20);
        cf19.setPrefHeight(20);
        cf20.setPrefHeight(20);
        cf21.setPrefHeight(20);
        cf22.setPrefHeight(20);
        
        craftbtn.setPrefSize(300, 50);
        craftbtn.setLayoutX((x / 2) - 150);
        craftbtn.setLayoutY(y - 50);

        cf1.setVisible(false);
        cf2.setVisible(false);
        cf3.setVisible(false);
        cf4.setVisible(false);
        cf5.setVisible(false);
        cf6.setVisible(false);
        cf7.setVisible(false);
        cf8.setVisible(false);
        cf9.setVisible(false);
        cf10.setVisible(false);
        cf11.setVisible(false);
        cf12.setVisible(false);
        cf13.setVisible(false);
        cf14.setVisible(false);
        cf15.setVisible(false);
        cf16.setVisible(false);
        cf17.setVisible(false);
        cf18.setVisible(false);
        cf19.setVisible(false);
        cf20.setVisible(false);
        cf21.setVisible(false);
        cf22.setVisible(false);
        craftbtn.setVisible(false);

        strike.setStartX(enemy.getX());
        strike.setStartY(enemy.getY());
        strike.setStartX(mainchar.getX());
        strike.setStartY(mainchar.getY());
        strike.setFill(Color.RED);
        strike.setStrokeWidth(0);

        FileInputStream inputstream = null;
        inputstream = new FileInputStream("Images/dead.png");
        died.setImage(new Image(inputstream));
        died.setFitWidth(650);
        died.setFitHeight(300);
        died.setY(100);
        died.setX((x / 2) - 325);
        died.setVisible(false);

        diedbg.setFill(Color.BLACK);

        playbtn2.setPrefSize(300, 70);
        playbtn2.setLayoutX((x / 2) - 150);
        playbtn2.setLayoutY(y / 2);
        playbtn2.setVisible(false);

        playbtn2.setOnAction(event -> {
            dungeon();
        });

        hp.setX(100);
        hp.setY(y - 20);
        hp.setFill(Color.rgb(255, 255, 255));

        enemyhb.setX(x - 200);
        enemyhb.setY(hp.getY() + 10);
        enemyhb.setFill(Color.rgb(255, 255, 255));

        chest.setFitHeight(40);
        chest.setFitWidth(40);
        FileInputStream loadimg = new FileInputStream("Images/chest.png");
        chest.setImage(new Image(loadimg));
        chest.setVisible(false);

        enbox.setX(x - 100);
        enbox.setY(hp.getY() - 10);
        enbox.setFill(Color.WHITE);

        ImageView logo = new ImageView();
        FileInputStream inputstream2 = new FileInputStream("Images/crawler.png");

        logo.setImage(new Image(inputstream2));
        logo.setFitHeight(300);
        logo.setFitWidth(650);
        logo.setX((x - 650) / 2);
        logo.setY(50);
        root.getChildren().add(logo);

        Button playbtn = new Button("Play");
        playbtn.setPrefSize(300, 70);
        playbtn.setLayoutX((x / 2) - 150);
        playbtn.setLayoutY(y / 2);

        playbtn.setOnAction(event -> {
            dungeon();
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setFullScreen(true);
        });

        root.getChildren().add(playbtn);

        primaryStage.setScene(scene);
        dungeonstage = primaryStage;
        primaryStage.show();
    }

    public static void dungeon(){
        save();

        Group refresh = new Group();
        root = refresh;

        chestgen = false;

        playbtn2.setVisible(false);
        died.setVisible(false);
        diedbg.setVisible(false);
        chestitemdisp.setVisible(false);

        room.setFitWidth(x - 200);
        room.setFitHeight(y - 100);
        room.setX(100);
        room.setY(50);

        chest.setVisible(false);

        try{
            FileInputStream inputstream = new FileInputStream("Images/floor.png");
            room.setImage(new Image(inputstream));
        }catch (Exception e){

        }

        door.setX(x - 100);
        door.setY(y / 2);
        door.setFill(Color.rgb(65, 40, 20));

        try{
            FileInputStream inputstream = new FileInputStream("Images/char.png");
            mainchar.setImage(new Image(inputstream));
        }catch (Exception e){

        }
        mainchar.setX(100);

        scene = new Scene(root, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        scene.setFill(javafx.scene.paint.Color.rgb(80, 80, 80));

        root.getChildren().add(room);
        root.getChildren().add(mainchar);
        root.getChildren().add(door);
        root.getChildren().add(hp);
        root.getChildren().add(fire);
        root.getChildren().add(strike);
        root.getChildren().add(chest);
        root.getChildren().add(enemyhb);
        root.getChildren().add(chestmenubg);
        root.getChildren().add(chesttitle);
        root.getChildren().add(chestexit);
        root.getChildren().add(chestitemdisp);
        root.getChildren().add(chestcollect);
        root.getChildren().add(invbg);
        root.getChildren().add(exitinv);
        root.getChildren().add(invtitle);
        root.getChildren().add(hmatsdisplay);
        root.getChildren().add(smatsdisplay);
        
        root.getChildren().add(cf1);
        root.getChildren().add(cf2);
        root.getChildren().add(cf3);
        root.getChildren().add(cf4);
        root.getChildren().add(cf5);
        root.getChildren().add(cf6);
        root.getChildren().add(cf7);
        root.getChildren().add(cf8);
        root.getChildren().add(cf9);
        root.getChildren().add(cf10);
        root.getChildren().add(cf11);
        root.getChildren().add(cf12);
        root.getChildren().add(cf13);
        root.getChildren().add(cf14);
        root.getChildren().add(cf15);
        root.getChildren().add(cf16);
        root.getChildren().add(cf17);
        root.getChildren().add(cf18);
        root.getChildren().add(cf19);
        root.getChildren().add(cf20);
        root.getChildren().add(cf21);
        root.getChildren().add(cf22);
        root.getChildren().add(craftbtn);
        
        enemygen();
        root.getChildren().add(diedbg);
        root.getChildren().add(died);
        root.getChildren().add(playbtn2);

        dungeonstage.setScene(scene);
        dungeonstage.show();
        dungeonstage.setFullScreen(true);

        scene.setOnKeyPressed(new javafx.event.EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W){
                    mainchar.setY(mainchar.getY() - 20);
                } else if (event.getCode() == KeyCode.S){
                    mainchar.setY(mainchar.getY() + 20);
                } else if (event.getCode() == KeyCode.A){
                    mainchar.setX(mainchar.getX() - 20);
                } else if (event.getCode() == KeyCode.D){
                    mainchar.setX(mainchar.getX() + 20);
                } else if (event.getCode() == KeyCode.E){
                    inventory();
                } else if (event.getCode() == KeyCode.C){
                    crafting();
                }

                double dxrange = door.getX() - mainchar.getX();
                double dyrange = door.getY() - mainchar.getY();
                if (dyrange < 50 && dxrange < 50 && enemyhp <= 0){
                    dungeon();
                }
            }
        });

        enemy.setOnMouseClicked(event -> {
            strike.setStartX(enemy.getX() + (enemy.getFitWidth() / 2));
            strike.setStartY(enemy.getY() + (enemy.getFitHeight() / 2));
            strike.setEndX(mainchar.getX() + (enemy.getFitWidth() / 2));
            strike.setEndY(mainchar.getY() + (enemy.getFitHeight() / 2));
            strike.setStroke(Color.GREEN);
            strike.setStrokeWidth(3);

            enemyhb.setText("Enemy Health: " + enemyhp);

            enemyhp -= strength;
            if (enemyhp <= 0){
                kill();
            }

            Timer timer = new Timer();
            timer.schedule(new strikegone(), 100);
        });
        Timer timer = new Timer();
        timer.schedule(new AI(), 0, 100);
        t = timer;
    }

    public static void enemygen(){
        try{
            FileInputStream inputstream = new FileInputStream("Images/enemy.png");
            enemy.setImage(new Image(inputstream));
        }catch (Exception e){

        }

        Random rand = new Random();
        int n = rand.nextInt(24) + 1;

        if (n == 1){
            enemyname = "";
        } else if (n == 2){
            enemyname = "Strengthened ";
            enemyhp = 5;
            enemydmg = 2;
        } else if (n <= 7){
            enemyname = "Mighty ";
            enemyhp = 7;
            enemydmg = 5;
        } else if (n <= 12){
            enemyname = "Beastly ";
            enemyhp = 10;
            enemydmg = 5;
        } else if (n <= 14){
            enemyname = "Brutish ";
            enemyhp = 12;
            enemydmg = 7;
        } else if (n <= 17){
            enemyname = "Supercharged ";
            enemyhp = 15;
            enemydmg = 10;
        } else if (n <= 19){
            enemyname = "Vicious ";
            enemyhp = 25;
            enemydmg = 15;
        } else if (n <= 21){
            enemyname = "Enormous ";
            enemyhp = 30;
            enemydmg = 17;
        } else if (n <= 23){
            enemyname = "Deathly ";
            enemyhp = 35;
            enemydmg = 20;
        } else if (n == 24){
            enemyname = "Ultra ";
            enemyhp = 45;
            enemydmg = 25;
        }

        int d = rand.nextInt(30) + 1;

        if (d <= 7){
            enemyname += "Hound";
            enemyhp += 10;
            enemydmg += 5;
        } else if (d <= 13){
            enemyname += "Ghoul";
            enemyhp += 35;
            enemydmg += 15;
        } else if (d <= 17){
            enemyname += "Bear";
            enemyhp += 50;
            enemydmg += 20;
        } else if (d <= 20){
            enemyname += "Nightwalker";
            enemyhp += 75;
            enemydmg += 25;
        } else if (d <= 23){
            enemyname += "Fearmaster";
            enemyhp += 80;
            enemydmg += 27;
        } else if (d <= 25){
            enemyname += "Slime";
            enemyhp += 95;
            enemydmg += 30;
        } else if (d <= 27){
            enemyname += "Magmus";
            enemyhp += 100;
            enemydmg += 32;
        } else if (d <= 29){
            enemyname += "Wraith";
            enemyhp += 105;
            enemydmg += 35;
        } else if (d <= 31){
            enemyname += "Endifier";
            enemyhp += 115;
            enemydmg += 40;
        } else if (d == 32){
            enemyname += "Infinifier";
            enemyhp += 150;
            enemydmg += 45;
        }

        enemy.setFitWidth(50);
        enemy.setFitHeight(50);
        enemy.setX(x/2);
        enemy.setY(y/2);
        enemy.setVisible(true);

        if (enemyhp > 100){
            enemy.setFitWidth(enemy.getFitWidth() + 25);
            enemy.setFitHeight(enemy.getFitHeight() + 25);
        }
        if (enemydmg >= 30){
            enemy.setFitWidth(enemy.getFitWidth() + 25);
            enemy.setFitHeight(enemy.getFitHeight() + 25);
        }

        enbox.setText("Enemy: " + enemyname);
        enbox.setX(x - 200);

        enemyhb.setText("Enemy Health: " + enemyhp);

        root.getChildren().add(enbox);
        root.getChildren().add(enemy);

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void kill(){
        chest.setX(enemy.getX());
        chest.setY(enemy.getY());
        chest.setVisible(true);
        enemy.setVisible(false);

        chest.setOnMouseClicked(event -> {
            chestmenubg.setVisible(true);
            chesttitle.setVisible(true);
            chestexit.setVisible(true);
            chestitemdisp.setVisible(true);
            chestcollect.setVisible(true);
            if (chestgen == false) {
                contentgen();
            }

            chestitemdisp.setText(chestnum + "X " + chestitem);
        });
    }

    public static void die(){
        Group refresh = new Group();
        root = refresh;

        health = 100;
        enemyhp = 0;

        diedbg.setVisible(true);
        died.setVisible(true);
        playbtn2.setVisible(true);

        save();

    }

    public static void contentgen(){
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;

        if (n <= 10){
            chestitem = "Bark";
        } else if (n <= 17){
            chestitem = "Wood";
        } else if (n <= 24){
            chestitem = "Carbon";
        } else if (n <= 31){
            chestitem = "Magma";
        } else if (n <= 35){
            chestitem = "Fire Dust";
        } else if (n <= 39){
            chestitem = "Life Dust";
        } else if (n <= 43){
            chestitem = "Soul Serum";
        } else if (n <= 45){
            chestitem = "Mortal Powder";
        } else if (n <= 47){
            chestitem = "Mega Powder";
        } else if (n <= 49){
            chestitem = "Ultra Powder";
        } else if (n == 50){
            chestitem = "Infinitum Powder";
        } else if (n <= 60){
            chestitem = "Gold";
        } else if (n <= 67){
            chestitem = "Sapphire";
        } else if (n <= 74){
            chestitem = "Nightstone";
        } else if (n <= 81){
            chestitem = "Energy Serum";
        } else if (n <= 85){
            chestitem = "Xenon Powder";
        } else if (n <= 89){
            chestitem = "Neon Powder";
        } else if (n <= 93){
            chestitem = "Concentrated Oganesson";
        } else if (n <= 95){
            chestitem = "Energy Elixir";
        } else if (n <= 97){
            chestitem = "Purification Stone";
        } else if (n <= 99){
            chestitem = "Maximus Crystals";
        } else if (n == 100){
            chestitem = "Eternal Pearls";
        }

        chestnum = rand.nextInt(50) + 1;
        chestgen = true;
    }

    public static void load(){
        try {
            FileReader fileReader = new FileReader("c\\crawlersavegame.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            savegame = bufferedReader.readLine();

            String[] splitsave = savegame.split(" ");

            health = Integer.valueOf(splitsave[0]);
            strength = Integer.valueOf(splitsave[1]);
            bark = Integer.valueOf(splitsave[2]);
            wood = Integer.valueOf(splitsave[3]);
            carbon = Integer.valueOf(splitsave[4]);
            magma = Integer.valueOf(splitsave[5]);
            firedust = Integer.valueOf(splitsave[6]);
            lifedust = Integer.valueOf(splitsave[7]);
            soulserum = Integer.valueOf(splitsave[8]);
            mortalpowder = Integer.valueOf(splitsave[9]);
            megapowder = Integer.valueOf(splitsave[10]);
            ultrapowder = Integer.valueOf(splitsave[11]);
            infinitumpowder = Integer.valueOf(splitsave[12]);
            gold = Integer.valueOf(splitsave[13]);
            sapphire = Integer.valueOf(splitsave[14]);
            nightstone = Integer.valueOf(splitsave[15]);
            energyserum = Integer.valueOf(splitsave[16]);
            xenonpowder = Integer.valueOf(splitsave[17]);
            neonpowder = Integer.valueOf(splitsave[18]);
            concentratedoganesson = Integer.valueOf(splitsave[19]);
            energyelixir= Integer.valueOf(splitsave[20]);
            purificationstone = Integer.valueOf(splitsave[21]);
            maximuscrystals = Integer.valueOf(splitsave[22]);
            eternalpearls = Integer.valueOf(splitsave[23]);

            hp.setText("Health: " + health);

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("No game found. Creating new savegame.");
        }
        catch(java.io.IOException ex) {
            System.out.println("Error reading game file.");
        }
    }

    public static void save(){
        try {
            savegame = health + " " + strength + " " + bark + " " + wood + " " + carbon + " " + magma + " " + firedust + " " + lifedust + " " + soulserum + " " + mortalpowder + " " + megapowder + " " + ultrapowder + " " + infinitumpowder + " " + gold + " " + sapphire + " " + nightstone + " " + energyserum + " " + xenonpowder + " " + neonpowder + " " + concentratedoganesson + " " + energyelixir + " " + purificationstone + " " + maximuscrystals + " " + eternalpearls;
            File file = new File ("c\\crawlersavegame.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(savegame);
            out.close();

            boolean fvar = file.createNewFile();
            if (fvar){
                System.out.println("New game created.");
            }
            else{
                System.out.println("Loading...");
            }
        }catch (IOException e){
            System.out.println("Save Exception Occurred:");
            e.printStackTrace();
        }
    }

    public static void inventory(){
        hmatsdisplay.setText("Bark: " + bark + "\nWood: " + wood + "\nCarbon: " + carbon + "\nMagma: " + magma + "\nFire Dust: " + firedust + "\nLife Dust: " + "\nSoul Serum: " + soulserum + "\nMortal Powder: " + mortalpowder + "\nMega Powder: " + megapowder + "\nUltra Powder: " + ultrapowder + "\nInfinitum Powder: " + infinitumpowder);
        smatsdisplay.setText("Gold: " + gold + "\nSapphire: " + sapphire + "\nNightstone: " + nightstone + "\nEnergy Serum: " + energyserum + "\nXenon Powder: " + xenonpowder + "\nNeon Powder: " + neonpowder + "\nConcentrated Oganesson: " + concentratedoganesson + "\nEnergy Elixir: " + energyelixir + "\nPurification Stone: " + purificationstone + "\nMaximus Crystals: " + maximuscrystals + "\nEternal Pearls: " + eternalpearls);
        invbg.setVisible(true);
        exitinv.setVisible(true);
        invtitle.setVisible(true);
        sworddisplay.setVisible(true);
        hmatsdisplay.setVisible(true);
        smatsdisplay.setVisible(true);
    }
    
    public static void crafting(){
        cf1.setVisible(true);
        cf2.setVisible(true);
        cf3.setVisible(true);
        cf4.setVisible(true);
        cf5.setVisible(true);
        cf6.setVisible(true);
        cf7.setVisible(true);
        cf8.setVisible(true);
        cf9.setVisible(true);
        cf10.setVisible(true);
        cf11.setVisible(true);
        cf12.setVisible(true);
        cf13.setVisible(true);
        cf14.setVisible(true);
        cf15.setVisible(true);
        cf16.setVisible(true);
        cf17.setVisible(true);
        cf18.setVisible(true);
        cf19.setVisible(true);
        cf20.setVisible(true);
        cf21.setVisible(true);
        cf22.setVisible(true);
        invbg.setVisible(true);
        craftbtn.setVisible(true);

        craftbtn.setOnAction(event -> {
            cf1.setVisible(false);
            cf2.setVisible(false);
            cf3.setVisible(false);
            cf4.setVisible(false);
            cf5.setVisible(false);
            cf6.setVisible(false);
            cf7.setVisible(false);
            cf8.setVisible(false);
            cf9.setVisible(false);
            cf10.setVisible(false);
            cf11.setVisible(false);
            cf12.setVisible(false);
            cf13.setVisible(false);
            cf14.setVisible(false);
            cf15.setVisible(false);
            cf16.setVisible(false);
            cf17.setVisible(false);
            cf18.setVisible(false);
            cf19.setVisible(false);
            cf20.setVisible(false);
            cf21.setVisible(false);
            cf22.setVisible(false);
            craftbtn.setVisible(false);
            invbg.setVisible(false);
            
            if (cf1.getText().contains("A") || cf1.getText().contains("B") || cf1.getText().contains("C") || cf1.getText().contains("D") || cf1.getText().contains("E") || cf1.getText().contains("F") || cf1.getText().contains("G") || cf1.getText().contains("H") || cf1.getText().contains("I") || cf1.getText().contains("J") || cf1.getText().contains("K") || cf1.getText().contains("L") || cf1.getText().contains("M") || cf1.getText().contains("N") || cf1.getText().contains("O") || cf1.getText().contains("P") || cf1.getText().contains("Q") || cf1.getText().contains("R") || cf1.getText().contains("S") || cf1.getText().contains("T") || cf1.getText().contains("U") || cf1.getText().contains("V") || cf1.getText().contains("W") || cf1.getText().contains("X") || cf1.getText().contains("Y") || cf1.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (1 * Integer.valueOf(cf1.getText()));
                bark -= Integer.valueOf(cf1.getText());
            }

            if (cf2.getText().contains("A") || cf2.getText().contains("B") || cf2.getText().contains("C") || cf2.getText().contains("D") || cf2.getText().contains("E") || cf2.getText().contains("F") || cf2.getText().contains("G") || cf2.getText().contains("H") || cf2.getText().contains("I") || cf2.getText().contains("J") || cf2.getText().contains("K") || cf2.getText().contains("L") || cf2.getText().contains("M") || cf2.getText().contains("N") || cf2.getText().contains("O") || cf2.getText().contains("P") || cf2.getText().contains("Q") || cf2.getText().contains("R") || cf2.getText().contains("S") || cf2.getText().contains("T") || cf2.getText().contains("U") || cf2.getText().contains("V") || cf2.getText().contains("W") || cf2.getText().contains("X") || cf2.getText().contains("Y") || cf2.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (2 * Integer.valueOf(cf2.getText()));
                wood -= Integer.valueOf(cf2.getText());
            }
            
            if (cf3.getText().contains("A") || cf3.getText().contains("B") || cf3.getText().contains("C") || cf3.getText().contains("D") || cf3.getText().contains("E") || cf3.getText().contains("F") || cf3.getText().contains("G") || cf3.getText().contains("H") || cf3.getText().contains("I") || cf3.getText().contains("J") || cf3.getText().contains("K") || cf3.getText().contains("L") || cf3.getText().contains("M") || cf3.getText().contains("N") || cf3.getText().contains("O") || cf3.getText().contains("P") || cf3.getText().contains("Q") || cf3.getText().contains("R") || cf3.getText().contains("S") || cf3.getText().contains("T") || cf3.getText().contains("U") || cf3.getText().contains("V") || cf3.getText().contains("W") || cf3.getText().contains("X") || cf3.getText().contains("Y") || cf3.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (5 * Integer.valueOf(cf3.getText()));
                carbon -= Integer.valueOf(cf3.getText());
            }
            
            if (cf4.getText().contains("A") || cf4.getText().contains("B") || cf4.getText().contains("C") || cf4.getText().contains("D") || cf4.getText().contains("E") || cf4.getText().contains("F") || cf4.getText().contains("G") || cf4.getText().contains("H") || cf4.getText().contains("I") || cf4.getText().contains("J") || cf4.getText().contains("K") || cf4.getText().contains("L") || cf4.getText().contains("M") || cf4.getText().contains("N") || cf4.getText().contains("O") || cf4.getText().contains("P") || cf4.getText().contains("Q") || cf4.getText().contains("R") || cf4.getText().contains("S") || cf4.getText().contains("T") || cf4.getText().contains("U") || cf4.getText().contains("V") || cf4.getText().contains("W") || cf4.getText().contains("X") || cf4.getText().contains("Y") || cf4.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (10 * Integer.valueOf(cf4.getText()));
                magma -= Integer.valueOf(cf4.getText());
            }
            
            if (cf5.getText().contains("A") || cf5.getText().contains("B") || cf5.getText().contains("C") || cf5.getText().contains("D") || cf5.getText().contains("E") || cf5.getText().contains("F") || cf5.getText().contains("G") || cf5.getText().contains("H") || cf5.getText().contains("I") || cf5.getText().contains("J") || cf5.getText().contains("K") || cf5.getText().contains("L") || cf5.getText().contains("M") || cf5.getText().contains("N") || cf5.getText().contains("O") || cf5.getText().contains("P") || cf5.getText().contains("Q") || cf5.getText().contains("R") || cf5.getText().contains("S") || cf5.getText().contains("T") || cf5.getText().contains("U") || cf5.getText().contains("V") || cf5.getText().contains("W") || cf5.getText().contains("X") || cf5.getText().contains("Y") || cf5.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (20 * Integer.valueOf(cf5.getText()));
                firedust -= Integer.valueOf(cf5.getText());
            }
            
            if (cf6.getText().contains("A") || cf6.getText().contains("B") || cf6.getText().contains("C") || cf6.getText().contains("D") || cf6.getText().contains("E") || cf6.getText().contains("F") || cf6.getText().contains("G") || cf6.getText().contains("H") || cf6.getText().contains("I") || cf6.getText().contains("J") || cf6.getText().contains("K") || cf6.getText().contains("L") || cf6.getText().contains("M") || cf6.getText().contains("N") || cf6.getText().contains("O") || cf6.getText().contains("P") || cf6.getText().contains("Q") || cf6.getText().contains("R") || cf6.getText().contains("S") || cf6.getText().contains("T") || cf6.getText().contains("U") || cf6.getText().contains("V") || cf6.getText().contains("W") || cf6.getText().contains("X") || cf6.getText().contains("Y") || cf6.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (25 * Integer.valueOf(cf6.getText()));
                lifedust -= Integer.valueOf(cf6.getText());
            }
            
            if (cf7.getText().contains("A") || cf7.getText().contains("B") || cf7.getText().contains("C") || cf7.getText().contains("D") || cf7.getText().contains("E") || cf7.getText().contains("F") || cf7.getText().contains("G") || cf7.getText().contains("H") || cf7.getText().contains("I") || cf7.getText().contains("J") || cf7.getText().contains("K") || cf7.getText().contains("L") || cf7.getText().contains("M") || cf7.getText().contains("N") || cf7.getText().contains("O") || cf7.getText().contains("P") || cf7.getText().contains("Q") || cf7.getText().contains("R") || cf7.getText().contains("S") || cf7.getText().contains("T") || cf7.getText().contains("U") || cf7.getText().contains("V") || cf7.getText().contains("W") || cf7.getText().contains("X") || cf7.getText().contains("Y") || cf7.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (30 * Integer.valueOf(cf7.getText()));
                soulserum -= Integer.valueOf(cf7.getText());
            }
            
            if (cf8.getText().contains("A") || cf8.getText().contains("B") || cf8.getText().contains("C") || cf8.getText().contains("D") || cf8.getText().contains("E") || cf8.getText().contains("F") || cf8.getText().contains("G") || cf8.getText().contains("H") || cf8.getText().contains("I") || cf8.getText().contains("J") || cf8.getText().contains("K") || cf8.getText().contains("L") || cf8.getText().contains("M") || cf8.getText().contains("N") || cf8.getText().contains("O") || cf8.getText().contains("P") || cf8.getText().contains("Q") || cf8.getText().contains("R") || cf8.getText().contains("S") || cf8.getText().contains("T") || cf8.getText().contains("U") || cf8.getText().contains("V") || cf8.getText().contains("W") || cf8.getText().contains("X") || cf8.getText().contains("Y") || cf8.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (38 * Integer.valueOf(cf8.getText()));
                mortalpowder -= Integer.valueOf(cf8.getText());
            }
            
            if (cf9.getText().contains("A") || cf9.getText().contains("B") || cf9.getText().contains("C") || cf9.getText().contains("D") || cf9.getText().contains("E") || cf9.getText().contains("F") || cf9.getText().contains("G") || cf9.getText().contains("H") || cf9.getText().contains("I") || cf9.getText().contains("J") || cf9.getText().contains("K") || cf9.getText().contains("L") || cf9.getText().contains("M") || cf9.getText().contains("N") || cf9.getText().contains("O") || cf9.getText().contains("P") || cf9.getText().contains("Q") || cf9.getText().contains("R") || cf9.getText().contains("S") || cf9.getText().contains("T") || cf9.getText().contains("U") || cf9.getText().contains("V") || cf9.getText().contains("W") || cf9.getText().contains("X") || cf9.getText().contains("Y") || cf9.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (45 * Integer.valueOf(cf9.getText()));
                megapowder -= Integer.valueOf(cf9.getText());
            }
            
            if (cf10.getText().contains("A") || cf10.getText().contains("B") || cf10.getText().contains("C") || cf10.getText().contains("D") || cf10.getText().contains("E") || cf10.getText().contains("F") || cf10.getText().contains("G") || cf10.getText().contains("H") || cf10.getText().contains("I") || cf10.getText().contains("J") || cf10.getText().contains("K") || cf10.getText().contains("L") || cf10.getText().contains("M") || cf10.getText().contains("N") || cf10.getText().contains("O") || cf10.getText().contains("P") || cf10.getText().contains("Q") || cf10.getText().contains("R") || cf10.getText().contains("S") || cf10.getText().contains("T") || cf10.getText().contains("U") || cf10.getText().contains("V") || cf10.getText().contains("W") || cf10.getText().contains("X") || cf10.getText().contains("Y") || cf10.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (50 * Integer.valueOf(cf10.getText()));
                ultrapowder -= Integer.valueOf(cf10.getText());
            }
            if (cf11.getText().contains("A") || cf11.getText().contains("B") || cf11.getText().contains("C") || cf11.getText().contains("D") || cf11.getText().contains("E") || cf11.getText().contains("F") || cf11.getText().contains("G") || cf11.getText().contains("H") || cf11.getText().contains("I") || cf11.getText().contains("J") || cf11.getText().contains("K") || cf11.getText().contains("L") || cf11.getText().contains("M") || cf11.getText().contains("N") || cf11.getText().contains("O") || cf11.getText().contains("P") || cf11.getText().contains("Q") || cf11.getText().contains("R") || cf11.getText().contains("S") || cf11.getText().contains("T") || cf11.getText().contains("U") || cf11.getText().contains("V") || cf11.getText().contains("W") || cf11.getText().contains("X") || cf11.getText().contains("Y") || cf11.getText().contains("Z")){
                healthincrease += 0;
            } else {
                healthincrease += (75 * Integer.valueOf(cf11.getText()));
                infinitumpowder -= Integer.valueOf(cf11.getText());
            }
            if (cf12.getText().contains("A") || cf12.getText().contains("B") || cf12.getText().contains("C") || cf12.getText().contains("D") || cf12.getText().contains("E") || cf12.getText().contains("F") || cf12.getText().contains("G") || cf12.getText().contains("H") || cf12.getText().contains("I") || cf12.getText().contains("J") || cf12.getText().contains("K") || cf12.getText().contains("L") || cf12.getText().contains("M") || cf12.getText().contains("N") || cf12.getText().contains("O") || cf12.getText().contains("P") || cf12.getText().contains("Q") || cf12.getText().contains("R") || cf12.getText().contains("S") || cf12.getText().contains("T") || cf12.getText().contains("U") || cf12.getText().contains("V") || cf12.getText().contains("W") || cf12.getText().contains("X") || cf12.getText().contains("Y") || cf12.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (1 * Integer.valueOf(cf12.getText()));
                gold -= Integer.valueOf(cf12.getText());
            }
            if (cf13.getText().contains("A") || cf13.getText().contains("B") || cf13.getText().contains("C") || cf13.getText().contains("D") || cf13.getText().contains("E") || cf13.getText().contains("F") || cf13.getText().contains("G") || cf13.getText().contains("H") || cf13.getText().contains("I") || cf13.getText().contains("J") || cf13.getText().contains("K") || cf13.getText().contains("L") || cf13.getText().contains("M") || cf13.getText().contains("N") || cf13.getText().contains("O") || cf13.getText().contains("P") || cf13.getText().contains("Q") || cf13.getText().contains("R") || cf13.getText().contains("S") || cf13.getText().contains("T") || cf13.getText().contains("U") || cf13.getText().contains("V") || cf13.getText().contains("W") || cf13.getText().contains("X") || cf13.getText().contains("Y") || cf13.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (2 * Integer.valueOf(cf13.getText()));
                sapphire -= Integer.valueOf(cf13.getText());
            }
            if (cf14.getText().contains("A") || cf14.getText().contains("B") || cf14.getText().contains("C") || cf14.getText().contains("D") || cf14.getText().contains("E") || cf14.getText().contains("F") || cf14.getText().contains("G") || cf14.getText().contains("H") || cf14.getText().contains("I") || cf14.getText().contains("J") || cf14.getText().contains("K") || cf14.getText().contains("L") || cf14.getText().contains("M") || cf14.getText().contains("N") || cf14.getText().contains("O") || cf14.getText().contains("P") || cf14.getText().contains("Q") || cf14.getText().contains("R") || cf14.getText().contains("S") || cf14.getText().contains("T") || cf14.getText().contains("U") || cf14.getText().contains("V") || cf14.getText().contains("W") || cf14.getText().contains("X") || cf14.getText().contains("Y") || cf14.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (4 * Integer.valueOf(cf14.getText()));
                nightstone -= Integer.valueOf(cf14.getText());
            }
            if (cf15.getText().contains("A") || cf15.getText().contains("B") || cf15.getText().contains("C") || cf15.getText().contains("D") || cf15.getText().contains("E") || cf15.getText().contains("F") || cf15.getText().contains("G") || cf15.getText().contains("H") || cf15.getText().contains("I") || cf15.getText().contains("J") || cf15.getText().contains("K") || cf15.getText().contains("L") || cf15.getText().contains("M") || cf15.getText().contains("N") || cf15.getText().contains("O") || cf15.getText().contains("P") || cf15.getText().contains("Q") || cf15.getText().contains("R") || cf15.getText().contains("S") || cf15.getText().contains("T") || cf15.getText().contains("U") || cf15.getText().contains("V") || cf15.getText().contains("W") || cf15.getText().contains("X") || cf15.getText().contains("Y") || cf15.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (8 * Integer.valueOf(cf15.getText()));
                energyserum -= Integer.valueOf(cf15.getText());
            }
            if (cf16.getText().contains("A") || cf16.getText().contains("B") || cf16.getText().contains("C") || cf16.getText().contains("D") || cf16.getText().contains("E") || cf16.getText().contains("F") || cf16.getText().contains("G") || cf16.getText().contains("H") || cf16.getText().contains("I") || cf16.getText().contains("J") || cf16.getText().contains("K") || cf16.getText().contains("L") || cf16.getText().contains("M") || cf16.getText().contains("N") || cf16.getText().contains("O") || cf16.getText().contains("P") || cf16.getText().contains("Q") || cf16.getText().contains("R") || cf16.getText().contains("S") || cf16.getText().contains("T") || cf16.getText().contains("U") || cf16.getText().contains("V") || cf16.getText().contains("W") || cf16.getText().contains("X") || cf16.getText().contains("Y") || cf16.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (12 * Integer.valueOf(cf16.getText()));
                xenonpowder -= Integer.valueOf(cf16.getText());
            }
            if (cf17.getText().contains("A") || cf17.getText().contains("B") || cf17.getText().contains("C") || cf17.getText().contains("D") || cf17.getText().contains("E") || cf17.getText().contains("F") || cf17.getText().contains("G") || cf17.getText().contains("H") || cf17.getText().contains("I") || cf17.getText().contains("J") || cf17.getText().contains("K") || cf17.getText().contains("L") || cf17.getText().contains("M") || cf17.getText().contains("N") || cf17.getText().contains("O") || cf17.getText().contains("P") || cf17.getText().contains("Q") || cf17.getText().contains("R") || cf17.getText().contains("S") || cf17.getText().contains("T") || cf17.getText().contains("U") || cf17.getText().contains("V") || cf17.getText().contains("W") || cf17.getText().contains("X") || cf17.getText().contains("Y") || cf17.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (15 * Integer.valueOf(cf17.getText()));
                neonpowder -= Integer.valueOf(cf17.getText());
            }
            if (cf18.getText().contains("A") || cf18.getText().contains("B") || cf18.getText().contains("C") || cf18.getText().contains("D") || cf18.getText().contains("E") || cf18.getText().contains("F") || cf18.getText().contains("G") || cf18.getText().contains("H") || cf18.getText().contains("I") || cf18.getText().contains("J") || cf18.getText().contains("K") || cf18.getText().contains("L") || cf18.getText().contains("M") || cf18.getText().contains("N") || cf18.getText().contains("O") || cf18.getText().contains("P") || cf18.getText().contains("Q") || cf18.getText().contains("R") || cf18.getText().contains("S") || cf18.getText().contains("T") || cf18.getText().contains("U") || cf18.getText().contains("V") || cf18.getText().contains("W") || cf18.getText().contains("X") || cf18.getText().contains("Y") || cf18.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (17 * Integer.valueOf(cf18.getText()));
                concentratedoganesson -= Integer.valueOf(cf18.getText());
            }
            if (cf19.getText().contains("A") || cf19.getText().contains("B") || cf19.getText().contains("C") || cf19.getText().contains("D") || cf19.getText().contains("E") || cf19.getText().contains("F") || cf19.getText().contains("G") || cf19.getText().contains("H") || cf19.getText().contains("I") || cf19.getText().contains("J") || cf19.getText().contains("K") || cf19.getText().contains("L") || cf19.getText().contains("M") || cf19.getText().contains("N") || cf19.getText().contains("O") || cf19.getText().contains("P") || cf19.getText().contains("Q") || cf19.getText().contains("R") || cf19.getText().contains("S") || cf19.getText().contains("T") || cf19.getText().contains("U") || cf19.getText().contains("V") || cf19.getText().contains("W") || cf19.getText().contains("X") || cf19.getText().contains("Y") || cf19.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (20 * Integer.valueOf(cf19.getText()));
                energyelixir -= Integer.valueOf(cf19.getText());
            }
            if (cf20.getText().contains("A") || cf20.getText().contains("B") || cf20.getText().contains("C") || cf20.getText().contains("D") || cf20.getText().contains("E") || cf20.getText().contains("F") || cf20.getText().contains("G") || cf20.getText().contains("H") || cf20.getText().contains("I") || cf20.getText().contains("J") || cf20.getText().contains("K") || cf20.getText().contains("L") || cf20.getText().contains("M") || cf20.getText().contains("N") || cf20.getText().contains("O") || cf20.getText().contains("P") || cf20.getText().contains("Q") || cf20.getText().contains("R") || cf20.getText().contains("S") || cf20.getText().contains("T") || cf20.getText().contains("U") || cf20.getText().contains("V") || cf20.getText().contains("W") || cf20.getText().contains("X") || cf20.getText().contains("Y") || cf20.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (25 * Integer.valueOf(cf20.getText()));
                purificationstone -= Integer.valueOf(cf20.getText());
            }
            if (cf21.getText().contains("A") || cf21.getText().contains("B") || cf21.getText().contains("C") || cf21.getText().contains("D") || cf21.getText().contains("E") || cf21.getText().contains("F") || cf21.getText().contains("G") || cf21.getText().contains("H") || cf21.getText().contains("I") || cf21.getText().contains("J") || cf21.getText().contains("K") || cf21.getText().contains("L") || cf21.getText().contains("M") || cf21.getText().contains("N") || cf21.getText().contains("O") || cf21.getText().contains("P") || cf21.getText().contains("Q") || cf21.getText().contains("R") || cf21.getText().contains("S") || cf21.getText().contains("T") || cf21.getText().contains("U") || cf21.getText().contains("V") || cf21.getText().contains("W") || cf21.getText().contains("X") || cf21.getText().contains("Y") || cf21.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (30 * Integer.valueOf(cf21.getText()));
                maximuscrystals -= Integer.valueOf(cf21.getText());
            }
            if (cf22.getText().contains("A") || cf22.getText().contains("B") || cf22.getText().contains("C") || cf22.getText().contains("D") || cf22.getText().contains("E") || cf22.getText().contains("F") || cf22.getText().contains("G") || cf22.getText().contains("H") || cf22.getText().contains("I") || cf22.getText().contains("J") || cf22.getText().contains("K") || cf22.getText().contains("L") || cf22.getText().contains("M") || cf22.getText().contains("N") || cf22.getText().contains("O") || cf22.getText().contains("P") || cf22.getText().contains("Q") || cf22.getText().contains("R") || cf22.getText().contains("S") || cf22.getText().contains("T") || cf22.getText().contains("U") || cf22.getText().contains("V") || cf22.getText().contains("W") || cf22.getText().contains("X") || cf22.getText().contains("Y") || cf22.getText().contains("Z")){
                strengthincrease += 0;
            } else {
                strengthincrease += (40 * Integer.valueOf(cf22.getText()));
                eternalpearls -= Integer.valueOf(cf22.getText());
            }

            if (bark < 0 || wood < 0 || carbon < 0 || magma < 0 || firedust < 0 || lifedust < 0 || soulserum < 0 || mortalpowder < 0 || megapowder < 0 || ultrapowder < 0 || infinitumpowder < 0 || gold < 0 || sapphire < 0 || energyserum < 0 || nightstone < 0 || xenonpowder < 0 || neonpowder < 0 || concentratedoganesson < 0 || energyelixir < 0 || purificationstone < 0 || maximuscrystals < 0 || eternalpearls < 0){
                try{
                    bark += Integer.valueOf(cf1.getText());
                }catch (Exception e){
                    
                }
                try{
                    wood += Integer.valueOf(cf2.getText());
                }catch (Exception e){

                }
                try{
                    carbon += Integer.valueOf(cf3.getText());
                }catch (Exception e){

                }
                try{
                    magma += Integer.valueOf(cf4.getText());
                }catch (Exception e){

                }
                try{
                    firedust += Integer.valueOf(cf5.getText());
                }catch (Exception e){

                }
                try{
                    lifedust += Integer.valueOf(cf6.getText());
                }catch (Exception e){

                }
                try{
                    soulserum += Integer.valueOf(cf7.getText());
                }catch (Exception e){

                }
                try{
                    mortalpowder += Integer.valueOf(cf8.getText());
                }catch (Exception e){

                }
                try{
                    megapowder += Integer.valueOf(cf9.getText());
                }catch (Exception e){

                }
                try{
                    ultrapowder += Integer.valueOf(cf10.getText());
                }catch (Exception e){

                }
                try{
                    infinitumpowder += Integer.valueOf(cf11.getText());
                }catch (Exception e){

                }
                try{
                    gold += Integer.valueOf(cf12.getText());
                }catch (Exception e){

                }
                try{
                    sapphire += Integer.valueOf(cf13.getText());
                }catch (Exception e){

                }
                try{
                    nightstone += Integer.valueOf(cf14.getText());
                }catch (Exception e){

                }
                try{
                    energyserum += Integer.valueOf(cf15.getText());
                }catch (Exception e){

                }
                try{
                    xenonpowder += Integer.valueOf(cf16.getText());
                }catch (Exception e){

                }
                try{
                    neonpowder += Integer.valueOf(cf17.getText());
                }catch (Exception e){

                }
                try{
                    concentratedoganesson += Integer.valueOf(cf18.getText());
                }catch (Exception e){

                }
                try{
                    energyelixir += Integer.valueOf(cf19.getText());
                }catch (Exception e){

                }
                try{
                    purificationstone += Integer.valueOf(cf20.getText());
                }catch (Exception e){

                }
                try{
                    maximuscrystals += Integer.valueOf(cf21.getText());
                }catch (Exception e){

                }
                try{
                    eternalpearls += Integer.valueOf(cf22.getText());
                }catch (Exception e){

                }
            }

            cf1.setText("Bark");
            cf2.setText("Wood");
            cf3.setText("Carbon");
            cf4.setText("Magma");
            cf5.setText("Fire Dust");
            cf6.setText("Life Dust");
            cf7.setText("Soul Serum");
            cf8.setText("Mortal Powder");
            cf9.setText("Mega Powder");
            cf10.setText("Ultra Powder");
            cf11.setText("Infinitum Powder");
            cf12.setText("Gold");
            cf13.setText("Sapphire");
            cf14.setText("Nightstone");
            cf15.setText("Energy Serum");
            cf16.setText("Xenon Powder");
            cf17.setText("Neon Powder");
            cf18.setText("Concentrated Oganesson");
            cf19.setText("Energy Elixir");
            cf20.setText("Purification Stone");
            cf21.setText("Maximus Crystals");
            cf22.setText("Eternal Pearls");

            health += healthincrease;
            strength += strengthincrease;
            hp.setText("Health: " + health);
        });
    }
}

class AI extends TimerTask{
    int attack = 0;

    public void run() {
        attack++;

        if (mainchar.getX() > enemy.getX()){
            enemy.setX(enemy.getX() + 10);
        } else if (mainchar.getX() < enemy.getX()){
            enemy.setX(enemy.getX() - 10);
        } else if (mainchar.getY() < enemy.getY()){
            enemy.setY(enemy.getY() - 10);
        } else if (mainchar.getY() > enemy.getY()){
            enemy.setY(enemy.getY() + 10);
        }

        if (attack == 1){
            fire.setStrokeWidth(0);
        }

        if (attack == 50 && enemyhp > 0){
            fire.setStartX(enemy.getX() + (enemy.getFitWidth() / 2));
            fire.setStartY(enemy.getY() + (enemy.getFitHeight() / 2));
            fire.setEndX(mainchar.getX() + (enemy.getFitWidth() / 2));
            fire.setEndY(mainchar.getY() + (enemy.getFitHeight() / 2));
            fire.setStroke(Color.RED);
            fire.setStrokeWidth(3);
            Main.health -= Main.enemydmg;
            Main.hp.setText("Health: " + Main.health);
            attack = 0;
        }

        if (Main.health <= 0){
            die();
        }

        if (enemyhp <= 0){
            enemyhb.setText("Enemy Health: 0");
            t.cancel();
        }
    }

}

class strikegone extends TimerTask{
    public void run(){
        Main.strike.setStrokeWidth(0);
    }
}