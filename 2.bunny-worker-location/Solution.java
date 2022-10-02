public class Solution {
    public static String solution(long x, long y) {
        long m = x + y - 1;
        long vertex = getVertexOnVerticalAxis(m);
        return String.valueOf(vertex + x - 1);
    }

    public static long getVertexOnVerticalAxis(long y) {
        return 1 + (y - 1) * y / 2;
    }
}

// Explanation for the formula

//| P(1,5)
//| 7 P(2,4)
//| 4 8 P(3,3)
//| 2 5 9 P(4,2)
//| 1 3 6 10 P(5,1)

// Comment 1: The points on the hypotenuse all have the same sum of indices.
// For example:
// - Sum indices (x + y) of P(1,5) = 1 + 5 = 6
// - Sum indices (x + y) of P(2,4) = 2 + 4 = 6
// - Sum indices (x + y) of P(3,3) = 3 + 3 = 6
// ...
// For a given point P(x,y), the coordinates of the vertex on the vertical axis of the triangle
// whose hypotenuse contains the point P is P(1, x + y - 1)
// And P(x,y) = P(1, x + y - 1) + (x -1)

// Comment 2:
// Let H(m) be the number of points on the hypotenuse of a right triangle whose side length is m.
// P(1,m) = 1 + H(m-1) + H(m-2) + ... + H(1)
// But: H(m) = m;
// => P(1,m) = 1 + ((m-1) + (m-2) + ... + 1) = 1 + (m-1)*m /2 ;