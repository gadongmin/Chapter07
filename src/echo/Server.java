package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 본사(천호동)
public class Server {

	public static void main(String[] args) throws IOException {
		// 서버 socket 생성
		ServerSocket serverSocket = new ServerSocket();

		// bind
		serverSocket.bind(new InetSocketAddress("192.168.0.28", 10001));

		// 서버시작
		System.out.println("<서버시작>");
		System.out.println("==================================");

		// ---------- 반복 시작
		
		while (true) {
			// 연결대기 → 연결
			System.out.println("[연결을 기다리고 있습니다.]");
			Socket socket = serverSocket.accept();
			System.out.println("[Client가 연결되었습니다.]");
			
			Thread st = new ServerThread(socket);
			st.start();
		}
		// ---------- 반복 종료

		/*
		System.out.println("==================================");
		System.out.println("<서버 종료>");
				
		// 자원 정리
		bw.close();
		br.close();
		socket.close();
		serverSocket.close();
		*/
	}

}
