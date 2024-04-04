/*
 * INFO0062 - Object-Oriented Programming
 * Code adopted from Exercise series 3
 *
 * This Coordinates class involved in all exercises of this series and isn't supposed to be 
 * edited.
 *
 * @author: J.-F. Grailet
 */

public class Coordinates
{
    private int x, y, z;
    
    public Coordinates()
    {
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Coordinates(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setZ(int z){
        this.z = z;
    }
    
    public void translate(int dx, int dy, int dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }

    // Checks if 2 points are equal
    public boolean isEqual(Coordinates c){
        return x == c.x && y == c.y && z == c.z;
    }
    
    public String toString()
    {
        return "(" + x + "," + y + "," + z + ")";
    }
}
