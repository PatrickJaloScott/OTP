/**
 * @author Patrick Scott
 */
public class BinarySearchPatrick {
    // gets the index of x from array a
    public static int search(int[] a, int x) {
        int firstI = 0, lastI = a.length,
                middleI = (int) (double) (lastI / 2);
        int current = a[middleI];
        while (true) {
            if(firstI == lastI && current != x) {
                break;
            }
            if (current == x) {
                return middleI;
            }
            else if (current < x) {
                firstI = middleI;
            }
            else {
                lastI = middleI;
            }
            middleI = (int)(double)((lastI-firstI)/2);
            current = a[middleI];
        }
        return -1;
    }

    public static int checkedSearch(int[] a, int x) {
        return 0;
    }
}
