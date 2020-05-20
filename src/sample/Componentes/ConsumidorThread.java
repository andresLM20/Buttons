package sample.Componentes;

import sample.Vistas.RecursoCompartido;

public class ConsumidorThread extends Thread{
    RecursoCompartido objRec;
    public ConsumidorThread(RecursoCompartido objRec) {
        this.objRec = objRec;
    }

    @Override
    public void run() {
        System.out.println("Inicia hilo CONSUMIDOR");
        super.run();
        for (int i = 1; i <= 50; i++){
            objRec.vaciaRecurso();}
        System.out.println("Termina hilo CONSUMIDOR");
    }
}
