
class Solution {

    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // Move left forward if it's already even
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }
            // Move right backward if it's already odd
            while (left < right && nums[right] % 2 != 0) {
                right--;
            }

            // If left is still less than right, swap them
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        return nums;
    }
}
