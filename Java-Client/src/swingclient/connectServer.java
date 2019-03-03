/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class connectServer {
    Socket socket;
    BufferedWriter socketWriter;
    BufferedReader socketReader;
    char[] charText;
    String stringText;
    
    public connectServer(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);    //Bağlantı oluşturur.
    }
    public void disconnectServer() throws IOException{
        socket.close(); //Bağlantıyı keser.
    }
    
    public void sendMessage(String sentMessage) throws IOException{
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));    //Socket üzerinde çıkış kanalı oluşturur.
        socketWriter.write(sentMessage);    //Mesajı socket'e yazar.
        socketWriter.flush();
    }
    
    public String recievedMessage() throws SocketException, IOException{
        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //Socket üzerinde giriş kanalı oluşturur.
        charText = new char[socket.getReceiveBufferSize()]; //Gelen mesajın buffer üzerinde kapladığı alan boyutu kadar char dizisi oluşturur.
        socketReader.read(charText, 0, socket.getReceiveBufferSize());  //Socket üzerinden okuma yapar ve okunan verileri char dizisine atar.(ASCII tablosuna göre)
        stringText = new String(charText);  //Okunan char dizisini stringe dönüştürür.
        return stringText;
    }
    
}
