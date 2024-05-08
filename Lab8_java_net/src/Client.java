import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket client=null;
        try {
            client=new Socket("localhost",3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner console = new Scanner(System.in);
            String name=console.nextLine();
            out.println(name);
            // Создаем поток для чтения входящих сообщений
            Thread readerThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readLine();
                        System.out.println(message);
                    }
                } catch (Exception e) {
                    System.out.println("Error receiving message: " + e.getMessage());
                }
            });
            readerThread.start();
            // Создаем поток для отправки сообщений
            Thread writerThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = console.nextLine();
                        out.println(name + ": " + message);
                    }
                } catch (Exception e) {
                    System.out.println("Error sending message: " + e.getMessage());
                }
            });
            writerThread.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
