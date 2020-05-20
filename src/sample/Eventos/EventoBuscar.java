package sample.Eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class EventoBuscar implements EventHandler<MouseEvent>{

    private int i,j,TR,nr,nc,hasbomb;
    private RandomAccessFile fl;
    private Button[][] arBtnCeldas;
    private VBox vbox;
    private GridPane gdpCampo;
    int minasEncontradas=0;
    int n = 1, total;
    boolean flagged = false;

    public EventoBuscar (Button[][] arBotonesx, int id, int ix, int jx, int NR, int NC, VBox vbox, GridPane gdpCampo,int total){
        this.i = ix;
        this.j = jx;
        this.TR = id;
        this.nr = NR;
        this.nc = NC;
        this.arBtnCeldas = arBotonesx;
        this.vbox = vbox;
        this.gdpCampo = gdpCampo;
        this.total = total;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton()== MouseButton.PRIMARY) {
            try {
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl = new RandomAccessFile("Buscaminas.dat", "rw");
                fl.seek(0);
                fl.seek(TR);
                hasbomb = fl.read();
                fl.seek(0);
                if (hasbomb == 0) {
                    buscar(i, j, n);
                } else {
                    fl.seek(0);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("Juego termiando");
                    alert.setContentText("Empieza de nuevo :)");
                    alert.showAndWait();
                    vbox.getChildren().remove(gdpCampo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getButton()== MouseButton.SECONDARY){
            if(!flagged) {
                Image flag = new Image("sample/Buscaminas2/bandera.png");
                arBtnCeldas[i][j].setGraphic(new ImageView(flag));
                flagged=true;
            }
            else{
                arBtnCeldas[i][j].setGraphic(null);
                flagged=false;
            }
        }
    }

    public void buscar(int i, int j, int n) {
        System.out.println("posicion: "+TR+", Id:"+arBtnCeldas[i][j].getId());
        String ver = arBtnCeldas[i][j].getId();

        try {

            for (int y = 0; y < nc; y++) {
                for (int x = 0; x < nr; x++) {
                    int numNeighboursBomb = 0;
                    ArrayList<Tiles> neighbours = new ArrayList<Tiles>();
                    int[] neighboursLocs = new int[]{-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};

                    for (int k = 0; k < neighboursLocs.length; k++) {
                        int dx = neighboursLocs[k];
                        int dy = neighboursLocs[++k];

                        int newX = i + dx;
                        int newY = j + dy;
                        //System.out.println("dx="+dx+",dy="+dy+",newX="+newX+",newY="+newY+",idnew="+arBtnCeldas[newX][newY].getId());
                        //TR = Integer.parseInt(arBtnCeldas[newX][newY].getId());

                        fl = new RandomAccessFile("Buscaminas.dat", "rw");
                        fl.seek(0);
                        fl.seek(TR);
                        hasbomb = fl.read(); //Devuelve 1 o 0
                        System.out.println("bomba? = "+hasbomb);

                        if (newX >= 0 && newX < nc && newY >= 0 && newY < nr) {
                            //neighbours.add(gdpCampo[newX][newY]);
                            if (hasbomb==1) {
                                numNeighboursBomb++;
                            }
                        }
                    }
                    //grid[x][y].numBombs = numNeighboursBomb;
                    //grid[x][y].neighbours = neighbours;
                }
            }
            //arBtnCeldas[i][j].setText(numNeighboursBomb+"");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        /*if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl = new RandomAccessFile("Buscaminas.dat", "rw");
                fl.seek(0);
                fl.seek(TR);
                mina = fl.read();
                if (mina == 0) {

                    fl.seek(0);

                    if (j == (nc - 1) && i == 0) {
                        //Esquina superior derecha
                        buscarIzquierda(i, (j - 1), n);
                        buscarDiagonalAbajoiz((i + 1), (j - 1), n);
                        buscarAbajo((i + 1), j, n);
                    } else {
                        if (j == (nc - 1) && i == (nr - 1)) {
                            //Esquina inferior derecha
                            buscarIzquierda(i, (j - 1), n);
                            buscarArriba((i - 1), j, n);
                            buscarDiagonalArribaiz((i - 1), (j - 1), n);
                        } else {
                            if (j == 0 && i == 0) {
                                // Esquina superior izquierda
                                buscarDerecha(i, (j + 1), n);
                                buscarAbajo((i + 1), j, n);
                                buscarDiagonalAbajoder((i + 1), (j + 1), n);
                            } else {
                                if (j == 0 && i == (nr - 1)) {
                                    // Esquina inferior izquierda
                                    buscarDerecha(i, (j + 1), n);
                                    buscarArriba((i - 1), j, n);
                                    buscarDiagonalArribader((i - 1), (j + 1), n);
                                } else {
                                    if (i == 0 && j!= 0 && j != (nc - 1)) {
                                        // Centro superior
                                        buscarDerecha(i, (j + 1), n);
                                        buscarIzquierda(i, (j - 1), n);
                                        buscarAbajo((i + 1), j, n);
                                        buscarDiagonalAbajoder((i + 1), (j + 1), n);
                                        buscarDiagonalAbajoiz((i + 1), (j - 1), n);

                                    } else {
                                        if (j == 0 && i!= 0 && i != (nr - 1)) {
                                            // Centro izquierdo
                                            buscarDerecha(i, (j + 1), n);
                                            buscarAbajo((i + 1), j, n);
                                            buscarArriba((i - 1), j, n);
                                            buscarDiagonalAbajoder((i + 1), (j + 1), n);
                                            buscarDiagonalArribader((i - 1), (j + 1), n);
                                        } else {
                                            if (i == (nr - 1) && j != 0 && j != (nc - 1)) {
                                                // Centro inferior
                                                buscarArriba((i - 1), j, n);
                                                buscarDerecha(i, (j + 1), n);
                                                buscarIzquierda(i, (j - 1), n);
                                                buscarDiagonalArribader((i - 1), (j + 1), n);
                                                buscarDiagonalArribaiz((i - 1), (j - 1), n);

                                            } else {
                                                if (j == (nc - 1) && i != 0 && i != (nr - 1)) {
                                                    // Centro derecho
                                                    buscarIzquierda(i, (j - 1), n);
                                                    buscarArriba((i - 1), j, n);
                                                    buscarAbajo((i + 1), j, n);
                                                    buscarDiagonalArribaiz((i - 1), (j - 1), n);
                                                    buscarDiagonalAbajoiz((i + 1), (j - 1), n);

                                                } else {
                                                    System.out.println("entro");
                                                    buscarIzquierda(i, (j - 1), n);
                                                    buscarArriba((i - 1), j, n);
                                                    buscarAbajo((i + 1), j, n);
                                                    buscarDerecha(i, (j + 1), n);
                                                    buscarDiagonalArribaiz((i - 1), (j - 1), n);
                                                    buscarDiagonalAbajoiz((i + 1), (j - 1), n);
                                                    buscarDiagonalArribader((i - 1), (j + 1), n);
                                                    buscarDiagonalAbajoder((i + 1), (j + 1), n);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    fl.seek(0);
                } else {
                    fl.seek(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarIzquierda(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i][j].setGraphic(null);
                    arBtnCeldas[i][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j + 1].setDisable(true);
                    arBtnCeldas[i][j+1].setGraphic(null);
                    arBtnCeldas[i][j + 1].setId("REVISADO");
                    if ((j - 1) <= (nc - 1) && (j - 1) >= 0) {
                        String rev = arBtnCeldas[i][j-1].getId();
                        if(rev != "REVISADO") {
                            buscar(i, (j - 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i][j + 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j + 1].setDisable(true);
                    arBtnCeldas[i][j + 1].setId("REVISADO");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarDerecha(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j - 1].setId("REVISADO");
                    arBtnCeldas[i][j - 1].setDisable(true);
                    if ((j + 1) <= (nc - 1) && (j + 1) >= 0) {
                        String rev = arBtnCeldas[i][j+1].getId();
                        if (rev !="REVISADO") {
                            buscar(i, (j + 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i][j - 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j - 1].setId("REVISADO");
                    arBtnCeldas[i][j - 1].setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarArriba(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i + 1][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j].setId("REVISADO");
                    arBtnCeldas[i + 1][j].setDisable(true);
                    if ((i - 1) <= (nr - 1) && (i - 1) >= 0) {
                        String rev =  arBtnCeldas[i-1][j].getId();
                        if (rev !="REVISADO") {
                            buscar((i - 1), j, n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i + 1][j].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i + 1][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j].setId("REVISADO");
                    arBtnCeldas[i + 1][j].setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarAbajo(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i - 1][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j].setId("REVISADO");
                    arBtnCeldas[i - 1][j].setDisable(true);
                    if ((i + 1) <= (nr - 1) && (i + 1) >= 0) {
                        String rev = arBtnCeldas[i+1][j].getId();
                        if (rev != "REVISADO") {
                            buscar((i + 1), j, n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i - 1][j].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i - 1][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j].setId("REVISADO");
                    arBtnCeldas[i - 1][j].setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarDiagonalAbajoiz(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i - 1][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j + 1].setId("REVISADO");
                    arBtnCeldas[i - 1][j + 1].setDisable(true);
                    if ((i + 1) <= (nr - 1) && (i + 1) >= 0 && (j - 1) <= (nc - 1) && (j - 1) >= 0) {
                        String rev = arBtnCeldas[i + 1][j - 1].getId();
                        if (rev != "REVISADO") {
                            buscar((i + 1), (j - 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i - 1][j + 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i - 1][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j + 1].setId("REVISADO");
                    arBtnCeldas[i - 1][j + 1].setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarDiagonalAbajoder(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i - 1][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j - 1].setId("REVISADO");
                    arBtnCeldas[i - 1][j - 1].setDisable(true);
                    if ((i + 1) <= (nr - 1) && (i + 1) >= 0 && (j + 1) <= (nc - 1) && (j + 1) >= 0) {
                        String rev = arBtnCeldas[i + 1][j + 1].getId();
                        if (rev != "REVISADO") {
                            buscar((i + 1), (j + 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i - 1][j - 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i - 1][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i - 1][j - 1].setId("REVISADO");
                    arBtnCeldas[i - 1][j - 1].setDisable(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void buscarDiagonalArribader(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i + 1][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j - 1].setId("REVISADO");
                    arBtnCeldas[i + 1][j - 1].setDisable(true);
                    if ((i - 1) <= (nr - 1) && (i - 1) >= 0 && (j + 1) <= (nc - 1) && (j + 1) >= 0) {
                        String rev = arBtnCeldas[i - 1][j + 1].getId();
                        if (rev != "REVISADO") {
                            buscar((i - 1), (j + 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i + 1][j - 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i + 1][j - 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j - 1].setId("REVISADO");
                    arBtnCeldas[i + 1][j - 1].setDisable(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void buscarDiagonalArribaiz(int i, int j,int n){
        String ver = arBtnCeldas[i][j].getId();
        if(i<=(nr-1) && i>=0 && j<=(nc-1) && j>=0 && ver!="REVISADO") {
            try {
                fl.seek(0);
                TR = Integer.parseInt(arBtnCeldas[i][j].getId());
                fl.seek(TR);
                mina = fl.read();
                fl.seek(0);
                if (mina == 0) {
                    arBtnCeldas[i][j].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i][j].setId("REVISADO");
                    arBtnCeldas[i][j].setDisable(true);
                    arBtnCeldas[i + 1][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j + 1].setId("REVISADO");
                    arBtnCeldas[i + 1][j + 1].setDisable(true);
                    if ((i - 1) <= (nr - 1) && (i - 1) >= 0 && (j - 1) <= (nc - 1) && (j - 1) >= 0) {
                        String rev = arBtnCeldas[i - 1][j - 1].getId();
                        if (rev != "REVISADO") {
                            buscar((i - 1), (j - 1), n);
                        }
                    }
                    n++;

                } else {
                    minasEncontradas++;
                    arBtnCeldas[i + 1][j + 1].setText("" + (Math.abs(minasEncontradas)));
                    arBtnCeldas[i + 1][j + 1].setStyle("-fx-base:#FFFFFF;");
                    arBtnCeldas[i + 1][j + 1].setId("REVISADO");
                    arBtnCeldas[i + 1][j + 1].setDisable(true);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

}