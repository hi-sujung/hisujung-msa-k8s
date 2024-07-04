import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AiFillHeart, AiOutlineHeart, AiFillHome } from 'react-icons/ai';
import { useAuth } from './../utils/AuthContext';
import '../styles/Activity.css';

const authNoticeUrl = '/hisujung/notice/univactivity/auth/';

export default function Notice() {
  const [initialLikedState, setInitialLikedState] = useState(false);
  const [heartFilled, setHeartFilled] = useState('');
  const [attendFilled, setAttendFilled] = useState('');
  const { activityId } = useParams();
  const [activityData, setActivityData] = useState({});
  const [recActivityData, setRecActivityData] = useState([]);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    fetchActivityDetail();
    fetchRecActivityDetail();
  }, []);

  useEffect(() => {
    if (activityData.isLiked === 1) {
      setHeartFilled(true);
    }
  }, [activityData]);

  const fetchActivityDetail = async () => {
    const headers = {
        Authorization: `Bearer ${token}`
    };

    try {
      const response = await axios.get(`${authNoticeUrl}id?actId=${activityId}`, { headers });
      if (response.status === 200) {
        const data = response.data;
        setActivityData(data);
        setHeartFilled(data.isLiked === 1);
        setAttendFilled(data.participated === 1);
      }
    } catch (error) {
      console.error('Error fetching activity detail:', error);
    }
  };

  const handleNoticePress = (activityId) => {
    navigate(`/notice/${activityId}`);
  };

  const toggleHeart = async () => {
    const headers = {
      Authorization: `Bearer ${token}`
    };

    try {
      if (heartFilled === true) { 
        const response = await axios.delete(`${authNoticeUrl}like-cancel?id=${activityId}`, { headers });
        if (response.status === 200) {
          setHeartFilled(false);
        }
      } else {
        const response = await axios.post(`${authNoticeUrl}like?actId=${activityId}`, null, { headers });
        if (response.status === 200) {
          setHeartFilled(true);
          console.log("좋아요 완료:" + response.data)
        }
      }
    } catch (error) {
      console.error('Error toggling like:', error);
    }
  };

  const handleReplace = () => {
    if (activityData && activityData.content) {
      return activityData.content.replaceAll('\\n', "\n");
    } else {
      return '';
    }
  };

  const formattedContent = handleReplace();

  const fetchRecActivityDetail = async () => {
    try {
      const response = await axios.get(`/hisujung/recommend/univ?activity_name=${activityId}`);
      if (response.status === 200) {
        setRecActivityData(response.data);
      }
    } catch (error) {
      console.error('Error fetching recommended activity detail:', error);
    }
  };

  const handleActListPress = () => {
    navigate('/actList');
  };

  const handleActivityPress = (activityId) => {
    navigate(`/notice/${activityId}`);
    window.location.reload(); // 페이지 새로고침
  };

  const handleHomePress = () => {
    navigate('/');
  };

  return (
    <div className="container">
      <div className="linearGradient">
        <div className="header">
          <button onClick={handleHomePress} className="homeButton">
            <AiFillHome />
          </button>
          <button onClick={handleActListPress}>
            게시물 목록
          </button>
        </div>
      </div>

      <div className="nav">
        <div className="navContent">
          <button className="navButton">전체</button>
          <button className="navButton">기획</button>
          <button className="navButton">아이디어</button>
          <button className="navButton">브랜드/네이밍</button>
          <button className="navButton">광고/마케팅</button>
          <button className="navButton">사진</button>
          <button className="navButton">개발/프로그래밍</button>
        </div>
      </div>

      <div className="main">
        <div className="activityList">
          <div className="activityItem">
            <div className="activityDetails">
              <span className="activityCategory">공지사항</span>
            </div>
            <div>
              <h1 className="activityItemTitle">{activityData.title}</h1>
              <p className="activitySubTitle">{activityData.link}</p>
              <p className="activityDescription">{formattedContent}</p>
            </div>
          </div>
        </div>

        <div className="buttonContainer">
          <button className="heartButton" onClick={toggleHeart}>
            {heartFilled ? <AiFillHeart color="red" /> : <AiOutlineHeart />}
          </button>
        </div>
        <div className="recommended">
          <h2 className="recommendedTitle">추천 게시물</h2>
          {recActivityData.map(item => (
            <div className="recommendedItem" key={item.univ_activity_id} onClick={() => handleActivityPress(item.univ_activity_id)}>
              <span className="recommendedItemTitle">{item.title}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
