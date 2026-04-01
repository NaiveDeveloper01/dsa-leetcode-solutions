import java.util.*;

public class Solutions {
    public int maximumSum(int[] arr, int window) {
        if (arr == null || arr.length == 0 || window > arr.length || window <= 0) {
            return 0;
        }
        int sum = 0;

        // first window
        for (int i = 0; i < window; i++) {
            sum += arr[i];
        }

        int maxSum = sum;

        for (int end = window; end < arr.length; end++) {
            sum += arr[end]; // expand
            sum -= arr[end - window]; // shrink
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int lengthOfUniqueSubstring(String s) {
        int start = 0;
        int end = 0;
        int ans = 0;
        Set<Character> characterSet = new HashSet<>();
        while (end < s.length()) {
            // window shrink for invalid window
            if (characterSet.contains(s.charAt(end))) {
                characterSet.remove(s.charAt(start));
                start++;
            } else {
                // window expand for valid window
                characterSet.add(s.charAt(end));
                ans = Math.max(ans, end - start + 1);
                end++;
            }

        }
        return ans;
    }


    //Find the longest substring with at most k distinct chars.
    public int getLengthForKDistinctCharSubstring(String s, int k) {
        Map<Character, Integer> characterFrequency = new HashMap<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;

        while (end < s.length()) {
            Character ch = s.charAt(end);
            characterFrequency.computeIfPresent(ch, (key, v) -> v + 1);
            characterFrequency.putIfAbsent(ch, 1);

            // window shrinking
            while (characterFrequency.size() > k) {
                Character startChar = s.charAt(start);
                characterFrequency.computeIfPresent(startChar, (key, v) -> v - 1);

                if (characterFrequency.get(startChar) <= 0)
                    characterFrequency.remove(startChar);
                start++;
            }

            // updating at valid window
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }

        return maxLength;
    }

    public List<Integer> getMaxOfAllWindows(int[] arr, int k) {

        Deque<Integer> monotonicQueue = new ArrayDeque<>();
        List<Integer> ans = new LinkedList<>();

        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            while (!monotonicQueue.isEmpty() && arr[monotonicQueue.peekLast()] < arr[end])
                monotonicQueue.pollLast();

            monotonicQueue.offerLast(end);
            if (end - start + 1 == k) {
                ans.add(arr[monotonicQueue.peekFirst()]);
                if (monotonicQueue.peekFirst() == start)
                    monotonicQueue.pollFirst();
                start++;
            }
        }
        return ans;

        // below is value based
       /* Deque<Integer> monotonicQueue = new ArrayDeque<>();
        List<Integer> ans = new LinkedList<>();

        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            while (!monotonicQueue.isEmpty() && monotonicQueue.peekLast() < arr[end])
                monotonicQueue.removeLast();
            monotonicQueue.addLast(arr[end]);

            if (end - start + 1 == k) {
                ans.add(monotonicQueue.peekFirst());
                if (Objects.nonNull(monotonicQueue.peekFirst()) && monotonicQueue.peekFirst() == arr[start])
                    monotonicQueue.removeFirst();
                start++;
            }
        }
        return ans;*/


        // priority queue is wrong  approach
        /*PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> ans = new LinkedList<>();
        int start = 0;
        for (int end = 0; end < arr.length; end++) {
            priorityQueue.add(arr[end]);
            if (end - start + 1 == k) {
                ans.add(Objects.isNull(priorityQueue.peek()) ? 0 : priorityQueue.peek());
                if (Objects.nonNull(priorityQueue.peek()) && priorityQueue.peek() == arr[start]) {
                    priorityQueue.remove(arr[start]);
                }
                start++;
            }
        }
        return ans;*/
    }

    public int findLengthOfSmallestSubStringWithAllChars(String s, String t) {
        Map<Character, Integer> tFrequency = new HashMap<>();
        // to store the frequency of from t string
        for (char c : t.toCharArray()) {
            tFrequency.put(c, tFrequency.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> sFrequency = new HashMap<>();
        int required = tFrequency.size();
        int start = 0;
        int ans = Integer.MAX_VALUE;
        int formed = 0;
        for (int end = 0; end < s.length(); end++) {
            Character c = s.charAt(end);
            sFrequency.put(c, sFrequency.getOrDefault(c, 0) + 1);

            // 'formed' variable is use to keep track of all matching frequencies.
            if (tFrequency.containsKey(c) && sFrequency.get(c).intValue() == tFrequency.get(c)) {
                formed++;
            }

            // this means required == formed all available characters in t have match the frequency
            while (start <= end && required == formed) {
                ans = Math.min(ans, end - start + 1);
                Character lChar = s.charAt(start);

                // we shrink the window
                sFrequency.put(lChar, sFrequency.get(lChar) - 1);

                // after reducing we are reducing frequency as well
                if (tFrequency.containsKey(lChar) && sFrequency.get(lChar) < tFrequency.get(lChar)) {
                    formed--;
                }

                // if element has reach to 0 that mean it has no use in current window so remove from map
                if (sFrequency.get(lChar) <= 0)
                    sFrequency.remove(lChar);
                start++;
            }
        }
        return ans;
    }
}
