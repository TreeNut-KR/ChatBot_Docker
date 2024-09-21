import React from "react";
import './text.css';

const Text = ({ messages }) => {
    return (
        <div className="Text">
            {messages.map((msg, index) => (
                <div key={index} className={`message ${msg.type}`}>
                    {msg.text}
                </div>
            ))}
        </div>
    );
}

export default Text;
