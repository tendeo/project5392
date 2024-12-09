import axios from 'axios';
const BASE_URL = 'http://localhost:8080/api';

class NodeClient {
  constructor(username, password, baseURL = BASE_URL) {
    this.baseURL = baseURL;
    this.axiosInstance = axios.create({
      baseURL: this.baseURL,
    });

   

      // Encode the credentials (username:password) to Base64
      const encodedCredentials = btoa(`${username}:${password}`);  // Base64 encoding
  
      // Set the Authorization header
    this.headers = {
        'Authorization': `Basic ${encodedCredentials}`,
        'Content-Type': 'application/json',  // if you're sending JSON data
        'Accept': '*/*',         // if you expect a JSON response
      };
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

      console.log("url is " , this.axiosInstance.getUri);
      console.log("headers ", this.headers);
     
      const response = await this.axiosInstance.get('/common/network/all-nodes', {
        headers: this.headers,  // Attach the headers to the request
      });
     

      console.log("calling get all nodes!");
      console.log(response.data);
      // maps the response.data to an array of Node objects
    
      return response.data;

    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  }

  // Get a node by id
  async getNode(nodeId) {
    try {
      const response = await this.axiosInstance.get(`/nodes/${nodeId}`,{
        headers: this.headers,
      });
      console.log('node information for nodeId: ' + nodeId);
      console.log(response.data);
      return response.data;  // Return the response data
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  }

  // Create a node
  async createOrUpdateNode(node) {
    try {
      console.log("calling create or update node!");
      // POST request to create or update a Node
      const response = await this.axiosInstance.post('/common/network/add-node', node, {
        headers: this.headers,
      });
  
      console.log("calling create or update node!");
      console.log(response.data);
      
      // Return the saved or updated node object from the response
      return response.data;
  
    } catch (error) {
      console.error('Error creating or updating node:', error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  }
  

  // Delete a node by Id
  async deleteNode(nodeId) {
    try {
        const response = await this.axiosInstance.delete(`/common/network/delete-node/${nodeId}`,{
          headers: this.headers,
        });
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
        const response = await this.axiosInstance.delete('/nodes', {
          headers: this.headers,
        });
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
