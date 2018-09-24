
class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val = val;
    }
}


public class Myself {
    static int fibonacci(int n){        //斐波那契非递归实现
        if(n == 0 || n == 1){
            return n;
        }

        int a = 0;
        int b = 1;
        int sum = 0;
        for(int i = 2; i <= n; i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

//    static int fibonacci(int n) {       //斐波那契递归实现
//        if (n == 0 || n == 1) {
//            return n;
//        }
//        return fibonacci(n - 1) + fibonacci(n - 2);
//
//    }
}
