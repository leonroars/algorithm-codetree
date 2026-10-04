import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

import java.util.ArrayDeque;

public class Main {
    static int N; // Map Width & Height
    static int M; // Unit Price of gold
    static boolean[][] map;
    
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // Unit price of gold.
        map = new boolean[N][N];

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
            }
        }

        // Solution - 각 위치를 중심으로 k 번 이내로 이동 가능한 범위 탐색 / K <= N 일 때까지 반복 
        // ** 여기서 놓친 것! K 의 한계 : 가장 
        // ** 여기서 중요한 것 -> 모양을 흉내내려고 하기 이전에, 해당 모양이 대칭 또는 규칙적으로 보인다면 그 규칙을 찾으려고 노력해봐라.
        int maxGoldCount = -Integer.MAX_VALUE;
        for(int k = 0; k <= 2 * (N - 1); k++){
            maxGoldCount = Math.max(maxGoldCountForK(k), maxGoldCount);
        }

        if(maxGoldCount == -1){maxGoldCount = 0;}

        System.out.print(maxGoldCount);
    }

    public static int maxGoldCountForK(int k){
        int maxGoldCountForK = -Integer.MAX_VALUE;

        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                int currentGoldCountForK = calculateGoldCountAndProfit(r, c, k);
                maxGoldCountForK = Math.max(maxGoldCountForK, currentGoldCountForK);
            }
        }

        return maxGoldCountForK;
    }

    // returns cnt. / 맵 밖의 영역은 방문 불필요. 어차피 방문 영역 넓이는 단순 수식으로 계산 가능.
    public static int calculateGoldCountAndProfit(int row, int col, int k){

        int currentGoldCount = 0;
        int currentGoldProfit = 0;
        int currentCost = (k*k) + (k+1)*(k+1);

        // 현 위치 금 여부 체크 후 결과에 따라 가산.
        if(map[row][col]){currentGoldCount++;}

        boolean[][] visited = new boolean[N][N];
        ArrayDeque<Node> q = new ArrayDeque<>();

        int[] dRow = new int[]{-1, 1, 0, 0};
        int[] dCol = new int[]{0, 0, -1, 1};
        
        visited[row][col] = true;
        q.offer(new Node(row, col, 0));

        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int d = 0; d < 4; d++){
                int nextRow = current.row + dRow[d];
                int nextCol = current.col + dCol[d];
                int nextDist = current.distFromSource + 1;

                if(isInsideOfMap(nextRow, nextCol) && !visited[nextRow][nextCol] && nextDist <= k){

                    if(map[nextRow][nextCol]){currentGoldCount++;}
                    visited[nextRow][nextCol] = true;
                    q.offer(new Node(nextRow, nextCol, nextDist));
                }
            }
        }

        // Cost Calculation.
        currentGoldProfit = (M * currentGoldCount) - currentCost;
        
        return (currentGoldProfit >= 0) ? currentGoldCount : 0;
    }

    public static boolean isInsideOfMap(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    static class Node {
        int row;
        int col;
        int distFromSource;

        public Node(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.distFromSource = d;
        }
    }
}