package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.RandomAccessFile;


public class EventoBuscaminas implements EventHandler{
    private TextField txtnr;
    private TextField txtnc;
    private GridPane gdpCampo;
    private Button[][] arBtnCeldas;
    private VBox vbox;
    private RandomAccessFile minaArchivo;
    int total,nr,nc;
    int TR = 0;

    public EventoBuscaminas(TextField txtNr,TextField txtNc,Button[][] arBtnCELDAS,GridPane gdpCAMPO,VBox vBOX){
        this.txtnr = txtNr;
        this.txtnc = txtNc;
        this.arBtnCeldas = arBtnCELDAS;
        this.gdpCampo = gdpCAMPO;
        this.vbox = vBOX;
    }


    @Override
    public void handle(Event event) {
        nr = Integer.parseInt(this.txtnr.getText());
        nc = Integer.parseInt(this.txtnc.getText());
        total = nc*nr;
        int porcentajeMinado = (int)(total*0.25);
        int numMina=0;
        try {
            minaArchivo = new RandomAccessFile("Buscaminas.dat","rw");
            for (int i = 0; i < total; i++) {
                int faltantes = porcentajeMinado - numMina;
                int quedantes = total - (i+1);
                double valor = Math.random();
                byte minado = 0;
                if (valor < 0.25) {
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
        }catch (Exception e){
            e.printStackTrace();
        }

        if(arBtnCeldas !=null){
            vbox.getChildren().remove(gdpCampo);
        }
        arBtnCeldas = new Button[nr][nc];
        gdpCampo = new GridPane();
        gdpCampo.setPadding(new Insets(15));
        gdpCampo.setAlignment(Pos.CENTER);
        TR=0;
        try {
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < nc ; j++) {
                    minaArchivo = new RandomAccessFile("Buscaminas.dat","rw");
                    minaArchivo.seek(TR);
                    arBtnCeldas[i][j] = new Button();
                    arBtnCeldas[i][j].setPrefSize(80, 80);
                    arBtnCeldas[i][j].setStyle("-fx-base:#9e9e9e; -fx-background-radius:0;");
                    arBtnCeldas[i][j].setId(TR+"");
                    arBtnCeldas[i][j].setText(""+minaArchivo.read());
                    int id = Integer.parseInt(arBtnCeldas[i][j].getId());
                    arBtnCeldas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,new EventoBuscar(arBtnCeldas,id,i,j,nr,nc,vbox,gdpCampo,total));
                    gdpCampo.add(arBtnCeldas[i][j], j, i);
                    TR++;
                }
            }
            minaArchivo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.vbox.getChildren().add(this.gdpCampo);
    }
}