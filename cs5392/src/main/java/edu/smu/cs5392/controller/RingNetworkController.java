package edu.smu.cs5392.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.smu.cs5392.backend.RingNetwork;
import edu.smu.cs5392.model.Message;
import edu.smu.cs5392.model.Node;

/* Network APIs

1. Add a Node
Endpoint: /api/network/add-node
HTTP Method: POST
Request Body: Node
Adds a new node to the ring network.

2. Delete a Node
Endpoint: /api/network/delete-node/{nodeID}
HTTP Method: DELETE
Path Variable: nodeID
Deletes the specified node from the ring network.

3. Send a Message
Endpoint: /api/network/send-message/{fromNodeID}
HTTP Method: POST
Path Variable: fromNodeID
Request Body: Message
Sends a message starting from a specific node.

4. Read a Message
Endpoint: /api/network/read-message/{nodeID}
HTTP Method: GET
Path Variable: nodeID
Reads a message from the inbox of the specified node.

5. Change Node Status
Endpoint: /api/network/change-status/{nodeID}
HTTP Method: PATCH
Path Variable: nodeID
Query Parameter: status (true for active, false for inactive)
Changes the status of the node (active/inactive).

*/

import java.util.List;

@RestController
@RequestMapping("/api/common/network")
public class RingNetworkController {

    @Autowired
    private RingNetwork ringNetwork;

    @PostMapping("/add-node")
    public ResponseEntity<Node> addNode(@RequestBody Node node) {
        Node addedNode = ringNetwork.addNode(node);
        return ResponseEntity.ok(addedNode);
    }

    @DeleteMapping("/delete-node/{nodeID}")
    public ResponseEntity<Node> deleteNode(@PathVariable String nodeID) {
        Node deletedNode = ringNetwork.removeNode(nodeID);
        return ResponseEntity.ok(deletedNode);
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestParam String senderID,
                                              @RequestParam String receiverID,
                                              @RequestParam String message) {
        System.out.println("senderID=" + senderID + ",receiverID" 
            + receiverID + ", message=" + message);
        ringNetwork.sendMessage(senderID, receiverID, message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/read-messages/{nodeID}")
    public ResponseEntity<List<Message>> readMessages(@PathVariable String nodeID) {
        List<Message> messages = ringNetwork.readMessages(nodeID);
        return ResponseEntity.ok(messages);
    }

    @PatchMapping("/change-status/{nodeID}")
    public ResponseEntity<Node> changeNodeStatus(@PathVariable String nodeID, @RequestParam boolean status) {
        Node updatedNode = ringNetwork.changeNodeStatus(nodeID, status);
        return ResponseEntity.ok(updatedNode);
    }

    @GetMapping("/all-nodes")
    public ResponseEntity<List<Node>> getAllNodes() {
        List<Node> allNodes = ringNetwork.getAllNodes();
        return ResponseEntity.ok(allNodes);
    }
}


