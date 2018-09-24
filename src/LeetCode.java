import java.util.*;

public class LeetCode {

    // 双指针问题
    public int[] twoSum(int[] numbers, int target) {      //有序数组的twoSum
        if(numbers == null) return null;
        int low = 0;
        int high = numbers.length-1;
        while(low < high){
            if(numbers[low] + numbers[high] == target){
                return new int[]{low+1,high+1};
            }else if(numbers[low] + numbers[high] < target){
                low++;
            }else{
                high--;
            }
        }
        return null;
    }

    public boolean judgeSquareSum(int c) {                //两数的平方和
        if(c < 0) return false;
        int a = 0;
        int b = (int)Math.sqrt(c);
        while(a <= b) {
            int sum = a*a + b*b;
            if (sum == c) {
                return true;
            }else if(sum < c){
                a++;
            }else{
                b--;
            }
        }
        return false;
    }

    public String reverseVowels(String s) {               //反转字符串中的元音字母
        char[] pre = s.toCharArray();       //字符串转换成字符数组
        int low = 0;
        int high = pre.length-1;
        String vowels = "aeiouAEIOU";
        while(low < high){
            while(low < high && !vowels.contains(pre[low]+"")){    //注意此行的字符串包含的判断
                low++;
            }
            while(low < high && !vowels.contains(pre[high]+"")){
                high--;
            }
            char temp = pre[low];
            pre[low++] = pre[high];
            pre[high--] = temp;
        }
        return new String(pre);            //注意此行的字符数组转换成字符串String  注意是字符数组
    }

    public String reverseVowels2(String s){
        char[] pre = s.toCharArray();
        int low = 0;
        int high = pre.length-1;
        HashSet<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        while(low < high){
            while(low < high && !set.contains(pre[low])){      //运用hashset 判断包含问题
                low++;
            }
            while(low < high && !set.contains(pre[high])){
                high--;
            }
            char temp = pre[low];
            pre[low++] = pre[high];
            pre[high--] = temp;
        }
        return String.valueOf(pre);         //另一种方式字符数组转换成String 注意是字符数组
    }



