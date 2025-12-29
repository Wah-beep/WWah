public class TaskOnceB extends Thread {
	public void run() {

    System.out.println("Starting the execution of TaskB");
    for (long i = 0; i < 200000; i++) {
        if (i % 2000 == 0) {
            System.out.print("B ");
        }
    }
    System.out.println("\nEnding the execution of TaskB");
}
}