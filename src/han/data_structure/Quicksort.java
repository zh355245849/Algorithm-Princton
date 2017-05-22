package han.data_structure;

/**
 * Created by zh355245849 on 2017/4/20.
 */
public class Quicksort {
    public static void main(String[] args) {
        Quicksort qs = new Quicksort();
        qs.quicksort(new int[] {5, 3, 2, 1, 1, 1, 9, 8, 7});
    }

    public void quicksort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return ;
        }
        quicksort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void quicksort(int[] arr, int start, int end) {
        if (start >= end) {
            return ;
        }
        int pivot = partition(arr, start, end);
        quicksort(arr, start, pivot - 1);
        quicksort(arr, pivot + 1, end);
    }

    public int partition(int[] nums, int start, int end) {
        int left = start, right = end;
        //should be random, we select it as left position element.
        //pick the nums[left] out
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
//find the first element smaller than pivot, and put the element to the left position, which we have picked out.
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
//find the first element bigger than pivot, and put the element to the right position.
            nums[right] = nums[left];
        }
//put the pivot back to the array
        nums[left] = pivot;
        return left;
    }
}
