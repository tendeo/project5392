import React, { useState } from "react";
import "./MainPage.css";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";

import Messagecreated from "../Parts/MessageCreated";

import RingNetworks from "../../RingNetwork";

const Mainpage = () => {
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

  const [CurrNodes, SetCurNodes] = useState([
    { id: "N1", leftNeighbor: "N3", rightNeighbor: "N2", inboxSize: 3 },
    { id: "N2", leftNeighbor: "N1", rightNeighbor: "N3", inboxSize: 3 },
    { id: "N3", leftNeighbor: "N2", rightNeighbor: "N1", inboxSize: 3 },
  ]);
  const [nodeId, setNodeId] = useState("");
  const [leftNeighbor, setLeftNeighbor] = useState("");
  const [rightNeighbor, setRightNeighbor] = useState("");
  const [inboxSize, setInboxSize] = useState("");

  const [DelNodeId, setDelNodeId] = useState("");

  const location = useLocation();
  const username = location.state?.username || "Guest";
  const navigate = useNavigate();

  const [messageSent, setMessageSent] = useState(false);
  const [sentMessageDetails, setSentMessageDetails] = useState({});

  const [showSendMessage, setShowSendMessage] = useState(true);

  const handleSendMessage = (e) => {
    e.preventDefault();

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

  const addNode = (newNode) => {
    SetCurNodes((prevNodes) => [...prevNodes, newNode]);
  };

  const handleDeleteNode = (e) => {
    e.preventDefault();
    SetCurNodes((prevNodes) =>
      prevNodes.filter((node) => node.id !== DelNodeId)
    );
    setDelNodeId("");
  };

  const handleCreateNode = (e) => {
    e.preventDefault();

    // Create a new node object
    const newNode = { id: nodeId, leftNeighbor, rightNeighbor, inboxSize };

    // Update neighbors' pointers and maintain circular order
    SetCurNodes((prevNodes) => {
      // Create a new array to maintain the correct circular order
      const updatedNodes = [];

      for (let node of prevNodes) {
        updatedNodes.push(node);

        // Insert the new node between leftNeighbor and rightNeighbor
        if (node.id === leftNeighbor) {
          updatedNodes.push(newNode);
        }
      }

      // Update the neighbors of the new node and its neighbors in the updated list
      return updatedNodes.map((node) => {
        if (node.id === leftNeighbor) {
          return { ...node, rightNeighbor: nodeId };
        }
        if (node.id === rightNeighbor) {
          return { ...node, leftNeighbor: nodeId };
        }
        if (node.id === nodeId) {
          return { ...node, leftNeighbor, rightNeighbor };
        }
        return node;
      });
    });

    // Clear the input fields
    setNodeId("");
    setLeftNeighbor("");
    setRightNeighbor("");
    setInboxSize("");
  };

  const handleCreateAccount = (e) => {
    e.preventDefault();
    const newNode = { FirstName, LastName, Type, Email };
    SetAccounts((prevNodes) => [...prevNodes, newNode]);

    setFirstName("");
    setLastName("");
    setType();
    setEmail("");
  };

  const handleDeleteAccount = (emailToDelete) => {
    SetAccounts((prevAccounts) =>
      prevAccounts.filter((account) => account.Email !== emailToDelete)
    );
  };
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
            <label htmlFor="node-actions">Node Actions:</label>
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
            <label htmlFor="create-delete-node">Create or Delete:</label>
            <select
              id="create-delete"
              value={selectedAction}
              onChange={(e) => setSelectedAction(e.target.value)}
            >
              <option value="crdl-action0">--</option>
              <option value="crdl-action1">Create a Account</option>
              <option value="crdl-action2">Delete A Account</option>
            </select>
          </div>
        </div>
        <div className="second-item middle">
          <h1>Network Display</h1>
          <RingNetworks nodes={CurrNodes} />
        </div>
        <div className="second-item right">
          <h2>Dialogue Space</h2>
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
                      const value = e.target.value;
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

                <div>
                  <label>Direction:</label>
                  <input
                    type="text"
                    value={Direction}
                    onChange={(e) => setDirection(e.target.value)}
                    required
                  />
                </div>
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
              <h3>Node Status</h3>

              <div>
                {CurrNodes.length > 0 ? (
                  CurrNodes.map((node) => (
                    <div key={node.id}>
                      <p>{node.id} : Active</p>
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
                    <div key={account.Email}>
                      <p>
                        {account.Email} :{" "}
                        <button
                          onClick={() => handleDeleteAccount(account.Email)}
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
