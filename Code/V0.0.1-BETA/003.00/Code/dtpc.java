import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.lang.String;
import java.security.*;
import javax.crypto.*;

class eiqs {
	public static void main (String[] args) throws Exception {
		try{
			int ports = 65501;
			System.out.println("[Server] E.I.Q Server V.0.0.1.");
			ServerSocket serverSocket=new ServerSocket(ports);
			System.out.println("[Server] Socket running......");
			int while_number = 0;
			while(true)
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("[Server] While(" + while_number + ").");
				System.out.println("[Server] Wait......");
				Socket socket=serverSocket.accept();
				System.out.println("[Server] Connection.");
				while_number = while_number + 1;
				//in = socket.getInputStream();
				byte[] b = new byte[1024];
				//int len = in.read(b);
				int len = socket.getInputStream().read(b);
				try{
					String datas = new String(b,0,len);
					String[] cmfg = datas.split("#");
					System.out.println("[Server]DATA:" + "\n" + new String(b,0,len));
					runcmd(datas,cmfg);
					/*try{
						if(cmfg[0] == "DATA"){
							if(cmfg[1] == "STOP"){
								System.exit(0);
							}
						}*/
						
						
					/*if(cmfg[1].equals("DATA")){
						if(cmfg[2].equals("STOP")){
							System.exit(0);
						}
					}*///FINISH
					
					
					/*}catch(*//*IOException | InterruptedException |*//* ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
					{
						//e.printStackTrace();
						System.out.println("[Server] Error.");
			
					}*/
				}catch(/*IOException | InterruptedException | */ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
					//e.printStackTrace();
					System.out.println("[Server] Error.");
				}
				//String datas = new String(b,0,len);
				//String[] dfg = datas.split("#");
				
				//runcmd(datas,dfg);
				/*if(dfg[0] == "DATA"){
					if(dfg[1] == "STOP"){
						System.exit(0);
					}
				}*/
				
				//System.out.println("[Server]DATA:" + "\n" + new String(b,0,len));
				//runcmd(datas,dfg);
				
				
				//out = socket.getOutputStream();
				//out.write("".getBytes());
				socket.getOutputStream().write("#DATA#RUN#FINISH#".getBytes());
				
			}
		}
		catch(IOException /*| InterruptedException*/ | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
		{
			//e.printStackTrace();
			System.out.println("[Server] Error.");
			rerun(args);
		}
	}
	
	public static void runcmd(String cmds,String[] cmfg) throws Exception {
		try{
			/*if(mfg[1] == "DATA"){
				if(mfg[2] == "STOP"){
					System.exit(0);
				}
			}*/
			if(cmfg[1].equals("DATA")){
				if(cmfg[2].equals("STOP")){
					System.out.println("[Server] Stop Server.");
					System.exit(0);
				}
			}
			debugsfun();
		}catch(/*IOException | InterruptedException |*/ ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
		{
			//e.printStackTrace();
			System.out.println("[Server] Error.");
			
		}
	}
	
	public static void rerun(String[] args) throws Exception {
		main(args);
	}
	
	public static void debugsfun() throws Exception {
		//sendbags("127.0.0.1",65501,"#DATA#DEBUG#SENGBUGS#");
	}
	
	
	public static void sendbags(String host,int hostport,String bagsdatas) throws Exception {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String serverIP = host;
		int port = hostport;
		try
		{
			socket = new Socket(serverIP,port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println(bagsdatas/*.getBytes()*/);
		}
		catch(IOException /*| InterruptedException | UnknownHostException*/ e)
		{
			//e.printStackTrace();
			System.out.println("[Server] Error.");
		}
	}
}
