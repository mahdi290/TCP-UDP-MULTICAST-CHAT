package multi;

import java.io.IOException;//Permet de g�rer les exceptions
import java.net.DatagramPacket;//Utilis�e pour l'�mission et la r�ception des donn�es
import java.net.InetAddress;//Utile manipuler les adresses internet
import java.net.MulticastSocket;//Utile pour envoyer et recevoir des paquets IP multicast
import java.net.UnknownHostException;//Utile pour lever une exception si l'h�te est inconnue

public class Agent {
	
	//Initialisation de l'adresse et du port de connexion pour la communication
    final static String INET_ADDR = "230.0.0.1";
    final static int PORT = 8547;

    public static void main(String[] args) throws UnknownHostException {

        // Obtenir l'adresse sur laquelle nous allons connet�
        InetAddress address = InetAddress.getByName(INET_ADDR);

         
        //Cr�er un tampon qui sera utilis� pour les donn�es entrantes contenant les informations du serveur
        byte[] buf = new byte[256];

        //Cr�er un nouveau socket multicast (qui va permettre � d'autres sockets.programmes de le rejoindre aussi)
        try (MulticastSocket clientSocket = new MulticastSocket(PORT)){

        	//Rejoindre le groupe 
            clientSocket.joinGroup(address);      

            while (true) {

                //Recevoir l'information et l'afficher
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket); 
                String msg = new String(buf, 0, buf.length);
                
                //Afficher le message re�u
                System.out.println("Vous avez 1 message re�u : " + msg);

            }
            //clientSocket.leaveGroup(address);

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}