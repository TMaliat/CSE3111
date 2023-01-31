import java.io.*;
import java.net.*;
import java.util.Scanner;
public class client3
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			Scanner scn = new Scanner(System.in);
			Socket s = new Socket("10.33.2.72", 5056);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true)
			{
				System.out.println(dis.readUTF());
				String tosend = "four.jpg";
				if(tosend.equals("Exit"))
				{
					System.out.println("Closing this connection : " + s);
					s.close();
					System.out.println("Connection closed");
					break;
				}
				String received = dis.readUTF();
				System.out.println(received);
			}
			
			// closing resources
			scn.close();
			dis.close();
			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

