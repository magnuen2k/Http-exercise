import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    private final String host;
    private final int port;
    private final String requestTarget;
    private int statusCode;

    public HttpClient(String host, int port, String requestTarget) throws IOException {
        this.host = host;
        this.port = port;
        this.requestTarget = requestTarget;
        executeRequest();
    }

    public static void main(String[] args) throws IOException {
       new HttpClient("urlecho.appspot.com", 80, "/echo").executeRequest();
    }

    public void executeRequest() throws IOException {
        Socket socket = new Socket(host, port);
        socket.getOutputStream().write(("GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "\r\n").getBytes());

        String res = readLine(socket);

        String[] strRes = res.split(" ");
        statusCode = Integer.parseInt(strRes[1]);
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder res = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != '\r') {
            // System.out.print((char) c);
            res.append((char) c);
        }
        return res.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getResponseHeader(String header) {
        return 0;
    }
}