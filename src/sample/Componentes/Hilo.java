package sample.Componentes;

public class Hilo extends Thread{

    public Hilo(String nomHilo){
        setName(nomHilo);
    }

    public void run(){
	    super.run();
        System.out.println("Inicia corredor: "+getName());
        for(int i = 1; i < 5; i++) {
            try {
                sleep((long)Math.random()*2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Corredor: "+getName()+" termino el km" + i);
        }
        System.out.println("Corredor "+getName()+ " llegó a la meta");
    }
}

//Sonic -> Hilo1
//Rubensin -> Hilo2
//Hulk -> Hilo3
//El prisas -> Hilo4
//El limas -> Hilo5
