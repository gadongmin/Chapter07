package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// 필드
	private Socket socket;

	// 생성자
	public ServerThread() {
	}

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드 gs

	// 메소드 일반
	public void run() {
		try {
			// 읽기 스트림
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 쓰기 스트림
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				// 메세지 받기
				String msg = br.readLine();
				if (msg == null) {
					break;
				}
				System.out.println("받은메세지: " + msg);

				// 메세지 보내기
				bw.write(msg); // 쓰기실행
				bw.newLine(); // 줄바꿈
				bw.flush(); // 메세지 강제전송

			}
			
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
