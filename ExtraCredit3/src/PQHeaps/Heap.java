package PQHeaps;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Heap {

	private int[] a; // array
	private int n; // number of elements

	public Heap() {
		a = new int[2];
		n = 0;
		a[0] = 100;
	}

	public Heap(int maxSize) {
		a = new int[maxSize];
		n = 0;
		a[0] = 100;
	}

	public void insertItem(int value) {
		n++;
		a[n] = value;
		upHeap(n);
	}

	private void upHeap(int i) {
		int k = a[i];
		int iparent = i / 2;
		while (a[iparent] > k) {
			a[i] = a[iparent];
			i = iparent;
			iparent = i / 2;
			if (iparent == i) {
				break;
			}
		}
		a[i] = k;
	}

	public int removeMax() {
		if (n == 0) {
			throw new NoSuchElementException("Heap is Empty!!");
		}
		int maxValue = a[1];
		a[1] = a[n];
		n--;
		downHeap(1);
		return maxValue;
	}

	public void downHeap(int i) {
		int k = a[i];
		int left = 2 * i;
		int right = left + 1;

		while (right <= n) {
			if (k <= a[left] && k <= a[right]) {
				a[i] = k;
				return;
			} else if (a[left] > a[right]) {
				a[i] = a[right];
				i = right;
			} else {
				a[i] = a[left];
				i = left;
			}
			left = 2 * i;
			right = left + 1;
		}

		/* If number of nodes is even there is one node without right child */
		if (left == n && k > a[left]) {
			a[i] = a[left];
			i = left;
		}
		a[i] = k;
	}

	public void max_heapify(int Arr[], int i, int N) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int largest;
		if (left <= N && Arr[left] < Arr[i])
			largest = left;
		else
			largest = i;
		if (right <= N && Arr[right] < Arr[largest])
			largest = right;
		if (largest != i) {
			int temp = Arr[i];
			Arr[i] = Arr[largest];
			Arr[largest] = temp;
			max_heapify(Arr, largest, N);
		}
	}

	public void printArray() {
		System.out.print("{");
		for (int i = 0; i <= n; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("}");
	}

	public static class DriverClass {
		public static void main(String[] args) {
			Heap heap = new Heap();
			heap.insertItem(500); // Insert item into Heap
			heap.removeMax();// Delete Max from the Heap
			heap.printArray(); // Display the Heap array

			// Convert user-input array to Heap
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			Heap heap2 = new Heap(10);
			int b[] = new int[10];
			System.out.println("\nEnter 2 elements:");
			for (int i = 0; i < 2; i++) {
				b[i] = scan.nextInt();
			}
			heap2.max_heapify(b, 2, 2);
			heap2.printArray();

		}
	}

}
