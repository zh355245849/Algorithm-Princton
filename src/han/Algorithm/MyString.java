package han.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh355245849 on 2017/4/28.
 */
public class MyString {

    public static void main(String[] args) {
        String testString = "a4b2c1d1e0f0g3";
        String s = "azb";
        String t = "#!!!";
        MyString myString = new MyString();
        System.out.println(myString.decompress(testString));
    }

    //Decompress string
    public String decompress(String input) {
        // Write your solution here.
        if (input.isEmpty()) {
            return "";
        }
        char[] chars = input.toCharArray();
        return decodeLonger(chars, decodeShorter(chars));
    }
    public int decodeShorter(char[] chars) {
        int slow = 0, fast = 0;
        while (fast < chars.length) {
            char c = chars[fast];
            int digit = getDigit(chars[fast + 1]);
            if (digit >= 0 && digit <= 2) {
                for (int i = 0; i < digit; i++) {
                    chars[slow++] = c;
                }
                fast += 2;
            } else {
                chars[slow++] = chars[fast++];
                chars[slow++] = chars[fast++];
            }
        }
        return slow;
    }
    public String decodeLonger(char[] chars, int len) {
        int newLength = len;
        for (int i = 0; i < chars.length; i++) {
            int d = getDigit(chars[i]);
            if (d > 2 && d <= 9) {
                newLength += (d - 2);
            }
        }
        char[] res = new char[newLength];
        int slow = newLength - 1;
        for (int i = len - 1; i > 0; i--) {
            int d = getDigit(chars[i]);
            if (d > 2 && d <= 9) {
                for (int k = 0; k < d; k++) {
                    res[slow--] = chars[i - 1];
                }
                i--;
            } else {
                res[slow--] = chars[i];
            }
        }
        return new String(res);
    }
    private int getDigit(char c) {
        return c - '0';
    }

    //Replace String with another string
    public String replace(String input, String s, String t) {
        // Write your solution here.
        if (s == null || t == null) {
            return s;
        }
        char[] chars = input.toCharArray();
        if (s.length() >= t.length()) {
            return replaceShort(chars, s, t);
        } else {
            return replaceLonger(chars, s, t);
        }
    }

    public String replaceShort(char[] carr, String s, String t) {
        int slow = 0, fast = 0;
        while (fast < carr.length) {
            if (fast <= carr.length - s.length() && equalsStr(carr, fast, s)) {
                replaceStr(carr, slow, t);
                slow += t.length();
                fast += s.length();
            } else {
                carr[slow++] = carr[fast++];
            }
        }
        return new String(carr, 0, slow);
    }

    public String replaceLonger(char[] carr, String s, String t) {
        List<Integer> matches = findMatchEndIndex(carr, s);
        char[] newcarr = new char[carr.length + matches.size() * (t.length() - s.length())];
        int slow = newcarr.length - 1, fast = carr.length - 1;
        int matchIndex = matches.size() - 1;
        while (fast >= 0) {
            if (matchIndex >= 0 && fast == matches.get(matchIndex)) {
                replaceStr(newcarr, slow - t.length() + 1, t);
                slow -= t.length();
                fast -= s.length();
                matchIndex--;
            } else {
                newcarr[slow--] = carr[fast--];
            }
        }
        return new String(newcarr);
    }
    public boolean equalsStr(char[] carr, int start, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (carr[start + i] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public void replaceStr(char[] carr, int start, String t) {
        for (int i = 0; i < t.length(); i++) {
            carr[start + i] = t.charAt(i);
        }
    }
    public List<Integer> findMatchEndIndex(char[] carr, String s) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i <= carr.length - s.length(); ) {
            if (equalsStr(carr, i, s)) {
                res.add(i + s.length() - 1);
                i += s.length();
            } else {
                i++;
            }
        }
        return res;
    }
}
