package com.zry.java.data.teleport.client;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.lang.String;
import java.security.*;
import javax.crypto.*;
import java.text.*;
//import java.sql.*;

class cmain {
	public static int ports = 51;
	public static int server_ports = 51;
	public static String client_version = "V0.0.1";
	
	//public static InetAddress host_ip4 = Inet4Address.getLocalHost() ;
	//public static String host_ip4_text = host_ip4.getHostAddress() ;
	
	
	
	
	
	public static void main (String[] args) throws Exception {
		try{
			//int ports = 52;
			
			//int server_ports = 51;
			
			//String client_version = "V0.0.1";
			
			System.out.println("[Client] Data TelePort Client V.0.0.1.");
			
			InetAddress host_ip4 = Inet4Address.getLocalHost();
			String host_ip4_text = host_ip4.getHostAddress();
			
			ServerSocket serverSocket=new ServerSocket(ports);
			System.out.println("[Client] Socket running......");
			int while_number = 0;
			System.out.println("[Client] Host ip:" + host_ip4_text);
			
			BufferedReader ip_input = new BufferedReader(new InputStreamReader(System.in));
			//Scanner ip_input = new Scanner(System.in);
			System.out.print("[Client] Input IP:");
			String ip_text = ip_input.readLine();
			//String ip_text = ip_input.next();
			System.out.print("\n");
			
			BufferedReader data_input = new BufferedReader(new InputStreamReader(System.in));
			//Scanner data_input = new Scanner(System.in);
			System.out.print("[Client] Input Datas:");
			String data_text = data_input.readLine();
			//String data_text = data_input.next();
			System.out.print("\n");
			
			System.out.println("[Client] Connecting to server......");
			String temp_datas = "#DATA#DTP#CLIENT##" + client_version + "#" + host_ip4_text + "#" + server_ports + "#" + ports + "#" + "######" + data_text + "###";
			sendbags(ip_text,server_ports,temp_datas);
			System.out.print("\n");
			System.out.println("[Client] Bags sending completed.");
			while(true)
			{
				Scanner scanner = new Scanner(System.in);
				//System.out.println("[Client] While(" + while_number + ").");
				System.out.println("[Client] Wait......");
				Socket socket=serverSocket.accept();
				System.out.println("[Client] Connection.");
				while_number = while_number + 1;
				//in = socket.getInputStream();
				byte[] b = new byte[16384];
				//int len = in.read(b);
				int len = socket.getInputStream().read(b);
				try{
					String datas = new String(b,0,len);
					String[] cmfg = datas.split("#");
					System.out.println("[Client] DATA:" + "\n" + new String(b,0,len));
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
						System.out.println("[Client] Error.");
			
					}*/
				}catch(/*IOException | InterruptedException | */ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e){
					//e.printStackTrace();
					System.out.println("[Client] Error.");
				}
				//String datas = new String(b,0,len);
				//String[] dfg = datas.split("#");
				
				//runcmd(datas,dfg);
				/*if(dfg[0] == "DATA"){
					if(dfg[1] == "STOP"){
						System.exit(0);
					}
				}*/
				
				//System.out.println("[Client]DATA:" + "\n" + new String(b,0,len));
				//runcmd(datas,dfg);
				
				
				//out = socket.getOutputStream();
				//out.write("".getBytes());
				//socket.getOutputStream().write("#DATA#RUN#FINISH#".getBytes());
				
			}
		}
		catch(IOException /*| InterruptedException*/ | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
		{
			//e.printStackTrace();
			System.out.println("[Client] Error.");
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
			/*if(cmfg[1].equals("DATA")){
				if(cmfg[2].equals("STOP")){
					System.out.println("[Client] Stop Client.");
					System.exit(0);
				}
			/}*/
			if(cmfg[1].equals("DATA")){
				if(cmfg[2].equals("DTP")){
					if(cmfg[3].equals("SERVER")){
						if(cmfg[4].equals("RUN")){
							if(cmfg[5].equals("FINISH")){
								System.out.println("[Client] Server received successfully.");
								System.out.println("[Client] Exiting......");
								exit_will();
							}
						}
					}
				}
			}
			debugsfun();
		}catch(/*IOException | InterruptedException |*/ ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | UnknownHostException e)
		{
			//e.printStackTrace();
			System.out.println("[Client] Error.");
			
		}
	}
	
	public static void rerun(String[] args) throws Exception {
		main(args);
	}
	
	public static void debugsfun() throws Exception {
		//sendbags("127.0.0.1",65501,"#DATA#DEBUG#SENGBUGS#");
	}
	
	public static void exit_will() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		System.exit(0);
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
			System.out.println("[Client] Error.");
		}
	}
	
}
