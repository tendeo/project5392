import React, { useState } from "react";
import "./RingNetwork.css"; // CSS for styling

const RingNetworks = ({ nodes, deleteNode, showDetails }) => {
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
    current = nodes.find((node) => node.id === current.rightNeighbor);
  }

  return (
    <div className="network-container">
      <svg width="400" height="400">
        {/* Draw nodes in their calculated positions */}
        {sortedNodes.map((node, index) => {
          const { x, y } = calculatePositions(index, sortedNodes.length);
          return (
            <g
              key={node.id}
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
                {node.id}
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
              key={`${node.id}-${nextNode.id}`}
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
          <h3>Node ID: {selectedNode.id}</h3>
          <button
            className="details-button"
            onClick={() => showDetails(selectedNode)}
          >
            Show Details
          </button>
          <button
            className="delete-button"
            onClick={() => {
              deleteNode(selectedNode.id);
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

export default RingNetworks;
