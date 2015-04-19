package net.pimathclanguage.main;

public class GraphTheory
{
    public boolean checkGraphTyp0(int[][][] graph)
    {
        // iterate through all (V,E) connections 
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            // check array length
            if (graph[pos1].length != 2 || graph[pos1][0].length != 1 || graph[pos1][1].length == 0)
            {
                System.out.println("Failed test 1");
                return false;
            }
        }
        
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            // check all V_beginn and V_end for duplication
            for (int pos2 = 0; pos2 < graph[pos1].length; pos2++)
            {
                // take one element
                for (int pos3 = 0; pos3 < graph[pos1][pos2].length; pos3++)
                {
                    // take another element
                    for (int pos4 = pos3 + 1; pos4 < graph[pos1][pos2].length; pos4++)
                    {
                        // and iterate through all elements through
                        if (graph[pos1][pos2][pos3] == graph[pos1][pos2][pos4])
                        {
                            System.out.println("Failed test 2");
                            return false;
                        }
                    }
                }
            }
            
            // check all V_begin and V_end for edges duplication
            // loop for V_beginn
            for (int pos2_1 = 0; pos2_1 < graph[pos1].length; pos2_1++)
            {
                // loop for V_end
                for (int pos2_2 = pos2_1 + 1; pos2_2 < graph[pos1].length; pos2_2++)
                {
                    // loop for V_beginn each element
                    for (int pos3_1 = 0; pos3_1 < graph[pos1][pos2_1].length; pos3_1++)
                    {
                        // loop for V_end each element
                        for (int pos3_2 = 0; pos3_2 < graph[pos1][pos2_2].length; pos3_2++)
                        {
                            if (graph[pos1][pos2_1][pos3_1] == graph[pos1][pos2_2][pos3_2])
                            {
                                System.out.println("Failed test 3");
                                return false;
                            }
                        }
                    }
                }
            }
            
            // check other V_begin with the first
            for (int pos2 = pos1 + 1; pos2 < graph.length; pos2++)
            {
                for (int pos3_1 = 0; pos3_1 < graph[pos1][0].length; pos3_1++)
                {
                    for (int pos3_2 = 0; pos3_2 < graph[pos2][0].length; pos3_2++)
                    {
                        if (graph[pos1][0][pos3_1] == graph[pos2][0][pos3_2])
                        {
                            System.out.println("Failed test 4");
                            return false;
                        }
                    }
                }
            }
        }
        
        // 1st most important Test!!!
        // check, if every V_end can be found as a V_begin!
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            for (int pos2 = 1; pos2 < graph[pos1].length; pos2++)
            {
                for (int pos3 = 0; pos3 < graph[pos1][pos2].length; pos3++)
                {
                    boolean isThere = false;
                    for (int pos4 = 0; pos4 < graph.length; pos4++)
                    {
                        if (graph[pos1][pos2][pos3] == graph[pos4][0][0])
                        {
                            isThere = true;
                            break;
                        }
                    }
                    
                    if (!isThere)
                    {
                        System.out.println("Failed test 5");
                        return false;
                    }
                }
            }
        }
        
        // 2nd most important Test!!!
        // check, if every V_begin can be found anywhere in V_end
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            boolean isThere = false;
            
            for (int pos2 = 0; pos2 < graph.length; pos2++)
            {
                for (int pos3 = 0; pos3 < graph[pos2][1].length; pos3++)
                {
                    if (graph[pos1][0][0] == graph[pos2][1][pos3])
                    {
                        isThere = true;
                        break;
                    }
                }
                if (isThere)
                {
                    break;
                }
            }
            
            if (!isThere)
            {
                System.out.println("Failed test 6");
                return false;
            }
        }
        return true;
    }
    
    public int[][][] convertGraphTyp1ToTyp0(int[][][] graph)
    {
        int[][][] newGraph;
        
        // check how many Vertexes this graph has
        int amountVertex = 0;
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            amountVertex += graph[pos1][0].length;
        }
        
        newGraph = new int[amountVertex][][];
        
        int pos = 0;
        
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            for (int pos2 = 0; pos2 < graph[pos1][0].length; pos2++)
            {
                newGraph[pos + pos2] = new int[graph[pos1].length][];
                
                newGraph[pos + pos2][0] = new int[1];
                newGraph[pos + pos2][0][0] = graph[pos1][0][pos2];
                
                for (int pos3 = 1; pos3 < graph[pos1].length; pos3++)
                {
                    newGraph[pos + pos2][pos3] = new int[graph[pos1][pos3].length];
                    
                    for (int pos4 = 0; pos4 < graph[pos1][pos3].length; pos4++)
                    {
                        newGraph[pos + pos2][pos3][pos4] = graph[pos1][pos3][pos4];
                    }
                }
            }
            pos += graph[pos1][0].length;
        }
        
        return newGraph;
    }
    
    public void printGraph(int[][][] graph)
    {
        System.out.println("Output of graph:");
        for (int pos1 = 0; pos1 < graph.length; pos1++)
        {
            System.out.print("VB" + pos1 + ":");
            for (int pos2 = 0; pos2 < graph[pos1][0].length; pos2++)
            {
                System.out.print(", " + graph[pos1][0][pos2]);
            }
            
            for (int pos2 = 1; pos2 < graph[pos1].length; pos2++)
            {
                System.out.print("   VE" + pos2);
                for (int pos3 = 0; pos3 < graph[pos1][pos2].length; pos3++)
                {
                    System.out.print(", " + graph[pos1][pos2][pos3]);
                }
            }
            System.out.print("\n");
        }
    }
}
