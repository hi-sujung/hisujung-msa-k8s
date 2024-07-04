import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/PortfolioList.css';
import { useNavigate } from 'react-router-dom';

export default function PortfolioList() {
  const [portfolioList, setPortfolioList] = useState([]);
  // const { user, token } = useAuth();
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    fetchPortfolioData();
  }, []);

  const fetchPortfolioData = async () => {
    const headers = {
      Authorization: `Bearer ${token}`
    };

    try {
      // const response = await axios.get(`${portfolioListUrl}portfoliolist`, { headers });
      const response = await axios.get(`/hisujung/portfolio/auth/portfoliolist`, { headers });
      if (response.status === 200) {
        setPortfolioList(response.data.data || []);  // Set portfolioList to an empty array if response.data.data is undefined
        console.log("포트폴리오 목록" + portfolioList);
        console.log("포트폴리오 id" + portfolioList);
      }
    } catch (error) {
      console.error('Error fetching portfolio data:', error);
      setPortfolioList([]);  // Set portfolioList to an empty array if there is an error
    }
  };

  const handlePortfolioPress = (portfolioId) => {
    navigate(`/myPortfolio/${portfolioId}`);
  };

  const handleHomePress = () => {
    navigate(`/`);
  };

  return (
    <div className="container">
      <header className="header" onClick={() => handleHomePress()}>
        <h1>나의 포트폴리오 목록</h1>
      </header>
      <main className="main">
        <a href="/createportfolio" className="nav-button-plus">추가</a>
        <ul className="portfolio-list">
          {portfolioList.map((item) => (
            <li key={item.id} className="portfolio-item" onClick={() => handlePortfolioPress(item.id)}>
              {/* <a href={`/myportfolio/${item.id}`}> */}
                <h2>{item.title}</h2>
                <p>{item.description}</p>
                <p>{item.urlLink}</p>
                <p>{item.createdDate}</p>
                <p>{item.modifiedDate}</p>
              
            </li>
          ))}
        </ul>
      </main>
    </div>
  );
}
