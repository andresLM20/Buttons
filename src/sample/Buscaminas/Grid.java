package sample.Buscaminas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Eventos.EventoBuscar;

import javax.swing.*;
import java.io.RandomAccessFile;

public class Grid extends Parent{
    int TR = 0;
    RandomAccessFile minaArchivo;
    int countColumn;
    int countRow;
    int countBomb;
    int banderas=0;
    Box[][] Celda;
    VBox vboxLayout;
    Contenedor Contenedor;
    boolean juegoFinalizado = false;

    private class Contenedor extends Parent{
        Text minasRestantes = new Text();
        Button btnRestart = new Button("REINICIAR PARTIDA");

        public Contenedor() {
            Text lblminasRestantes = new Text("Minas Marcadas:");
            HBox hBox = new HBox(10);
            btnRestart.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    Reiniciar();
                    juegoFinalizado=false;
                }
            });
            hBox.getChildren().addAll(btnRestart,lblminasRestantes,minasRestantes);
            this.getChildren().add(hBox);
            hBox.setAlignment(Pos.CENTER);
            //hBox.prefHeight(0);
            //hBox.minHeight(100);
            hBox.setFillHeight(true);
        }

        public void ponerMinas(int minasR){
            minasRestantes.setText(Integer.toString(minasR));
        }
    }
    //****************************************************************************************************************

    private void mostrarMinas(Box CeldaClicked) {
        CeldaClicked.ponerCubierta(false);
        if(CeldaClicked.getBombCount()>0 ){
            CeldaClicked.showBombCount();
        }else if(CeldaClicked.getBombCount()==0){
            int posRow=CeldaClicked.getBoxRow();
            int posCol=CeldaClicked.getBoxCol();

            if (!((posRow-1) < 0 ) && !((posCol-1) < 0) && !Celda[posRow-1][posCol-1].tieneBomba() && Celda[posRow-1][posCol-1].estaCubierta() && !Celda[posRow-1][posCol-1].bandera()){
                mostrarMinas(Celda[posRow-1][posCol-1]);
            }
            if (!((posCol-1) < 0 ) && !Celda[posRow][posCol-1].tieneBomba() && Celda[posRow][posCol-1].estaCubierta() && !Celda[posRow][posCol-1].bandera()){
                mostrarMinas(Celda[posRow][posCol-1]);
            }
            if (!((posRow+1) >= countRow ) && !((posCol-1) < 0) && !Celda[posRow+1][posCol-1].tieneBomba() && !((posCol-1) < 0) && Celda[posRow+1][posCol-1].estaCubierta() && !Celda[posRow+1][posCol-1].bandera()){
                mostrarMinas(Celda[posRow+1][posCol-1]);
            }
            if (!((posRow+1) >= countRow ) && !Celda[posRow+1][posCol].tieneBomba() && Celda[posRow+1][posCol].estaCubierta() && !Celda[posRow+1][posCol].bandera()){
                mostrarMinas(Celda[posRow+1][posCol]);
            }
            if (!((posRow+1) >= countRow ) && !((posCol+1) >= countColumn ) && !Celda[posRow+1][posCol+1].tieneBomba() && Celda[posRow+1][posCol+1].estaCubierta() && !Celda[posRow+1][posCol+1].bandera()){
                mostrarMinas(Celda[posRow+1][posCol+1]);
            }
            if (!((posCol+1) >= countColumn) && !Celda[posRow][posCol+1].tieneBomba() && Celda[posRow][posCol+1].estaCubierta() && !Celda[posRow][posCol+1].bandera()){
                mostrarMinas(Celda[posRow][posCol+1]);
            }
            if (!((posRow-1) < 0 ) && !((posCol+1) >= countColumn) && !Celda[posRow-1][posCol+1].tieneBomba() && Celda[posRow-1][posCol+1].estaCubierta() && !Celda[posRow-1][posCol+1].bandera()){
                mostrarMinas(Celda[posRow-1][posCol+1]);
            }
            if (!((posRow-1) < 0 ) && !Celda[posRow-1][posCol].tieneBomba() && Celda[posRow-1][posCol].estaCubierta() && !Celda[posRow-1][posCol].bandera()){
                mostrarMinas(Celda[posRow-1][posCol]);
            }
        }
    }

    void revisarBombasconBand(){
        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                if(Celda[i][j].tieneBomba() && !Celda[i][j].bandera()){
                    return;
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("Juego terminado");
        alert.setContentText("GANASTEEEEEEEE!!!!!!! :)");
        alert.showAndWait();
        juegoFinalizado=true;
    }

    public Grid(int columns,int rows) {
        countColumn=columns;
        countRow=rows;
        Celda=new Box[countRow][countColumn];
        Contenedor = new Contenedor();
        vboxLayout = new VBox(5);
        vboxLayout.getChildren().add(Contenedor);
        //vboxLayout.setAlignment(Pos.TOP_CENTER);

        for (int i = 0; i < countRow; i++) {
            HBox hboxLayout=new HBox(2);
            for (int j = 0; j < columns; j++) {
                Celda[i][j]=new Box(35,35);
                Celda[i][j].setBoxPosition(i,j);
                Celda[i][j].setOnMouseClicked(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent event) {
                        Box CeldaClicked=(Box)event.getSource();
                        if(juegoFinalizado)
                            return;
                        if(event.getButton()==MouseButton.PRIMARY){
                            if(CeldaClicked.tieneBomba() && !CeldaClicked.bandera()){
                                for (int i = 0; i < countRow; i++) {
                                    for (int j = 0; j < countColumn; j++) {
                                        if(Celda[i][j].tieneBomba()){
                                            Celda[i][j].explotarBomba();
                                        }
                                    }
                                }
                                juegoFinalizado=true;
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("GAME OVER");
                                alert.setHeaderText("Juego terminado");
                                alert.setContentText("PERDISTEEEE!!! :(");
                                alert.showAndWait();
                            }else{
                                if(!CeldaClicked.bandera()){
                                    mostrarMinas(CeldaClicked);
                                }
                            }
                        }else if(event.getButton()==MouseButton.SECONDARY){
                            CeldaClicked.ponerBandera(!CeldaClicked.bandera());
                            banderas=CeldaClicked.tieneBandera?banderas+1:banderas-1;
                            Contenedor.ponerMinas(countBomb-banderas);
                            revisarBombasconBand();
                        }
                    }
                });
                hboxLayout.getChildren().add(Celda[i][j]);
            }
            vboxLayout.getChildren().add(hboxLayout);
        }

        getChildren().add(vboxLayout);

        instalarJuego();
    }

    private void Reiniciar(){
        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                Celda[i][j].reiniciarCelda();
            }
        }
        instalarJuego();
    }

    private void instalarJuego(){
        int random_row,random_col,numeroMinas;
        try{
            minaArchivo = new RandomAccessFile("Buscaminas.dat", "rw");
            TR=0;
            int minas = 0;
            byte minita = 0;
            for (int i = 0; i < countRow; i++) {
                for (int j = 0; j < countColumn ; j++) {
                    minaArchivo.seek(TR);
                    double random = Math.random();
                    if(random < 0.25){
                        minita = 1;
                    }else{
                        minita = 0;
                    }
                    minaArchivo.write(minita);
                    System.out.println(minaArchivo.getFilePointer()+", MINITA: "+minita);
                    TR++;
                }
            }
            System.out.println("Archivo creado exitosamente!");
            minaArchivo.seek(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        //**************************************************************************************************
        TR=0;
        try {
            System.out.println("ENTRA A MINAS");
            for (int i = 0; i < countRow; i++) {
                for (int j = 0; j < countColumn ; j++) {
                    minaArchivo = new RandomAccessFile("Buscaminas.dat","rw");
                    minaArchivo.seek(TR);
                    int minaBool = minaArchivo.read();
                    System.out.println(minaBool);
                    if(minaBool == 1)
                        Celda[i][j].setBombBox(true);
                    TR++;
                }
            }
            System.out.println("SALE A MINAS");
            minaArchivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //**************************************************************************************************

        countBomb=0;
        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                countBomb=Celda[i][j].isBomb?countBomb+1:countBomb;
            }
        }

        banderas=0;
        Contenedor.ponerMinas(countBomb - banderas);

        int bomb_count=0;
        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                if(Celda[i][j].tieneBomba()){
                    continue;
                }

                bomb_count=0;
                if (!((i-1) < 0 ) && !((j-1) < 0) && Celda[i-1][j-1].tieneBomba()){
                    bomb_count++;
                }
                if (!((j-1) < 0 ) && Celda[i][j-1].tieneBomba()){
                    bomb_count++;
                }
                if (!((i+1) >= countRow ) && !((j-1) < 0) && Celda[i+1][j-1].tieneBomba()){
                    bomb_count++;
                }
                if (!((i+1) >= countRow ) && Celda[i+1][j].tieneBomba()){
                    bomb_count++;
                }
                if (!((i+1) >= countRow ) && !((j+1) >= countColumn) && Celda[i+1][j+1].tieneBomba()){
                    bomb_count++;
                }
                if (!((j+1) >= countColumn) && Celda[i][j+1].tieneBomba()){
                    bomb_count++;
                }
                if (!((i-1) < 0 ) && !((j+1) >= countColumn) && Celda[i-1][j+1].tieneBomba()){
                    bomb_count++;
                }
                if (!((i-1) < 0 ) && Celda[i-1][j].tieneBomba()){
                    bomb_count++;
                }
                Celda[i][j].setBombCount(bomb_count);
            }
        }
    }

}


