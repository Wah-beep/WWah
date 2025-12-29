public class TaskWriter extends Thread {

    long counter = 0;

    Messenger mailbox;

    public TaskWriter(Messenger box) {
        mailbox = box;
    }

    public void run() {
        while (true) {
            
            System.out.println("Writing iteration "+counter+" ");
            mailbox.write();
            ++counter;
            
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}