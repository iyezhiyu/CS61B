import java.io.*;

public class Nuke2 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader keyboard;
		String inputLine;
		
		keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		inputLine = keyboard.readLine();
		inputLine = inputLine.charAt(0)+inputLine.substring(2);
		System.out.println(inputLine);
		
		
	}
}
