package edu.smu.cs5392;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.smu.cs5392.model.Inbox;
import edu.smu.cs5392.model.NetworkEntity;
import edu.smu.cs5392.model.Node;
import edu.smu.cs5392.model.Store;
import edu.smu.cs5392.model.SystemBuffer;
import edu.smu.cs5392.model.User;
import edu.smu.cs5392.model.Message;
import edu.smu.cs5392.model.LoginCredentials;
import edu.smu.cs5392.repository.NodeRepository;
import edu.smu.cs5392.repository.StoreRepository;
import edu.smu.cs5392.repository.SystemBufferRepository;
import edu.smu.cs5392.repository.UserRepository;
import edu.smu.cs5392.repository.InboxRepository;
import edu.smu.cs5392.repository.LoginCredentialsRepository;
import edu.smu.cs5392.repository.MessageRepository;
import edu.smu.cs5392.repository.NetworkRepository;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Cs5392Application implements CommandLineRunner {

	@Autowired
	NodeRepository nodeRepository;

	@Autowired
	NetworkRepository networkRepository;

	@Autowired
	InboxRepository inboxRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	LoginCredentialsRepository loginCredentialsRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	SystemBufferRepository systemBufferRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cs5392Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception { 

		// Populate initial User table 
		initializeUserData();

		// Populate initial User credentials
		initializeLoginCredentialsData();

		// Populate initial Inbox table row and retrive it
		initializeInboxData();

		// Populate initial Network table row and retrive it
		NetworkEntity network = new NetworkEntity("network",
		 3, 
		 10);
		networkRepository.save(network);
		System.out.println(networkRepository.findById("network"));

		initializeNodeData();

		initializeMessageData();

		initializeStoreData();

		initializeSystemBufferData();

	}

	private void initializeUserData() {

		// Create some test User entries
		User donald = new User ("dduck", "Donald", "Duck", 
			"donald.duck@duck.com", "administrator");
		User bart = new User("bsimpson", "Bart", "Simpson", 
			"bart.simpson@simpson.com", "operator");

		// Save the test data to the database
        userRepository.saveAll(List.of(donald, bart));

        // Log the saved entries
        System.out.println("User data initialized:");
        userRepository.findAll().forEach(System.out::println);

	}

	private void initializeLoginCredentialsData() {

		// Create some test User entries
		LoginCredentials donald = new LoginCredentials("dduck", "daisy");
		
		LoginCredentials bart = new LoginCredentials("bsimpson", "lisa");

		// Save the test data to the database
        loginCredentialsRepository.saveAll(List.of(donald, bart));

        // Log the saved entries
        System.out.println("User login credentials initialized:");
        userRepository.findAll().forEach(System.out::println);

	}

	private void initializeMessageData() {
		Message node1m1 = new Message("node1m1", "node2","node1", 
							new Date(),  
							new Date(),
							"left",
							"node2-node1", "delivered", "Donald to Bart");

		// Save the test data to the database
        messageRepository.save(node1m1);

       	// Log the saved entries
	   	System.out.println("Message data initialized:");
	   	messageRepository.findAll().forEach(System.out::println);		

	}

	private void initializeNodeData() {
		Node node1 = new Node("node1", "network", "node2", "node3",
							  "node1inbox", "node1store", true);
		Node node2 = new Node("node2", "network", "node1", "node3",
							  "node2inbox", "node2store", true);
		Node node3 = new Node("node3", "network", "node2", "node1",
							  "node3inbox", "node3store", true);
					  
		// Save the test data to the database
        nodeRepository.saveAll(List.of(node1, node2, node3));

	   // Log the saved entries
	   System.out.println("Test data initialized:");
	   nodeRepository.findAll().forEach(System.out::println);

	}

	private void initializeInboxData() {
		// Create some test Inbox entries
        Inbox n1Inbox = new Inbox("node1m1", "node1inbox", new Date(), "node1", 10);
        Inbox n2Inbox = new Inbox("node2m1", "node2inbox", new Date(), "node2", 15);
        Inbox n3Inbox = new Inbox("node3m1", "node3inbox", new Date(), "node3", 20);

        // Save the test data to the database
        inboxRepository.saveAll(List.of(n1Inbox, n2Inbox, n3Inbox));

        // Log the saved entries
        System.out.println("Test data initialized:");
        inboxRepository.findAll().forEach(System.out::println);

	}

	private void initializeStoreData() {
		Store store1 = new Store("node1store",
			"node1m1", 
			new Date(), "node1");
		Store store2 = new Store("node2store", "node2m1", 
			new Date(), 
			"node2");

		// Save the test data to the database
		storeRepository.saveAll(List.of(store1, store2));

		// Log the saved entries
		System.out.println("Test data initialized:");
		storeRepository.findAll().forEach(System.out::println);
		

	}

	private void initializeSystemBufferData() {

		// Create some test User entries

		SystemBuffer buffer1 = new SystemBuffer("node1m1", new Date());

		SystemBuffer buffer2 = new SystemBuffer("node1m2", new Date());

		// Save the test data to the database
        systemBufferRepository.saveAll(List.of(buffer1, buffer2));

        // Log the saved entries
        System.out.println("SystemBuffer data initialized:");
        systemBufferRepository.findAll().forEach(System.out::println);

	}
}
