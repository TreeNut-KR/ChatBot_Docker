<<<<<<< Updated upstream
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
=======
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Pages/Home';
import SideBar from './Component/SideBar/SideBar';
import './App.css';
import Topbar from './Component/TopBar/TopBar';
import Login from './Component/Login';
import UserProfile from './Pages/UserProfile';

function App() {
  return (
    <Router>
      <div className="App">
        <Topbar />
        <div className="container">
          <SideBar />
          <div className="others">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} /> {/* 로그인 페이지 라우트 추가 */}
              <Route path="/userProfile" element={<UserProfile />} />
              {/* 다른 라우트 추가 가능 */}
            </Routes>
          </div>
        </div>
      </div>
    </Router>
>>>>>>> Stashed changes
  );
}

export default App;
