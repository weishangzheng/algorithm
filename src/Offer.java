import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class Offer {

    public static boolean Find(int target, int[][] a){     //二维数组中的查找
        int row = a.length;
        int column = a[0].length;
        int i = row -1;
        int j = 0;
        while(i >= 0 && j <= column-1){
            if(a[i][j] == target){
                return  true;
            }else if(a[i][j] < target){
                j++;
            }else{
                i--;
            }
        }
        return false;
    }


//    public static String replaceSpace(StringBuffer  str){       //替换空格（使用额外的一个StringBuffer)
//        if(str == null)  return null;
//
//        StringBuffer newstr = new StringBuffer();
//        int length = str.length();
//        for (int i = 0; i < length ; i++) {
//            if(str.charAt(i) == ' '){
//                newstr.append('%');
//                newstr.append('2');
//                newstr.append('0');
//            }else{
//                newstr.append(str.charAt(i));
//            }
//        }
//        return newstr.toString();
//    }

    public static String replaceSpace(StringBuffer str){       //替换空格（在原来StringBuffer的基础上）
        if(str == null)  return null;

        int spacenum = 0;
        for(int i = 0; i < str.length(); i++){    //这个地方错了几次了，注意遍历是小于长度
            if(str.charAt(i) == ' ')
                spacenum++;
        }
        int oldlength = str.length();
        int newlength = oldlength + 2*spacenum;
        int newindex = newlength - 1;
        str.setLength(newlength);      //别忘了设置长度
        for(int i = oldlength-1; i >= 0; i--){
            if(str.charAt(i) == ' '){
                str.setCharAt(newindex--, '0');
                str.setCharAt(newindex--, '2');
                str.setCharAt(newindex--, '%');
            }else{
                str.setCharAt(newindex--, str.charAt(i));
            }
        }
        return str.toString();
    }


    public ArrayList<Integer> printListFromTailToHead(ListNode head) {    // 从尾到头打印链表

        Stack<Integer> stack = new Stack<>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while(! stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }




    public TreeNode reConstructBinaryTree(int[] pre,int[] in) {                 //根据前中序序列构造二叉树
        TreeNode root =reConstructBTree(pre, 0, pre.length-1, in , 0, in.length-1);
        return root;
    }

    private TreeNode reConstructBTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn){
        if(startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);//确定根
        for(int i = startIn; i<= endIn; i++){      //注意此处的i= startIn.因为这个原因下面函数中的参数才会有一个减去startIn
            if(in[i] == pre[startPre]){
                root.left = reConstructBTree(pre, startPre+1, startPre+i-startIn, in ,startIn, i-1);
                root.right = reConstructBTree(pre, startPre+i-startIn+1,endPre, in, i+1,endIn);
            }
        }
        return root;
    }



    Stack<Integer> stack1 = new Stack<Integer>();      //两个栈实现队列的入列出列
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {            //模仿入列
        while(! stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }
    public int pop() {                    //模仿出列
        while(! stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }



    public int minNumberInRotateArray(int[] array) {         //旋转数组的最小数字
        if(array.length == 0)  return 0;
        int low = 0 ;
        int high = array.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(array[mid] > array[high]){
                low = mid + 1;
            }else if(array[mid] == array[high]){
                high = high - 1;
            }else{
                high = mid;          //注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid 一定会指向下标靠前的数字
            }                        //比如 array = [4,6]  array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ;如果high = mid - 1，就会产生错误， 因此high = mid
        }
        return array[low];
    }


//    public int JumpFloor(int target) {              //跳台阶递归写法
//        if(target <= 0) return -1;
//
//        if(target == 1 || target == 2){
//            return target;
//        }else{
//            return JumpFloor(target - 1) + JumpFloor(target - 2);
//        }
//    }

    public int JumpFloor(int target){                //跳台阶非递归写法
        if(target <= 0) return -1;                   //类似的有矩形覆盖问题

        if(target == 1 || target == 2){
            return target;
        }else{
            int a = 1;
            int b = 2;
            int sum = 0;
            for(int i = 3; i <= target; i++){
                sum = a+b;
                a = b;
                b = sum;
            }
            return sum;
        }
    }


    public int JumpFloorII(int target) {       //变态跳台阶问题
        if(target <= 0)  return -1;            //f(n) = 2^(n-1)

        int result = 1;
        for(int i = 1; i < target; i++){
            result *= 2;
        }
        return result;
    }
                                   //思路： //    因为n级台阶，第一步有n种跳法：跳1级、跳2级、到跳n级
                                            //    跳1级，剩下n-1级，则剩下跳法是f(n-1)
                                            //    跳2级，剩下n-2级，则剩下跳法是f(n-2)
                                            //    所以f(n)=f(n-1)+f(n-2)+...+f(1)
                                            //    因为f(n-1)=f(n-2)+f(n-3)+...+f(1)
                                            //    所以f(n)=2*f(n-1)



    public int NumberOf1(int n) {                //二进制中1的个数
        int count  = 0;                          //思路：一个整数减去1后再和原来的整数做位于运算，得到的结果
        while(n != 0){                           //相当于把整数的二进制表示中最右边的1变成0.
            count++;
            int m = n-1;                         //类似问题：1.判断整数是不是2的整数次方
            n = n & m;                                    // 2.两个整数m和n，二进制表示中需要改变多少位才相等
        }
        return count;

    }



    public double Power(double base, int exponent) {         //数值的整数次方
        int e = 0;
        if(exponent < 0){
            if(Math.abs(base) < 1e-15){                      //判断小数是否为零的代码
                throw  new RuntimeException("分母不可为0");  //抛出异常的代码
            }else{
                e = -exponent;
            }
        }else{
            e = exponent;
        }

        double result = 1;
        while(e != 0){                 //快速做乘方
            if((e & 1) == 1){         //判断是否是奇数的位运算
                result *= base;
            }
            base *= base;
            e >>= 1;                 //除以二的位运算
        }

        return exponent < 0 ? 1/result : result;    //三位运算符
    }



    public void reOrderArray(int[] array) {             //调整数组使奇数位于偶数前面并保证相对位置不变

        //要保证原有次序：  1.顺序移动    2.相邻交换
        for(int i = 0; i < array.length-1;i++){
            for(int j = array.length-1; j > i; j--){
                if((array[j-1]&1) == 0 && (array[j] & 1) == 1){  //此处使用位运算判断奇偶性

                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }



    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null || k <= 0){   //若链表为空或者k<=0
            return null;
        }

        ListNode pre = head;
        ListNode last = head;
        for(int i = 0; i< k-1; i++){
            if(pre.next == null){    //此处是若k大于链表的长度时所作的处理
                return null;
            }
            pre = pre.next;
        }

        while(pre.next != null){
            pre = pre.next;
            last = last.next;
        }

        return last;
    }





    public boolean HasSubtree(TreeNode root1,TreeNode root2) {          //判断树的子结构
        if(root1 == null || root2 == null)  return false;
        return isSubtree(root1,root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);   //利用短路，从root1的根开始递归判断是否是子树
    }


    boolean isSubtree(TreeNode root1, TreeNode root2){    //判断是否为子树，也是使用递归
        if(root2 == null)  return true;
        if(root1 == null)  return false;
        if(root1.val == root2.val){
            return isSubtree(root1.left, root2.left)&&  isSubtree(root1.right,root2.right);
        }else  return false;
    }



        public void Mirror(TreeNode root) {       //二叉树的镜像
       /* if(root == null)                        //递归写法
            return;
        if(root.left == null && root.right == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if(root.left != null)
            Mirror(root.left);
        if(root.right != null)
            Mirror(root.right);
            */

        if(root == null)                        //非递归写法
            return;
        Stack<TreeNode> stack =new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode t =stack.pop();
            if(t.left != null || t.right != null){
                TreeNode temp = t.left;
                t.left = t.right;
                t.right = temp;
            }
            if(t.left != null)  stack.push(t.left);
            if(t.right != null) stack.push(t.right);
        }
    }



    public ArrayList<Integer> printMatrix(int[][] array) {        //顺时针打印矩阵

        ArrayList<Integer> result = new ArrayList<Integer> ();
        if(array.length==0) return result;
        int n = array.length,m = array[0].length;
        if(m==0) return result;
        int layers = (Math.min(n,m)-1)/2+1;//这个是层数
        for(int i=0;i<layers;i++){
            for(int k = i;k<m-i;k++) result.add(array[i][k]);//左至右
            for(int j=i+1;j<n-i;j++) result.add(array[j][m-i-1]);//右上至右下
            for(int k=m-i-2;(k>=i)&&(n-i-1!=i);k--) result.add(array[n-i-1][k]);//右至左
            for(int j=n-i-2;(j>i)&&(m-i-1!=i);j--) result.add(array[j][i]);//左下至左上
        }
        return result;
    }

//    public class Solution {                   //包含min函数的栈
//        Stack<Integer> s1 = new Stack<>();
//        Stack<Integer> s2 = new Stack<>();
//        public void push(int node) {
//            s1.push(node);
//            if(s2.isEmpty() || s2.peek() >= node){
//                s2.push(node);
//            }else{
//                s2.push(s2.peek());
//            }
//        }
//
//        public void pop() {
//            s1.pop();
//            s2.pop();
//        }
//
//        public int top() {
//            return s1.peek();
//        }
//
//        public int min() {
//            return s2.peek();
//        }
//    }



    public boolean IsPopOrder(int[] pushA,int[] popA) {         //栈的压入弹出序列
        if(pushA.length == 0 || popA.length == 0)  return false;

        Stack<Integer> s = new Stack<>();
        int pop_index = 0;
        for (int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);
            while( ! s.isEmpty() && s.peek() == popA[pop_index]){
                s.pop();
                pop_index++;
            }
        }
        return s.isEmpty();
    }



    public static void  printTree(TreeNode root){           //层次遍历二叉树
        if(root == null) return ;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(! queue.isEmpty()){
            TreeNode temp = queue.poll();
            System.out.print(temp.val + "\t");
            if(temp.left != null) queue.offer(temp.left);
            if(temp.right != null) queue.offer(temp.right);
        }
    }

    public static void printTreeInLine(TreeNode root){       //从上到下分行打印二叉树
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(! queue.isEmpty()){
            for(int size = queue.size(); size >0; size--){
                TreeNode temp = queue.poll();
                System.out.print(temp.val + "\t");
                if(temp.left != null) queue.offer(temp.left);
                if(temp.right != null) queue.offer(temp.right);
            }
            System.out.println();
        }
    }

    public static void print_zhizixing(TreeNode root){        //之字形打印二叉树
        if(root == null) return;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        int level = 1;
        while(! s1.isEmpty() || !s2.isEmpty()){
            if(level % 2  != 0){
                while(! s1.isEmpty()){
                    TreeNode temp = s1.pop();
                    System.out.print(temp.val + "\t");
                    if(temp.left != null)   s2.push(temp.left);
                    if(temp.right != null)   s2.push(temp.right);
                }
                level++;
            }else{
                while(! s2.isEmpty()){
                    TreeNode temp = s2.pop();
                    System.out.print(temp.val + "\t");
                    if(temp.right != null)    s1.push(temp.right);
                    if(temp.left != null)    s1.push(temp.left);
                }
                level++;
            }
        }
    }


    public boolean VerifySquenceOfBST(int [] sequence) {        //二叉搜索树的后序遍历序列
        if(sequence.length == 0) return false;
        return IsTreeBST(sequence, 0, sequence.length-1);
    }
    private boolean IsTreeBST(int [] sequence,int start,int end ){
        if(end <= start) return true;
        int i = start;
        for (; i < end; i++) {
            if(sequence[i] > sequence[end]) break;
        }
        for (int j = i; j < end; j++) {
            if(sequence[j] < sequence[end]) return false;   //关键信息在这一步
        }
        return IsTreeBST(sequence, start, i-1) && IsTreeBST(sequence, i, end-1);
    }










}
