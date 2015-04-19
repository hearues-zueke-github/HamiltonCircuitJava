package net.pimathclanguage.main;

public class Main
{
    public static void main(String[] args)
    {
//        System.out.println("Hello World");
        GraphTheory gt = new GraphTheory();
        int[][][] graph = {{{1},{3,4}},
                           {{2,6},{1,3}},
                           {{3},{2,4,5}},
                           {{4},{1,2,6,3}},
                           {{5},{2,4}}};
        boolean testing;
        gt.printGraph(graph);
        testing = gt.checkGraphTyp0(graph);
        System.out.println("Graph is " + testing);
        
        graph = gt.convertGraphTyp1ToTyp0(graph);
        
        gt.printGraph(graph);
        testing = gt.checkGraphTyp0(graph);
        System.out.println("Graph is " + testing);
    }
}
