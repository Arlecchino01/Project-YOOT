public class Yut {
    
    private boolean[] yuts = new boolean[4];

    public Yut(){

    }

    public void throwYut(){
        for (int i = 0; i < 4; i++) {
            this.yuts[i] = Math.random() < 0.5;
        }
    }

    public void throwCustomYut(){

    }

    public boolean[] getYutResult(){
        return yuts;
    }
    
    public void printYutResult(){
        for(int i=0; i<4; i++){
            System.out.println(yuts[i]);
        }
    }


    
}
