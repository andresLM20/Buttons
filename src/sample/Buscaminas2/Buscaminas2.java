package sample.Buscaminas2;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Buscaminas.Buscaminas;
import sample.Eventos.EventoBuscar;

import static sun.plugin.javascript.navig.JSType.Image;

public class Buscaminas2 extends Stage implements EventHandler<MouseEvent> {
    public Scene scene;
    public HBox HBox;
    public Label lblNoRows, lblNoColumns;
    public TextField txtNoRows, txtNoColumns;
    public Button btnMinar;
    public VBox VBox;
    public  int nr=0, nc=0;
    public int total=0;
    public Pane root;
    public RandomAccessFile minaArchivo;
    public int TR=0;
    public long bombs=0;
    public MouseEvent event;
    public RandomAccessFile fl;


    public Tile[][] grid;
    public  int TILE_SIZE = 40;

    public  int X_TILES = nc;
    public  int Y_TILES = nr;

    public Parent createContent() {
        root = new Pane();
        root.setPrefSize(800, 600);

        //ME QUEDE EN ESTA PARTE, HAY QUE OBTENER HASBOMBSN POR MEDIO DE DOS FOR ANIDADOS OBTENIDOS A PARTIR DEL ARCHIVO Y SU ID
        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                boolean hasBombSN = Math.random()<0.25;
                Tile tile = new Tile(x, y,hasBombSN);
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        //************************************************************************************
        TR=0;
        try {
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nc ; j++) {
                    //System.out.println(TR);
                    grid[i][j].setId(TR+"");
                    TR++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //************************************************************************************
        int porcentajeMinado = (int)(total*0.25);
        int numMina=0;
        try{
            minaArchivo = new RandomAccessFile("Buscaminas.dat","rw");
            for (int i = 0; i < total; i++) {
                int faltantes = porcentajeMinado - numMina;
                int quedantes = total - (i+1);
                double valor = Math.random();
                byte minado = 0;
                if (valor < 0.5) {
                    if(numMina == porcentajeMinado){
                        //minado=1;
                    }else {
                        if (quedantes <= faltantes) {
                            minado = 1;
                            numMina++;
                        } else {
                            minado = 0;
                        }
                    }
                }else{
                    if(numMina == porcentajeMinado){
                        minado = 0;
                    }else{
                        minado = 1;
                        numMina++;
                    }
                }
                minaArchivo.write(minado);
            }
            minaArchivo.seek(0);
            minaArchivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        //***************************************************************************************************

        for (int a = 0; a < Y_TILES; a++) {
            for (int b = 0; b < X_TILES; b++) {
                if (grid[a][b].hasBomb)
                    continue;
                bombs = getNeighbors(grid[a][b]).stream().filter(t -> t.hasBomb).count();
                if (bombs > 0)
                    grid[a][b].text.setText(String.valueOf(bombs));
            }
        }
        return root;
    }

    public List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt

        int[] points = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }

    @Override
    public void handle(MouseEvent event) {
        nr = Integer.parseInt(this.txtNoRows.getText());
        nc = Integer.parseInt(this.txtNoColumns.getText());
        total = nr*nc;
        btnMinar.setDisable(true);
        X_TILES = nc;
        Y_TILES = nr;
        grid = new Tile[nr][nc];

            if (nr == nc) {
                VBox.getChildren().add(createContent());
                /*if(!root.getChildren().isEmpty()){
                    root.getChildren().clear();
                    VBox.getChildren().remove(1);
                }*/
            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Revisa los datos");
                alert.setContentText("Los valores tienen que ser iguales");
                alert.showAndWait();
                btnMinar.setDisable(false);
        }

    }

    public class Tile extends StackPane {
        public int x, y;
        public boolean hasBomb;
        public boolean isOpen = false;

        public Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        public Text text = new Text();

        public Tile(int m, int n, boolean hasBomb) {
            this.x = m;
            this.y = n;
            this.hasBomb = hasBomb;

            border.setStroke(Color.LIGHTGRAY);
            border.setFill(Color.GRAY);

            text.setFont(Font.font(18));
            text.setText(hasBomb ? "X" : "");
            text.setVisible(true);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open()); //Evento cuando se clickea una celda
        }

        public void open() {

            if (isOpen)
                return;

            if (hasBomb) {
                for (int a = 0; a < Y_TILES; a++) {
                    for (int b = 0; b < X_TILES; b++) {
                        if(grid[a][b].hasBomb){
                            grid[a][b].border.setFill(Color.RED);
                        }
                    }
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("Juego terminado");
                alert.setContentText("Empieza de nuevo :)");
                alert.showAndWait();
                root.getChildren().clear();
                VBox.getChildren().remove(1);
                btnMinar.setDisable(false);

                return;
            }

            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
                }
            }
        }

    public Buscaminas2() {
        HBox = new HBox();
        VBox = new VBox();
        lblNoRows = new Label("No Rows");
        lblNoColumns = new Label("No Columns");
        txtNoRows = new TextField();
        txtNoColumns = new TextField();
        btnMinar = new Button("Minar Campo");
        btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED,this::handle);
        HBox.getChildren().addAll(lblNoRows, txtNoRows, lblNoColumns, txtNoColumns, btnMinar);

        VBox.getChildren().addAll(HBox);
        scene = new Scene(VBox,500,350);
        this.setScene(scene);
        this.show();
    }
}
