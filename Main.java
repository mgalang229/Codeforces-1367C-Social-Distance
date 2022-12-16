import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

k = 3

012345678
100000101
100010001

(i-k-1, i+k+1)

indices of 1:
from the left
from the right

012345678
---------
100000101
-00000-6- (from left)
666666-8- (from right)

concept: nearest 1s (both left & right) for every 1

check the existing 1s and the new 1s to be placed

43210123456789 10 11 12 13
--------------------------
00001000001010  0  0  0  0

i think its because of the new placement of 1s

1
13 3
0010000000101

consider the irregular cases

indices of 1s (left & right):
0  1  2  3  4  5  6  7  8  9 10 11 12
0  0  1  0  0  0  0  0  0  0  1  0  1
-  -  -  2  2  2  2  2  2  2  2 10 10
2  2 10 10 10 10 10 10 10 10 12 12  -

0  1  0  0  0  1  0  0  0  0  1  0  1
-  -  1  1  1  1  1  1  1  1  1 10 10
2  2 10 10 10 10 10 10 10 10 12 12  -

left -> change will start at i+1
we don't care about the right side
after placing a new 1, update the subsequent left indices

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), k = fs.nextInt();
			char[] s = fs.next().toCharArray();
			int count = countPossibleOnes(n, k, s);
			System.out.println(count);
		}
		out.close();
	}
	
	static int countPossibleOnes(int n, int k, char[] s) {
		int[] right = new int[n];
		int index = n + k;
		for (int i = n - 1; i >= 0; i--) {
			right[i] = index;
			if (s[i] == '1') {
				index = i;
			}
		}
		int count = 0, leftIndex = -k - 1;
		for (int i = 0; i < n; i++) {
			if (s[i] == '0') {
				boolean checker = true;
				if ((i - leftIndex) <= k || (right[i] - i) <= k) {
					checker = false;
				}
				if (checker) {
					count++;
					//update the subsequent left indices
					leftIndex = i;
				}
			} else {
				leftIndex = i;
			}
		}
		return count;
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
