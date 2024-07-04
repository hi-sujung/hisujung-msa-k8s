import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/ActivityList.css';

const activityUrl = process.env.REACT_APP_ACTIVITY_API_URL;

function ActivityList() {
  const [activityList, setActivityList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchActivityList = async () => {
      try {
        // const response = await axios.get(activityUrl);
        const response = await axios.get('/hisujung/notice/externalact/');
        
        if (response.status === 200) {
          setActivityList(response.data);
        }
      } catch (err) {
        console.log('Error fetching activity data:', err);
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchActivityList();
  }, [activityUrl]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  const handleActivityPress = (activityId) => {
    navigate(`/activity/${activityId}`);
  };

  return (
    <div className="container">
      <h1>대외활동</h1>
      <div className="main">
        <div className="activityList">
          {activityList.map((activity) => (
            <div key={activity.id} className="activityItem" onClick={() => handleActivityPress(activity.id)}>
              <span className="activityItemTitle">{activity.title}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default ActivityList;
