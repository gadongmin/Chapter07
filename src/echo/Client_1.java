package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client_1 {

	public static void main(String[] args) throws IOException {
		// Socket 객체 생성
		Socket socket = new Socket();

		// ServerSocket IP, Port 연결
		socket.connect(new InetSocketAddress("192.168.0.28", 10001));

		// read
		InputStream in = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// write
		OutputStream out = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(osw);

		// Scanner 준비
		Scanner sc = new Scanner(System.in);

		while (true) {
			String msg = sc.nextLine();

			if ("/q".equals(msg)) {
				break;
			}

			// write 실행
			bw.write(msg);
			bw.newLine();
			bw.flush();

			// read 실행
			String reMsg = br.readLine();
			System.out.println("server:[ " + reMsg + " ]");

		}

		// close
		sc.close();
		br.close();
		bw.close();
		socket.close();
	}

}
