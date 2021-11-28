import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class NetworkServer implements Runnable{
    Socket socket;
    InteractServer interactserver;
    public NetworkServer(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            Scanner scan = new Scanner(socket.getInputStream());
            interactserver = new InteractServer(scan, pw);
            interactserver.interact();
            /*
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.printf("%s says: %s\n", socket, line);
                pw.printf("echo: %s\n", line);
                pw.flush();
            }

            pw.close();
            scan.close();
             */
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6457);
        System.out.printf("server is running, waiting for information from client on %s\n",
                serverSocket);
        while (true) {
            Socket socket = serverSocket.accept();
            NetworkServer server = new NetworkServer(socket);
            new Thread(server).start();
        }
    }
}
