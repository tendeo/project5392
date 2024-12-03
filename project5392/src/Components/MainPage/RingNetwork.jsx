import React, { useState } from "react";
import "./RingNetwork.css"; // CSS for styling
import Node from "../../Models/Node";

const RingNetwork = ({ nodes = [], deleteNode, showDetails }) => {

  console.log("inside of RingNetwork the nodes array is " + JSON.stringify(nodes));
  console.log('Is nodes an array?', Array.isArray(nodes));
  console.log(nodes[0].props);
  console.log(nodes[0].props.nodeID);

  const nodesCopy = nodes
  const nodeInstances = nodes.map(node => {
    console.log(node);  // Check if node.props exists and is in the correct structure
    if (node && node.props) {
      return new Node(
        node.props.nodeID,
        node.props.networkID,
        node.props.leftNeighborID,
        node.props.rightNeighborID,
        node.props.inboxID,
        node.props.storeID,
        node.props.status
      );
    }
    return null;
  }).filter(node => node !== null);


  // console.log("the array of nodes is " + nodeInstances);
  // console.log("inside of nodes the nodes array is " + JSON.stringify(nodeInstances));
  // console.log('Is nodeInstances an array?', Array.isArray(nodeInstances));
  


  const radius = 150; // Radius of the circle
  const centerX = 200; // Center X coordinate
  const centerY = 200; // Center Y coordinate

  // State to track the selected node
  const [selectedNode, setSelectedNode] = useState(null);

  // Calculate positions of each node based on its order in the ring
  const calculatePositions = (index, totalNodes) => {
    const angle = (index / totalNodes) * (2 * Math.PI); // Calculate angle for each node
    const x = centerX + radius * Math.cos(angle); // X position
    const y = centerY + radius * Math.sin(angle); // Y position
    return { x, y };
  };

  // Maintain a sorted order of nodes based on their connectivity
  const sortedNodes = [];
  let current = nodes.length > 0 ? nodes[0] : null; // Start with the first node in the list

  // Sort nodes based on their neighbors to form a proper ring
  while (current && sortedNodes.length < nodes.length) {
    sortedNodes.push(current);
    current = nodes.find((node) => node.nodeID === current.rightNeighbor);
  }

  return (
    <div className="network-container">
      <svg width="400" height="400">
        {/* Draw nodes in their calculated positions */}
        {sortedNodes.map((node, index) => {
          const { x, y } = calculatePositions(index, sortedNodes.length);
          return (
            <g
              key={node.nodeID}
              transform={`translate(${x}, ${y})`}
              onClick={() => setSelectedNode(node)} // Set the clicked node as selected
              style={{ cursor: "pointer" }} // Change cursor to pointer on hover
            >
              <circle r="20" fill="blue" />
              <text
                x="0"
                y="0"
                textAnchor="middle"
                dy=".3em"
                fill="white"
                fontSize="12px"
              >
                {node.nodeID}
              </text>
            </g>
          );
        })}

        {/* Draw lines between nodes to show the ring connections */}
        {sortedNodes.map((node, index) => {
          const { x, y } = calculatePositions(index, sortedNodes.length);
          const nextNode = sortedNodes[(index + 1) % sortedNodes.length];
          const { x: nextX, y: nextY } = calculatePositions(
            (index + 1) % sortedNodes.length,
            sortedNodes.length
          );

          return (
            <line
              key={`${node.nodeID}-${nextNode.nodeID}`}
              x1={x}
              y1={y}
              x2={nextX}
              y2={nextY}
              stroke="#ccc"
              strokeWidth="2"
            />
          );
        })}
      </svg>

      {/* Show buttons when a node is selected */}
      {selectedNode && (
        <div
          className="node-popup"
          style={{
            position: "absolute",
            top: `${selectedNode.y + centerY + 50}px`,
            left: `${selectedNode.x + centerX}px`,
            transform: "translate(-50%, -50%)",
            padding: "10px",
            border: "1px solid #ccc",
            background: "#fff",
            zIndex: 10,
          }}
        >
          <h3>Node ID: {selectedNode.nodeID}</h3>
          <button
            className="details-button"
            onClick={() => showDetails(selectedNode)}
          >
            Show Details
          </button>
          <button
            className="delete-button"
            onClick={() => {
              console.log("Deleting Node ID:", selectedNode.nodeID); // Log the ID
              deleteNode(selectedNode);
              setSelectedNode(null);
            }}
          >
            Delete Node
          </button>
          <button className="close-button" onClick={() => setSelectedNode(null)}>
            Close
          </button>
        </div>
      )}
    </div>
  );
};

export default RingNetwork;
