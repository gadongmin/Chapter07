package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_1 {

	public static void main(String[] args) throws IOException {
		// ServerSocket A객체 생성
		ServerSocket serverSocket = new ServerSocket();

		// ServerSocket IP, Port 연결
		serverSocket.bind(new InetSocketAddress("192.168.0.28", 10001));

		// Socket 객체 생성
		Socket socket = serverSocket.accept();

		// read
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// write
		OutputStream out = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		while (true) {
			String msg = br.readLine();
			if (msg == null) {
				break;
			}
			System.out.println("받은메세지: " + msg);

			// write 실행
			bw.write(msg);
			bw.newLine();
			bw.flush();
		}

		// close
		br.close();
		bw.close();
		socket.close();
		serverSocket.close();

	}
}
