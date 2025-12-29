public class TaskReader extends Thread {
	
	// shared memory with the TaskWriter
    Messenger mailbox;

	// keep track of how many iterations have been executed
    long counter = 0;

    public TaskReader(Messenger box) {
        mailbox = box;
    }

    public void run() {
    	// forever execute (use ctrl-c to exit)
        while(true) {
            System.out.println("Reading iteration "+counter+" ");
            String[] msg = new String[50];
            mailbox.read(msg); 
            int i = 0;
            while (i<msg.length && msg[i] != null) {
	            System.out.print(msg[i]+" ");
	            i++;
            }
            System.out.println();
            counter++;
            
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            } 
        }
    }
}