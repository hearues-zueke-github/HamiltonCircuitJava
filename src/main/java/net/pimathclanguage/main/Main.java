package net.pimathclanguage.main;

import lombok.core.configuration.FileSystemSourceCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void createStringCombinations(int symbols, int length, boolean useDeterministicFunction) {
        GraphTheory gt = new GraphTheory();
        int[][][][] moreGraphs = 
        {{{{1},{2,3,4}},
        {{2},{3,4,5,6}},
        {{3},{4,5,1}},
        {{4},{5,1,2}},
        {{5},{1,2,3}},
        {{6},{1,2,3}}},
       {{{ 1},{2}},
        {{ 2},{3,4}},
        {{ 3},{5,6}},
        {{ 4},{7,8}},
        {{ 5},{9,10}},
        {{ 6},{11,12}},
        {{ 7},{13,14}},
        {{ 8},{15,16}},
        {{ 9},{1,2}},
        {{10},{3,4}},
        {{11},{5,6}},
        {{12},{7,8}},
        {{13},{9,10}},
        {{14},{11,12}},
        {{15},{13,14}},
        {{16},{15}}},
       {{{ 1},{2}},
        {{ 2},{3,4}},
        {{ 3},{5,6}},
        {{ 4},{7,8}},
        {{ 5},{9,10}},
        {{ 6},{11,12}},
        {{ 7},{13,14}},
        {{ 8},{15,16}},
        {{ 9},{17,18}},
        {{10},{19,20}},
        {{11},{21,22}},
        {{12},{23,24}},
        {{13},{25,26}},
        {{14},{27,28}},
        {{15},{29,30}},
        {{16},{31,32}},
        {{17},{1,2}},
        {{18},{3,4}},
        {{19},{5,6}},
        {{20},{7,8}},
        {{21},{9,10}},
        {{22},{11,12}},
        {{23},{13,14}},
        {{24},{15,16}},
        {{25},{17,18}},
        {{26},{19,20}},
        {{27},{21,22}},
        {{28},{23,24}},
        {{29},{25,26}},
        {{30},{27,28}},
        {{31},{29,30}},
        {{32},{31}}},
       {{{ 1},{    2, 3}},
        {{ 2},{ 4, 5, 6}},
        {{ 3},{ 7, 8, 9}},
        {{ 4},{10,11,12}},
        {{ 5},{13,14,15}},
        {{ 6},{16,17,18}},
        {{ 7},{19,20,21}},
        {{ 8},{22,23,24}},
        {{ 9},{25,26,27}},
        {{10},{ 1, 2, 3}},
        {{11},{ 4, 5, 6}},
        {{12},{ 7, 8, 9}},
        {{13},{10,11,12}},
        {{14},{13,   15}},
        {{15},{16,17,18}},
        {{16},{19,20,21}},
        {{17},{22,23,24}},
        {{18},{25,26,27}},
        {{19},{ 1, 2, 3}},
        {{20},{ 4, 5, 6}},
        {{21},{ 7, 8, 9}},
        {{22},{10,11,12}},
        {{23},{13,14,15}},
        {{24},{16,17,18}},
        {{25},{19,20,21}},
        {{26},{22,23,24}},
        {{27},{25,26   }}}};
    
        System.out.println(String.format("symbols: %d, length: %d", symbols, length));
        int[][][] graph = gt.getNewGraphStringAllCombinations(symbols, length);
        int[][] foundHamiltonCycles;
        if (!useDeterministicFunction) {
            foundHamiltonCycles = gt.getHamiltonCircuits(graph, 1);
        } else {
            foundHamiltonCycles = new int[1][];
            if (length == 2) {
                System.out.println("GOT HERE!!! 222");
                foundHamiltonCycles[0] = gt.getStringCombinationLength2(symbols);
            } else if (length == 3) {
                System.out.println("GOT HERE!!! 333");
                foundHamiltonCycles[0] = gt.getStringCombinationLength3(symbols);
            } else if (length == 4) {
                System.out.println("GOT HERE!!! 444444");
                foundHamiltonCycles[0] = gt.getStringCombinationLength4(symbols);
            } else if (length == 5) {
                System.out.println("GOT HERE!!! 55555555555");
                foundHamiltonCycles[0] = gt.getStringCombinationLength5(symbols);
            } else if (length == 6) {
                System.out.println("GOT HERE!!! 666666666666666");
                foundHamiltonCycles[0] = gt.getStringCombinationLength6(symbols);
            } else {
                foundHamiltonCycles = gt.getHamiltonCircuits(graph, 1);
            }
        }
        System.out.println("1 Solution found!");
        System.out.println("Convert FoundArray to StringNumberArray");

        String folder_path = System.getProperty("user.home")+"/Documents/string_combinations";
        String file_path = folder_path+"/sc_"+symbols+"_"+length+(useDeterministicFunction?"_determ":"")+".txt";

        try {
            File folder = new File(folder_path);
            folder.mkdirs();
            FileWriter writer = new FileWriter(file_path);
            boolean withZero = true;
            for (int[] cycle: foundHamiltonCycles) {
                int[] numberArray;
                if (!useDeterministicFunction) {
                    numberArray = gt.convertStringAllCombinationsToArray(cycle, symbols, length, withZero);
                } else {
                    numberArray = foundHamiltonCycles[0];
                }
                writer.write(Integer.toString(numberArray[0]));
                for (int i = 1; i < numberArray.length; i++) {
                    writer.write(","+Integer.toString(numberArray[i]));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void calculateAGraph() {
        GraphTheory gt = new GraphTheory();
        int[][][][] moreGraphs = 
      {{{{1},{2,3,5}},
        {{2},{3,1,5}},
        {{3},{1,2,4,5}},
        {{4},{3,5}},
        {{5},{4,3,2,1}}},
       {{{ 1        },{    2, 3}},
        {{    14    },{13,   15}},
        {{        27},{25,26   }},
        {{    10, 19},{ 1, 2, 3}},
        {{ 2, 11, 20},{ 4, 5, 6}},
        {{ 3, 12, 21},{ 7, 8, 9}},
        {{ 4, 13, 22},{10,11,12}},
        {{ 5,     23},{13,14,15}},
        {{ 6, 15, 24},{16,17,18}},
        {{ 7, 16, 25},{19,20,21}},
        {{ 8, 17, 26},{22,23,24}},
        {{ 9, 18    },{25,26,27}}},
       {{{1}, {2, 3, 4}},
        {{2}, {3, 4, 1}},
        {{3}, {4, 1, 2}},
        {{4}, {1, 2, 3}}}};

        int[][][] graph = moreGraphs[1];
        graph = gt.convertGraphTyp1ToTyp0(graph);
        
        gt.printGraph(graph);
        boolean testing = gt.checkGraphTyp0(graph);
        System.out.println("Graph is " + (testing ? "Type 0" : "not Type 0"));
        
        if (testing) {
            int[][] found = gt.getHamiltonCircuits(graph, 4);
            System.out.println("Solutions found!");
            gt.printFoundHamiltonCircuits(found);
        }
    } 
    
    public static void main(String[] args) throws IOException {
        Main main = new Main();

        String usage = "usages:\n" +
                "  <program> allcombinations <symbols> <length>\n" +
                "  <program> getstringcombo <symbols> <length>\n" +
                "  <program> getstringcombo2 <symbols> <length>";

        if (args.length < 3) {
            System.out.println(usage);
            return;
        }

        String modus = args[0];
        String symobolsStr = args[1];
        String lengthStr = args[2];

        int symbols;
        int length;
        try {
            symbols = Integer.valueOf(symobolsStr);
        } catch (Exception e) {
            System.out.println("Input for symbols is not a Number!");
            return;
        }
        try {
            length = Integer.valueOf(lengthStr);
        } catch (Exception e) {
            System.out.println("Input for length is not a Number!");
            return;
        }

        if (modus.equals("allcombinations")) {
//        main.calculateAGraph();
        } else if (modus.equals("getstringcombo")) {
            main.createStringCombinations(symbols, length, false);
        } else if (modus.equals("getstringcombodeter")) {
            main.createStringCombinations(symbols, length, true);
        } else {
            System.out.println("2nd argument is wrong!");
            System.out.println(usage);
            return;
        }

        return;
    }
}
