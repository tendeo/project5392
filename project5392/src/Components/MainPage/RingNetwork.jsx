import React, { useState, useEffect } from "react";
import "./RingNetwork.css"; // CSS for styling



const RingNetwork = ({ nodes, deleteNode, showDetails }) => {

  console.log("inside of RingNetwork the nodes array is " + nodes);
  
  useEffect(() => { console.log("RENDERING RING NETWORK")}, []);

    // Step 2: Find the starting node (first node)
    console.log("before start node nodes is ", nodes);
    console.log("start node should be ", nodes[0]);
  //const startNode = nodes.find(node => node.leftNeighbor === null); // Assuming the first node has no left neighbor
  const startNode = nodes.length > 0 ? nodes[0] : null; // Start with the first node in the list
  
  console.log("start node " + JSON.stringify(startNode));

  // Step 3: Sort nodes based on their rightNeighbor link
  const sortedNodes = [];
  let currentNode = startNode;

  // Step 4: Traverse through the nodes based on rightNeighbor and form the ring
  while (currentNode) {

    sortedNodes.push(currentNode);

    // next node should be the node whose id is the current's right neighbor (clocl)
    const nextNode = nodes.find((node) => node.nodeID === currentNode.rightNeighborID);
    console.log("next node", nextNode)

    if (nextNode === startNode) break; // If we loop back to the start node, stop
    currentNode = nextNode;
  }

  console.log("sorted nodes size ", sortedNodes.length);

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

  return (
    <div className="network-container">
      <svg width="400" height="400">
        {/* Draw nodes in their calculated positions */}
        {sortedNodes.map((node, index) => {
          const { x, y } = calculatePositions(index, sortedNodes.length);
          console.log("node: " + JSON.stringify(node) + " x: " + x + " y: " + y);
          return (
            <g
              key={node.nodeID}
              transform={`translate(${x}, ${y})`}
              onClick={() => {
                setSelectedNode(node)
                console.log("node on click setting selected node ", node);
              }} // Set the clicked node as selected
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
            onClick={() => {
              console.log("selected node", selectedNode);
              showDetails(selectedNode);
              
            }}
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
