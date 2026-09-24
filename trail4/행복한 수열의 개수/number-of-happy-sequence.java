import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        // logic
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){map[i][j] = Integer.parseInt(st.nextToken());}
        }

        int happyCnt = 0;

        // Count Horizontally
        for(int r = 0; r < N; r++){
            int maxSeqCnt = 0;
            int currentSeqCnt = 0;
            int prev = -1;
            for(int c = 0; c < N; c++){
                if(prev == -1){
                    prev = map[r][c];
                    currentSeqCnt = 1;
                }
                else {
                    if(map[r][c] == prev) {currentSeqCnt++;}
                    else {
                        maxSeqCnt = Math.max(maxSeqCnt, currentSeqCnt);
                        prev = map[r][c];
                        currentSeqCnt = 1;
                    }
                }
            }

            maxSeqCnt = Math.max(maxSeqCnt, currentSeqCnt);

            if(maxSeqCnt >= M){happyCnt++;}
        }

        // Count Horizontally
        for(int c = 0; c < N; c++){
            int maxSeqCnt = 0;
            int currentSeqCnt = 0;
            int prev = -1;
            for(int r = 0; r < N; r++) {
                if(prev == -1){prev = map[r][c]; currentSeqCnt = 1;}
                else {
                    if(prev == map[r][c]){currentSeqCnt++;}
                    else {
                        maxSeqCnt = Math.max(maxSeqCnt, currentSeqCnt);
                        prev = map[r][c];
                        currentSeqCnt = 1;
                    }
                }
            }
            maxSeqCnt = Math.max(maxSeqCnt, currentSeqCnt);
            if(maxSeqCnt >= M){happyCnt++;}
        }
        

        System.out.print(happyCnt);
    }
}