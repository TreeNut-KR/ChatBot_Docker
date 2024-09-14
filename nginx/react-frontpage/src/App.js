import React from 'react';
import Home from './Pages/Home';
import SideBar from './Component/SideBar/SideBar';
import  './App.css'
import Topbar from './Component/TopBar/TopBar';

function App() {
  return (
    <div className="App">
      <Topbar/>
      <div className="container">
        <SideBar/>
        <div className="others">
          <Home/>
        </div>
      </div>
    </div>
  );
}

export default App;
