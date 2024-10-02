import React from "react";

const Messagecreated = ({ message, messageID, senderID, receiverID }) => {

    const getCurrentTimestamp = () => {
        const now = new Date();
        const options = { month: 'short', day: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false };
        const formattedDate = now.toLocaleString('en-US', options).replace(',', '').replace(/\//g, ':');
        return formattedDate;
    };

    const timestamp = getCurrentTimestamp();

    return (
        
        <div className="message-details">
            <h3>Message Details</h3>
            <p><strong>Message:</strong> {message}</p>
            <p><strong>Message ID:</strong> {messageID}</p>
            <p><strong>Sender ID:</strong> {senderID}</p>
            <p><strong>Receiver ID:</strong> {receiverID}</p>
            <p><strong>Status:</strong> Created</p>
            <p><strong>Created On: </strong>{timestamp} </p>
            <p><strong>Received On: </strong> ""</p>
        </div>
        

        
    );
};

export default Messagecreated;