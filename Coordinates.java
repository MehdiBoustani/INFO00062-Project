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