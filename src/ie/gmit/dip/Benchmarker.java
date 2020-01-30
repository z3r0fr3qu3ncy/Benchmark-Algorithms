//A benchmarking application in Java for testing efficiency of Sorting Algorithms - J.Heffernan 2019
package ie.gmit.dip;
import java.util.*;

public class Benchmarker {
    public static List<Double> extract = new ArrayList<Double>();
    public static double sum = 0;
    public static double[] results;
    public static int exIdx = 0;
    public static double[] sumTotals = new double[40];
    public static double[] finTotal = new double[40];
    
    
    public static void insertionSort(int[] arr) {  //Insertion Sort implementation - Dr P. Mannion - GMIT Lecture - Sorting Algorithms
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; //Value to be inserted
            int j = i-1;
            //Move all elements > key right one position
            while (j >= 0 && arr[j] > key) {
              arr[j+1] = arr[j];
              j = j-1;
            }
        arr[j+1] = key;//Insert key in it's new position
        }
    }
    
    
    public static void countSort(int[] arr) {     //Counting Sort implementation from javainuse.com
 	  int arrayLength = arr.length;
	   if (arrayLength == 0)
		  return;
            /** find maximum and minimum values **/
            int max = arr[0], min = arr[0];
            for (int i = 1; i < arrayLength; i++) {
                if (arr[i] > max)
                    max = arr[i];
                if (arr[i] < min)
                    min = arr[i];
            }
            int range = max - min + 1;

            int[] count = new int[range]; ///initialize the occurrence of each element in the count array
            for (int i = 0; i < arrayLength; i++)
                count[arr[i] - min]++;
            for (int i = 1; i < range; i++) //calculate sum of indexes 
                count[i] += count[i - 1];
            int j = 0;                //modify original array according to the sum count
            for (int i = 0; i < range; i++)
                while (j < count[i])
                    arr[j++] = i + min;
        }


    public static int partition(int arr[], int left, int right) //Partition section for Quick Sort
    {
      int i = left, j = right;
      int tmp;
      int pivot = arr[(left + right) / 2];  
      while (i <= j) {
            while (arr[i] < pivot)
                  i++;
            while (arr[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
      };
      return i;
    }

 
    public static void quickSort(int arr[], int left, int right) { //Quick Sort implementation from algolist.net
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
    }
    
    

    
    
    static void gnomeSort( int[] arr ) {     //Gnome Sort implementation - wikibooks.org
      for ( int index = 1; index < arr.length; ) { 
         if ( arr[index - 1] <= arr[index] ) { 
            ++index; 
         } else { 
            int tempVal = arr[index]; 
            arr[index] = arr[index - 1]; 
            arr[index - 1] = tempVal; 
            --index; 
            if ( index == 0 ) { 
               index = 1; 
                }           
            } 
            } 
        }

    public static void bubbleSort(int[] a) {  //Bubble Sort implementation by Dr P.Mannion - GMIT lecture - Sorting Al
        int outer, inner;
        for (outer = a.length-1; outer > 0; outer--) {
            for (inner = 0; inner < outer; inner++) {
                if (a[inner] > a[inner + 1]) {
                    int temp = a[inner];
                    a[inner] = a[inner + 1];
                    a[inner + 1] = temp;
                }
            }
        }
        
    }
    
    public static void executor() {
        int numRuns = 20;      //we will use a switch after 10 runs warming the JIT compiler.
        //int dummyRuns = 10;
        results = new double[numRuns];
        for(int run = 0; run < numRuns; run++) {
        for (int num : new int[] {250, 500, 1000, 2000, 4000, 6000, 8000, 10000}) { //Here we have loaded up instructions
        for (int a = 1; a < 6; a++) {                                               //in these loops to fire different sized arrays of input at our
        String sort = null;                                                         //algorithms int turn
        int[] input = new int[num];
        for(int i = 0; i < input.length; i++) 
        {
            input[i] = (int) (Math.random() * 100);
        }
        long startTime = System.nanoTime();     
        if (a == 1) {               //Selection loop - Expanded from original idea by Tobias k - Stackoverflow Jul 19 '14 at 21:43
            sort = "insertionsort";
            insertionSort(input);
        } else if (a == 2) {           
            sort = "countingsort";
            countSort(input);
        } else if (a == 3) {
            sort = "quicksort";
            quickSort(input, input[0], input.length-1);
        } else if (a ==4) {
            sort = "gnomesort";
            gnomeSort(input);
        } else if (a == 5) {
            sort = "bubblesort";
            bubbleSort(input);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double timeMillis = elapsedTime/1000000.0;
        results[run] = timeMillis;
        if(run < 10) {System.out.println("Warming JIT Compiler");}  // <-- JIT warming
        if(run >= 10){
        String consoleOut = String.format("%.3f", timeMillis);
        System.out.println(sort + " for " + num + " integers: " + consoleOut);
        extract.add(exIdx, results[run]);
        exIdx++;             
                    }
    
                }
            }
        }     

    }  

        public static void main(String[] args) {
            executor();
            int totalIndex = 0;
            System.out.println("Test Break=====================================================");
            System.out.print(extract);
            int itemCount = extract.size();
            System.out.println("\nThe arraylist has " + itemCount + " elements");
            System.out.println("Test Break =====================================================");
            System.out.println("\nSorting results");
            for(int i = 0; i < extract.size() - 360; i++) {
            for(int j = i; j < extract.size(); j += 40) {
                sum += extract.get(j);
                //System.out.print(extract.get(j));        
                }
                sumTotals[totalIndex] = sum;
                totalIndex++;
                sum = 0;
            }

            System.out.println("Test Break ==========================================================");
            finTotal = new double[40];
            for(int i = 0; i < finTotal.length; i++) {
                finTotal[i] = (sumTotals[i]/10);
            }
            System.out.println("\nCreating array for average totals over 10 runs at input size n:\n");
            //System.out.print(Arrays.toString(finTotal));
            //System.out.println("\n");
            
            System.out.println("Input Size:     250     500     1000    2000    4000    6000    8000    10000");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("Insertionsort" + ("\t") + String.format("%.3f", finTotal[0]) + ("\t") + String.format("%.3f", finTotal[5]) + ("\t") + String.format("%.3f", finTotal[10]) + ("\t") + String.format("%.3f", finTotal[15]) + ("\t") + String.format("%.3f", finTotal[20]) + ("\t") + String.format("%.3f", finTotal[25]) + ("\t") + String.format("%.3f", finTotal[30]) +("\t") + String.format("%.3f", finTotal[35]));  
            System.out.println("Countingsort"  + ("\t") + String.format("%.3f", finTotal[1]) + ("\t") + String.format("%.3f", finTotal[6]) + ("\t") + String.format("%.3f", finTotal[11]) + ("\t") + String.format("%.3f", finTotal[16]) + ("\t") + String.format("%.3f", finTotal[21]) + ("\t") + String.format("%.3f", finTotal[26]) + ("\t") + String.format("%.3f", finTotal[31]) +("\t") + String.format("%.3f", finTotal[36]));
            System.out.println("Quicksort"     + ("\t") + String.format("%.3f", finTotal[2]) + ("\t") + String.format("%.3f", finTotal[7]) + ("\t") + String.format("%.3f", finTotal[12]) + ("\t") + String.format("%.3f", finTotal[17]) + ("\t") + String.format("%.3f", finTotal[22]) + ("\t") + String.format("%.3f", finTotal[27]) + ("\t") + String.format("%.3f", finTotal[32]) +("\t") + String.format("%.3f", finTotal[37]));
            System.out.println("Gnomesort"     + ("\t") + String.format("%.3f", finTotal[3]) + ("\t") + String.format("%.3f", finTotal[8]) + ("\t") + String.format("%.3f", finTotal[13]) + ("\t") + String.format("%.3f", finTotal[18]) + ("\t") + String.format("%.3f", finTotal[23]) + ("\t") + String.format("%.3f", finTotal[28]) + ("\t") + String.format("%.3f", finTotal[33]) +("\t") + String.format("%.3f", finTotal[38]));
            System.out.println("Bubblesort"    + ("\t") + String.format("%.3f", finTotal[4]) + ("\t") + String.format("%.3f", finTotal[9]) + ("\t") + String.format("%.3f", finTotal[14]) + ("\t") + String.format("%.3f", finTotal[19]) + ("\t") + String.format("%.3f", finTotal[24]) + ("\t") + String.format("%.3f", finTotal[29]) + ("\t") + String.format("%.3f", finTotal[34]) +("\t") + String.format("%.3f", finTotal[39]));

        }
     
}