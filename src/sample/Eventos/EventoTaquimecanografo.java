package sample.Eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EventoTaquimecanografo implements EventHandler<KeyEvent> {

    Button[] arbotones1,arbotones2,arbotones3,arbotones4,arbotones5,arbotones6;
    Boolean ban = true;

    public EventoTaquimecanografo(Button[] arreglo1, Button[] arreglo2, Button[] arreglo3, Button[] arreglo4, Button[] arreglo5, Button[] arreglo6){
        arbotones1 = arreglo1;
        arbotones2 = arreglo2;
        arbotones3 = arreglo3;
        arbotones4 = arreglo4;
        arbotones5 = arreglo5;
        arbotones6 = arreglo6;
    }

    public void handle(KeyEvent event){
        Button objButton;
        int pos = 0;
        //ARREGLO 1*****************************************************************************************************
        if(event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.F1 || event.getCode() == KeyCode.F2 || event.getCode() == KeyCode.F3 || event.getCode() == KeyCode.F4 || event.getCode() == KeyCode.F5 || event.getCode() == KeyCode.F6 ||
                event.getCode() == KeyCode.F7 || event.getCode() == KeyCode.F8 || event.getCode() == KeyCode.F9 || event.getCode() == KeyCode.F10 || event.getCode() == KeyCode.F11 || event.getCode() == KeyCode.F12 || event.getCode() == KeyCode.INSERT || event.getCode() == KeyCode.PRINTSCREEN)
        {
            if(event.getCode() == KeyCode.ESCAPE){ pos = 0;}
            if(event.getCode() == KeyCode.F1){ pos = 1;}
            if(event.getCode() == KeyCode.F2){ pos = 2;}
            if(event.getCode() == KeyCode.F3){ pos = 3;}
            if(event.getCode() == KeyCode.F4){ pos = 4;}
            if(event.getCode() == KeyCode.F5){ pos = 5;}
            if(event.getCode() == KeyCode.F6){ pos = 6;}
            if(event.getCode() == KeyCode.F7){ pos = 7;}
            if(event.getCode() == KeyCode.F8){ pos = 8;}
            if(event.getCode() == KeyCode.F9){ pos = 9;}
            if(event.getCode() == KeyCode.F10){ pos = 10;}
            if(event.getCode() == KeyCode.F11){ pos = 11;}
            if(event.getCode() == KeyCode.F12){ pos = 12;}
            if(event.getCode() == KeyCode.INSERT){ pos = 13;}
            if(event.getCode() == KeyCode.PRINTSCREEN){ pos = 14;}

            if(ban) {arbotones1[pos].setStyle("-fx-base: #f44336"); } else {arbotones1[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
        }
        //ARREGLO 2*****************************************************************************************************
        if(event.getCode() == KeyCode.BACK_QUOTE || event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.DIGIT3 || event.getCode() == KeyCode.DIGIT4|| event.getCode() == KeyCode.DIGIT5 || event.getCode() == KeyCode.DIGIT6 ||
                event.getCode() == KeyCode.DIGIT7 || event.getCode() == KeyCode.DIGIT8 || event.getCode() == KeyCode.DIGIT9 || event.getCode() == KeyCode.DIGIT0 || event.getCode() == KeyCode.MINUS || event.getCode() == KeyCode.EQUALS || event.getCode() == KeyCode.BACK_SPACE)
        {
            if(event.getCode() == KeyCode.BACK_QUOTE){ pos = 0;}
            if(event.getCode() == KeyCode.DIGIT1){ pos = 1;}
            if(event.getCode() == KeyCode.DIGIT2){ pos = 2;}
            if(event.getCode() == KeyCode.DIGIT3){ pos = 3;}
            if(event.getCode() == KeyCode.DIGIT4){ pos = 4;}
            if(event.getCode() == KeyCode.DIGIT5){ pos = 5;}
            if(event.getCode() == KeyCode.DIGIT6){ pos = 6;}
            if(event.getCode() == KeyCode.DIGIT7){ pos = 7;}
            if(event.getCode() == KeyCode.DIGIT8){ pos = 8;}
            if(event.getCode() == KeyCode.DIGIT9){ pos = 9;}
            if(event.getCode() == KeyCode.DIGIT0){ pos = 10;}
            if(event.getCode() == KeyCode.MINUS){ pos = 11;}
            if(event.getCode() == KeyCode.EQUALS){ pos = 12;}
            if(event.getCode() == KeyCode.BACK_SPACE){ pos = 13;}

            if(ban) {arbotones2[pos].setStyle("-fx-base: #f44336"); } else {arbotones2[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
        }
        //ARREGLO 3*****************************************************************************************************
        if(event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.Q || event.getCode() == KeyCode.W || event.getCode() == KeyCode.E || event.getCode() == KeyCode.R || event.getCode() == KeyCode.T || event.getCode() == KeyCode.Y ||
                event.getCode() == KeyCode.U || event.getCode() == KeyCode.I || event.getCode() == KeyCode.O || event.getCode() == KeyCode.P || event.getCode() == KeyCode.OPEN_BRACKET || event.getCode() == KeyCode.CLOSE_BRACKET || event.getCode() == KeyCode.BACK_SLASH)
        {
            if(event.getCode() == KeyCode.TAB){ pos = 0;}
            if(event.getCode() == KeyCode.Q){ pos = 1;}
            if(event.getCode() == KeyCode.W){ pos = 2;}
            if(event.getCode() == KeyCode.E){ pos = 3;}
            if(event.getCode() == KeyCode.R){ pos = 4;}
            if(event.getCode() == KeyCode.T){ pos = 5;}
            if(event.getCode() == KeyCode.Y){ pos = 6;}
            if(event.getCode() == KeyCode.U){ pos = 7;}
            if(event.getCode() == KeyCode.I){ pos = 8;}
            if(event.getCode() == KeyCode.O){ pos = 9;}
            if(event.getCode() == KeyCode.P){ pos = 10;}
            if(event.getCode() == KeyCode.OPEN_BRACKET){ pos = 11;}
            if(event.getCode() == KeyCode.CLOSE_BRACKET){ pos = 12;}
            if(event.getCode() == KeyCode.BACK_SLASH){ pos = 13;}

            if(ban) {arbotones3[pos].setStyle("-fx-base: #f44336"); } else {arbotones3[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
        }
        //ARREGLO 4*****************************************************************************************************
        if(event.getCode() == KeyCode.CAPS || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S || event.getCode() == KeyCode.D || event.getCode() == KeyCode.F || event.getCode() == KeyCode.G || event.getCode() == KeyCode.H ||
                event.getCode() == KeyCode.J || event.getCode() == KeyCode.K || event.getCode() == KeyCode.L || event.getCode() == KeyCode.SEMICOLON || event.getCode() == KeyCode.QUOTE || event.getCode() == KeyCode.ENTER)
        {
            if(event.getCode() == KeyCode.CAPS){ pos = 0;}
            if(event.getCode() == KeyCode.A){ pos = 1;}
            if(event.getCode() == KeyCode.S){ pos = 2;}
            if(event.getCode() == KeyCode.D){ pos = 3;}
            if(event.getCode() == KeyCode.F){ pos = 4;}
            if(event.getCode() == KeyCode.G){ pos = 5;}
            if(event.getCode() == KeyCode.H){ pos = 6;}
            if(event.getCode() == KeyCode.J){ pos = 7;}
            if(event.getCode() == KeyCode.K){ pos = 8;}
            if(event.getCode() == KeyCode.L){ pos = 9;}
            if(event.getCode() == KeyCode.SEMICOLON){ pos = 10;}
            if(event.getCode() == KeyCode.QUOTE){ pos = 11;}
            if(event.getCode() == KeyCode.ENTER){ pos = 12;}

            if(ban) {arbotones4[pos].setStyle("-fx-base: #f44336"); } else {arbotones4[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
        }
        //ARREGLO 5*****************************************************************************************************
        if(event.getCode() == KeyCode.SHIFT || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.X || event.getCode() == KeyCode.C || event.getCode() == KeyCode.V || event.getCode() == KeyCode.B ||
                event.getCode() == KeyCode.N || event.getCode() == KeyCode.M || event.getCode() == KeyCode.COMMA || event.getCode() == KeyCode.PERIOD || event.getCode() == KeyCode.SLASH || event.getCode() == KeyCode.UP)
        {
            if(event.getCode() == KeyCode.SHIFT){
                if (ban) {
                    arbotones5[0].setStyle("-fx-base: #f44336");
                    arbotones5[11].setStyle("-fx-base: #f44336");
                } else {
                    arbotones5[0].setStyle("-fx-base: #673ab7");
                    arbotones5[11].setStyle("-fx-base: #673ab7");
                }
                ban = !ban;
            } else{
                if(event.getCode() == KeyCode.SHIFT){ pos = 0;}
                if(event.getCode() == KeyCode.Z){ pos = 1;}
                if(event.getCode() == KeyCode.X){ pos = 2;}
                if(event.getCode() == KeyCode.C){ pos = 3;}
                if(event.getCode() == KeyCode.V){ pos = 4;}
                if(event.getCode() == KeyCode.B){ pos = 5;}
                if(event.getCode() == KeyCode.N){ pos = 6;}
                if(event.getCode() == KeyCode.M){ pos = 7;}
                if(event.getCode() == KeyCode.COMMA){ pos = 8;}
                if(event.getCode() == KeyCode.PERIOD){ pos = 9;}
                if(event.getCode() == KeyCode.SLASH){ pos = 10;}
                if(event.getCode() == KeyCode.SHIFT){ pos = 11;}
                if(event.getCode() == KeyCode.UP){ pos = 12;}
                if(ban) {arbotones5[pos].setStyle("-fx-base: #f44336"); } else {arbotones5[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
            }
        }
        //ARREGLO 6*****************************************************************************************************
        if(event.getCode() == KeyCode.CONTROL || event.getCode() == KeyCode.WINDOWS || event.getCode() == KeyCode.ALT || event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ALT_GRAPH ||
                event.getCode() == KeyCode.CONTEXT_MENU || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT)
        {
            if(event.getCode() == KeyCode.CONTROL)
            {
                if (ban) {
                    arbotones6[0].setStyle("-fx-base: #f44336");
                    arbotones6[7].setStyle("-fx-base: #f44336");
                } else {
                    arbotones6[0].setStyle("-fx-base: #673ab7");
                    arbotones6[7].setStyle("-fx-base: #673ab7");
                }
                ban = !ban;
            }
            else {
                if(event.getCode() == KeyCode.WINDOWS){ pos = 2;}
                if(event.getCode() == KeyCode.ALT){ pos = 3;}
                if(event.getCode() == KeyCode.SPACE){ pos = 4;}
                if(event.getCode() == KeyCode.ALT_GRAPH){ pos = 5;}
                if(event.getCode() == KeyCode.CONTEXT_MENU){ pos = 6;}
                if(event.getCode() == KeyCode.LEFT){ pos = 8;}
                if(event.getCode() == KeyCode.DOWN){ pos = 9;}
                if(event.getCode() == KeyCode.RIGHT){ pos = 10;}
                if(ban) {arbotones6[pos].setStyle("-fx-base: #f44336"); } else {arbotones6[pos].setStyle("-fx-base: #673ab7");} ban=!ban;
            }
        }

        /*switch(event.getCode().getName()){
            case "0":
                System.out.println("Presionaste un 0");
                break;
            case "Backspace":
                System.out.println("Borraste un caracter");
                break;
            case "Space":
                System.out.println("Pusiste un espacio");
                break;
        }*/
        System.out.println(event.getCode());
    }
}
