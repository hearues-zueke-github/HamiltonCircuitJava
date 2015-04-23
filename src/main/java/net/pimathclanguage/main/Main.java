package net.pimathclanguage.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws IOException
    {
//        System.out.println("Hello World");
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
        
        int symbols = 6;
        int length = 4;
//        int[][][] graph = gt.getNewGraphEveryoneWithEveryone(100);//moreGraphs[3];
        int[][][] graph = gt.getNewGraphStringAllCombinations(symbols, length);
        boolean testing = true;
        //gt.printGraph(graph);
        //testing = gt.checkGraphTyp0(graph);
        System.out.println("Graph is " + (testing ? "Type 0" : "not Type 0"));
        
        graph = gt.convertGraphTyp1ToTyp0(graph);
        
        //gt.printGraph(graph);
        //testing = gt.checkGraphTyp0(graph);
        System.out.println("Graph is " + (testing ? "Type 0" : "not Type 0"));
        
        if (testing)
        {
            int[][] found = gt.getHamiltonCircuits(graph, 1);
            System.out.println("1 Solution found!");
            //gt.printFoundHamiltonCircuits(found);
            System.out.println("Convert FoundArray to StringNumberArray");
            int[] numberArray = gt.convertStringAllCombinationsToArray(found[0], symbols, length, true);
            //System.out.println(gt.convertInt1DToString(numberArray));
            
            String file_path = "/home/haris/Dropbox/all_document/string_combinations/" +
                    "sc_" + symbols + "_" + length + ".txt";
            FileWriter writer = null;
            
            File file = new File(file_path);
            file.createNewFile();
            writer = new FileWriter(file_path);
            
            for (int i = 0; i < numberArray.length; i++)
            {
                writer.write(Integer.toString(numberArray[i]));
                writer.write(",");
            }
            writer.close();
        }
    }
}
