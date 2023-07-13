import java.io.*;
import java.net.*;

public class client1
{
    public static void main(String ars[]) throws Exception
    {
        Socket s=new Socket("localhost",5000);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            System.out.println("enter the calculation operator: or 'stop' to close: ");
            String op=br.readLine();
            DataInputStream dis=new DataInputStream(s.getInputStream());
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());

            if(op.equals("stop"))
            {
                dos.writeUTF(op);
                dos.flush();
                break;
            }
            dos.writeUTF(op);
            //dos.flush();

            System.out.println("enter the first number: ");
            String num1=br.readLine();
            dos.writeUTF(num1);
            System.out.println("enter the second number: ");
            String num2=br.readLine();
            dos.writeUTF(num2);
            dos.flush();
            
            String output=dis.readUTF();
            if(output.equals("wrong"))
            {
                System.out.println("wrong operator..");
                continue;
            }
            System.out.println(output);
        }
        s.close();
    }
}