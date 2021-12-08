package com.projecttango.examples.java.pointcloud;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer implements Runnable {

    private final static int SERVER_PORT = 4445;
    private static DatagramSocket socket = null;

    public static void broadcast(
            String broadcastMessage, InetAddress address) throws IOException, InterruptedException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, SERVER_PORT);
        while(true) {
//            Log.d("SERVER", "sending message");
            socket.send(packet);
//            Thread.sleep(500);
        }
//        socket.close();
    }

    @Override
    public void run() {
        Log.d("SERVER", "server is running");
        try {
            broadcast("Hello", InetAddress.getByName("192.168.10.255"));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
