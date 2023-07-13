import java.io.*;
import java.net.*;

class calculator
{
    double add(double a,double b)
    {
        return a+b;
    }
    double sub(double a,double b)
    {
        return a-b;
    }
    double mul(double a,double b)
    {
        return a*b;
    }
    double div(double a,double b)
    {
        return a/b;
    }
}

public class server1 
{
    public static void main(String ars[]) throws Exception
    {
        ServerSocket ss=new ServerSocket(5000);
        System.out.println("server waiting..");
        Socket s=ss.accept();
        DataInputStream dis=new DataInputStream(s.getInputStream());
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());

        while(true)
        {
            double r;
            String op=dis.readUTF();
            System.out.println("cleint sent operator: "+op);
            if(op.equals("stop"))
                break;
            String a=dis.readUTF();
            System.out.println("first number: "+a);
            Double n1=Double.parseDouble(a);
            String b=dis.readUTF();
            System.out.println("second number: "+b);
            Double n2=Double.parseDouble(b);
            calculator ob=new calculator();
            if(op.equals("+"))
                r=ob.add(n1,n2);
            else if(op.equals("-"))
                r=ob.sub(n1,n2);
            else if(op.equals("*"))
                r=ob.mul(n1,n2);
            else if(op.equals("/"))
                r=ob.div(n1,n2);
            else
            {
                dos.writeUTF("wrong");
                dos.flush();
                continue;
            }
            dos.writeUTF("the result is: "+r);
            dos.flush();
        }
        ss.close();
    }
}
