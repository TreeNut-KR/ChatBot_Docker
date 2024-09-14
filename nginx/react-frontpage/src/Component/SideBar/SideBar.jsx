import React from "react";
import './sidebar.css';
import { useNavigate } from 'react-router-dom';

export default function SideBar() { // props 추가

  return (
    <div className="sidebar">
      <div className="sidebarWrapper">
          <div className="SidebarLogo">
            <div className="SidebarLogoText">TreeNut</div>
          </div>
        <div className="sidebarListItem">
          <div className="SidebarText">
            Home
          </div>    
        </div>

        <div className="sidebarListItem">
          <div className="SidebarText">
            Character Chat
            </div>
        </div>

        <div className="sidebarListItem">
          <div className="SidebarText">
            Character Create
          </div>
        </div>
      </div>
    </div>
  )
}
