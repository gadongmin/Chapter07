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

// 고객(USA)
public class Client {

	public static void main(String[] args) throws IOException {
		// socket 생성(종이컵 전화기)
		Socket socket = new Socket();

		System.out.println("<클라이언트 시작>");
		System.out.println("==================================");
		System.out.println("[서버에 연결을 요청합니다.]");

		socket.connect(new InetSocketAddress("192.168.0.96", 10001));
		System.out.println("[Server에 연결되었습니다.]");

		// 메세지(쓰기 stream)
		// 파일일때, OutputStream out = FileOutputStream("C:\\javaStudy\\MS949-copy.txt");
		
		// 메인스트림, socket 안에 있는 빨대 연결
		OutputStream out = socket.getOutputStream();
		// 보조스트림
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지(읽기 stream)
		// 메인스트림
		InputStream in = socket.getInputStream();
		// 보조스트림
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 스캐너 준비
		Scanner sc = new Scanner(System.in);
		/*
		InputStream sin = System.in;
		InputStreamReader sisr = new InputStreamReader(sin, "MS949");
		BufferedReader sbr =  new BufferedReader(sisr);
		*/
		while (true) {
			//메세지 키보드로 입력받기
			String msg = sc.nextLine(); // 입력대기
			// String msg = sbr.readLine();
			
			if ("/q".equals(msg)) { // 종료 조건 설정
				break;
			}
			
			// 메세지 입력
			bw.write("김동민" + msg); // 쓰기실행
			bw.newLine(); // 줄바꿈
			bw.flush(); // 메세지 강제전

			// 메세지 받기
			String reMsg = br.readLine(); // 읽기실행
			System.out.println("server:[ " + reMsg + " ]");
		}
		
		System.out.println("==================================");
		// System.out.println("<클라이언트 종료>");
		
		// println 스트림
		OutputStream pout = System.out;
		OutputStreamWriter posw = new OutputStreamWriter(pout, "MS949");
		BufferedWriter pbw = new BufferedWriter(posw);
		
		pbw.write("<클라이언트 종료>");
		pbw.newLine();
		pbw.flush();

		// 자원 정리
		pbw.close();
		sc.close();
		// sbr.close();
		br.close();
		bw.close();
		socket.close();
	}

}
