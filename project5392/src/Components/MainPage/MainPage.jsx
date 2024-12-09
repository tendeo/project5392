import React, { useState, useEffect } from "react";
import "./MainPage.css";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { isEmpty } from "lodash";

import Messagecreated from "../Parts/MessageCreated";

import RingNetwork from "../MainPage/RingNetwork";

import NodeClient from "../../Clients/NodeClient";

import UserClient from "../../Clients/UserClient";



const Mainpage = () => {
  const [selectedAction, setSelectedAction] = useState("");

  const [Message, setMessage] = useState("");
  const [MessageID, setMessageID] = useState("");
  const [ReceiverID, setReceiverID] = useState();
  const [SenderID, setSenderID] = useState();
  const [Direction, setDirection] = useState("");
  const [CurrNodes, SetCurNodes] = useState("");

  const [Accounts, SetAccounts] = useState([]);
  const [submittedFirstName, setSubmittedFirstName] = useState("");
  const [submittedLastName, setSubmittedLastName] = useState("");
  const [submittedType, setSubmittedType] = useState();
  const [submittedEmail, setSubmittedEmail] = useState("");

  const emailValidation = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const location = useLocation();
  const nodeClient = new NodeClient(location.state?.username, location.state?.password);
  const userClient = new UserClient(location.state?.username, location.state?.password);

  const [selectedInboxMessage, setSelectedInboxMessage] = useState("");
  const [selectedStoreMessage, setSelectedStoreMessage] = useState("");
  const [showMessageStatus, setShowMessageStatus] = useState(false);

  const handleToggleMessageStatus = () => {
    setShowMessageStatus(!showMessageStatus);
  };

  useEffect(() => {
    const getNodes = async () => {
      try {
        console.log("in the use effect on main page and trying to fetch the nodes");
        const fetchedNodes = await nodeClient.getNodes();
        console.log(fetchedNodes);
        SetCurNodes(fetchedNodes);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
  
    getNodes();  // Trigger the API call when the component mounts
  }, []);

  const createNode = async (node) => {
    console.log("attempting to create a node!", node);
    try {

      const createdNode = await nodeClient.createOrUpdateNode(node);
      
      // update the pointer and update the curr nodes
      SetCurNodes((prevNodes) => {
        const updatedNodes = prevNodes.map(prevNode => {
          console.log("prevNode is ", prevNode);
          console.log("submitted left neighbhor ", submittedLeftNeighbor);
          console.log("submitted right neighbhor ", submittedRightNeighbor);
          if (prevNode.nodeID === submittedLeftNeighbor) {
            return { ...prevNode, rightNeighborID: submittedNodeID };
          }
          if (prevNode.nodeID === submittedRightNeighbor) {
            return { ...prevNode, leftNeighborID: submittedNodeID };
          }
          return prevNode;
        }).concat(createdNode);
        localStorage.setItem("nodes", JSON.stringify(updatedNodes)); // Save to localStorage
        console.log("updatedNodes in create node is ", updatedNodes);
        return updatedNodes;
      });


    } catch (error) {
      console.error('Error creating node:', error);
    }
  }

  const deleteNode = async (nodeID) => {
    console.log("attempting to delete a node!", nodeID);
    try {

      const deletedNode = await nodeClient.deleteNode(nodeID);

       // Find left and right neighbors
      const nodeToDelete = CurrNodes.find(node => node.nodeID === submittedDeleteNodeId);
      const leftNeighborId = nodeToDelete.leftNeighborID;
      const rightNeighborId = nodeToDelete.rightNeighborID;

      console.log("my node I wanna delete's left neighhir is ", leftNeighborId);
      console.log("my node I wanna delete's right neighhir is ", rightNeighborId);

      // Update neighbors' pointers and remove the deleted node
      SetCurNodes((prevNodes) => {
        const updatedNodes = prevNodes.map((node) => {
          if (node.nodeID === leftNeighborId) {
            return { ...node, rightNeighborID: rightNeighborId };
          }
          if (node.nodeID === rightNeighborId) {
            return { ...node, leftNeighborID: leftNeighborId };
          }
          return node;
        }).filter(node => node.nodeID !== deletedNode.nodeID);
        localStorage.setItem("nodes", JSON.stringify(updatedNodes)); // Save to localStorage
        console.log("The updated nodes after deletion is ", updatedNodes);
        return updatedNodes;
      });


    } catch (error) {
      console.error('Error creating node:', error);
    }
  }

  const createAccount = async (account) => {
    // grab the account object you made

    // try catch block (see above for exmaples)

    // call nodeClient.creatOrUpdateUser(account)
    // createdNewUser = await userClient.createOrUpdateUser(account)

    // now we need to updated the accounts state var
    // SetAccounts((existingAccounts) => [...existingAccounts, createdNewUser]);

    console.log("attempting to create a user!", account);
    try {

      const createdNewUser = await userClient.createUser(account);

      //add created user to user list

      console.log("user I tieed to make is ", createdNewUser);
      console.log("existing accounts ", Accounts );
      SetAccounts((existingAccounts) => [...existingAccounts, createdNewUser]);

      console.log("and now account is ", Accounts);

    } catch (error) {
      console.error('Error creating new user:', error);
    }

  }

  const deleteAccount = async (account) => {
    console.log("attempting to delete a user!", account);
    try {

     const deletedUser = await userClient.deleteUser(account);


    
      if (deletedUser) {
      // Update the accounts list in the state to remove the deleted user
        SetAccounts((prevAccounts) =>
        prevAccounts.filter((acc) => acc.userName !== account.userName)
      );
    }

    } catch (error) {
      console.error('Error deleting user:', error);
    }

  }



  // const [CurrNodes, SetCurNodes] = useState(() => {
  //   const savedNodes = localStorage.getItem("nodes");
    
    
  //   return savedNodes ? JSON.parse(savedNodes) : [
  //     { id: "N1", status: "Active", leftNeighbor: "N3", rightNeighbor: "N2", inboxSize: 2, inbox: ["Message 1", "Message 2"], store: [] },
  //     { id: "N2", status: "Active", leftNeighbor: "N1", rightNeighbor: "N3", inboxSize: 2, inbox: ["Message 1"], store: [] },
  //     { id: "N3", status: "Active", leftNeighbor: "N2", rightNeighbor: "N1", inboxSize: 3, inbox: [], store: [] },
  //   ];
  // });

  console.log("CURR NODES IN MAIN PAGE IS ", CurrNodes);

  const [submittedNodeID, setSubmittedNodeID] = useState("");
  const [submittedLeftNeighbor, setSubmittedLeftNeighbor] = useState("");
  const [submittedRightNeighbor, setSubmittedRightNeighbor] = useState("");
  const [submittedInboxSize, setSubmittedInboxSize] = useState("");
  const [submittedDeleteNodeId, setSubmittedDeleteNodeId] = useState("");
  const [selectedNodeDetails, setSelectedNodeDetails] = useState(null);
  
  const username = location.state?.username || "Guest";
  const navigate = useNavigate();
  const [messageSent, setMessageSent] = useState(false);
  const [sentMessageDetails, setSentMessageDetails] = useState({});

  const [showSendMessage, setShowSendMessage] = useState(true);

  const validateMessageID = (messageID, senderNode) => {
    const regex = new RegExp(`^${senderNode}\\d+$`); // Matches SenderID followed by one or more digits
    return regex.test(messageID);
};


  const handleShowDetails = (node) => {
    setSelectedNodeDetails(node); // Set the selected node for details view
    setSelectedAction("node-details");
  };

  const toggleNodeStatus = (nodeId) => {
    SetCurNodes((prevNodes) => {
      const updatedNodes = prevNodes.map(node => 
        node.id === nodeId ? { ...node, status: node.status === "Active" ? "Inactive" : "Active" } : node
      );
      localStorage.setItem("nodes", JSON.stringify(updatedNodes)); // Save to localStorage
      return updatedNodes;
    });
  };

  const handleSendMessage = (e) => {
    e.preventDefault();

    // Validate Message ID format
    if (!validateMessageID(MessageID, SenderID)) {
      alert(`Message ID must be in the format '${SenderID}y' where y is unique.`);
      return;
    }

    setSentMessageDetails({
      message: Message,
      messageID: MessageID,
      senderID: SenderID,
      receiverID: ReceiverID,
    });

    setMessageSent(true);
    setShowSendMessage(false);

    setMessage("");
    setMessageID("");
    setDirection("");

    setTimeout(() => {
      setMessageSent(false);
      setShowSendMessage(true);
    }, 5000);
  };

  const handleDeleteNode = (e) => {
    //e.preventDefault();

    // Check if there are only 3 nodes
    if (CurrNodes.length <= 3) {
      alert("Cannot delete a node when there are only 3 nodes.");
      return; // Stop if the count is too low
    }

    // Check if the node ID exists
  
    const nodeToDelete = CurrNodes.find(node => node.nodeID === submittedDeleteNodeId);
    if (!nodeToDelete) {
        alert("Node ID does not exist. Please enter a valid Node ID.");
        return; // Stop if the node does not exist
    }

    deleteNode(submittedDeleteNodeId);

   
    // Clear the delete input field
    setSubmittedDeleteNodeId("");

  };

  const handleDirectDeleteNode = (nodeId) => {

    if (CurrNodes.length <= 3) {
      alert("Cannot delete a node when there are only 3 nodes.");
      return; // Stop if the count is too low
    }

    SetCurNodes((prevNodes) => {

      // First, update the neighboring nodes
      const nodeToDelete = prevNodes.find(node => node.id === nodeId);
      if (nodeToDelete) {

        const leftNeighborId = nodeToDelete.leftNeighbor;

        const rightNeighborId = nodeToDelete.rightNeighbor;

        // Update pointers of neighboring nodes

        const updatedNodes = prevNodes.map(node => {

          if (node.id === leftNeighborId) return { ...node, rightNeighbor: rightNeighborId };

          if (node.id === rightNeighborId) return { ...node, leftNeighbor: leftNeighborId };

          return node;

        });
        // Filter out the node being deleted

        return updatedNodes.filter(node => node.id !== nodeId);
      }
      return prevNodes;
    });

    setSelectedNodeDetails(null); // Reset node details

    setSelectedAction(""); // Reset action state

  };

  const handleCreateNode = (e) => {
    e.preventDefault();

    console.log("CurrNodes ", CurrNodes);

    // Check if there are already 10 nodes
    if (CurrNodes.length >= 10) {
      alert("Cannot create a node when there are already 10 nodes.");
      return; // Stop if the count is too high
    }

    // Validation for node ID format
    const nodeFormat = /^N\d+$/; // Regex for Nx where x is a number
    console.log("trya create this", submittedNodeID);
    if (!nodeFormat.test(submittedNodeID)) {
      alert("Node ID must be in the format 'Nx' where x is a number.");
      return; // Stop if the format is invalid
    }

    // Check if node ID already exists
    const nodeExists = CurrNodes.some(node => node.nodeID === submittedNodeID);
    if (nodeExists) {
        alert("Node ID already exists. Please use a unique ID.");
        return; // Stop if the ID already exists
    }

    // Check if left and right neighbors exist
    const leftNeighborExists = CurrNodes.some(node => node.nodeID === submittedLeftNeighbor);
    const rightNeighborExists = CurrNodes.some(node => node.nodeID === submittedRightNeighbor);
    if (!leftNeighborExists || !rightNeighborExists) {
        alert("Both left and right neighbors must exist in the current nodes.");
        return; // Stop if neighbors do not exist
    }

    // Create a new node object
    const newNode = {
      nodeID: submittedNodeID,          // Example Node ID (String)
      networkID: 'network',    // Example Network ID (String)
      leftNeighborID: submittedLeftNeighbor,  // Example Left Neighbor ID (String, optional)
      rightNeighborID: submittedRightNeighbor, // Example Right Neighbor ID (String, optional)
      // inboxID: 'testInboxID',        // Example Inbox ID (String, optional)
      // storeID: 'testStoreID',        // Example Store ID (String, optional)
      status: true,               // Example Status (Boolean)
    };
    // const newNode = { id: submittedNodeID, leftNeighbor: submittedLeftNeighbor, rightNeighbor: submittedRightNeighbor, inboxSize: submittedInboxSize };


    // Try to create the node with the API
    createNode(newNode);

    console.log("AFTER CREATE NODE HERE IS CURRNODES", CurrNodes);

    // Get the new nodes list frome the API and set curr nodes here


    // SetCurNodes((prevNodes) => {
    //   const updatedNodes = prevNodes.map(prevNode => {
    //     if (prevNode.id === submittedLeftNeighbor) {
    //       return { ...prevNode, rightNeighbor: submittedNodeID };
    //     }
    //     if (prevNode.id === submittedRightNeighbor) {
    //       return { ...prevNode, leftNeighbor: submittedNodeID };
    //     }
    //     return prevNode;
    //   }).concat(newNode);
    //   localStorage.setItem("nodes", JSON.stringify(updatedNodes)); // Save to localStorage
    //   return updatedNodes;
    // });

    // Clear the input fields
    setSubmittedNodeID("");
    setSubmittedLeftNeighbor("");
    setSubmittedRightNeighbor("");
    setSubmittedInboxSize("");
  };

  const handleCreateAccount = (e) => {
    e.preventDefault();

    // Validate email format
    if (!emailValidation.test(submittedEmail)) {
      alert("Please enter a valid email address.");
      return; // Stop the function if the email is invalid
    }

    // Generate username
    const generatedUserName = `${submittedFirstName.charAt(0).toLowerCase()}${submittedLastName.toLowerCase()}`;

   // const newUser = { FirstName: submittedFirstName, LastName: submittedLastName, Type: submittedType, Email: submittedEmail, username};


    const user = {
      userName: generatedUserName,  // Example username
      password: "newUserPassword",
      firstName: submittedFirstName,    // Example first name
      lastName: submittedLastName,      // Example last name
      email: submittedEmail, // Example email
      type: submittedType         // Example type
  };

    createAccount(user);
  
    setSubmittedFirstName("");
    setSubmittedLastName("");
    setSubmittedType();
    setSubmittedEmail("");
  };

  // making changes here
  const handleDeleteAccount = (usernameToDelete) => {

    deleteAccount(usernameToDelete);

    // SetAccounts((prevAccounts) =>
    //   prevAccounts.filter((account) => account.username !== usernameToDelete)
    // );

  };


  // useEffect(() => {
  //   // Load nodes from localStorage when the component mounts
  //   const savedNodes = localStorage.getItem("nodes");
  //   if (savedNodes) {
  //     SetCurNodes(JSON.parse(savedNodes));
  //   }
  // }, []);

  return (
    <div>
      <div className="header">
        <div className="header-item blank">Administrator</div>
        <div className="header-item welcome">
          <h1>Welcome</h1>
        </div>
        <div className="header-item username">
          {username}
          <a
            href="#logout"
            className="logout-link"
            onClick={() => navigate("/")}
          >
            Logout
          </a>
        </div>
      </div>
      <div className="second_header">
        <div className="second-item left">
          <h2>Tasks</h2>
          <div className="dropdown-node-actions">
            <label htmlFor="node-actions">Node Actions: </label>
            <select
              id="node-actions"
              value={selectedAction}
              onChange={(e) => setSelectedAction(e.target.value)}
            >
              <option value="node-action0">--</option>
              <option value="create-node">Create a Node</option>
              <option value="delete-node">Delete A Node</option>
              <option value="send-message">Send Message</option>
            </select>
          </div>
          <a
            href="#Status"
            className="node-status"
            onClick={() => setSelectedAction("node-status")}
          >
            Node Status
          </a>

          <div className="dropdown-create-delete-node">
            <label htmlFor="create-delete-node">Create or Delete: </label>
            <select
              id="create-delete"
              value={selectedAction}
              onChange={(e) => setSelectedAction(e.target.value)}
            >
              <option value="crdl-action0">--</option>
              <option value="crdl-action1">Create an Account</option>
              <option value="crdl-action2">Delete an Account</option>
            </select>
          </div>
        </div>
        <div className="second-item middle">
          <h1>Network Display</h1>
          <RingNetwork nodes={CurrNodes} deleteNode={handleDeleteNode} showDetails={(details) => handleShowDetails(details)}/>
        </div>
        <div className="second-item right">
          <h2>Dialogue Space</h2>
          {selectedAction === "node-details" && !isEmpty(selectedNodeDetails) && (
            <div className="node-details">
              <h3>Node Details</h3>
              <p><strong>Node ID:</strong> {selectedNodeDetails.nodeID}</p>
              <p><strong>Left Neighbor:</strong> {selectedNodeDetails.leftNeighborID}</p>
              <p><strong>Right Neighbor:</strong> {selectedNodeDetails.rightNeighborID}</p>
              <div>
                <strong>Inbox:</strong>
                <select
                  value={selectedInboxMessage}
                  onChange={(e) => setSelectedInboxMessage(e.target.value)}
                >
                  <option value=""></option>
                  {["Message 1", "Message 2", "Message 3"].map((message, index) => (
                    <option key={index} value={message}>{message}</option>
                  ))}
                </select>
              </div>
              <div>
                <strong>Store:</strong>
                <select
                  value={selectedStoreMessage}
                  onChange={(e) => setSelectedStoreMessage(e.target.value)}
                >
                  <option value=""></option>
                {["Message 1", "Message 2", "Message 3"].map((message, index) => (
                  <option key={index} value={message}>{message}</option>
                ))}
              </select>
              <button 
                onClick={() => navigate("/ArchivedStore")} // added deleteNode Adjust the route as needed
              >
                Archive Messages
              </button>
              </div>
              <p><strong>Status:</strong> Active</p>
              <button onClick={() => {handleDeleteNode(selectedNodeDetails.id);}}>Delete</button>
              {/* Message Status Button */}
              <button onClick={handleToggleMessageStatus}>
                Message Status
              </button>
              {showMessageStatus && <p>No messages</p>} {/* Conditional display */}
            </div>
          )}
          {selectedAction === "create-node" && (
            <div className="create-node-form">
              <h3>Create a Node</h3>
              <form onSubmit={handleCreateNode}>
                <div>
                  <label>Node ID:</label>
                  <input
                    type="text"
                    value={submittedNodeID}
                    onChange={(e) => setSubmittedNodeID(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Left Neighbor:</label>
                  <input
                    type="text"
                    value={submittedLeftNeighbor}
                    onChange={(e) => setSubmittedLeftNeighbor(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Right Neighbor:</label>
                  <input
                    type="text"
                    value={submittedRightNeighbor}
                    onChange={(e) => setSubmittedRightNeighbor(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Inbox Size:</label>
                  <input
                    type="number"
                    value={submittedInboxSize}
                    onChange={(e) => {
                      const value = parseInt(e.target.value);
                      if (value >= 0) {
                        setSubmittedInboxSize(value);
                      }
                    }}
                    min="1"
                    required
                  />
                </div>
                <button type="submit">Create</button>
              </form>
            </div>
          )}
          {selectedAction === "delete-node" && (
            <div className="delete-node-form">
              <h3>Delete a Node</h3>
              <form onSubmit={handleDeleteNode}>
                <div>
                  <label>Node ID:</label>
                  <input
                    type="text"
                    value={submittedDeleteNodeId}
                    onChange={(e) => setSubmittedDeleteNodeId(e.target.value)}
                    required
                  />
                </div>

                <button type="submit">Delete</button>
              </form>
            </div>
          )}
          {selectedAction === "send-message" && showSendMessage && (
            <div className="message-node-form">
              <h3>Send a Message</h3>
              <form onSubmit={handleSendMessage}>
                <div>
                  <label>Message:</label>
                  <input
                    type="text"
                    value={Message}
                    onChange={(e) => setMessage(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>MessageID:</label>
                  <input
                    type="text"
                    value={MessageID}
                    onChange={(e) => setMessageID(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>SenderID:</label>
                  <select
                    id="node-dropdown-sender"
                    value={SenderID}
                    onChange={(e) => setSenderID(e.target.value)}
                  >
                    <option value="">-- Select a Node --</option>
                    {CurrNodes.map((node) => (
                      <option key={node.id} value={node.id}>
                        {node.id}
                      </option>
                    ))}
                  </select>
                </div>
                <div>
                  <label>ReceiverID:</label>
                  <select
                    id="node-dropdown-recevier"
                    value={ReceiverID}
                    onChange={(e) => setReceiverID(e.target.value)}
                  >
                    <option value="">-- Select a Node --</option>
                    {CurrNodes.map((node) => (
                      <option key={node.id} value={node.id}>
                        {node.id}
                      </option>
                    ))}
                  </select>
                </div>
                <label>Direction:</label>
                <select
                  value={Direction}
                  onChange={(e) => setDirection(e.target.value)}
                  required
                >
                  <option value="">-- Select Direction --</option>
                  <option value="Left">Left</option>
                  <option value="Right">Right</option>
                </select>
                <button type="submit">Send</button>
              </form>
            </div>
          )}

          {messageSent && (
            <div ClassName="message-sent-form">
              {
                <Messagecreated
                  message={sentMessageDetails.message}
                  messageID={sentMessageDetails.messageID}
                  senderID={sentMessageDetails.senderID}
                  receiverID={sentMessageDetails.receiverID}
                />
              }
            </div>
          )}

          {selectedAction === "node-status" && (
            <div className="node-status-form">
              <a
            href="#Status"
            className="node-status"
            onClick={() => setSelectedAction("node-status")}
          ></a>
              <h3>Node Status</h3>

              <div>
                {CurrNodes.length > 0 ? (
                  CurrNodes.map((node) => (
                    <div key={node.id}>
                      <p>{node.id} : {node.status}</p>
                      <button onClick={() => toggleNodeStatus(node.id)}>
                        {node.status === "Active" ? "Set Inactive" : "Set Active"}
                      </button>
                    </div>
                  ))
                ) : (
                  <p>No nodes currently in the network.</p>
                )}
              </div>
            </div>
          )}

          {selectedAction === "crdl-action1" && (
            <div className="create-account-form">
              <h3>Create a Account</h3>
              <form onSubmit={handleCreateAccount}>
                <div>
                  <label>First Name:</label>
                  <input
                    type="text"
                    value={submittedFirstName}
                    onChange={(e) => setSubmittedFirstName(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Last Name:</label>
                  <input
                    type="text"
                    value={submittedLastName}
                    onChange={(e) => setSubmittedLastName(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Type:</label>
                  <select
                    id="Choice-Type"
                    value={submittedType}
                    onChange={(e) => setSubmittedType(e.target.value)}
                  >
                    <option value="First-Action">Adminstrator</option>
                    <option value="Second-Action">Operator</option>
                  </select>
                </div>
                <div>
                  <label>Email:</label>
                  <input
                    type="text"
                    value={submittedEmail}
                    onChange={(e) => setSubmittedEmail(e.target.value)}
                    required
                  />
                </div>
                <button type="submit">Create</button>
              </form>
            </div>
          )}

          {selectedAction === "crdl-action2" && (
            <div className="delete-account-form">
              <h3>Delete An Account</h3>

              <div>
                {Accounts.length > 0 ? (
                  Accounts.map((account) => (
                    <div key={account.username}>
                      <p>
                        {console.log("Account is", Accounts)}
                        {account.username} :{" "}
                        <button
                          onClick={() => {
                            console.log("inside the onclick and accounts is ", Accounts);
                            handleDeleteAccount(account.username)}
                          }
                        >
                          Delete
                        </button>
                      </p>
                    </div>
                  ))
                ) : (
                  <p>No Accounts Available To Delete.</p>
                )}
              </div>
            </div>
          )}
        </div>
      </div>
      <div className="button-container"></div>
      <button
        className="system-buffer-link"
        onClick={() => navigate("/SystemBuffer")}
      >
        System Buffer
      </button>
    </div>
  );
};

export default Mainpage;