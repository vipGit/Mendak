/**
 *
 * Created by Vipul Somani (vasomani@usc.edu) on 10/25/2015.
 */
public class Solution {

    public static void main(String[] args) {
        int width = 10;
        int maxJump = 3;
        int[] timeLeafArray = new int[]{9, 6, 8, 6, 2, 6, 5, 6, 7, 7}; //index = 0 is second 1
        Leaf[] positions = new Leaf[width];
        for (int i = 0; i < width; i++) {
            positions[i] = new Leaf();
        }
        for (int aTimeLeafArray : timeLeafArray) {
            Leaf l = positions[aTimeLeafArray];
            l.count++;
        }
        int pre = -1;
        for (int i = 0; i < width; i++) {
            if (positions[i].count > 0) {
                if (pre != -1) {
                    positions[pre].next = i;
                }
                positions[i].previous = pre;
                pre = i;
            }
        }
        boolean exists = true;
        boolean first = false;
        for (int i = 0; i < width && exists; i++) {
            if (positions[i].next != -1) {
                if (i <= maxJump && !first) {
                    first = true;
                }
                if ((positions[i].next - i) > maxJump) {
                    exists = false;
                } else {
                    i = positions[i].next - 1;
                }
            } else {
                if (i > maxJump && !first) {
                    exists = false;
                }
                if (i > maxJump && first && width - i > maxJump){
                    exists = false;
                }
            }
        }
        if (!exists) {
            System.out.println("Not exists path");
            return;
        }
        exists = true;
        int i;
        for (i = timeLeafArray.length - 1; i >= 0 && exists; i--) {
            int pos = timeLeafArray[i] - 1;
            positions[pos].count--;
            if (positions[pos].count == 0) {
                int prev = positions[pos].previous;
                int next = positions[pos].next;
                positions[pos].previous = -1;
                positions[pos].previous = -1;
                if (prev != -1) {
                    positions[prev].next = next;
                }
                if (next != -1) {
                    positions[next].previous = prev;
                }
                if (pos == width - 1) {
                    if (width - prev > maxJump) {
                        exists = false;
                    }
                } else if (pos == 0) {
                    if (next > maxJump) {
                        exists = false;
                    }
                } else {
                    if (next - prev > maxJump) {
                        exists = false;
                    }
                }
            }
        }
        if (!exists) {
            System.out.println("Minimum Time Required is " + (i + 2));
        }
    }
}

