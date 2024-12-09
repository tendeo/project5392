import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api';

class UserClient {
  constructor(username, password, baseURL = BASE_URL) {
    this.baseURL = baseURL;
    this.axiosInstance = axios.create({
      baseURL: this.baseURL,
    });

        console.log("USERSNAME in user client ", username);
        console.log("PASSWORd in user client ", password);

        // Encode the credentials (username:password) to Base64
        const encodedCredentials = btoa(`${username}:${password}`);  // Base64 encoding
  
        // Set the Authorization header
      this.headers = {
          'Authorization': `Basic ${encodedCredentials}`,
          'Content-Type': 'application/json',  // if you're sending JSON data
          'Accept': '*/*',         // if you expect a JSON response
        };
  }


  // Get the users
  async getUsers() {
    try {
      const response = await this.axiosInstance.get('/users');
      console.log("calling get all users!");

      return response.data

    } catch (error) {
      console.error("Error fetching users", error);
      throw error;  // Rethrow the error to handle it elsewhere
    }

  };

  // Fetch a user by userName
  async getUser(userName){
    try {

      const response = await this.axiosInstance.get(`/users/${userName}`);
      console.log('user information for username: ' + userName);
      console.log(response.data);
      return response.data

    } catch (error) {
      console.error("Error fetching user", error);
      throw error;  // Rethrow the error to handle it elsewhere
    }

  };


  // Create a user
  async createUser(user){
    
    try {
        console.log("calling create user!", user);
        console.log("headers ", this.headers);
        // POST request to create or update a User

        const paramsToSend = {
            userName: user.userName,
            password: user.password,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email
        }
        const response = await this.axiosInstance.post('/users/operator', {
            params: paramsToSend,
            headers: this.headers, 
        });
    
        console.log("user create sucessful: ", response.data);
        console.log(response.data);
        
        // Return the saved or updated node object from the response
        return response.data;

    } catch (error) {
      console.error("Error creating or updating user", error);
      throw error;  // Rethrow the error to handle it elsewhere
    }
  };


  // Delete a user by userName
  async deleteUser(user) {
    try {
        const response = await this.axiosInstance.delete(`/users/${user}`);
        console.log('deleting user: ' + user);
        return response.data;  // Return the response data
      } catch (error) {
        console.error('Error fetching data:', error);
        throw error;  // Rethrow the error to handle it elsewhere
      }
  };

}

export default UserClient;