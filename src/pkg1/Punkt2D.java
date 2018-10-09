
public class Punkt2D{
    private  double x = 0;
    private  double y = 0;

    public Punkt2D(double newx, double newy){
        x = newx;
        y = newy;
    }

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    void setX(double newx){
        x = newx;
    }
    void setY(double newy){
        y = newy;
    }
    double distance(Punkt2D that) {
        return Math.sqrt(Math.pow(that.getX()-this.getX(),2)+Math.pow(that.getY()-this.getY(),2));
    }

}