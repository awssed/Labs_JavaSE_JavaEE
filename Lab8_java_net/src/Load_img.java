import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.net.URL;

public class Load_img {
    public static void main(String[] args) {

        try {
            InetAddress currentAdress=null;
            currentAdress=InetAddress.getByName("www.belstu.by");
            System.out.println(currentAdress.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String destUrl="https://it.belstu.by/wp-content/uploads/2019/05/LogoFIT-1-e1557999163520.png";
        String destFile="fit.png";
        int timeout=10_000;
        byte[] buffer=new byte[1000];
        URL url;
        try{
            url = new URL(destUrl);
            URLConnection connection= url.openConnection();
            connection.setConnectTimeout(timeout);
            InputStream inputStream=connection.getInputStream();
            FileOutputStream outputStream=new FileOutputStream(destFile);
            while(inputStream.available()>0)
            {
                int count=inputStream.read(buffer);
                outputStream.write(buffer,0,count);
            }
            System.out.println("Image downloaded successfully.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}