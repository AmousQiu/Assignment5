import java.io.*;
import java.util.*;

public class graphDemo {
    public static void main(String args[]) throws IOException {
        //read from the input file
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter the file to read from:");
        String filename = keyboard.nextLine();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        int num = Integer.parseInt(inputFile.nextLine());
        //System.out.println(num);
        int[][] adjMatrix = new int[num][num];//create an adjacency matrix
        while (inputFile.hasNext()) {
            String firstVertex = inputFile.next();
            String secondVertex = inputFile.next();
            int x = firstVertex.charAt(0) - 65;//transfer the letter to num
            //System.out.println(x);
            int y = secondVertex.charAt(0) - 65;
            //System.out.println(y);
            adjMatrix[x][y] = 1;//1 in the matrix means a route such as A->E
            adjMatrix[y][x] = 1;
        }
        //print the matrix
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }

        boolean[] visited = new boolean[num];
        System.out.println();
        //Depth first Search (Recursive algorithm )
        depthFirstRecursive(0, adjMatrix, visited);

        System.out.println();
        //Depth first Search (Recursive algorithm )
        boolean[] nodeVisited = new boolean[num];
        breadthFirst(adjMatrix, nodeVisited);
    }

    //Depth first Search (Recursive algorithm )
    public static void depthFirstRecursive(int i, int[][] adjMatrix, boolean[] visited) {
        if (!visited[i]) {
            visited[i] = true;
            System.out.print((char) (i + 65) + " ");//visit the vertex and mark it as read
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] == 1 && !visited[j]) {//go to its neighbourhood who has not been visited
                    depthFirstRecursive(j, adjMatrix, visited);
                }
            }
        }
    }

    //Depth first Search (Recursive algorithm )
    public static void breadthFirst(int[][] adjMatrix, boolean[] nodeVisited) {
        Queue<Integer> que = new LinkedList<>();//initialize a queue to store the vertex
        int temp;
        que.add(0);//add the first vertex into the queue
        while (!que.isEmpty()) {
            temp = que.remove();//remove the first and put it into the temp position
            if (!nodeVisited[temp]) {
                System.out.print((char) (temp + 65) + " ");
                nodeVisited[temp] = true;//make it as read
            }
            for (int i = 0; i < adjMatrix[0].length; i++) {//go visit all the neighborhoods
                if ((adjMatrix[temp][i] == 1) && (!nodeVisited[i])) {//when the neighbour is not visited before
                    que.add(i);//add it to the queue
                    nodeVisited[i] = true;//make it as read
                    System.out.print((char) (i + 65) + " ");
                    temp = i;
                }
            }

        }

    }
}

