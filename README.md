# Benchmark-Algorithms
Sorting Algorithms benchmark application in Java

This Algorithm Benchmarker measures time complexity of algorithms with test input cases of integers up to 10,000.

While not very OO we were awarded extra credit for building this as one monolithic codeblock so thats what I did.


Compile with javac ie/gmit/dip/*.java
Run --> ie.gmit.dip.Benchmarker


Graph output screens

https://user-images.githubusercontent.com/60437881/73456371-aeb5d280-4369-11ea-92e7-09d213a5b3fb.png



Tabular output


![image](https://user-images.githubusercontent.com/60437881/73456537-f50b3180-4369-11ea-857b-066622c59bab.png)




We are benchmarking the algorithms we introduced by creating arrays of random numbers at various input  sizes  from  250  up  to  10,000  integers  and  measuring  time  complexity  in  terms  of  timing  the execution  time  for  a  given  algorithm  to  sort  the  input  in  milliseconds.  We  dothis by using Java’s System.nanoTime()  function  by  starting  the  clock  just  before  we  call  the  algorithm  and  stopping  it directly  after  it  returns.  After  which  we  convert  the  time  to  milliseconds  for  analysis  where  we compute the average result over 10 runs for each algorithm at each input size.  The results are then output  to  console  for  consumption  although  the  benchmarking  application  isnot  in what  might  be called afinal release version so there are branches where we could pipe off other outputs easilyif that were  required. The  Benchmarking  application  contains  an  implementation  of  each  algorithm presented in this paper with the source listed in comments. There is an executor function which after being called by the main method goes through a number of iterations looping through each algorithm for each input size. This is accomplished by creating and then filling arrays of random integers with the  help  of  the  math.random()  function.  The  executor  then  builds  a  list  of  the  results  for  each algorithm witheach test being performed 10 times. Back in the main method we wind our results back on nested loop. Due to the way we have set up our loop toprocess our data and iterated through the algorithms in turn each corresponding result item for 5 algorithms by 8 different input sizes will be 40 positions forward of its predecessor. After looping throughthe results arraywhileadding our related resultselementsand  dividing  by  10  to  get  the  average  over  10  runs  we  output  the  totals  to  the console. 
