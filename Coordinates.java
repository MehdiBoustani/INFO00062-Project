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
    
    public void translate(int dx, int dy, int dz)
    {
        x += dx;
        y += dy;
        z += dz;
    }
    
    public String toString()
    {
        return "(" + x + "," + y + "," + z + ")";
    }
}