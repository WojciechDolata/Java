
public class Punkt3D extends Punkt2D{

    private  double z = 0;

    public Punkt3D(double newx, double newy, double newz){
        super(newx, newy);
        z = newz;
    }

    double getZ(){
        return z;
    }

    void setZ(double newz){
        z = newz;
    }
    double distance(Punkt3D that) {
        return Math.sqrt(Math.pow(that.getX()-this.getX(),2)+Math.pow(that.getY()-this.getY(),2)+Math.pow(this.getZ()-that.getZ(),2));
    }

}