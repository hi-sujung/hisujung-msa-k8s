import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/CreatePortfolio.css';
import { useAuth } from './../utils/AuthContext';

const portfolioUrl = process.env.REACT_APP_SPRING_GATEWAY_PORTFOLIO_URL;

export default function CreatePortfolio() {
  const [portfolioData, setPortfolio] = useState({});
  const [editedTitle, setEditedTitle] = useState('');
  const [editedSubTitle, setEditedSubTitle] = useState('');
  const [editedContent, setEditedContent] = useState('');
  // const { user, token } = useAuth();
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const CreatePortfolioData = async (updatedData) => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
  
    try {
      const response = await axios.post(
        // `${portfolioUrl}new`,
        `/hisujung/portfolio/auth/new`,
        updatedData,
        { headers }
      );
  
      if (response.status === 200) {
        console.log('포트폴리오 생성 완료');
        alert('포트폴리오가 생성되었습니다.');
        handleToPortfolioList();
      } else {
        console.error('포트폴리오 생성 실패:', response.status);
      }
    } catch (error) {
      console.error('포트폴리오 생성 에러:', error);
    }
  };

  const handleCreateButton = async () => {
    if (!editedTitle) {
      alert('포트폴리오 제목을 입력하세요');
      return;
    }
    const updatedButton = {
      title: editedTitle,       // 사용자가 편집한 제목
      urlLink: editedSubTitle, // 사용자가 편집한 부제목
      description: editedContent,   // 사용자가 편집한 내용
    };
  
    try {
      await CreatePortfolioData(updatedButton); // 서버에 수정된 데이터를 저장
    } catch (error) {
      console.error('포트폴리오 생성 에러:', error);
    }
  };

  const handleHomePress = () => {
    navigate('/');
  };

  const handleToPortfolioList = () => {
    navigate('/portfolioList');
  };

  return (
    <div className="container">
      <div className="header">
        <button onClick={handleHomePress} className="homeButton">
          <i className="fas fa-home"></i>
        </button>
        <h1 className="headerTitle">포트폴리오 생성</h1>
      </div>

      <div className="main">
        <label className="infoLabel">포트폴리오 제목</label>
        <input
          className="infoInput"
          value={editedTitle}
          onChange={(e) => setEditedTitle(e.target.value)}
        />
        <label className="infoLabel">포트폴리오 링크</label>
        <input
          className="infoInput"
          value={editedSubTitle}
          onChange={(e) => setEditedSubTitle(e.target.value)}
        />
        <label className="infoLabel">내용</label>
        <textarea
          className="bigInfoInput"
          value={editedContent}
          onChange={(e) => setEditedContent(e.target.value)}
        />
        <div className="saveButton">
          <button onClick={handleCreateButton} className="saveButtonText">생성</button>
        </div>
      </div>
    </div>
  );
}
