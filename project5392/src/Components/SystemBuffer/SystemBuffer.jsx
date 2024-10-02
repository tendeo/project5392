// src/components/SystemBuffer.js
import React, { useState } from 'react'; // Import useState
import './SystemBuffer.css'; // Ensure you have a CSS file for styling
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import Messagecreated from "../Parts/MessageCreated";

const SystemBuffer = () => {
  const [selectedMessage, setSelectedMessage] = useState(null);
  const [bufferSize, setBufferSize] = useState(4); // Initialize buffer size
  const navigate = useNavigate(); // Initialize useNavigate

  const handleButtonClick = (index) => {
    const messageDetails = {
      message: `Undelivered Message ${index + 1}`, // Sample content
      messageID: `Nx${index + 1}`,
      senderID: 'Ns',
      receiverID: 'Nr',

    };
    setSelectedMessage(messageDetails);
  };

  const increaseBufferSize = () => {
    setBufferSize(bufferSize + 1);
  };

  const decreaseBufferSize = () => {
    if (bufferSize > 1) { // Ensure at least one message remains
      setBufferSize(bufferSize - 1);
  };
  }
  
  
  return (
    <div className="system-buffer-container">
      <button 
        onClick={() => navigate('/MainPage')} // Change to your desired route
        className="back-button"
        style={{ position: 'absolute', top: '10px', left: '10px' }} // Simple inline style for positioning
      >
        Back
      </button>
      <h1 className="system-buffer-title">System Buffer</h1>
      <div className="undelivered-messages">
        <h2>Undelivered Messages</h2>
        {Array.from({ length: bufferSize }, (_, index) => (
          <button
            key={index}
            className="undelivered-message-button"
            onClick={() => handleButtonClick(index)}
          >
            Undelivered Message {index + 1}
            </button>
        ))}

        {/* New buttons for changing buffer size */}
        <div className="action-buttons" style={{ marginTop: '20px', display: 'flex', alignItems: 'center' }}>
        <div className="undelivered-message-button" style={{ cursor: 'default', flex: 1, textAlign: 'center' }}>
            System Buffer Size: {bufferSize}
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', marginLeft: '10px' }}>
            <button className="undelivered-message-button" onClick={increaseBufferSize}>+</button>
            <button className="undelivered-message-button" onClick={decreaseBufferSize}>-</button>
          </div>
          <button className="undelivered-message-button" style={{ marginTop: '20px' }} onClick={() => navigate('/ArchivedBuffer')}>
            Archive Messages
          </button>
        </div>        
      </div>
      {selectedMessage && (
        <Messagecreated 
          message={selectedMessage.message}
          messageID={selectedMessage.messageID}
          senderID={selectedMessage.senderID}
          receiverID={selectedMessage.receiverID}
        />
      )}
    </div>
  );
};

export default SystemBuffer;
