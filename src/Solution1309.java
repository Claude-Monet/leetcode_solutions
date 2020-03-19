public class Solution1309 {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9' && i + 2 < s.length() && s.charAt(i + 2) == '#') {
                sb.append((char)(Integer.parseInt(s.substring(i, i + 2)) - 10 + 'j'));
                i += 2;
            } else {
                sb.append((char)(c - '0' + 'a'));
            }
        }
        return sb.toString();
    }
}
