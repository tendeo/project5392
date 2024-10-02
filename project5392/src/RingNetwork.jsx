import React from 'react';
import './RingNetwork.css'; // CSS for styling

const RingNetwork = ({ nodes }) => {

    const radius = 150; // Radius of the circle
    const centerX = 200; // Center X coordinate
    const centerY = 200; // Center Y coordinate

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
        <svg width="400" height="400">
            {/* Draw nodes in their calculated positions */}
            {sortedNodes.map((node, index) => {
                const { x, y } = calculatePositions(index, sortedNodes.length);
                return (
                    <g key={node.id} transform={`translate(${x}, ${y})`}>
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
    );
};

export default RingNetwork;