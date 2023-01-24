import java.io.*;
import java.net.*;

class Server {

	public static void main(String args[])
		throws Exception
	{
		ServerSocket ss = new ServerSocket(5000);
		Socket s = ss.accept();
		System.out.println("Connection established");
		PrintStream ps= new PrintStream(s.getOutputStream());
		BufferedReader br= new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedReader kb= new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Prime check");
			String str;
			while ((str = br.readLine()) != "Over") {
				int num=Integer.parseInt(str);
				boolean flag = false;
				int i=2;System.out.println("Checking");
				while (i <= num / 2) {
					if (num % i == 0) {
						flag = true;
						break;
					}++i;
				}

				if (!flag) ps.println("Prime Number");
				else ps.println("Not Prime Number");	
			}
			ps.close();
			br.close();
			kb.close();
			ss.close();
			s.close();
			System.exit(0);
		} 
	}
}