    public boolean validPalindrome(String s) {            //回文字符串
        int low = 0;
        int high = s.length()-1;
        while(low < high){
            if(s.charAt(low) != s.charAt(high)){
                return isPalindrome(s, low, high-1) || isPalindrome(s, low+1, high);
            }else{                          //注：此处不可使用high-- 和 low++ ，因为这样low和high值会发生变化
                low++;
                high--;
            }
        }
        return true;
    }
    private static boolean isPalindrome(String s, int low, int high){   //判断是否是回文
        while(low < high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }else{
                low++;
                high--;
            }
        }
        return true;
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {        //两个排序数组的合并
        int k = m + n -1;
        int i = m-1;
        int j = n-1;
        while(i >= 0 && j >= 0){
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];   //三目运算符
        }
        while(j >= 0){
            nums1[k--] = nums2[j--];
        }
    }


    public boolean hasCycle(ListNode head) {             //判断链表是否有环
        if(head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)   return true;
        }
        return false;
    }



    public String findLongestWord(String s, List<String> d) {         // 最长子序列
        String longest = "";
        for(String target: d){
            if(! isValid(s,target)){
                continue;
            }else{
                if(longest.length() < target.length() || (longest.length() == target.length() && longest.compareTo(target) > 0)){
                    longest = target;                                //注：compareTo()方法的使用
                }else{
                    continue;
                }
            }
        }
        return longest;
    }
    private static boolean isValid(String s, String target){    //判断是否是子字符串
        int i = 0;
        int j = 0;
        while(i < s.length() && j < target.length()){
            if(s.charAt(i) == target.charAt(j)){
                i++;
                j++;
            }else{
                i++;
            }
        }
        return j == target.length();
    }






    // 排序





    //数据结构
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {      //找出两个链表的交点
        if(headA == null || headB == null) return null;
        ListNode l1 = headA;
        ListNode l2 = headB;
        while(l1 != l2){
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }


    public ListNode reverseList(ListNode head) {           //反转链表迭代算法
        if(head == null)  return null;

        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public ListNode reverse_list(ListNode head){           //反转链表递归算法
        return reverse(head, null);
    }
    private ListNode reverse(ListNode head, ListNode pre){
        if(head == null)  return pre;

        ListNode next = head.next;
        head.next = pre;
        return reverse(next, head);
    }



    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {    //归并两个链表的非递归写法
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode mergeList = null;
        ListNode current = null;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                if(mergeList == null){
                    mergeList = l1;
                    current = l1;
                }else{
                    current.next = l1;
                    current = current.next;
                }
                l1 = l1.next;
            }else{
                if(mergeList == null){
                    mergeList = l2;
                    current = l2;
                }else{
                    current.next = l2;
                    current = current.next;
                }
                l2 = l2.next;
            }
        }
        if(l1 != null) current.next = l1;
        if(l2 != null) current.next = l2;
        return mergeList;
    }
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {      //归并两个排序链表的递归写法
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists2(l2.next, l1);
            return l2;
        }
    }


    public ListNode deleteDuplicates(ListNode head) {          //删除重复结点非递归
        ListNode list = head;
        while(list != null && list.next != null){
            if(list.val == list.next.val){
                list.next = list.next.next;
            }else{
                list = list.next;
            }
        }
        return head;
    }
    public ListNode deleteDuplicates2(ListNode head){         //删除重复结点递归解法
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates2(head.next);
        return head.val == head.next.val ? head.next : head;
    }


    public ListNode removeNthFromEnd(ListNode head, int n){      //删除链表中倒数第N个结点
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        if(fast == null) return head.next; //表明删除的是倒数第n个结点（第一个结点）
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public ListNode swapPairs(ListNode head) {         //交换链表中相邻结点递归解法
        if(head == null) return null;
        if(head.next == null) return head;

        ListNode node = head.next;
        head.next = swapPairs(head.next.next);
        node.next = head;
        return node;
    }
    public ListNode swapPairs2(ListNode head) {      //交换链表中相邻结点非递归解法
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
            ListNode l1 = pre.next, l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;

            pre = l1;
        }
        return node.next;
    }


    public boolean isPalindrome(ListNode head) {              //回文链表
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) slow = slow.next;
        slow = reverse(slow);
        while(slow != null){
            if(slow.val != head.val) return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    private ListNode reverse(ListNode node){                //反转链表
        ListNode pre = null;
        while(node != null){
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public ListNode oddEvenList(ListNode head) {             //链表中的奇偶结点聚集
        if(head == null) return null;
        ListNode odd = head;
        ListNode even =  head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }





    public int[] twoSum2(int[] nums, int target) {        //返回和为给定值的两个数的坐标值
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexForNum.containsKey(target - nums[i])) {
                return new int[]{indexForNum.get(target - nums[i]), i};
            } else {
                indexForNum.put(nums[i], i);
            }
        }
        return null;
    }


    public boolean containsDuplicate(int[] nums) {            //数组中是否包含重复元素
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() < nums.length;
    }







    //树
    public int maxDepth(TreeNode root){            //树的最大深度（高度）
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();  //利用层次遍历来求
        queue.offer(root);
        int result = 0;
        while(! queue.isEmpty()){
            result++;
            for(int size = queue.size(); size > 0; size--){
                TreeNode temp = queue.poll();
                if(temp.left != null) queue.offer(temp.left);
                if(temp.right != null) queue.offer(temp.right);
            }
        }
        return result;
    }
    public int maxDepth2(TreeNode root){         //树的最大深度（高度）
        if(root == null)  return 0;
        return 1 + Math.max(maxDepth2(root.left), maxDepth2(root.right));  //递归来求
    }


    private boolean flag = true;                       //判断是否为平衡二叉树
    public boolean isBalanced(TreeNode root) {
        maxDep(root);
        return flag;
    }
    public int maxDep(TreeNode root){
        if(root == null) return 0;
        int left = maxDep(root.left);
        int right = maxDep(root.right);
        if(Math.abs(left - right) > 1)  flag = false;     //在求最大深度的时候加上一步判断用于改变flag值
        return Math.max(left, right) + 1;
    }


    private int diameter = 0;                              //两节点的最长路径
    public int diameterOfBinaryTree(TreeNode root) {
        maxDep_diameter(root);
        return diameter;
    }
    public int maxDep_diameter(TreeNode root){
        if(root == null)  return 0;
        int left = maxDep_diameter(root.left);
        int right = maxDep_diameter(root.right);
        if((left + right) > diameter) diameter = left + right;   //利用最大深度
        return Math.max(left, right) + 1;
    }


    public TreeNode invertTree(TreeNode root) {             //翻转树
        if (root == null) return null;
        TreeNode left = root.left;         // 后面的操作会改变 left 指针，因此先保存下来
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {    //合并两个二叉树
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }




































    public TreeNode sortedArrayToBST(int[] num) {           //排序数组构建二叉搜索树
        return  toBST(num, 0, num.length - 1);
    }

    private static TreeNode toBST(int[] a, int low, int high){
        if(low > high) return null;

        int mid = (low+ high + 1) >>1;       //数组个数为偶数时，取右边的为中位数，故括号内+1
        TreeNode root = new TreeNode(a[mid]);
        root.left = toBST(a, low, mid - 1);
        root.right = toBST(a, mid+1, high);

        return root;
    }



    public TreeNode sortedListToBST(ListNode head) {              //有序链表构建二叉搜索树
        return toBST(head, null);
    }

    private static TreeNode toBST(ListNode head, ListNode tail){
        if(head == tail) return null;

        ListNode slow = head;                //用快慢指针查找链表的中间结点
        ListNode fast = head;
        while(fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);

        return root;
    }







}