class Box extends Parent{
    Rectangle rectBox ;
    Text rectText;
    boolean isBomb=false;
    int bomb_count=0;
    int posRow , posCol;
    boolean estaCubierta=true;
    boolean tieneBandera=false;
    ImageView banderita=null;
    ImageView bombita=null;
    Group grp=new Group();


    public Box(int width,int height){
        rectBox=new Rectangle(width,height);
        rectBox.setFill(Color.GRAY);

        rectText=new Text(width/2,height/2,(new Integer(bomb_count)).toString());
        rectText.setFill(Color.BLACK);
        rectText.setVisible(false);

        grp.getChildren().add(rectBox);
        getChildren().add(grp);
    }

    public void reiniciarCelda(){
        isBomb=false;
        bomb_count=0;
        estaCubierta=true;
        tieneBandera=false;
        rectBox.setFill(Color.GRAY);
        rectText.setFill(Color.BLACK);
        rectText.setVisible(false);

        grp.getChildren().remove(rectText);
        grp.getChildren().remove(bombita);
        grp.getChildren().remove(banderita);

    }

    void setBombBox(boolean isBomb){
        if(!this.isBomb && isBomb){
            this.isBomb=isBomb;
            bombita = new ImageView(new Image("sample/Buscaminas/images/imgBomb.png"));
            bombita.setFitWidth(rectBox.getWidth());
            bombita.setFitHeight(rectBox.getHeight());
            bombita.setVisible(false);
            grp.getChildren().add(bombita);
        }else if(this.isBomb && !isBomb){
            getChildren().remove(bombita);
        }
    }

