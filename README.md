Author: Joselyne Malan
No partners 

Resources for Representation Invariants:
- https://www.columbia.edu/~cs2035/courses/csor4231.F15/heap-invariant.pdf
- https://www.teach.cs.toronto.edu/~csc148h/notes/object-oriented-programming/representation_invariants.html
- https://ocw.mit.edu/courses/6-170-laboratory-in-software-engineering-fall-2005/1a1f80fa85176ed3cae09ccd74f902d3_lec8.pdf
These resources helped me understand the implementation of a Representation Invariant and where it's functionality to the structure of any given data structure 

a. demonstrate its strengths, explaining what you're doing as you run it, in enough detail that we could do so ourselves and try  a variant of what you did
- two different version of single thread examples
- one example that runs multiple threads concurrently
- one intentional corrpt example and function 

b. explain any shortcomings you know of, and giving your ideas about how you might go about addressing them
point out the part(s) of the project that were most interesting to you
    - there can be more efficent appraches that can help check for starvation - semaphores that permits running more than 1 thread 
    - The invariant checker can be vulnerable to data races, so I also made sure it can run concurrently
    - I colorzied certain prints statements so that it is easier to identify them 
    - I was more weary on over synchronizing the function so I only syncronized the function that would need to be synchronized
c. describe the most interesting/important/valuable thing you learned, preferably with references to specific parts of your project
    - I wanted to use semaphores again similar to lab 5
    - I was thinking about the section of using synchronized(lock) and semaphore(num).aquire() and weather that was an instance of overly synchronizing my application
    - The key aspect I was missing is that the combination of synchronized(lock) and semaphore(num).aquire() would ideally work if I am allowing multiple threads inside the semaphore
    - Since this data structure is already using multithreads on the main function, I wanted to keep it simple and just allow one thread at a time
    - I also realized that it wasnt nessasary to synchronize all the function inside Priority Queue

e. (please review your video, before submitting, to confirm that your screen was actually recorded, the audio is audible, etc.)
