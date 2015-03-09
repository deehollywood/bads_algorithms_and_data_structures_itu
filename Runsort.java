public class Runsort
{

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
	{ 
		// Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];

		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid) a[k] = aux[j++];
			else if (j > hi ) a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
	}

	 public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        int runs = 1;
        int firstRunStart = 0;
        int firstRunEnd = 0;
        int runStart = 0;
        int runEnd = 1;
        for(int pointer = 0; pointer < N; pointer++){
        	
        	boolean fullRun = false;
        	if(runEnd == N){		
                if(runs == 2){
        		  merge(a, aux, firstRunStart, firstRunEnd,  (runEnd-1));
                }
        		if(firstRunStart != 0){
        			sort(a);
        		}
        	}else{
        		fullRun = less(a[runEnd], a[runStart]);
        	}

        	if(fullRun){               
        		if(runs == 1){
        			runs++;
        			firstRunEnd = runStart;
        		}else{
        			merge(a, aux, firstRunStart, firstRunEnd,  (runEnd-1));
        			runs = 1;
        			//show(a);
        			firstRunStart = runEnd;       				
        		}   		
        		runStart = runEnd;        		
        	}else{       		
        		runStart++;		
        	}
            runEnd++;
        }
        assert isSorted(a);
    }

	private static boolean less(Comparable v, Comparable w)
	{ 
		return v.compareTo(w) < 0; 
	}
	
	private static void exch(Comparable[] a, int i, int j)
	{ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
	
	private static void show(Comparable[] a)
	{ 
		// Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
			StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a)
	{ 
		// Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
			return true;
	}
	
	public static void main(String[] args)
	{
		// Read strings from standard input, sort them, and print.
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}