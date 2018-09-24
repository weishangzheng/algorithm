public class Sort {

    public static void bubble_sort(int[] a){            //冒泡排序
        for(int i = 0; i < a.length-1;i++){
            for(int j = 0; j < a.length-1-i; j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] =  a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
    public static void selection_sort(int[] a){           //选择排序
        for (int i = 0; i < a.length-1; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if(a[j] < a[min])
                    min = j;
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }


    public static void insert_sort(int[] a){            //插入排序
        for(int i = 1;  i< a.length; i++){
            for(int j = i; j > 0; j--){
                if(a[j] < a[j-1]){
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }


    public static void shell_sort(int[] a){             //希尔排序
        int N = a.length;
        int gap = 1;
        while(3*gap < N)  gap = 3*gap+1;    //设置初始步长
        while(gap >= 1){
            for(int i = gap; i< N ; i++){
                for(int j = i; j >= gap; j -= gap){
                    if(a[j] < a[j-gap]){
                        int temp = a[j];
                        a[j] = a[j-gap];
                        a[j-gap] = temp;
                    }
                }
            }
            gap = gap/3;
        }
    }


    public static void merge_sort(int[] a){             //归并排序
        mergesort(a,0, a.length-1);
    }   //归并排序
    public static void mergesort(int[] a, int low, int high){

        if (low< high){
            int mid = (low+high)/2;
            mergesort( a,  low,  mid);
            mergesort(a, mid+1, high);
            merge(a, low, mid, high);
        }
    }
    public static void merge(int[] a, int low, int mid, int high){
        int[] temp = new int[high - low + 1];
        int k = 0;
        int i = low;
        int j = mid+1;
        while(i <= mid && j <= high){
            if(a[i] <  a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        while(i <= mid){
            temp[k++] = a[i++];
        }
        while(j <= high){
            temp[k++] = a[j++];
        }

        for (int k2 = 0; k2 < temp.length; k2++) {
            a[low+k2] = temp[k2];
        }
    }



    public static void quick_sort(int[] a){       //快速排序
        sort(a, 0, a.length-1);
    }
    public static void sort(int[] a, int low, int high){
        if(low >= high) return;

        int pivot = partion(a, low, high);
        sort(a, low, pivot -1);
        sort(a, pivot+1, high);
    }

    public static int partion(int[] a, int low, int high){
        int temp = a[low];
        while(low < high){
            while(low < high && a[high] >= temp)   high--;
            a[low] = a[high];
            while(low < high && a[low] <= temp)  low++;
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }



    public static void heapSort(int[] a) {              //堆排序
        for (int i = (a.length-1) / 2; i >= 0; i--) {
            adjustHeap(a, i, a.length );
        }
        for (int i = a.length - 1; i >= 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            adjustHeap(a, 0, i);
        }
    }
    public static void adjustHeap(int[] a, int root, int n) {
        int child = 2 * root + 1;
        while(child < n){
            if(child + 1 < n && a[child+1] > a[child]){
                child = child +1;
            }
            if(a[root] >= a[child]){
                break;
            }else{
                int temp = a[root];
                a[root] = a[child];
                a[child] = temp;
                root = child;
                child = 2 * child + 1;
            }
        }
    }


}
