/*import java.util.Scanner;

public class Main {
    private static final int FACTOR = 1000000007;
    private static int[][] save;

    //计算一条有n对房子的胡同 可能拥有的遍历路径
    //只能从胡同两端的四个房子开始
    //comeBack如果是true，表示路径终点的房子和起点的房子必须是一对（一个上一个下）
    private static long solution(int n, boolean comeBack) {
        if (n <= 0) return 1;
        else if (n == 1) return (comeBack)? 2 : 0;
        else if (n == 2) return (comeBack)? 4 : 8;
        //如果已经存了计算结果，直接返回结果
        if (save[n][((comeBack)? 0 : 1)] != 0) return save[n][((comeBack)? 0 : 1)];

        long result = 0;
        //如果路径要求返回，子路径也必须是返回类型的
        if (comeBack) {
            result = (2 * solution(n-1, true));
        } else {//如果不要求返回，子路径可以是任意类型，但要保证终点不和起点成对，否则和上一种情况有重复计算
            result += (2 * getInAnyState(n-1))%FACTOR;
            result += (4 * getInAnyState(n-2))%FACTOR;
        }
        result %= FACTOR;
        save[n][((comeBack)? 0 : 1)] = (int) result;
        return result;
    }

    //任意的路径类型时，可能的路径数量为两种情况之和
    private static long getInAnyState(int n) {
        return (solution(n, false) + solution(n, true))%FACTOR;
    }

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        save = new int[n+1][2];
        //预先计算，把结果保存在数组里
        for (int i = 1; i <= n; i++) {
            getInAnyState(i);
        }

        long result = 0;
        //把每一对房子当做一次起点
        for (int i = 1; i <= n; i++) {
            //如果是把两端作为起点
            if (i == 1 || i == n) {
                //任意的路径类型
                result += getInAnyState(n);
            } else {
                //如果是胡同中间的房子作为起点
                //先拜访一侧的房子，也就是说一侧的路径类型是“需要返回”
                //然后再拜访另一侧的房子，也就是另一侧的路径类型是“任意”
                //路径数量就是两侧路径数量的乘积*2（起点有上下两个房子可选）*2（先去左或右两种可能性）
                //由于对称性，当i=n-i+1时数量相同，式子一样。
                result += (4 * solution(i-1, true) * getInAnyState(n-i))%FACTOR;
            }
            result %= FACTOR;
        }
        System.out.println(result);


    }
}
*/