import java.util.Scanner;

public class Main{
	
	public static void main(String[] args){
		TaskOnceA taskA = new TaskOnceA();
		TaskOnceB taskB = new TaskOnceB();
		
		/* class activity
		taskA.start();
		try {
			taskA.join();
		} catch (Exception e) {

		}
		taskB.start();
		*/
		
		/* class activity 2
		DisplayTask displayTaskA = new DisplayTask(400,400,"Display A",1000);
		DisplayTask displayTaskB = new DisplayTask(400,400,"Display B",2000);

		Thread displayA = new Thread(displayTaskA);
		Thread displayB = new Thread(displayTaskB);

		displayA.start();
		displayB.start();
		
		Scanner scanner = new Scanner(System.in);
		String userInput;
		while (true) {
			userInput = scanner.nextLine();
			displayTaskA.displayText(userInput);
		}
		*/
		
		Messenger mailbox = new Messenger();
	
		TaskReader readTask = new TaskReader(mailbox);
		TaskWriter writeTask = new TaskWriter(mailbox);
				
		writeTask.start();
		readTask.start();
		
		
	}
}