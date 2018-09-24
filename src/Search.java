public class Search {
    public static int binary_search(int[] a, int key){          //二分查找递归实现
        return search(a, key, 0, a.length-1);
    }
    public static int search(int[] a, int key, int low, int high){
        if(low > high) return -1;    //递归方法开头的特征 结束的约束条件

        int mid = (low+high) >>1;
        if(key < a[mid]){
            return search(a, key, low, mid - 1);
        }else if(key > a[mid]){
            return search(a, key, mid+1, high);
        }else{
            return mid;
        }
    }

//    public static int binary_search(int[] a, int key){             //二分查找非递归实现
//        int low = 0;
//        int high = a.length-1;
//
//        while(low <= high){
//            int mid = (low + high) >> 1;
//            if(key < a[mid]){
//                high = mid-1;
//            }else if(key > a[mid]){
//                low = mid+1;
//            }else{
//                return mid;
//            }
//        }
//        return -1;
//    }


   // ，虽然我是测试类，但是你改我也改
    // 看我改了一些哦，在这里

 //这是我为了测试pull 功能加的改变



}
