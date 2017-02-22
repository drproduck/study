import java.util.Arrays;

public class medianfind {
	static double[] array;
	static double find_nth_element(double[] list, int n) {
		array = Arrays.copyOf(list, list.length);
		int true_index = select(0, list.length - 1, n);
		return array[true_index];
	}

	static private int select(int left, int right, int n) {
        if (right - left < 5) {
            partition5(left, right);
            return n;
        }
        while (true) {
			if (left == right) {
				return left;
			}
			int pivot_index = pivot(left, right);
			pivot_index = partition(left, right, pivot_index);
			if (n == pivot_index) {
				return n;
			} else if (n < pivot_index) {
				right = pivot_index - 1;
			} else left = pivot_index + 1;
		}
	}

	static private int pivot(int left, int right) {
		if (right - left < 5)
			return partition5(left, right);

		for (int i = left; i <= right; i += 5) {
			int sub_right = i + 4;
			if (sub_right > right)
				sub_right = right;
			int median5 = partition5(i, sub_right);
			swap(median5, left+(int)Math.floor((i-left)/5));
		}
		return select(left, left + (int) Math.ceil((right - left) / 5), left + (right - left) / 10);
	}

	static private int partition5(int left, int right) {
		int median = left + (right - left) / 2;
		int k = left;
		while (k <= median) {
			for (int i = right; i > k; i--) {
				if (array[i] < array[i - 1]) {
					swap(i, i - 1);
				}
			}
			k++;
		}
		return median;
	}

	static private int partition(int left, int right, int pivot_index) {
		double pivot_value = array[pivot_index];
		swap(pivot_index, right);
		int store_index = left;
		for (int i = left; i < right; i++) {
			if (array[i] < pivot_value) {
				swap(store_index, i);
				store_index++;
			}

		}
		swap(right, store_index);
		return store_index;
	}

	static private void swap(int i, int j) {
		if (i != j) {
			double temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	static double simpleInsertionSort(double[] list, int n) {
		for (int i = 1; i < list.length; i++) {
			while (i > 0 && list[i] < list[i - 1]) {
				double t = list[i];
				list[i] = list[i - 1];
				list[i - 1] = t;
				i--;
			}
		}
		return list[n];
	}

	public static void main(String[] args) {
		int i = 10001;
		double[] list = new double[i];
		while (i > 0) {
			i--;
			list[i] = -1000 + 2000 * Math.random();
		}
		//System.out.println(Arrays.toString(list));
		System.out.println(find_nth_element(list, 500));
		System.out.println(simpleInsertionSort(list, 500));
	}

}

