package net.pimathclanguage.main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by doublepmcl on 1/13/18.
 */
public class MathProblems {
    public int[][] getSolutionsForSquareSumProblem(int n) {
        int n_sqr_max = (int)Math.sqrt((n-1)*2);

        System.out.println("n: "+n);
        System.out.println("n_sqr_max: "+n_sqr_max);

        int[] sqr_nums = new int[n_sqr_max-1];
        for (int i = 2; i <= n_sqr_max; i++) {
            sqr_nums[i-2] = i*i;
        }
        System.out.println("sqr_nums: "+ Arrays.toString(sqr_nums));

        List<List<Integer>> g_array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g_array.add(new ArrayList<Integer>());
        }
        for (int sqr_n: sqr_nums) {
            int max_i = (sqr_n+1)/2;
            for (int i = 1; i < max_i; i++) {
                int n1 = i-1;
                int n2 = sqr_n-i-1;
                if (n2 > n-1) {
                    continue;
                }
                g_array.get(n1).add(n2);
                g_array.get(n2).add(n1);
            }
        }

        int[][][] g = new int[n][][];
        for (int i = 0; i < n; i++) {
            int[][] g1 = new int[2][];
            int[] g11 = new int[] {i};
            List<Integer> temp_arr = g_array.get(i);
            Iterator<Integer> iterator = temp_arr.iterator();
            int[] g12 = new int[temp_arr.size()];
            for (int j = 0; j < g12.length; j++) {
                g12[j] = iterator.next();
            }
            g1[0] = g11;
            g1[1] = g12;

            g[i] = g1;
        }

        System.out.println("g: "+Arrays.deepToString(g));

        GraphTheory gt = new GraphTheory();
        int [][] found_solutions = null;
        for (int i = 0; i < n; i++) {
            found_solutions = gt.getHamiltonCircuits(g, 10, i, false);
            if (found_solutions != null) {
                break;
            }
        }

        if (found_solutions != null) {
            for (int[] solution: found_solutions) {
                for (int i = 0; i < solution.length; i++) {
                    solution[i]++;
                }
            }
            gt.printFoundHamiltonCircuits(found_solutions);
        } else {
            System.out.println("nothing found!");
        }

        return found_solutions;
    }
}
