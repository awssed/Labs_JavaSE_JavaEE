import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

public class UDP_Sender {
    private String host;
    private int port;
    UDP_Sender(String host, int port)
    {
        this.host=host;
        this.port=port;
    }
    private void sendMessage(String message)
    {
        try{
            byte[] data= message.getBytes();
            InetAddress address=InetAddress.getByName(host);
            DatagramPacket pack=new DatagramPacket(data,data.length,address,port);
            DatagramSocket socket=new DatagramSocket();
            socket.send(pack);
            socket.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        UDP_Sender sender=new UDP_Sender("localhost",3000);
        String message="Hello";
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sender.sendMessage(message);
            }
        }, 1000, 1000);
    }
}
