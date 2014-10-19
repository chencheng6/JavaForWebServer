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

		String base_url = "E://课件/计算机网络原理/实验/实验1/";

		while (true) {

			try {
				ServerSocket serverSocket = new ServerSocket(80);
				System.out.println("正在监听请求");
				Socket socket = serverSocket.accept();
				System.out.println("已收到请求");
				InputStream inputStream = socket.getInputStream();
				System.out.println("已得到输入流");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				System.out.println("已得到Reader");
				String line = reader.readLine();
				System.out.println(line);
				String url = line.substring(5, line.indexOf("HTTP") - 1);
				System.out.println(url);

				// 获取文件内容
				inputStream = new FileInputStream(base_url + url);
				OutputStream outputStream = socket.getOutputStream();
				byte[] buffer = new byte[4 * 1024];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				outputStream.flush();
				
				//打印出返回给浏览器的数据
				System.out.println("给客户端浏览器返回的数据为 : ");
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
