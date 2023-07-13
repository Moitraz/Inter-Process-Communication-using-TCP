import java.io.*;
import java.net.*;

public class chatclient 
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Socket s = new Socket("localhost",4000);
            System.out.println("ready to send msgs");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Thread sender = new Thread(new Runnable()
            {
                String msg;
                public void run()
                {
                    while(true)
                    {
                        try
                        {
                            msg=br.readLine();
                            dos.writeUTF(msg);
                            dos.flush();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable()
            {
                String msg;
                public void run()
                {
                    try
                    {
                        msg=dis.readUTF();
                        while(true)
                        {
                            System.out.println("server: "+msg);
                            msg=dis.readUTF();
                            if(msg.equals("bye"))
                                break;
                        }
                        System.out.println("server disconnected !");
                        dos.close();
                        s.close();
                    }
                    catch(IOException e)
                    {
                        System.out.println("internal error");
                        e.printStackTrace();
                    }
                }
            });
            receiver.start();
        }
        catch(IOException e)
        {
            System.out.println("external error");
            e.printStackTrace();
        }
    }
}
