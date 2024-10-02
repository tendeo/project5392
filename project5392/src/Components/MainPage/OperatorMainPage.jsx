import React, { useState } from "react";
import "./MainPage.css";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";

import Messagecreated from "../Parts/MessageCreated";

import RingNetwork from "../MainPage/RingNetwork";

const OperatorMainPage = () => {
  const [selectedAction, setSelectedAction] = useState("");

  const [Message, setMessage] = useState("");
  const [MessageID, setMessageID] = useState("");
  const [ReceiverID, setReceiverID] = useState();
  const [SenderID, setSenderID] = useState();
  const [Direction, setDirection] = useState("");

  const [Accounts, SetAccounts] = useState([]);
  const [FirstName, setFirstName] = useState("");
  const [LastName, setLastName] = useState("");
  const [Type, setType] = useState();
  const [Email, setEmail] = useState("");

  const emailValidation = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const [selectedInboxMessage, setSelectedInboxMessage] = useState("");
  const [selectedStoreMessage, setSelectedStoreMessage] = useState("");
  const [showMessageStatus, setShowMessageStatus] = useState(false);

  const handleToggleMessageStatus = () => {
    setShowMessageStatus(!showMessageStatus);
  };

  const [CurrNodes, SetCurNodes] = useState([
    { id: "N1", status: "Active", leftNeighbor: "N3", rightNeighbor: "N2", inboxSize: 2, inbox: ["Message 1", "Message 2"], store: [] },
    { id: "N2", status: "Active", leftNeighbor: "N1", rightNeighbor: "N3", inboxSize: 2, inbox: ["Message 1"], store: [] },
    { id: "N3", status: "Active",leftNeighbor: "N2", rightNeighbor: "N1", inboxSize: 3, inbox: [], store: [] },
  ]);
  const [nodeId, setNodeId] = useState("");
  const [leftNeighbor, setLeftNeighbor] = useState("");
  const [rightNeighbor, setRightNeighbor] = useState("");
  const [inboxSize, setInboxSize] = useState("");

  const [DelNodeId, setDelNodeId] = useState("");

  const [selectedNodeDetails, setSelectedNodeDetails] = useState(null);

  const location = useLocation();
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

    const nodeDetails = {
        id: node.id,
        leftNeighbor: node.leftNeighbor || "Unknown",
        rightNeighbor: node.rightNeighbor || "Unknown",
        inbox: node.inbox || [], // Ensure inbox is an array
        store: node.store || [], // Ensure store is an array
      };

    setSelectedNodeDetails(node); // Set the selected node for details view
    setSelectedAction("node-details");
  };

  const toggleNodeStatus = (nodeId) => {
    SetCurNodes((prevNodes) => 
      prevNodes.map(node => 
        node.id === nodeId ? { ...node, status: node.status === "Active" ? "Inactive" : "Active" } : node
      )
    );
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
  
    const nodeToDelete = CurrNodes.find(node => node.id === DelNodeId);
    if (!nodeToDelete) {
        alert("Node ID does not exist. Please enter a valid Node ID.");
        return; // Stop if the node does not exist
    }


    // Find left and right neighbors
    const leftNeighborId = nodeToDelete.leftNeighbor;
    const rightNeighborId = nodeToDelete.rightNeighbor;

      // Update neighbors' pointers and remove the deleted node
    SetCurNodes((prevNodes) => {
      // First, find the neighbors that need to be updated
      const updatedNodes = prevNodes.map((node) => {
        if (node.id === leftNeighborId) {
          return { ...node, rightNeighbor: rightNeighborId }; // Update right neighbor of left node
        }
        if (node.id === rightNeighborId) {
          return { ...node, leftNeighbor: leftNeighborId }; // Update left neighbor of right node
        }
        return node; // Return the node as is if it doesn't need updating
      });

      // Now filter out the deleted node
      return updatedNodes.filter(node => node.id !== DelNodeId);
    });

    // Clear the delete input field
    setDelNodeId("");

  };

  const handleCreateNode = (e) => {
    e.preventDefault();

    // Check if there are already 10 nodes
    if (CurrNodes.length >= 10) {
      alert("Cannot create a node when there are already 10 nodes.");
      return; // Stop if the count is too high
    }

    // Validation for node ID format
    const nodeFormat = /^N\d+$/; // Regex for Nx where x is a number
    if (!nodeFormat.test(nodeId)) {
      alert("Node ID must be in the format 'Nx' where x is a number.");
      return; // Stop if the format is invalid
    }

    // Check if node ID already exists
    const nodeExists = CurrNodes.some(node => node.id === nodeId);
    if (nodeExists) {
        alert("Node ID already exists. Please use a unique ID.");
        return; // Stop if the ID already exists
    }

    // Check if left and right neighbors exist
    const leftNeighborExists = CurrNodes.some(node => node.id === leftNeighbor);
    const rightNeighborExists = CurrNodes.some(node => node.id === rightNeighbor);
    if (!leftNeighborExists || !rightNeighborExists) {
        alert("Both left and right neighbors must exist in the current nodes.");
        return; // Stop if neighbors do not exist
    }

    // Create a new node object
    const newNode = { id: nodeId, leftNeighbor, rightNeighbor, inboxSize };

      // Update nodes with new node
      SetCurNodes((prevNodes) => {
        // Update the neighbors' pointers correctly
        const updatedNodes = prevNodes.map(prevNode => {
          if (prevNode.id === leftNeighbor) {
            return { ...prevNode, rightNeighbor: nodeId };
          }
          if (prevNode.id === rightNeighbor) {
            return { ...prevNode, leftNeighbor: nodeId };
          }
          return prevNode;
        });

        // Add the new node
        updatedNodes.push(newNode);
        return updatedNodes;
      });
    // Clear the input fields
    setNodeId("");
    setLeftNeighbor("");
    setRightNeighbor("");
    setInboxSize("");
  };

  const handleCreateAccount = (e) => {
    e.preventDefault();

    // Validate email format
    if (!emailValidation.test(Email)) {
      alert("Please enter a valid email address.");
      return; // Stop the function if the email is invalid
    }

    // Generate username
    const username = `${FirstName.charAt(0).toLowerCase()}${LastName.toLowerCase()}`;

    const newNode = { FirstName, LastName, Type, Email, username};
    SetAccounts((prevNodes) => [...prevNodes, newNode]);

    setFirstName("");
    setLastName("");
    setType();
    setEmail("");
  };

  const handleDeleteAccount = (usernameToDelete) => {
    SetAccounts((prevAccounts) =>
      prevAccounts.filter((account) => account.username !== usernameToDelete)
    );
  };
  return (
    <div>
      <div className="header">
        <div className="header-item blank">Operator</div>
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
        </div>
        <div className="second-item middle">
          <h1>Network Display</h1>
          <RingNetwork nodes={CurrNodes} 
          deleteNode={handleDeleteNode}
          showDetails={handleShowDetails}/>
        </div>
        <div className="second-item right">
          <h2>Dialogue Space</h2>
          {selectedAction === "node-details" && selectedNodeDetails && (
            <div className="node-details">
              <h3>Node Details</h3>
              <p><strong>Node ID:</strong> {selectedNodeDetails.id}</p>
              <p><strong>Left Neighbor:</strong> {selectedNodeDetails.leftNeighbor}</p>
              <p><strong>Right Neighbor:</strong> {selectedNodeDetails.rightNeighbor}</p>
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
                onClick={() => navigate("/ArchivedStore")} // Adjust the route as needed
              >
                Archive Messages
              </button>
              </div>
              <p><strong>Status:</strong> Active</p>
              <button onClick={() => handleDeleteNode(selectedNodeDetails.id)}>Delete</button>
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
                    value={nodeId}
                    onChange={(e) => setNodeId(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Left Neighbor:</label>
                  <input
                    type="text"
                    value={leftNeighbor}
                    onChange={(e) => setLeftNeighbor(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Right Neighbor:</label>
                  <input
                    type="text"
                    value={rightNeighbor}
                    onChange={(e) => setRightNeighbor(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Inbox Size:</label>
                  <input
                    type="number"
                    value={inboxSize}
                    onChange={(e) => {
                      const value = parseInt(e.target.value);
                      if (value >= 0) {
                        setInboxSize(value);
                      }
                    }}
                    min="0"
                    required
                  />
                </div>
                <button type="submit">Create</button>
              </form>
            </div>
          )}
          {selectedAction === "delete-node" && (
            <div className="delete-node-form">
              <h3>delete a Node</h3>
              <form onSubmit={handleDeleteNode}>
                <div>
                  <label>Node ID:</label>
                  <input
                    type="text"
                    value={DelNodeId}
                    onChange={(e) => setDelNodeId(e.target.value)}
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
                    value={FirstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Last Name:</label>
                  <input
                    type="text"
                    value={LastName}
                    onChange={(e) => setLastName(e.target.value)}
                    required
                  />
                </div>
                <div>
                  <label>Type:</label>
                  <select
                    id="Choice-Type"
                    value={Type}
                    onChange={(e) => setType(e.target.value)}
                  >
                    <option value="First-Action">Adminstrator</option>
                    <option value="Second-Action">Operator</option>
                  </select>
                </div>
                <div>
                  <label>Email:</label>
                  <input
                    type="text"
                    value={Email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <button type="submit">Create</button>
              </form>
            </div>
          )}

          {selectedAction === "crdl-action2" && (
            <div className="delete-account-form">
              <h3>Delete A Account</h3>

              <div>
                {Accounts.length > 0 ? (
                  Accounts.map((account) => (
                    <div key={account.username}>
                      <p>
                        {account.username} :{" "}
                        <button
                          onClick={() => handleDeleteAccount(account.username)}
                        >
                          Delete
                        </button>
                      </p>
                    </div>
                  ))
                ) : (
                  <p>No Accounts Avaible To Delete.</p>
                )}
              </div>
            </div>
          )}
        </div>
      </div>
      <div className="button-container"></div>
      <button
        className="system-buffer-link"
        onClick={() => navigate("/OperatorSystemBuffer")}
      >
        System Buffer
      </button>
    </div>
  );
};

export default OperatorMainPage;