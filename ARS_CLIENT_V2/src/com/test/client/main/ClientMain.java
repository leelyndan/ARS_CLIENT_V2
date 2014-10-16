package com.test.client.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {

	private static final String IP="127.0.0.1";
	
	private static final int PORT=4700;
	
	private void startClient() 
	{
		try 
		{
			Socket socket = new Socket(IP, PORT);
			executeMethod(socket);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void executeMethod(Socket socket) 
	{
		if (socket == null) 
		{
			System.out.println("can not connect server ");
		}
		try
		{
			//A101IX20140822134849
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) 
			{
				String messageFromServer=read.readLine();
				if(messageFromServer.contains("NL"))
				{
					messageFromServer=messageFromServer.replaceAll("NL", "\n");
				}
				System.out.println(messageFromServer);
				String input = sin.readLine();
				os.println(input);
				os.flush();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		ClientMain client = new ClientMain();
		client.startClient();
	}
	
}
