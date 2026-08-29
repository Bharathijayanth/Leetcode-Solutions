 class Solution{

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int index, List<String> current, List<String> result) {
        // If we have 4 segments and used all characters, it's valid
        if (current.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // Try 1 to 3 digit long segments
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) {
                break;
            }

            String segment = s.substring(index, index + len);

            if (isValid(segment)) {
                current.add(segment);
                backtrack(s, index + len, current, result);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    private boolean isValid(String segment) {
        // Check for leading zero or out of range
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }

        int value = Integer.parseInt(segment);
        return value >= 0 && value <= 255;
    }
}
