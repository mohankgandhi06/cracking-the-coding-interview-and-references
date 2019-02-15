package z.reference.dynamicProgramming;

import java.util.Arrays;

public class ZInterviewAnagram {

    public char[] subject;
    public char[] anagram;

    public ZInterviewAnagram(String subject, String anagram) {
        this.subject = subject.toCharArray();
        this.anagram = anagram.toCharArray();
    }

    public static void main(String[] args) {
        String subject = "fluster";
        String anagram = "restful";

        ZInterviewAnagram game = new ZInterviewAnagram(subject, anagram);
        System.out.println("Is " + anagram + " is an anagram of " + subject + ": " + game.solve());
    }

    private boolean solve() {
        if (this.subject.length != this.anagram.length) return false;

        Arrays.sort(this.subject);
        Arrays.sort(this.anagram);

        for (int i = 0; i < this.subject.length; i++) {
            if (this.subject[ i ] != this.anagram[ i ]) {
                return false;
            }
        }
        return true;
    }
}