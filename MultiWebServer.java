import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiWebServer {

	public static void main(String[] args) {

		String base_url = "E://�μ�/���������ԭ��/ʵ��/ʵ��1/";

		while (true) {

			try {
				ServerSocket serverSocket = new ServerSocket(80);
				System.out.println("���ڼ�������");
				Socket socket = serverSocket.accept();
				System.out.println("���յ�����");
				InputStream inputStream = socket.getInputStream();
				System.out.println("�ѵõ�������");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				System.out.println("�ѵõ�Reader");
				String line = reader.readLine();
				System.out.println(line);
				String url = line.substring(5, line.indexOf("HTTP") - 1);
				System.out.println(url);

				// ��ȡ�ļ�����
				inputStream = new FileInputStream(base_url + url);
				OutputStream outputStream = socket.getOutputStream();
				byte[] buffer = new byte[4 * 1024];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				outputStream.flush();
				
				//��ӡ�����ظ������������
				System.out.println("���ͻ�����������ص�����Ϊ : ");
				inputStream = new FileInputStream(base_url + url);
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line=reader.readLine()) != null){
					System.out.println(line);
				}
				

				serverSocket.close();
				socket.shutdownInput();
				socket.close();
				inputStream.close();
				reader.close();

				outputStream.close();

			} catch (Exception e) {
			}

		}

	}

}
