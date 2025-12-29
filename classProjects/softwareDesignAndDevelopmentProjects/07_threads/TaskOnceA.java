public class TaskOnceA extends Thread {
	public void run() {

    System.out.println("Starting the execution of TaskA");
    for (long i = 0; i < 100000; i++) {
        if (i % 1000 == 0) {
            System.out.print("A ");
        }
    }
    System.out.println("\nEnding the execution of TaskA");
}
}

