package object;



import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        collision = true;
    }

}
