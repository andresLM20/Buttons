package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Eventos.EventoTaquimecanografo;
import sample.Eventos.EventoVentanaTaqui;

import java.io.File;

public class Taquimecanografo extends Stage {
    private Scene escena;
    private ToolBar tblMenu;
    private Button btnAbrir;
    private TextArea txaTexto, txaEscritura;
    private VBox vPrincipal, vTeclado;
    private HBox hTeclas1, hTeclas2, hTeclas3, hTeclas4, hTeclas5, hTeclas6;
    private Button[] arBotones1, arBotones2, arBotones3, arBotones4, arBotones5, arBotones6;
    private String[]
            arTeclas1 = {"ESC","F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","INSERT", "PRINTSC"}, //15 botones
            arTeclas2 = {"~","1","2","3","4","5","6","7","8","9","0","-","=","BACKSPACE"}, //14 botones
            arTeclas3 = {"->","Q","W","E","R","T","Y","U","I","O","P","[","]","|"}, //14 botones
            arTeclas4 = {"MAYUS","A","S","D","F","G","H","J","K","L",";","'","ENTER",}, //13 botones
            arTeclas5 = {"SHIFT","Z","X","C","V","B","N","M","COMA",".","/","SHIFT","^"}, //13 botones
            arTeclas6 = {"CTRL","FN","WIN","ALT","SPACE","ALT","OPC","CTRL","<","v",">"}; //11 botones
    private FileChooser flcArchivos;

    public Taquimecanografo (){
        CrearGUI();
        this.setTitle("Mi tutor de mecanografía");
        this.setScene(escena);
        this.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventoVentanaTaqui());
        this.show();
    }

    private void CrearGUI() {
        tblMenu = new ToolBar();
        btnAbrir = new Button();
        //btnAbrir.setPrefSize(15,15);
        btnAbrir.setGraphic(new ImageView("sample/Imagenes/open.png"));
        btnAbrir.setOnAction(event -> AbrirExplorador());
        tblMenu.getItems().add(btnAbrir);

        txaTexto = new TextArea();
        txaTexto.setEditable(false);
        txaTexto.setPrefRowCount(5);

        txaEscritura = new TextArea();
        txaEscritura.setPrefRowCount(5);


        arBotones1 = new Button[15];
        arBotones2 = new Button[14];
        arBotones3 = new Button[14];
        arBotones4 = new Button[13];
        arBotones5 = new Button[13];
        arBotones6 = new Button[11];

        hTeclas1 = new HBox();
        hTeclas1.setId("hbox-custom");
        hTeclas2 = new HBox();
        hTeclas2.setId("hbox-custom");
        hTeclas3 = new HBox();
        hTeclas3.setId("hbox-custom");
        hTeclas4 = new HBox();
        hTeclas4.setId("hbox-custom");
        hTeclas5 = new HBox();
        hTeclas5.setId("hbox-custom");
        hTeclas6 = new HBox();
        hTeclas6.setId("hbox-custom");
        for(int i = 0; i < 15; i++)
        {
            arBotones1[i] = new Button(arTeclas1[i]);
            hTeclas1.getChildren().add(arBotones1[i]);
            if(i!=0 && i!=13 && i!=14)
                arBotones1[i].setId("btnRow");
            else
                arBotones1[i].setId("btnRowMediano");
        } //Termina for para hilera 1 **********************************************************************************
        for(int i = 0; i < 14; i++)
        {
            arBotones2[i] = new Button(arTeclas2[i]);
            hTeclas2.getChildren().add(arBotones2[i]);

            arBotones3[i] = new Button(arTeclas3[i]);
            hTeclas3.getChildren().add(arBotones3[i]);

            if(i!=13)
                arBotones2[i].setId("btnRow");
            else
                arBotones2[i].setId("btnRowGrande");
            if(i!=0 && i!=13)
                arBotones3[i].setId("btnRow");
            else
                arBotones3[i].setId("btnRowGrande");
        } //Termina for para hilera 2 y 3 ******************************************************************************
        EventoTaquimecanografo objEvento = new EventoTaquimecanografo(arBotones1, arBotones2, arBotones3, arBotones4, arBotones5, arBotones6);
        txaEscritura.setOnKeyPressed(objEvento);
        txaEscritura.setOnKeyReleased(objEvento);
        for(int i = 0; i < 13; i++)
        {
            arBotones4[i] = new Button(arTeclas4[i]);
            hTeclas4.getChildren().add(arBotones4[i]);

            arBotones5[i] = new Button(arTeclas5[i]);
            hTeclas5.getChildren().add(arBotones5[i]);

            if(i!=0 && i!=13)
                arBotones4[i].setId("btnRow");
            else
                arBotones4[i].setId("btnRowGrande");
            if(i!=0 && i!=13)
                arBotones5[i].setId("btnRow");
            else
                arBotones5[i].setId("btnRowGrande");
        } //Termina for para hilera 4 y 5 ******************************************************************************
        for(int i = 0; i < 11; i++)
        {
            arBotones6[i] = new Button(arTeclas6[i]);
            hTeclas6.getChildren().add(arBotones6[i]);

            if(i!=4)
                arBotones6[i].setId("btnRow");
            else
                arBotones6[i].setId("btnRowExGrande");
        } //Termina for para hilera 6 **********************************************************************************
        vTeclado = new VBox();
        vTeclado.getChildren().addAll(hTeclas1,hTeclas2,hTeclas3,hTeclas4,hTeclas5,hTeclas6);

        /**************************************************************/
        vPrincipal = new VBox();
        vPrincipal.setSpacing(5);
        vPrincipal.getChildren().addAll(tblMenu, txaTexto, txaEscritura, vTeclado);
        escena = new Scene(vPrincipal, 700,450);
        escena.getStylesheets().add("sample/Estilos/estilos_taquimecanografo.css");
    }

    private void AbrirExplorador() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(this);
        File objFile = flcArchivos.showOpenDialog(this);
    }
}
