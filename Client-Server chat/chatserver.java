import java.io.*;
import java.net.*;

public class chatserver 
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ServerSocket ss = new ServerSocket(4000);
            Socket s = ss.accept();
            System.out.println("server running...");
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
            Thread recieve = new Thread(new Runnable()
            {
                String msg;
                public void run() 
                {
                    try
                    {
                        msg=dis.readUTF();
                        while(true)
                        {
                            System.out.println("client: "+msg);
                            msg = dis.readUTF();
                            if(msg.equals("bye"))
                                break;
                        }
                        System.out.println("client disconnected !");
                        dis.close();
                        s.close();
                        ss.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            recieve.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
