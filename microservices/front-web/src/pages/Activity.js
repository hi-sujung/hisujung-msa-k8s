import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AiFillHeart, AiOutlineHeart, AiFillHome } from 'react-icons/ai';
import { useAuth } from './../utils/AuthContext';
import '../styles/Activity.css';

const authActivityUrl = '/hisujung/notice/externalact/auth/';

export default function Activity() {
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

  const fetchActivityDetail = async () => {
    const headers = {
      Authorization: `Bearer ${token}`
    };

    try {
      const response = await axios.get(`${authActivityUrl}id?id=${activityId}`, { headers });
      // const response = await axios.get(`/hisujung/notice/externalact/auth/id?id=${activityId}`, { headers });
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

  const toggleHeart = async () => {
    const headers = {
      Authorization: `Bearer ${token}`
    };

    try {
      if (heartFilled) {
        const response = await axios.delete(`${authActivityUrl}likecancel?id=${activityId}`,  { headers });
        // const response = await axios.delete(`/hisujung/notice/externalact/auth/likecancel?actId=${activityId}`,  { headers });
        if (response.status === 200) {
          setHeartFilled(false);
        }
      } else {
        const response = await axios.post(`${authActivityUrl}like?actId=${activityId}`, null, { headers });
        // const response = await axios.post(`/hisujung/notice/externalact/auth/like?actId=${activityId}`, null, { headers });
        if (response.status === 200) {
          setHeartFilled(true);
          console.log("좋아요 완료" + response.data);
        }
      }
    } catch (error) {
      console.error('Error toggling like:', error);
    }
  };

  const toggleAttend = async () => {
    const headers = {
      Authorization: `Bearer ${token}`
    };

    try {
      if (attendFilled) {
        const response = await axios.delete(`${authActivityUrl}check-cancel?id=${activityId}`, { headers });
        // const response = await axios.delete(`/hisujung/notice/externalact/auth/check-cancel?id=${activityId}`, { headers });
        if (response.status === 200) {
          setAttendFilled(false);
        }
      } else {
        const response = await axios.post(`${authActivityUrl}check?actId=${activityId}`, null, { headers });
        // const response = await axios.post(`/hisujung/notice/externalact/auth/check?actId=${activityId}`, null, { headers });
        if (response.status === 200) {
          setAttendFilled(true);
          console.log("참여 완료" + response.data);
        }
      }
    } catch (error) {
      console.error('Error toggling attendance:', error);
    }
  };

  const handleReplace = () => {
    if (activityData && activityData.content) {
      return activityData.content.replaceAll('\\n', "\n");
    } else {
      // console.log('activityData or content is undefined');
      return '';
    }
  };

  const formattedContent = handleReplace();

  const fetchRecActivityDetail = async () => {
    try {
      // const response = await axios.get(`${recNotice}external?activity_name=${activityId}`);
      const response = await axios.get(`/hisujung/recommend/external?activity_name=${activityId}`);
      if (response.status === 200) {
        setRecActivityData(response.data);
      }
    } catch (error) {
      console.error('Error fetching recommended activity detail:', error);
    }
  };

  const handleActListPress = () => {
    navigate('/activityList');
  };

  const handleActivityPress = (activityId) => {
    navigate(`/activity/${activityId}`);
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
              <span className="activityCategory">대외활동</span>
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
          <button
            className="attendButton"
            onClick={toggleAttend}
            style={{ backgroundColor: attendFilled ? "grey" : "rgba(153, 153, 255, 0.3)" }}
          >
            {attendFilled ? "참여 취소" : "참여"}
          </button>
        </div>

        <div className="recommended">
          <h2 className="recommendedTitle">추천 게시물</h2>
          {recActivityData.map((item, index) => (
            <div className="recommendedItem" key={`rec-${item.external_act_id}-${index}`} onClick={() => handleActivityPress(item.external_act_id)}>
              <span className="recommendedItemTitle">{item.title}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
