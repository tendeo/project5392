import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api';

class MessageClient {
    constructor(baseURL = BASE_URL) {
      this.baseURL = baseURL;
      this.axiosInstance = axios.create({
        baseURL: this.baseURL,
      });
    }


    // Get a node by id
  async getMessage(nodeId) {
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


}