package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientClass {

	 String Serverip;
	 Socket clientsocket;
	 PrintWriter out;
	 ObjectInputStream in;
	 BufferedReader stdIn;
	 public ClientClass(String serverip, String serverport) throws NumberFormatException, UnknownHostException, IOException, ClassNotFoundException{
	  clientsocket=new Socket(serverip, Integer.parseInt(serverport));
	  out= new PrintWriter(clientsocket.getOutputStream(), true);
	  in =
	    new ObjectInputStream(clientsocket.getInputStream());
	  out.flush();
	  System.out.println((String)in.readObject());
	  
	 }
	 public String sendmessage(String message) throws IOException, ClassNotFoundException{
	  out.println(message);
	  out.flush();
	  String str=null;
	  Object obj;
	  obj=in.readObject();
	  str=(String)obj;
	  return str;
	  
	 }
	 
	 public ArrayList<ComplaintHolder> sendmessage2(String message) throws IOException, ClassNotFoundException{
		  out.println(message);
		  out.flush();
		  ArrayList<ComplaintHolder> str=null;
		  Object obj;
		  obj=in.readObject();
		  str=(ArrayList<ComplaintHolder>)obj;
		  return str;
		  
		 }
	 
	 public void CloseConnection() throws IOException{
	  clientsocket.close();
	 }
	}