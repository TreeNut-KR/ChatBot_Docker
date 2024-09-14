import React from "react";
import './home.css';
import Login from "../../src/Component/Login"
import Text from "../Component/Text/Text";
import Chatting from "../Component/Chatting/Chatting";

export default function Home()
{
    return( 
        <div className="home">
            <Text/>
            <Chatting/>
        </div>
    )
}
