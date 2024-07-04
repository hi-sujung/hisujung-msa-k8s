import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from './../utils/AuthContext';
import '../styles/MyPortfolio.css';

export default function MyPortfolio() {
  const { portfolioId } = useParams();
  const navigate = useNavigate();

  const [portfolioData, setPortfolioData] = useState({});
  const [isEditMode, setIsEditMode] = useState(false);
  const [editedTitle, setEditedTitle] = useState('');
  const [editedSubTitle, setEditedSubTitle] = useState('');
  const [editedContent, setEditedContent] = useState('');

  useEffect(() => {
    fetchPortfolioData();
  }, []);

  const fetchPortfolioData = async () => {
    try {
      const response = await axios.get(`/hisujung/portfolio/id?id=${portfolioId}`);
      if (response.status === 200) {
        setPortfolioData(response.data.data);
        setEditedTitle(response.data.data.title);
        setEditedSubTitle(response.data.data.urlLink);
        setEditedContent(response.data.data.description);
      }
    } catch (error) {
      console.error('Error fetching portfolio data:', error);
    }
  };

  const handleSaveButton = async () => {
    const updatedData = {
      title: editedTitle,
      urlLink: editedSubTitle,
      description: editedContent,
    };

    try {
      const response = await axios.post(
        `/hisujung/portfolio/update/id?id=${portfolioId}`,
        updatedData
      );

      if (response.status === 200) {
        setPortfolioData(updatedData);
        setIsEditMode(false);
        fetchPortfolioData();
      } else {
        console.error('Failed to update portfolio:', response.status);
      }
    } catch (error) {
      console.error('Error updating portfolio:', error);
    }
  };

  const handleDelete = async (portfolioId) => {
    try {
      const response = await axios.delete(`/hisujung/portfolio/portfolio/id?id=${portfolioId}`);
  
      if (response.status === 200) {
        console.log('포트폴리오 삭제 완료');
        handleToPortfolioList();
      } else {
        console.error('Failed to delete portfolio:', response.status);
      }
    } catch (error) {
      console.error('Error deleting portfolio:', error);
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
      <header className="header">
        <a href="/" className="home-button">Home</a>
        <h1>포트폴리오 관리</h1>
      </header>
      <main className="main">
        {isEditMode ? (
          <input
            type="text"
            value={editedTitle}
            onChange={(e) => setEditedTitle(e.target.value)}
          />
        ) : (
          <h2>{portfolioData.title}</h2>
        )}
        <button onClick={() => setIsEditMode(!isEditMode)}>
          {isEditMode ? '취소' : '수정'}
        </button>
        {isEditMode ? (
          <>
            <input
              type="text"
              value={editedSubTitle}
              onChange={(e) => setEditedSubTitle(e.target.value)}
            />
            <textarea
              value={editedContent}
              onChange={(e) => setEditedContent(e.target.value)}
            />
            <button onClick={handleSaveButton}>저장하기</button>
          </>
        ) : (
          <>
            <p>{portfolioData.urlLink}</p>
            <p>{portfolioData.description}</p>
          </>
        )}
        <button onClick={() => handleDelete(portfolioData.id)}>삭제</button>
      </main>
    </div>
  );
}
