package Threads;

/**
 * Example 4-3 demonstrates how deadlock can occur when two threads each attempt to
 * obtain a lock held by the other. Modify the example to create deadlock among three
 * threads, where each thread is trying to acquire a lock held by one of the other threads.
 */
public class Deadlock2 {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";
        final Object resource3 = "resource3";

        // Here's the first thread. It tries to lock resource1 then resource3
        Thread t1 = new Thread() {
            public void run() {
                // Lock resource 1
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");

                    // Pause for a bit, simulating some file I/O or
                    // something. Basically, we just want to give the
                    // other thread a chance to run. Threads and deadlock
                    // are asynchronous things, but we're trying to force
                    // deadlock to happen here...
                    try { Thread.sleep(50); }
                    catch (InterruptedException e) {}

                    // Now wait 'till we can get a lock on resource 2
                    synchronized (resource3) {
                        System.out.println("Thread 1: locked resource 3");
                    }
                }
            }
        };

        // Here's the second thread. It tries to lock resource2 then resource1
        Thread t2 = new Thread() {
            public void run() {
                // This thread locks resource 2 right away
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");

                    // Then it pauses, just like the first thread.
                    try { Thread.sleep(50);}
                    catch (InterruptedException e) {}

                    // Then wait 'till we can get a lock on resource 1
                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };

        // Here's the third thread. It tries to lock resource2 then resource1
        Thread t3 = new Thread() {
            public void run() {
                // This thread locks resource 3 right away
                synchronized (resource3) {
                    System.out.println("Thread 3: locked resource 3");

                    // Then it pauses, just like the first thread.
                    try { Thread.sleep(50);}
                    catch (InterruptedException e) {}

                    // Then it tries to lock resource2. But Thread 2 locked
                    // resource2, and won't release it 'till it gets a lock
                    // on resource1, but resource1 is locked by Thread 1
                    // won't release it before it locks resource3, and resource3
                    // is locked by Thread 3 and can't be released after this, so
                    // neither thread can run, and the program freezes up.
                    synchronized (resource2) {
                        System.out.println("Thread 3: locked resource 2");
                    }
                }
            }
        };
        // Start the two threads. If all goes as planned, deadlock wil occur,
        // and the program will never exit.
        t1.start();
        t2.start();
        t3.start();
    }
}
