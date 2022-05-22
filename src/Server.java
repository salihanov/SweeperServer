import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; //серверсокет
    private static BufferedReader in;   //поток чтения из сокета
    private static BufferedWriter out;  //поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                //серверсокет прослушивает порт 4004
                server = new ServerSocket(4004);
                //объявление о запуске сервера
                System.out.println("Server is on!");
                //accept() будет ждать пока кто-нибудь не захочет подключиться
                clientSocket = server.accept();

                try {
                    //создание потоков ввода/вывода.
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    //ждём пока клиент что-нибудь нам напишет
                    String word = in.readLine();
                    System.out.println(word);
                    //ответ клиенту
                    out.write("Hello, it is SweeperServer. New winner: " + word + "\n");
                    //очистка буфера
                    out.flush();

                } finally {
                    //закрытие сокета
                    clientSocket.close();
                    //закрытие потоков
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is off!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
