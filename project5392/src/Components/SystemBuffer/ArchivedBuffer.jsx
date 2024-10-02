import React, { useState } from 'react'; // Import useState
import './ArchivedBuffer.css'; // Ensure you have a CSS file for styling
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import Messagecreated from "../Parts/MessageCreated";

const ArchivedBuffer = () => {
    const [selectedMessage, setSelectedMessage] = useState(null);
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
    
    return (
      <div className="system-buffer-container">
        <button 
          onClick={() => navigate('/SystemBuffer')} // Change to your desired route
          className="back-button"
          style={{ position: 'absolute', top: '10px', left: '10px' }} // Simple inline style for positioning
        >
          Back
        </button>
        <h1 className="system-buffer-title">Archived System Buffer</h1>
        <div className="undelivered-messages">
          <h2>Undelivered Messages</h2>
          {Array.from({ length: 4 }, (_, index) => ( // Change 4 to your desired buffer size
            <button
              key={index}
              className="undelivered-message-button"
              onClick={() => handleButtonClick(index)}
            >
              Undelivered Message {index + 1}
            </button>
          ))}
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
  
  export default ArchivedBuffer;

