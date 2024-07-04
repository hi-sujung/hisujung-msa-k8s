import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AiOutlineFundProjectionScreen, AiOutlineNotification, AiOutlineRobot } from 'react-icons/ai';
import '../styles/Home.css';
import { useAuth } from './../utils/AuthContext';

function Home() {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');
  const [userName, setUserName] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    fetchUserInfo();
  }, []);

  const fetchUserInfo = async () => {
    if (token) {
      setUserName('user.userName'); 
      setLoggedIn(true);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token'); // 토큰 삭제
    alert('로그아웃되었습니다.');
    setLoggedIn(false);
    navigate('/');
  };

  const handleLogin = () => {
    navigate('/login'); 
  };

  return (
    <div className="homeLinearGradient">
      <div className="homeContainer">
        <div className="homeOuterBox">
          <h1 className="homeTitle">
            {/* {userName ? `Hi, ${userName} 수정이🔮` : 'Hi, 수정이🔮'} */}
            Hi, 수정이🔮
          </h1>
          <div className="homeRow">
            <Link to="/likedNotice" className="homeButton homeSecondButton">
              <AiOutlineNotification size={50} className="homeButtonIcon" />
              <p className="homeButtonText">찜한 활동</p>
            </Link>
            <Link to="/attendActList" className="homeButton homeThirdButton">
              <AiOutlineNotification size={50} className="homeButtonIcon" />
              <p className="homeButtonText homeThirdButtonText">참여한<br />대외활동</p>
            </Link>
            <Link to="/portfolioList" className="homeButton homeFirstButton">
              <AiOutlineFundProjectionScreen size={50} className="homeButtonIcon" />
              <p className="homeButtonText">포트폴리오</p>
            </Link>
          </div>
        </div>
        <div className="homeChatBotButton">
          <AiOutlineRobot size={20} />
          {loggedIn ? (
            <p className="homeChatBotButtonText" onClick={handleLogout}>로그아웃</p>
          ) : (
            <p className="homeChatBotButtonText" onClick={handleLogin}>로그인</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default Home;
