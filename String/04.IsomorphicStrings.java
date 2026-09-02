import java.util.HashMap;

/*
 * Problem: Isomorphic Strings
 * LeetCode: 205
 * Difficulty: Easy
 *
 * Pattern:
 * HashMap - Character Mapping
 *
 * Approach:
 * Maintain a mapping from characters of string s to characters of string t.
 *
 * 1. If a character from s is already mapped, it must map to the same
 *    character in t.
 *
 * 2. If the character from s is not mapped yet, make sure the target
 *    character from t is not already mapped to another character.
 *
 * This ensures a one-to-one and consistent mapping.
 *
 * Time Complexity:
 * O(n^2) in the worst case because containsValue() can take O(n).
 * For the fixed character set used in this problem, this is effectively
 * very small.
 *
 * Space Complexity:
 * O(k), where k is the number of distinct characters.
 */

class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {

        // Strings must have the same length
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char cs = s.charAt(i);
            char ct = t.charAt(i);

            // Character from s is already mapped
            if (map.containsKey(cs)) {

                // It must map to the same character
                if (map.get(cs) != ct) {
                    return false;
                }

            } else {

                // Target character must not already be mapped
                // to another source character
                if (map.containsValue(ct)) {
                    return false;
                }

                // Create the mapping
                map.put(cs, ct);
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "egg";
        String t = "add";

        System.out.println(isIsomorphic(s, t));
    }
}
