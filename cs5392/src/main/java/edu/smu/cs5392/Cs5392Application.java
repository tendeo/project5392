package edu.smu.cs5392;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.smu.cs5392.backend.RingNetwork;

@SpringBootApplication
public class Cs5392Application implements CommandLineRunner {

    @Autowired
    private RingNetwork ringNetwork;

	public static void main(String[] args) {
		SpringApplication.run(Cs5392Application.class, args);
	}


	@Override
    public void run(String... args) throws Exception {
        // Example usage
     //   Node node1 = new Node("NN1", "Network1", null, null, "Inbox1", "Store1", true);
      /*   Node node2 = new Node("2", "Network1", null, null, "Inbox2", "Store2", true);
        Node node3 = new Node("3", "Network1", null, null, "Inbox3", "Store3", true);

        ringNetwork.addNode(node1);
        ringNetwork.addNode(node2);
        ringNetwork.addNode(node3);
		*/
	
		//System.out.println(node1);

		ringNetwork.loadNodesFromDatabase();
        ringNetwork.printRingNetwork();

        //ringNetwork.deleteNode("N2");

        //ringNetwork.printNetwork();
    }
}

