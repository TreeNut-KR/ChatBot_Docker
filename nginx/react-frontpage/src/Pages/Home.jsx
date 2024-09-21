import React, { useState } from "react";
import './home.css';
import Login from "../../src/Component/Login";
import Text from "../Component/Text/Text";
import Chatting from "../Component/Chatting/Chatting";

export default function Home() {
    const [messages, setMessages] = useState([
        { text: "안녕하세요, 반갑습니다.", type: "client" },
    ]);

    const handleSendMessage = (message) => {
        setMessages((prevMessages) => [
            ...prevMessages,
            { text: message, type: "user" }
        ]);
    };

    return( 
        <div className="home">
            <Text messages={messages} />
            <Chatting onSend={handleSendMessage} />
        </div>
    );
}
