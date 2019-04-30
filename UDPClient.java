package com.company;

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;

class UDPClient {
    public static void main(String args[]) {
        try{
            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(1);
            String ip = "192.168.1.8";
            byte Ipaddr[] = ip.getBytes();
            InetAddress IPAddress = InetAddress.getByName(ip);
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];


            for(int i = 0; i <10;i++) {

                Date date= new Date();
                long time = date.getTime();
                Timestamp sentTime = new Timestamp(time);
                long start = System.currentTimeMillis();
                String sentence = "ping " + i + " " + sentTime;
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                Thread.sleep(1000);
                System.out.println("Sent: "+sentence);
                DatagramPacket receivePacket = new DatagramPacket
                        (receiveData, receiveData.length);
                try {
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("FROM SERVER:" + modifiedSentence);
                    System.out.println("Round Trip Time: "+((System.currentTimeMillis()-start)/1000)+" seconds");
                }
                catch(SocketTimeoutException e){
                    System.out.println("Request timed out");
                }


            }clientSocket.close();
        }
        catch(IOException | InterruptedException e) { System.out.println(e.getMessage());}
    }
}
