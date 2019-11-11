
import java.util.*;

public class Main {
    private static int M, N, K;
    private static int[][] sensors;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int RANGE = 2;
    private static Queue<Integer> queue;
    private static Set<Integer> open;
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        M = cin.nextInt();
        N = cin.nextInt();
        K = cin.nextInt();
        sensors = new int[K][3];
        queue = new LinkedList<Integer>();
        open = new HashSet<Integer>((int) (K*1.5));
        for (int i = 0; i < K; i++) {
            sensors[i][X] = cin.nextInt();
            sensors[i][Y] = cin.nextInt();
            sensors[i][RANGE] = cin.nextInt();
            if (touch(true, i)) queue.add(i);
            else open.add(i);
        }

        System.out.println(solution());
    }


    private static char solution() {
        for (int e : queue) {
            if (touch(false, e)) return 'N';
        }
        List<Integer> close;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            close = new LinkedList<>();
            for (int e : open) {
                if (contact(temp, e)) {
                    if (touch(false, e)) {
                        return 'N';
                    }
                    queue.add(e);
                    close.add(e);
                }
            }
            open.removeAll(close);
        }
        return 'S';
    }


    private static boolean touch(boolean isSideOne, int sensor) {
        if (isSideOne) {
            return sensors[sensor][X] <= sensors[sensor][RANGE] ||
                    N - sensors[sensor][Y] <= sensors[sensor][RANGE];
        } else {
            return M - sensors[sensor][X] <= sensors[sensor][RANGE] ||
                    sensors[sensor][Y] <= sensors[sensor][RANGE];
        }
    }

    private static boolean contact(int sensor1, int sensor2) {
        int x = (sensors[sensor1][X] - sensors[sensor2][X]);
        int y = (sensors[sensor1][Y] - sensors[sensor2][Y]);
        int range = (sensors[sensor1][RANGE] + sensors[sensor2][RANGE]);
        return range*range >= x*x + y*y;
    }
}
