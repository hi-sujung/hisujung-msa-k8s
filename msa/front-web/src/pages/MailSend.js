import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/MailSend.css';

export default function MailSend() {
  const [email, setEmail] = useState("");
  const [verificationCode, setVerificationCode] = useState("");
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorText, setShowErrorText] = useState(false);
  const navigate = useNavigate();
  const [mailCode, setMailCode] = useState(null);

  const handleEmailSubmit = async () => {
    if (!email) {
      setShowErrorText(true);
      return;
    }
    try {
      const fullEmail = email + "@sungshin.ac.kr";

      const response = await axios.post(`/hisujung/member/mailSend`,
        {
          email: fullEmail
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );

      setShowSuccessMessage(true);
      setShowErrorText(false);

      console.log('이메일 전송 응답:', response.data);
      setMailCode(response.data);
    } catch (error) {
      console.error('이메일 전송 오류:', error);
      alert('오류', '이메일 전송에 실패했습니다. 다시 시도해주세요.');
    }
  };

  const handleVerificationSubmit = () => {
    if (String(mailCode) === String(verificationCode)) {
      setShowSuccessMessage(true);
      setShowErrorText(false);
      navigate('/register');
    } else {
      console.log("인증실패");
      setShowSuccessMessage(false);
      setShowErrorText(true);
    }
  };

  const handleBackPress = () => {
    navigate('/login');
  };

  return (
    <div className="linearGradient">
      <div className="container">
        <div className="headingContainer">
          <button className="backButton" onClick={handleBackPress}>
            &larr;
          </button>
          <h1 className="title">이메일 인증</h1>
        </div>
        <p className="subtitle">인증번호 입력</p>

        <div className="inputContainer">
          <input
            className="input"
            placeholder="이메일"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <span className="emailText">@sungshin.ac.kr</span>
          <button className="submitButton" onClick={handleEmailSubmit}>
            전송
          </button>
        </div>

        {showSuccessMessage && (
          <p className="successText">인증 메일이 전송되었습니다. 메일함을 확인해주세요!</p>
        )}

        {showErrorText && <p className="errorText">올바르지 않은 형식입니다.</p>}

        <div className="inputContainer">
          <input
            className="input verificationInput"
            placeholder="인증번호"
            value={verificationCode}
            onChange={(e) => setVerificationCode(e.target.value)}
          />
        </div>

        <button className="commit" onClick={handleVerificationSubmit}>
          제출
        </button>
      </div>
    </div>
  );
}
