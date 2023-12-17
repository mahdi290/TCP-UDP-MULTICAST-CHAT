package multi;

import java.io.IOException;//Permet de gérer les exceptions
import java.net.DatagramPacket;//Utilisée pour l'émission et la réception des données
import java.net.InetAddress;//Utile manipuler les adresses internet
import java.net.MulticastSocket;//Utile pour envoyer et recevoir des paquets IP multicast
import java.net.UnknownHostException;//Utile pour lever une exception si l'hôte est inconnue

public class Agent {
	
	//Initialisation de l'adresse et du port de connexion pour la communication
    final static String INET_ADDR = "230.0.0.1";
    final static int PORT = 8547;

    public static void main(String[] args) throws UnknownHostException {

        // Obtenir l'adresse sur laquelle nous allons conneté
        InetAddress address = InetAddress.getByName(INET_ADDR);

         
        //Créer un tampon qui sera utilisé pour les données entrantes contenant les informations du serveur
        byte[] buf = new byte[256];

        //Créer un nouveau socket multicast (qui va permettre à d'autres sockets.programmes de le rejoindre aussi)
        try (MulticastSocket clientSocket = new MulticastSocket(PORT)){

        	//Rejoindre le groupe 
            clientSocket.joinGroup(address);      

            while (true) {

                //Recevoir l'information et l'afficher
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket); 
                String msg = new String(buf, 0, buf.length);
                
                //Afficher le message reçu
                System.out.println("Vous avez 1 message reçu : " + msg);

            }
            //clientSocket.leaveGroup(address);

        } catch (IOException ex) {

            ex.printStackTrace();

        }

    }

}