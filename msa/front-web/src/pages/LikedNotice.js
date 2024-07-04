import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { AiFillHeart, AiOutlineHeart, AiFillHome } from 'react-icons/ai';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../utils/AuthContext'; // 이 부분은 그대로 유지
import '../styles/LikedNotice.css';

const noticeUrl = '/hisujung/notice/';

export default function LikedNotice() {
    const [uniLikeList, setUniLikeList] = useState([]);
    const [extLikeList, setExtLikeList] = useState([]);
    // const { user, token } = useAuth(); // 현재 로그인한 유저의 user, token
    const navigate = useNavigate();
    const token = localStorage.getItem('token');

    useEffect(() => {
        const fetchData = async () => {
            await fetchUniLikeData();
            await fetchExtLikeData();
        };

        fetchData();
    }, []);

    const fetchUniLikeData = async () => {
        const headers = {
            Authorization: `Bearer ${token}`
        };

        try {
            const response = await axios.get(`${noticeUrl}univactivity/auth/like-list`, { headers });
            if (response.status === 200) {
                console.log('Uni like list data:', response.data); // 콘솔 로그 추가
                setUniLikeList(response.data);
            }
        } catch (error) {
            console.error('Error fetching uniLike list data:', error);
        }
    };

    const fetchExtLikeData = async () => {
        const headers = {
            Authorization: `Bearer ${token}`
        };

        try {
            const response = await axios.get(`${noticeUrl}externalact/auth/like-list`, { headers });
            if (response.status === 200) {
                console.log('Ext like list data:', response.data); // 콘솔 로그 추가
                setExtLikeList(response.data);
            }
        } catch (error) {
            console.error('Error fetching external like list data:', error);
        }
    };

    const handleHomePress = () => {
        navigate('/');
    };

    const handleActivityPress = (activityId) => {
        navigate(`/activity/${activityId}`);
    };

    const handleNoticePress = (activityId) => {
        navigate(`/notice/${activityId}`);
    };

    return (
        <div style={styles.container}>
            <div style={styles.linearGradient}>
                <div style={styles.header}>
                    <button onClick={handleHomePress} className="homeButton">
                        <AiFillHome />
                    </button>
                    <h1 style={styles.headerTitle}>찜한 활동</h1>
                </div>
            </div>
            <div style={styles.main}>
                <ul style={styles.activityList}>
                    {[...uniLikeList, ...extLikeList].map((item) => (
                        <li key={item.id} style={styles.activityItem}>
                            <button
                                onClick={() => item.postDepartment
                                    ? handleNoticePress(item.id)
                                    : handleActivityPress(item.id)
                                }
                                style={styles.button}
                            >
                                <div style={styles.activityDetails}>
                                    <span style={styles.activityCategory}>
                                        {item.postDepartment ? '공지사항' : '대외활동'}
                                    </span>
                                </div>
                                <span style={styles.activityItemTitle}>{item.title}</span>
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

const styles = {
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100vh',
    },
    linearGradient: {
        background: 'linear-gradient(135deg, #E2D0F8 0%, #A0BFE0 100%)',
        borderBottom: '1px solid white',
    },
    header: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        padding: '10px',
        marginTop: '20px',
    },
    homeButton: {
        backgroundColor: 'white',
        borderRadius: '10px',
        padding: '5px 10px',
        border: 'none',
        cursor: 'pointer',
    },
    headerTitle: {
        color: 'black',
        fontSize: '18px',
        fontWeight: 'bold',
        marginLeft: '10px',
    },
    main: {
        flex: 1,
        padding: '20px',
        backgroundColor: 'white',
    },
    activityList: {
        display: 'flex',
        flexDirection: 'column',
        listStyleType: 'none',
        padding: 0,
    },
    activityItem: {
        backgroundColor: 'rgba(226, 208, 248, 0.3)',
        borderRadius: '10px',
        padding: '15px 20px',
        marginBottom: '10px',
        border: 'none',
        cursor: 'pointer',
        textAlign: 'left',
    },
    button: {
        width: '100%',
        background: 'none',
        border: 'none',
        cursor: 'pointer',
        textAlign: 'left',
        padding: 0,
    },
    activityDetails: {
        display: 'flex',
        justifyContent: 'space-between',
        marginBottom: '5px',
    },
    activityCategory: {
        fontWeight: 'bold',
        color: 'rgba(74, 85, 162, 1)',
    },
    activityItemTitle: {
        fontWeight: 'bold',
        fontSize: '16px',
    },
};
