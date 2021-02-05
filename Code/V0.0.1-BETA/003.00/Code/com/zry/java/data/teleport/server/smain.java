package com.zry.java.data.teleport.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.security.*;
import javax.crypto.*;
import java.text.*;
//import java.sql.*;

class smain {
	public static int ports = 51;
	public static int client_ports = 51;
	public static String server_version = "V0.0.1.BETA.003";
	
	public static String time_str = time_gettime();
	//public static InetAddress host_ip4 = Inet4Address.getLocalHost();
	//public static String host_ip4_text = host_ip4.getHostAddress();
	
	public static void main (String[] args) throws Exception {
		try{
			//int ports = 52;
			
			//int client_ports = 51;
			
			//String server_version = "V0.0.1";
			
			System.out.println("[Server] Data TelePort Server V.0.0.1.BETA.003");
			
			InetAddress host_ip4 = Inet4Address.getLocalHost();
			String host_ip4_text = host_ip4.getHostAddress();
			
			
			ServerSocket serverSocket=new ServerSocket(ports);
			System.out.println("[Server] Socket running......");
			int while_number = 0;
			System.out.println("[Server] Host ip:" + host_ip4_text);
			System.out.println("[Server] Run dir:" + dir_rundir_user());
			System.out.println("[Server] Time:" + time_gettime());
			dir_make(dir_rundir_user() + "\\DTP_DATA");
			dir_make(dir_rundir_user() + "\\DTP_DATA\\" + time_str);
			while(true)
			{
				Scanner scanner = new Scanner(System.in);
				//System.out.println("[Server] While(" + while_number + ").");
				System.out.println("[Server] Wait......");
				Socket socket=serverSocket.accept();
				System.out.println("[Server] Connection.");
				while_number = while_number + 1;
				//in = socket.getInputStream();
				byte[] b = new byte[32768];
				//int len = in.read(b);
				int len = socket.getInputStream().read(b);
				try{
					String datas = new String(b,0,len);
					String[] cmfg = datas.split("#");
					System.out.println("[Server] DATA:" + "\n" + new String(b,0,len));
					InetAddress client_host_ip = serverSocket.getInetAddress();
					String client_host_ip_text = client_host_ip.getHostAddress();
					System.out.println("[Server] Client ip:" + client_host_ip_text);
					runcmd(datas,cmfg,client_host_ip_text);
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
				socket.getOutputStream().write("#DATA#DTP#SERVER#RUN#FINISH#".getBytes());
				
			}
		}
		catch(IOException /*| InterruptedException*/ | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e)
		{
			//e.printStackTrace();
			System.out.println("[Server] Error.");
			rerun(args);
		}
	}
	
	public static void runcmd(String cmds,String[] cmfg,String client_ip) throws Exception {
		try{
			/*if(mfg[1] == "DATA"){
				if(mfg[2] == "STOP"){
					System.exit(0);
				}
			}*/
			/*if(cmfg[1].equals("DATA")){
				if(cmfg[2].equals("STOP")){
					System.out.println("[Server] Stop Server.");
					System.exit(0);
				}
			/}*/
			if(cmfg[1].equals("DATA")){
				if(cmfg[2].equals("DTP")){
					if(cmfg[3].equals("CLIENT")){
						if(cmfg[5].equals(server_version)){
							if(cmfg[7].equals(String.valueOf(ports))){
								if(cmfg[8].equals(String.valueOf(client_ports))){
									//15
									dir_make(dir_rundir_user() + "\\DTP_DATA\\" + time_str + "\\" + client_ip);
									dir_make(dir_rundir_user() + "\\DTP_DATA\\" + time_str + "\\" + client_ip + "\\" + cmfg[6]);
									String file_temp = dir_rundir_user() + "\\DTP_DATA\\"+ time_str + "\\" + client_ip + "\\" + cmfg[6] + "\\" + time_gettime() + ".dfs";
									file_make(file_temp);
									String file_add_temp = "[#DATA#START#]\n#IP#" + cmfg[6] + "\n#DATA#\n" + cmfg[15] + "\n" + "[#DATA#END#]";
									file_add(file_temp,file_add_temp);
								}
							}
						}
					}
				}
			}
			debugsfun();
		}catch(/*IOException | InterruptedException |*/ ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | UnknownHostException e)
		{
			//e.printStackTrace();
			System.out.println("[Server] Error.");
			
		}
	}
	
	public static void file_set(String filenames,String filedatas) throws IOException {
		String str = filedatas;
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filenames));
	    writer.write(str);
	    writer.close();
	}

	public static void file_add(String filenames,String filedatas) throws IOException {
		String str = filedatas;
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filenames, true));
	    writer.append(str);
	    writer.close();
	}
	    
	public static String time_gettime() {
		Date date = new Date();
        String strDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		String time_text = sdf.format(date);
		return time_text;
	}
	
	public static void dir_make (String dir) throws Exception {
		File folder = new File(dir);
		if (!folder.exists() && !folder.isDirectory()) {
		    folder.mkdirs();
		    System.out.println("[Server] Make dir:" + dir);
		} 
	}
	
	public static void file_make(String filename) throws Exception {
		File file = new File(filename);
		if (!file.exists()){
			file.createNewFile();
			System.out.println("[Server] Make file:" + filename);
		}
	}
	
	public static String dir_rundir() {
		return System.getProperty("usr.dir");
	}
	
	public static String dir_rundir_user() {
		return System.getProperty("user.dir");
	}
	
	public static String dir_rundir_class() {
		return System.getProperty("java.class.path");
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
			System.out.println("[Server] Error.");
		}
	}
	
	
	
}
