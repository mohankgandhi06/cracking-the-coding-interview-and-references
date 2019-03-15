package z.reference.grokking.ELongestCommonSubstring;

import java.util.HashMap;
import java.util.Map;

public class MStringsInterleaving {
    public static void main(String[] args) {
        MStringsInterleaving game = new MStringsInterleaving();
        String m = "abd";
        String n = "cef";
        String p = "abcdef";
        System.out.println("M: " + m + " N: " + n + " P: " + p);
        game.solveWithBruteForce(m, n, p);
        game.solveWithMemoization(m, n, p);
        game.solveWithTabulation(m, n, p);

        m = "abd";
        n = "cef";
        p = "adcbef";
        System.out.println("\nM: " + m + " N: " + n + " P: " + p);
        game.solveWithBruteForce(m, n, p);
        game.solveWithMemoization(m, n, p);
        game.solveWithTabulation(m, n, p);

        m = "abc";
        n = "def";
        p = "abdccf";
        System.out.println("\nM: " + m + " N: " + n + " P: " + p);
        game.solveWithBruteForce(m, n, p);
        game.solveWithMemoization(m, n, p);
        game.solveWithTabulation(m, n, p);

        m = "abcdef";
        n = "mnop";
        p = "mnaobcdepf";
        System.out.println("\nM: " + m + " N: " + n + " P: " + p);
        game.solveWithBruteForce(m, n, p);
        game.solveWithMemoization(m, n, p);
        game.solveWithTabulation(m, n, p);
    }

    private void solveWithBruteForce(String m, String n, String p) {
        boolean result = solve(m, n, p, 0, 0, 0);
        System.out.println("(Brute Force) string interleaving condition for the above strings: " + result);
    }

    private boolean solve(String m, String n, String p, int currentIndexOfM, int currentIndexOfN, int currentIndexOfP) {
        if (currentIndexOfM == m.length() && currentIndexOfN == n.length() && currentIndexOfP == p.length()) {
            /* This means all the string reached their end and everything went well before
            this i.e. there were matching character either in the m and p or n and p */
            return true;
        }
        if (currentIndexOfP == p.length()) {
            /* This means that we had reached only the end of the p and not the other two. so it is false */
            return false;
        }
        boolean matchWithM = false;
        boolean matchWithN = false;
        if (currentIndexOfM < m.length() && m.charAt(currentIndexOfM) == p.charAt(currentIndexOfP)) {
            matchWithM = solve(m, n, p, currentIndexOfM + 1, currentIndexOfN, currentIndexOfP + 1);
        }
        if (currentIndexOfN < n.length() && n.charAt(currentIndexOfN) == p.charAt(currentIndexOfP)) {
            matchWithN = solve(m, n, p, currentIndexOfM, currentIndexOfN + 1, currentIndexOfP + 1);
        }
        return matchWithM || matchWithN;
    }

    private void solveWithMemoization(String m, String n, String p) {
        Map<String, Boolean> memo = new HashMap<String, Boolean>();
        boolean result = solve(m, n, p, 0, 0, 0, memo);
        System.out.println("(Memoization) string interleaving condition for the above strings: " + result);
    }

    private boolean solve(String m, String n, String p, int currentIndexOfM, int currentIndexOfN, int currentIndexOfP, Map<String, Boolean> memo) {
        if (currentIndexOfM == m.length() && currentIndexOfN == n.length() && currentIndexOfP == p.length())
            return true;
        if (currentIndexOfP == p.length()) return false;
        String key = currentIndexOfM + "|" + currentIndexOfN + "|" + currentIndexOfP;
        if (!memo.containsKey(key)) {
            boolean matchWithM = false;
            boolean matchWithN = false;
            if (currentIndexOfM < m.length() && m.charAt(currentIndexOfM) == p.charAt(currentIndexOfP)) {
                matchWithM = solve(m, n, p, currentIndexOfM + 1, currentIndexOfN, currentIndexOfP + 1, memo);
            }
            if (currentIndexOfN < n.length() && n.charAt(currentIndexOfN) == p.charAt(currentIndexOfP)) {
                matchWithN = solve(m, n, p, currentIndexOfM, currentIndexOfN + 1, currentIndexOfP + 1, memo);
            }
            memo.put(key, matchWithM || matchWithN);
        }
        return memo.get(key);
    }

    private void solveWithTabulation(String m, String n, String p) {
        boolean[][] matrix = new boolean[ m.length() + 1 ][ n.length() + 1 ];
        boolean result = solve(m, n, p, matrix);
        System.out.println("(Tabulation) string interleaving condition for the above strings: " + result);
    }

    private boolean solve(String m, String n, String p, boolean[][] matrix) {
        for (int mIndex = 0; mIndex < matrix.length; mIndex++) {
            for (int nIndex = 0; nIndex < matrix[ 0 ].length; nIndex++) {
                if (mIndex == 0 && nIndex == 0) matrix[ mIndex ][ nIndex ] = true;
                else if (mIndex == 0 && (n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex - 1)))
                    matrix[ mIndex ][ nIndex ] = matrix[ mIndex ][ nIndex - 1 ];
                else if (nIndex == 0 && (m.charAt(mIndex - 1) == p.charAt(mIndex + nIndex - 1)))
                    matrix[ mIndex ][ nIndex ] = matrix[ mIndex - 1 ][ nIndex ];
                else {
                    if ((mIndex > 0) && m.charAt(mIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                        matrix[ mIndex ][ nIndex ] = matrix[ mIndex - 1 ][ nIndex ];
                    }
                    if ((nIndex > 0) && n.charAt(nIndex - 1) == p.charAt(mIndex + nIndex - 1)) {
                        matrix[ mIndex ][ nIndex ] |= matrix[ mIndex ][ nIndex - 1 ];
                    }
                }
            }
        }
        return matrix[ m.length() ][ n.length() ];
    }
}