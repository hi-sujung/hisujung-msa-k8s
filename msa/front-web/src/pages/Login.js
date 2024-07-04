import React, { useState } from 'react';
import axios from 'axios';
import { useAuth } from './../utils/AuthContext';
import { useNavigate } from 'react-router-dom';
import '../styles/Login.css'; 

function Login() {
  const { login } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorText, setShowErrorText] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const memberUrl = process.env.REACT_APP_MEMBER_API_URL;
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post(`${memberUrl}login`, {
        email: email,
        password: password
      });

      if (response.status === 200) {
        console.log(response.data.token);
        setShowSuccessMessage(true);
        setShowErrorText(false);
        setErrorMessage('');
        const token = response.data.token;
        const userInfo = { id: response.data.userId, name: response.data.username, email: response.data.email };

        login(token, userInfo);
        localStorage.setItem('token', token); // 토큰 저장

        console.log(token);

        console.log('login successful');

        navigate('/'); 
      } else {
        setShowSuccessMessage(false);
        setShowErrorText(true);
      }
    } catch (error) {
      console.error('Error logging in:', error);
      setErrorMessage('로그인 중에 오류가 발생했습니다.');
      setShowSuccessMessage(false);
      setShowErrorText(true);
    }
  };

  return (
    <div className="linear-gradient">
      <div className="container">
        <h1 className="title">안녕, 수정이</h1>
        <input
          className="input"
          placeholder="아이디"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          className="input"
          placeholder="비밀번호"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="button" onClick={handleLogin}>
          로그인
        </button>
        {showSuccessMessage && (
          <span className="successMessage">로그인 성공</span>
        )}
        {showErrorText && (
          <span className="errorMessage">{errorMessage}</span>
        )}
        <button onClick={() => navigate('/email')} className="signupText">
          계정이 없으신가요? <span className="signupLink">회원가입</span>
        </button>
      </div>
    </div>
  );
}

export default Login;