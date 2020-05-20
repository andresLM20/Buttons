package sample.Eventos;

import javafx.scene.paint.Color;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;


public class Tiles extends StackPane {

    Button btn = new Button();
    int x, y = 0;
    boolean hasBomb;
    int numBombs = 0;
    Color color = null;
    boolean flagged = false;
    ArrayList<Tiles> neighbours = new ArrayList<Tiles>();
    boolean active = true;

    static Image flag = new Image("application/flag.png");

    public Tiles(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        /*if (hasBomb) {
            Main.numBombs++;
        }*/

        btn.setMinHeight(40);
        btn.setMinWidth(40);

        btn.setOnMouseClicked(e -> {
            //onClick(e);
        });

        getChildren().addAll(btn);

        setTranslateX(x * 40);
        setTranslateY(y * 40);

    }
}