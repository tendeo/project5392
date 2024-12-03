import React, { Component } from 'react';

class Node extends Component {
  // Constructor to initialize the node with default or provided values
  constructor(props) {
    super(props);
    this.state = {
      nodeID: props.nodeID || '',  // The unique node identifier
      networkID: props.networkID || '',  // The network identifier
      leftNeighborID: props.leftNeighborID || null,  // ID for left neighbor
      rightNeighborID: props.rightNeighborID || null,  // ID for right neighbor
      inboxID: props.inboxID || null,  // ID for inbox
      storeID: props.storeID || null,  // ID for store
      status: props.status !== undefined ? props.status : true,  // The status, default is true (active)
    };
  }

  // Methods to handle updating the node properties
  setNodeID = (nodeID) => {
    this.setState({ nodeID });
  }

  setNetworkID = (networkID) => {
    this.setState({ networkID });
  }

  setLeftNeighborID = (leftNeighborID) => {
    this.setState({ leftNeighborID });
  }

  setRightNeighborID = (rightNeighborID) => {
    this.setState({ rightNeighborID });
  }

  setInboxID = (inboxID) => {
    this.setState({ inboxID });
  }

  setStoreID = (storeID) => {
    this.setState({ storeID });
  }

  setStatus = (status) => {
    this.setState({ status });
  }

  // Method to display the current node information
  displayNodeInfo = () => {
    const { nodeID, networkID, leftNeighborID, rightNeighborID, inboxID, storeID, status } = this.state;
    return (
      <div>
        <p><strong>NodeID:</strong> {nodeID}</p>
        <p><strong>NetworkID:</strong> {networkID}</p>
        <p><strong>Left Neighbor ID:</strong> {leftNeighborID}</p>
        <p><strong>Right Neighbor ID:</strong> {rightNeighborID}</p>
        <p><strong>Inbox ID:</strong> {inboxID}</p>
        <p><strong>Store ID:</strong> {storeID}</p>
        <p><strong>Status:</strong> {status ? 'Active' : 'Inactive'}</p>
      </div>
    );
  }

  render() {
    return (
      <div>
        <h1>Node Information</h1>
        {this.displayNodeInfo()}
        <button onClick={() => this.setStatus(!this.state.status)}>Toggle Status</button>
        <button onClick={() => this.setNodeID('newNodeID')}>Set New Node ID</button>
        {/* You can add more buttons or inputs to change other properties */}
      </div>
    );
  }
}

export default Node;
