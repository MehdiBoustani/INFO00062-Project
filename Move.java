/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * Move enum represents the possible directions of movement in a 3D space.
 * Each direction corresponds to movement along one of the axes: x, y, or z.
 * 
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

public enum Move {
    UP,     // x + 1
    DOWN,   // x - 1

    LEFT,   // y - 1 
    RIGHT,  // y + 1
    
    BACK,   // z - 1
    FRONT   // z + 1
}
