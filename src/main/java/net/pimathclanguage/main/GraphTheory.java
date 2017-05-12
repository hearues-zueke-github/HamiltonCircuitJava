package net.pimathclanguage.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GraphTheory
{
    // for example 6! complete graph
    public int[][][] getNewGraphEveryoneWithEveryone(int length)
    {
        int[][][] graph = new int[length][][];
        
        for (int i = 1; i < length + 1; i++)
        {
            graph[i - 1] = new int[2][];
            
            graph[i - 1][0] = new int[1];
            graph[i - 1][0][0] = i;
            
            graph[i - 1][1] = new int[length - 1];
            int pos = 0;
            for (int j = 1; j < length + 1; j++)
            {
                if (i != j)
                {
                    graph[i - 1][1][pos] = j;
                    pos++;
                }
            }
        }
        
        return graph;
    }
    
    // for example 1122331321 complete graph
    public int[][][] getNewGraphStringAllCombinations(int symbols, int length) {
        int size = (int)Math.pow(symbols, length);
        int[][][] graph = new int[size][][];
        
        int vertex = 0;
        for (int pos = 0; pos < size; pos++) {
            graph[pos] = new int[2][];
            
            graph[pos][0] = new int[1];
            graph[pos][0][0] = pos + 1;
            
            int newsize = (((pos + 1 - vertex) > 0) && ((vertex + symbols - pos) > 0)) ? symbols - 1 : symbols;
            graph[pos][1] = new int[newsize];
            
            int counter = 0;
            for (int pos2 = 0; pos2 < symbols; pos2++) {
                if (vertex + pos2 != pos) {
                    graph[pos][1][counter] = vertex + pos2 + 1;
                    counter++;
                }
            }
            
            vertex = (vertex + symbols) % size;
        }
        
        return graph;
    }
    
    public int[] convertStringAllCombinationsToArray(int[] array, int symbols, int length, boolean withZero)
    {
        int size = array.length;
        int[] output = new int[size];

        int[] first = new int[length];

        for (int loop = 0; loop < length-1; loop++) {
            output[loop] = (array[size-length+1+loop]+symbols-1) % symbols;
        }
        for (int loop = 0; loop < size-length+1; loop++) {
            output[length-1+loop] = (array[loop]+symbols-1) % symbols + (withZero ? 0 : 1);
        }
        
        return output;
    }
    
    public String convertInt1DToString(int[] array) {
        String output = ""+array[0];
        
        for (int pos = 1; pos < array.length; pos++) {
            output += ", "+array[pos];
        }
        
        return output;
    }

    public int[] getStringCombinationLength2(int symbols) {
        int[] combination = new int[symbols*symbols];

        int pos = 0;
        for (int a1 = 0; a1 < symbols; a1++) {
            combination[pos++] = a1;
            for (int a2 = a1+1; a2 < symbols; a2++) {
                combination[pos++] = a1;
                combination[pos++] = a2;
            }
        }

        return combination;
    }

    public int[] getStringCombinationLength3(int symbols) {
        int[] combination = new int[symbols*symbols*symbols];

        int pos = 0;
        for (int a1 = 0; a1 < symbols; a1++) {
            combination[pos++] = a1;
            for (int a2 = a1; a2 < symbols; a2++) {
                for (int a3 = a1+1; a3 < symbols; a3++) {
                    combination[pos++] = a1;
                    combination[pos++] = a2;
                    combination[pos++] = a3;
                }
            }
        }

        return combination;
    }
    
    public boolean checkGraphTyp0(int[][][] graph) {
        // Check if anything is null!
        if (graph == null) {
            return false;
        }
        for (int[][] gg: graph) {
            if (gg == null) {
                return false;
            }
            for (int[] g: gg) {
                if (g == null) {
                    return false;
                }
            }
        }

        // iterate through all (V,E) connections 
        for (int pos1 = 0; pos1 < graph.length; pos1++) {
            // check array length
            if (graph[pos1].length != 2 || graph[pos1][0].length != 1 || graph[pos1][1].length == 0) {
                System.out.println("Failed test 1");
                return false;
            }
        }

        List<int[][]> graph_array = new ArrayList<>();
        for (int[][] g: graph) {
            graph_array.add(g);
        }

        for (int[][] g1: graph) {
            for (int[] g12: g1) {
                List<Integer> g12_array = new ArrayList<>();
                for (int i: g12) {
                    g12_array.add(i);
                }
                int size = g12_array.size();
                for (int i = 0; i < g12_array.size()-1; i++) {
                    if (g12_array.subList(i+1, size).contains(g12_array.get(i))) {
                        System.out.println("Failed test 2");
                        return false;
                    }
                }
            }
            
            // check all V_begin and V_end for edges duplication

            for (int pos2_1 = 0; pos2_1 < g1.length; pos2_1++) { // loop for V_beginn
                for (int pos2_2 = pos2_1 + 1; pos2_2 < g1.length; pos2_2++) { // loop for V_end
                    for (int pos3_1 = 0; pos3_1 < g1[pos2_1].length; pos3_1++) { // loop for V_beginn each element
                        for (int pos3_2 = 0; pos3_2 < g1[pos2_2].length; pos3_2++) { // loop for V_end each element
                            if (g1[pos2_1][pos3_1] == g1[pos2_2][pos3_2]) {
                                System.out.println("Failed test 3");
                                return false;
                            }
                        }
                    }
                }
            }
            
            // check other V_begin with the first
            for (int pos2 = graph_array.indexOf(g1)+1; pos2 < graph.length; pos2++) {
                for (int pos3_1 = 0; pos3_1 < g1[0].length; pos3_1++) {
                    for (int pos3_2 = 0; pos3_2 < graph[pos2][0].length; pos3_2++) {
                        if (g1[0][pos3_1] == graph[pos2][0][pos3_2]) {
                            System.out.println("Failed test 4");
                            return false;
                        }
                    }
                }
            }
        }
        
        // 1st most important Test!!!
        // check, if every V_end can be found as a V_begin!
        for (int pos1 = 0; pos1 < graph.length; pos1++) {
            for (int pos2 = 1; pos2 < graph[pos1].length; pos2++) {
                for (int pos3 = 0; pos3 < graph[pos1][pos2].length; pos3++) {
                    for (int pos4 = 0; pos4 < graph.length; pos4++) {
                        if (graph[pos1][pos2][pos3] == graph[pos4][0][0]) {
                            System.out.println("Failed test 5");
                            return false;
                        }
                    }
                }
            }
        }
        
        // 2nd most important Test!!!
        // check, if every V_begin can be found anywhere in V_end
        for (int pos1 = 0; pos1 < graph.length; pos1++) {
            for (int pos2 = 0; pos2 < graph.length; pos2++) {
                for (int pos3 = 0; pos3 < graph[pos2][1].length; pos3++) {
                    if (graph[pos1][0][0] == graph[pos2][1][pos3]) {
                        System.out.println("Failed test 6");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public int[][][] convertGraphTyp1ToTyp0(int[][][] graph) {
        int[][][] newGraph;
        
        // check how many Vertexes this graph has
        int amountVertex = 0;
        for (int pos1 = 0; pos1 < graph.length; pos1++) {
            amountVertex += graph[pos1][0].length;
        }
        
        newGraph = new int[amountVertex][][];
        
        int pos = 0;
        
        for (int pos1 = 0; pos1 < graph.length; pos1++){
            for (int pos2 = 0; pos2 < graph[pos1][0].length; pos2++) {
                newGraph[pos + pos2] = new int[graph[pos1].length][];
                
                newGraph[pos + pos2][0] = new int[1];
                newGraph[pos + pos2][0][0] = graph[pos1][0][pos2];
                
                for (int pos3 = 1; pos3 < graph[pos1].length; pos3++) {
                    newGraph[pos + pos2][pos3] = new int[graph[pos1][pos3].length];
                    
                    for (int pos4 = 0; pos4 < graph[pos1][pos3].length; pos4++) {
                        newGraph[pos + pos2][pos3][pos4] = graph[pos1][pos3][pos4];
                    }
                }
            }
            pos += graph[pos1][0].length;
        }
        
        return newGraph;
    }
    
    public void printGraph(int[][][] graph) {
        System.out.println("Output of graph:");
        for (int pos1 = 0; pos1 < graph.length; pos1++) {
            System.out.print("VB " + pos1 + ":");
            for (int pos2 = 0; pos2 < graph[pos1][0].length; pos2++) {
                System.out.print(", " + graph[pos1][0][pos2]);
            }
            
            for (int pos2 = 1; pos2 < graph[pos1].length; pos2++) {
                System.out.print("   VE " + pos2+":");
                for (int pos3 = 0; pos3 < graph[pos1][pos2].length; pos3++) {
                    System.out.print(", " + graph[pos1][pos2][pos3]);
                }
            }
            System.out.print("\n");
        }
    }

    private int[] getMixedIndices(int length) {
        int[] idxs = new int[length];

        for (int i = 0; i < length; i++) {
            idxs[i] = i;
        }

        Random rand = new Random();
        for (int i = 0; i < length*5; i++) {
            int idx1 = rand.nextInt(length);
            int idx2 = (idx1 + rand.nextInt(length-1)) % length;

            int t = idxs[idx1];
            idxs[idx1] = idxs[idx2];
            idxs[idx2] = t;
        }

        return idxs;
    }

    public int[][][] mixGraph(int[][][] graph) {
        int[][][] newGraph = new int[graph.length][][];

        int length = graph.length;
        int[] idxs = getMixedIndices(length);
        for (int i = 0; i < length; i++) {
            newGraph[i] = graph[idxs[i]];
        }

        return newGraph;
    }
    
    public void printFoundHamiltonCircuits(int[][] found)
    {
        System.out.println("Found Hamilton Circuits:");
        for (int pos1 = 0; pos1 < found.length; pos1++) {
            System.out.print(pos1 + 1 + " Solution:");
            for (int x : found[pos1]) {
                System.out.print(", " + x);
            }
            System.out.println("");
        }
    }
    
    public int[][] getHamiltonCircuits(int[][][] graph, int amount) {
        if (amount < 0) {
            return null;
        }

        int[][] foundCircuits = new int[amount][graph.length];
        int found = 0;
        
        // Search for hamilton circuits in an graph
        int pos = 0;
        int posMax = 0;
        int[] temp = new int[graph.length];
        int[] temp_pos = new int[graph.length];
        HashMap<Integer, HashMap<Integer, Integer>> map = getMapOfGraph(graph);
        HashMap<Integer, Integer> mapSize = getMapOfGraphSize(graph);
        
        temp[0] = graph[0][0][0];
        for (int loop = 0; loop < temp_pos.length; loop++) {
            temp_pos[loop] = -1;
        }
        
        System.out.println("Do the search");
        while (pos > -1) {
            temp_pos[pos]++;
            if (temp_pos[pos] >= mapSize.get(temp[pos])) {
                temp_pos[pos] = -1;
                pos--;
            } else {
                temp[pos + 1] = map.get(temp[pos]).get(temp_pos[pos]);
                pos++;
                if ((posMax < pos) && (pos % 1000 == 0)) {
                    posMax = pos;
                    System.out.println("Best found depth: " + posMax);
                }
                if (pos >= temp.length - 1) {
                    boolean isFound = false;

                    for (int[][] x : graph) {
                        if (x[0][0] == temp[pos]) {
                            if (findValueInArray(x[1], temp[0])) {
                                isFound = true;
                                break;}
                            else {
                                break;
                            }
                        }
                    }

                    if (isFound && !isDuplicationUntilPos(temp, pos)) {
                        foundCircuits[found] = deepcopyInt1D(temp);
                        found++;
                        if (found >= amount) {
                            break;
                        }
                    }

                    pos--;
                } else if (isDuplicationUntilPos(temp, pos)) {
                    pos--;
                }
            }
        }
        
        return foundCircuits;
    }
    
    public int[] deepcopyInt1D(int[] array)
    {
        int[] ret = new int[array.length];
        
        for (int pos1 = 0; pos1 < array.length; pos1++)
        {
            ret[pos1] = array[pos1];
        }
        
        return ret;
    }
    
    public int[][] deepcopyInt2D(int[][] array)
    {
        int[][] ret = new int[array.length][];
        
        for (int pos1 = 0; pos1 < array.length; pos1++)
        {
            ret[pos1] = new int[array[pos1].length];
            
            for (int pos2 = 0; pos2 < array[pos1].length; pos2++)
            {
                ret[pos1][pos2] = array[pos1][pos2];
            }
        }
        
        return ret;
    }
    
    public int[][][] deepcopyInt3D(int[][][] array)
    {
        int[][][] ret = new int[array.length][][];
        
        for (int pos1 = 0; pos1 < array.length; pos1++)
        {
            ret[pos1] = new int[array[pos1].length][];
            
            for (int pos2 = 0; pos2 < array[pos1].length; pos2++)
            {
                ret[pos1][pos2] = new int[array[pos1][pos2].length];
                for (int pos3 = 0; pos3 < array[pos1][pos2].length; pos3++)
                {
                    ret[pos1][pos2][pos3] = array[pos1][pos2][pos3];
                }
            }
        }
        
        return ret;
    }
    
    private HashMap<Integer, HashMap<Integer, Integer>> getMapOfGraph(int[][][] graph)
    {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            map.put(graph[pos1][0][0], new HashMap<Integer, Integer>());
        }
        
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            for (int pos2 = 0; pos2 < graph[pos1][1].length; pos2++)
            {
                map.get(graph[pos1][0][0]).put(pos2, graph[pos1][1][pos2]);
            }
        }
        
        return map;
    }

    private HashMap<Integer, Integer> getMapOfGraphSize(int[][][] graph) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int loop = 0; loop < graph.length; loop++) {
            map.put(graph[loop][0][0], graph[loop][1].length);
        }
        
        return map;
    }
    
    private boolean isDuplication(int[] array, int pos) {
        if (pos >= array.length || pos < 0) {
            return false;
        }
        int val = array[pos];
        for (int loop = 0; loop < pos; loop++) {
            if (val == array[loop]) {
                return true;
            }
        }
        for (int loop = pos+1; loop < array.length; loop++) {
            if (val == array[loop]) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicationUntilPos(int[] array, int pos) {
        if (pos >= array.length || pos < 0) {
            return false;
        }
        int val = array[pos];
        for (int loop = 0; loop < pos; loop++) {
            if (val == array[loop]) {
                return true;
            }
        }
        return false;
    }
    
    private boolean findValueInArray(int[] array, int value) {
        for (int x: array) {
            if (x == value) {
                return true;
            }
        }
        return false;
    }
}
