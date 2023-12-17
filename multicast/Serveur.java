package multi;

import java.io.IOException;//Permet de gérer les exceptions
import java.net.DatagramPacket;//Utilisée pour l'émission et la réception des données
import java.net.DatagramSocket;//Permet de créer des objets qui contiendront les mdonnées envoyées ou reçues ainsi que l'adresse de provenance/destination
import java.net.InetAddress;//PErmet de manipuler les adresses internet
import java.net.UnknownHostException;//Permet de lever une exception si l'hôte est inconnue
import java.util.Scanner;//Pour récuper les valeurs saisies au clavier

public class Serveur {

	//Initialiser l'adresse de diffusion
    final static String GROUP_BROADCAST_ADDRESS = "230.0.0.1";
    //Initialiser le port d'écoute du serveur
    final static int PORT = 8547;  
    
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
    	 		
	    // Obtenir l'adresse de connexion	
	    InetAddress addr = InetAddress.getByName(GROUP_BROADCAST_ADDRESS);
	    
		Scanner txt = new Scanner(System.in);
        String stay ="oui";
        String msg;
	  
	    //Ouvrir un nouveau datagramSockect, qui sera utilisé pour envoyer les données
	    try (DatagramSocket serverSocket = new DatagramSocket()) {
	        
	    	while (true){//Pour saisir les messages à envoyer à tous les clients connectés, tant que la valeur "non" n'est pas saisie
			    
                System.out.println("Saisir le texte à envoyer aux clients connectés ('non' pour quitter) : ");
                msg = txt.nextLine();              
                stay = msg;
                if (stay.equalsIgnoreCase("non")) break;
	
	            //Créer un paquet qui va contenir le message sous forme de bytes et l'envoyer
	            DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, PORT);
	            
	            //Envoi
	            serverSocket.send(msgPacket);
	           
	            //Affichage
	            System.out.println("Message envoyé : " + msg+"\n\n");
	            Thread.sleep(500);
	        }
	    	System.out.println("FIN DIFFUSION !!!");
	
	    } catch (IOException ex) {	
	        ex.printStackTrace();	
	    }
    }
}