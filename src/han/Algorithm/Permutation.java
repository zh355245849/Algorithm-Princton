package han.Algorithm;

import java.util.*;
/**
 * Created by zh355245849 on 2017/4/21.
 */
public class Permutation {
    public static void main(String[] args) {
        Permutation p = new Permutation();
        p.permutations("abc");
    }

    public List<MyString> permutations(String set) {
        // Write your solution here.
        List<MyString> res = new ArrayList<>();
        if (set == null) {
            return res;
        }
        char[] sets = set.toCharArray();
        dfs(sets, res, 0);
        return res;
    }

    public void dfs(char[] set, List<MyString> res, int index) {
        System.out.println("dfs level : " + index);
        if (index == set.length) {
            System.out.println(new String(set));
            return ;
        }
        System.out.println("dfs level : --------------" + index);
        for (int i = index; i < set.length; i++) {
            swap(set, index, i);
            System.out.println("swap : " + "index " + index +  "  i " + i + "  " + new String(set));
            dfs(set, res, index + 1);
            System.out.println("popout ---- i++");
            swap(set, index, i);
        }
    }

    public void swap(char[] sets, int i, int j) {
        char tmp = sets[i];
        sets[i] = sets[j];
        sets[j] = tmp;
    }
}
