// src/components/SystemBuffer.js
import React from 'react';
import './SystemBuffer.css'; // Ensure you have a CSS file for styling

const SystemBuffer = () => {
  return (
    <div className="system-buffer-container">
      <h1 className="system-buffer-title">System Buffer</h1>
      <textarea
        className="system-buffer-textbox"
        rows="10"
        cols="100"
        placeholder="Display your big text here..."
      />
      <div className="undelivered-messages">
        <h2>Undelivered Messages</h2>
        {Array.from({ length: 5 }, (_, index) => (
          <textarea
            key={index}
            className="undelivered-message-textbox"
            rows="3"
            cols="80"
            placeholder={`Undelivered Message ${index + 1}`}
          />
        ))}
      </div>
    </div>
  );
};

export default SystemBuffer;
