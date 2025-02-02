import java.util.*;

class AnagramFinder {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        int left = 0, right = 0;
        while (right < s.length()) {
            sCount[s.charAt(right) - 'a']++;
            
            if (right - left + 1 == p.length()) {
                if (Arrays.equals(sCount, pCount)) {
                    result.add(left);
                }
                sCount[s.charAt(left) - 'a']--;
                left++;
            }
            right++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println("Anagram indices: " + findAnagrams(s, p));
    }
}
