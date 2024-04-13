/*
 * INFO0062 - Object-Oriented Programming
 * Programming Project -- Academic Year 2023-2024
 *
 * CubeType enum represents the type of cubes that can be present in a snake cube puzzle.
 * • E (for Endpoint) if the cube is the first or the last one, i.e., it has only one neighbor (or zero if n = 1).
 * • S (for Straight) if the cube has two neighbors on opposite sides.
 * • A (for Angle) if the cube has two neighbors that share a common edge
 * 
 * @author: ALBASHITYALSHAIER Abdelkader & BOUSTANI Mehdi 
 * @date : 14/04/2024
 */

public enum CubeType {
    ENDPOINT,
    ANGLE,
    STRAIGHT
}
