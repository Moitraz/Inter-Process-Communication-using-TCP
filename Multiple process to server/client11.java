import java.io.*;
import java.net.*;

public class client11
{
    public static void main(String ars[]) throws Exception
    {
        Socket s=new Socket("localhost",6000);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        System.out.println("enter your name client: ");
        String name=br.readLine();
        dos.writeUTF(name);
        dos.flush();
        System.out.println("enter the number");
        String num=br.readLine();
        dos.writeUTF(num);
        dos.flush();
        String output=dis.readUTF();
        System.out.println("the square of the number is: "+output);
        s.close();
    }
}