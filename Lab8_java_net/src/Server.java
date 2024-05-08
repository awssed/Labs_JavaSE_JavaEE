import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static Map<String,Socket> allClients;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);
            allClients = new HashMap<>();
            Socket currentClient = null;
            OutputStream out = null;
            BufferedReader in = null;
            PrintWriter writer = null;

            while (true) {
                currentClient = server.accept();
                in = new BufferedReader(new InputStreamReader(currentClient.getInputStream()));
                String name = in.readLine();
                allClients.put(name, currentClient);
                System.out.println("Connected new user: " + name);
                new ServerThread(currentClient).start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static class ServerThread extends Thread
    {
        private PrintStream os;
        private BufferedReader is;
        private InetAddress address;
        private Socket socket;
        public ServerThread(Socket s) throws IOException
        {
            os = new PrintStream(s.getOutputStream());
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            socket=s;
        }
        public void run()
        {
            sendClientListToAllClients();
            while(true)
            {
                try {
                    String message=is.readLine();
                    System.out.println(message);
                    sendMessageToChat(message);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        private void sendMessageToChat(String message)
        {
            for (Socket s:allClients.values())
            {
                if(s!=socket)
                {
                    PrintStream clientOut = null;
                    try {
                        clientOut = new PrintStream(s.getOutputStream());
                        clientOut.println(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        private static void sendClientListToAllClients() {
            StringBuilder clientList = new StringBuilder();
            for (String name : allClients.keySet()) {
                clientList.append(name).append("\t");
            }

            for (Socket socket : allClients.values()) {
                try {
                    PrintStream clientOut = new PrintStream(socket.getOutputStream());
                    clientOut.println(clientList.toString());
                } catch (IOException e) {
                    System.out.println("Error sending client list: " + e.getMessage());
                }
            }
        }
    }
}
