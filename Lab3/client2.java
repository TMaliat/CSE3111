import java.io.*;
import java.net.Socket;

public class client2 {
	private static DataOutputStream dataOutputStream = null;
	private static DataInputStream dataInputStream = null;

	public static void main(String[] args)
	{
		// Create Client Socket connect to port 2000
		try (Socket socket = new Socket("10.33.2.72", 2000)) {
			
		dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			System.out.println("Sending the File to the Server");
		// Call SendFile Method
		    //
            
            receiveFile("NewFile1.png");
            sendFile("G:/meme/four.jpg");
 
            dataInputStream.close();
            dataOutputStream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// sendFile function define here
	private static void sendFile(String path)
		throws Exception
	{
		int bytes = 0;
		// Open the File where he located in your pc
		File file = new File(path);
		FileInputStream fileInputStream= new FileInputStream(file);

		// Here we send the File to Server
		dataOutputStream.writeLong(file.length());
		// Here we break file into chunks
		byte[] buffer = new byte[4 * 1024];
		while ((bytes = fileInputStream.read(buffer))
			!= -1) {
		// Send the file to Server Socket
		dataOutputStream.write(buffer, 0, bytes);
			dataOutputStream.flush();
		}
		// close the file here
		fileInputStream.close();
	}


private static void receiveFile(String fileName)
        throws Exception
    {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
 
        long size= dataInputStream.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0&& (bytes = dataInputStream.read(buffer, 0,(int)Math.min(buffer.length, size))) != -1) {
            // Here we write the file using write method
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        // Here we received file
        System.out.println("File is Received");
        fileOutputStream.close();
    }
}

