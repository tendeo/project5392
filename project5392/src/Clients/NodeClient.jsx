import axios from 'axios';
import Node from "../Models/Node";

const BASE_URL = 'http://localhost:8080/api';

class NodeClient {
  constructor(baseURL = BASE_URL) {
    this.baseURL = baseURL;
    this.axiosInstance = axios.create({
      baseURL: this.baseURL,
    });
  }

    // // CREATE or UPDATE a node
    // @PostMapping
    // public ResponseEntity<Node> createOrUpdateNode(@RequestBody Node node) {
    //     try {
    //         Node savedNode = nodeService.saveNode(node);
    //         return new ResponseEntity<>(savedNode, HttpStatus.CREATED);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
  // Create or update nodes
//   async createOrUpdateNode(node) {
//     try {
//       const body = new Node(node);
//     }

//   }

  // Get the nodes
  async getNodes() {
    try {
      const response = await this.axiosInstance.get('/nodes');

      console.log("calling get all nodes!");
      // maps the response.data to an array of Node objects
      const nodes = response.data.map(nodeData => {
        return (
          <Node
            key={nodeData.nodeID}  // Use nodeID as the key to ensure uniqueness
            nodeID={nodeData.nodeID}
            networkID={nodeData.networkID}
            leftNeighborID={nodeData.leftNeighborID}
            rightNeighborID={nodeData.rightNeighborID}
            inboxID={nodeData.inboxID}
            storeID={nodeData.storeID}
            status={nodeData.status}
          />
        );
      });

      console.log("It returned " + JSON.stringify(nodes));
      console.log("First node id is " + nodes[0].props.nodeID);
      return nodes;

    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  }

  // Get a node by id
  async getNode(nodeId) {
    try {
      const response = await this.axiosInstance.get(`/nodes/${nodeId}`);
      console.log('node information for nodeId: ' + nodeId);
      console.log(response.data);
      return response.data;  // Return the response data
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  }

  // Delete a node by Id
  async deleteNode(nodeId) {
    try {
        const response = await this.axiosInstance.delete(`/nodes/${nodeId}`);
        console.log('deleting nodeId: ' + nodeId);
        return response.data;  // Return the response data
      } catch (error) {
        console.error('Error fetching data:', error);
        throw error;  // Rethrow the error to handle it elsewhere
      }
  }

   // Delete a node by Id
   async deleteNodes() {
    try {
        const response = await this.axiosInstance.delete('/nodes');
        console.log('deleting all nodes');
        return response.data;  // Return the response data
      } catch (error) {
        console.error('Error fetching data:', error);
        throw error;  // Rethrow the error to handle it elsewhere
      }
  }

//   // Method to submit data to the POST endpoint
//   async submitData(data) {
//     try {
//       const response = await this.axiosInstance.post('/submit', data);
//       return response.data;  // Return the response data
//     } catch (error) {
//       console.error('Error submitting data:', error);
//       throw error;  // Rethrow the error to handle it elsewhere
//     }
//   }
}
const nodeClient = new NodeClient();
export default NodeClient;
