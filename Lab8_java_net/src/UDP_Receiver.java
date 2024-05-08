import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Receiver {
    public static void main(String[] args) {
        try
        {
            DatagramSocket ds=new DatagramSocket(3000);
            while(true)
            {
                DatagramPacket pack=new DatagramPacket(new byte[5],5);
                ds.receive(pack);
                System.out.println(new String(pack.getData()));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
