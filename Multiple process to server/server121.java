import java.io.*;
import java.lang.Thread;
import java.net.*;

public class server121 
{    
    public static void main(String ars[]) throws Exception
    {
        try (ServerSocket ss = new ServerSocket(6000)) {
            while(true)
            {
                Socket s=ss.accept();
                Thread t=new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            DataInputStream dis=new DataInputStream(s.getInputStream());
                            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
                            String name=dis.readUTF();
                            System.out.println("client name: "+name);
                            int n=Integer.parseInt(dis.readUTF());
                            int sq=n*n;
                            dos.writeUTF(String.valueOf(sq));
                            dos.flush();
                        }
                        catch(Exception e)
                        {}
                    }
                };
                t.start();
            }
        }
    }
}