    void setBoxPosition(int row_position,int col_position){
        posRow=row_position;
        posCol=col_position;
    }

    int getBoxRow(){ return posRow; }

    int getBoxCol(){ return posCol; }

    void setBombCount(int bomb_count){
        this.bomb_count=bomb_count;
    }

    void showBombCount(){
        if(!rectText.isVisible() && !isBomb ){
            ponerBandera(false);
            rectText.setText((new Integer(bomb_count).toString()));
            grp.getChildren().add(rectText);
            rectText.setVisible(true);
            estaCubierta=false;
        }
    }

    void ponerCubierta(boolean estaCubierta){
        this.estaCubierta=estaCubierta;
            rectBox.setFill(Color.LIGHTGRAY);
    }

    boolean estaCubierta(){
        return estaCubierta;
    }

    boolean tieneBomba(){
        return isBomb;
    }

    void explotarBomba(){
        if(isBomb){
            bombita.setVisible(true);
            if(tieneBandera) banderita.setVisible(false);
            bombita.setImage(new Image(Grid.class.getResourceAsStream("images/imgExplosion.png")));
        }
    }

    int getBombCount() {
        return bomb_count;
    }

    boolean bandera() {
        return tieneBandera;
    }

    void ponerBandera(boolean tieneBandera) {
        if(estaCubierta){
            this.tieneBandera=tieneBandera;
            if(tieneBandera){
                banderita = new ImageView(new Image(Grid.class.getResourceAsStream("images/imgRedFlag.png")));
                banderita.setFitHeight(rectBox.getHeight());
                banderita.setFitWidth(rectBox.getWidth());
                banderita.setVisible(tieneBandera);
                grp.getChildren().add(banderita);
            }else{
                grp.getChildren().remove(banderita);
                banderita=null;
            }
        }
    }
}