import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;


public class Main {
    static int MAX_SUM = -Integer.MAX_VALUE;
    static int[] dRow = new int[]{-1, 1, 0, 0};
    static int[] dCol = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][M];
        
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // Q. 어떻게 주어진 블록을 회전 & 뒤집기를 적용해볼 수 있을까?
        // CoT & A.
        // 1) 주어진 도형의 특징을 생각했다 -> 정사각 블록 세 개를 이어붙여 만들 수 있는 모든 형태임.
        // 2) 그리고 뒤집거나 회전할 수 있다? -> 어떻게든 3개를 연속하도록 이어붙이기만하면 만족하겠네?
        // 3) 그러면 DFS (Iterative X / Recursive O)

        boolean[][] visited = new boolean[N][M]; // 이걸 루프 안쪽에서 함수 인자로 던져줄때 매번 생성해서 던져줬더니 MLE.
        // -> 얼마나 대충 나왔을까?
        //  -> 4만 바이트 크기의 boolean 객체가 최대 4만 번 new 키워드로 생성 -> GC 속도보다 Eden Space 차오른느 속도가 더 빨라 Mem Pressure.
        


        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                simulateTrominoPlacement(map, visited, r, c, 0, 0);
            }
        }
        
        System.out.print(MAX_SUM);
    }

    public static void simulateTrominoPlacement(
        int[][] map, boolean[][] visited, int currentRow, int currentCol, int currentDepth, int accumSum) {
        // Base Case
        if(currentDepth == 3) {
            MAX_SUM = Math.max(accumSum, MAX_SUM);
            return;
        }
        
        else {
            visited[currentRow][currentCol] = true;

            for(int d = 0; d < 4; d++){
                int nextRow = currentRow + dRow[d];
                int nextCol = currentCol + dCol[d];

                if(nextRow >= 0 && nextRow < map.length && nextCol >= 0 && nextCol < map[0].length 
                && !visited[nextRow][nextCol]) {
                    simulateTrominoPlacement(map, visited, nextRow, nextCol, currentDepth + 1, accumSum + map[nextRow][nextCol]);
                }
            }
            visited[currentRow][currentCol] = false;
        }
    }
}