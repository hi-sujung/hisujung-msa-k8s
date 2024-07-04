import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../styles/Register.css';

export default function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [email, setEmail] = useState('');
  const [department1, setDepartment1] = useState('');
  const [department2, setDepartment2] = useState('');
  const [passwordMatchError, setPasswordMatchError] = useState(false);
  const [showErrorText, setShowErrorText] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const memberUrl = process.env.REACT_APP_MEMBER_API_URL;

  const handleRegister = async () => {
    handleChkPwd();
    try {
      const response = await axios.post(`${memberUrl}/join`, {
        email,
        username,
        password,
        checkedPassword: confirmPassword,
        department1,
        department2,
        role: "USER"
      });

      if (response.status === 200) {
        setShowErrorText(false);
        setErrorMessage('');
        console.log('Register Successful');
        navigate('/login');
      } else {
        setShowErrorText(true);
        if (response.data && response.data.error) {
          setErrorMessage(response.data.error);
        } else {
          setErrorMessage('아이디 또는 비밀번호 형식이 올바르지 않습니다.');
        }
      }
    } catch (error) {
      console.error('Error Join in:', error);
      setErrorMessage('회원가입에 실패하였습니다.' + error.message);
      setShowErrorText(true);
    }
  };

  const handleChkPwd = () => {
    setPasswordMatchError(password !== confirmPassword);
  };

  return (
    <div className="register-container">
      <h1>회원가입</h1>
      <input
        type="text"
        placeholder="아이디"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="text"
        placeholder="이름"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <input
        type="password"
        placeholder="비밀번호 확인"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
      />
      <select value={department1} onChange={(e) => setDepartment1(e.target.value)}>
        <option value="">-- 주전공 --</option>
        <option value="국어국문학과">국어국문학과</option>
        <option value="영어영문학과">영어영문학과</option>
        <option value="독일어문·문화학과">독일어문·문화학과</option>
        <option value="프랑스어문·문화학과">프랑스어문·문화학과</option>
        <option value="일본어문·문화학과">일본어문·문화학과</option>
        <option value="중국어문·문화학과">중국어문·문화학과</option>
        <option value="사학과">사학과</option>
        <option value="문화예술경영학과">문화예술경영학과</option>
        <option value="미디어영상연기학과">미디어영상연기학과</option>
        <option value="현대실용음악학과">현대실용음악학과</option>
        <option value="무용예술학과">무용예술학과</option>
        <option value="정치외교학과">정치외교학과</option>
        <option value="심리학과">심리학과</option>
        <option value="지리학과">지리학과</option>
        <option value="미디어커뮤니케이션학과">미디어커뮤니케이션학과</option>
        <option value="경영학부">경영학부</option>
        <option value="사회복지학과">사회복지학과</option>
        <option value="법학부">법학부</option>
        <option value="의류산업학과">의류산업학과</option>
        <option value="소비자생활문화산업학과">소비자생활문화산업학과</option>
        <option value="뷰티산업학과">뷰티산업학과</option>
        <option value="수리통계데이터사이언스학부">수리통계데이터사이언스학부</option>
        <option value="화학·에너지융합학부">화학·에너지융합학부</option>
        <option value="바이오신약의과학부">바이오신약의과학부</option>
        <option value="바이오헬스융합학부">바이오헬스융합학부</option>
        <option value="스포츠과학부">스포츠과학부</option>
        <option value="서비스·디자인공학과">서비스·디자인공학과</option>
        <option value="융합보안공학과">융합보안공학과</option>
        <option value="컴퓨터공학과">컴퓨터공학과</option>
        <option value="청정융합에너지공학과">청정융합에너지공학과</option>
        <option value="바이오식품공학과">바이오식품공학과</option>
        <option value="바이오생명공학과">바이오생명공학과</option>
        <option value="간호학과">간호학과</option>
        <option value="교육학과">교육학과</option>
        <option value="사회교육과">사회교육과</option>
        <option value="윤리교육과">윤리교육과</option>
        <option value="한문교육과">한문교육과</option>
        <option value="유아교육과">유아교육과</option>
        <option value="동양화과">동양화과</option>
        <option value="서양화과">서양화과</option>
        <option value="조소과">조소과</option>
        <option value="공예과">공예과</option>
        <option value="디자인과">디자인과</option>
        <option value="성악과">성악과</option>
        <option value="기악과">기악과</option>
        <option value="작곡과">작곡과</option>
        
      </select>
      <select value={department2} onChange={(e) => setDepartment2(e.target.value)}>
        <option value="">-- 복수전공 --</option>
        <option value="국어국문학과">국어국문학과</option>
        <option value="영어영문학과">영어영문학과</option>
        <option value="독일어문·문화학과">독일어문·문화학과</option>
        <option value="프랑스어문·문화학과">프랑스어문·문화학과</option>
        <option value="일본어문·문화학과">일본어문·문화학과</option>
        <option value="중국어문·문화학과">중국어문·문화학과</option>
        <option value="사학과">사학과</option>
        <option value="문화예술경영학과">문화예술경영학과</option>
        <option value="미디어영상연기학과">미디어영상연기학과</option>
        <option value="현대실용음악학과">현대실용음악학과</option>
        <option value="무용예술학과">무용예술학과</option>
        <option value="정치외교학과">정치외교학과</option>
        <option value="심리학과">심리학과</option>
        <option value="지리학과">지리학과</option>
        <option value="미디어커뮤니케이션학과">미디어커뮤니케이션학과</option>
        <option value="경영학부">경영학부</option>
        <option value="사회복지학과">사회복지학과</option>
        <option value="법학부">법학부</option>
        <option value="의류산업학과">의류산업학과</option>
        <option value="소비자생활문화산업학과">소비자생활문화산업학과</option>
        <option value="뷰티산업학과">뷰티산업학과</option>
        <option value="수리통계데이터사이언스학부">수리통계데이터사이언스학부</option>
        <option value="화학·에너지융합학부">화학·에너지융합학부</option>
        <option value="바이오신약의과학부">바이오신약의과학부</option>
        <option value="바이오헬스융합학부">바이오헬스융합학부</option>
        <option value="스포츠과학부">스포츠과학부</option>
        <option value="서비스·디자인공학과">서비스·디자인공학과</option>
        <option value="융합보안공학과">융합보안공학과</option>
        <option value="컴퓨터공학과">컴퓨터공학과</option>
        <option value="청정융합에너지공학과">청정융합에너지공학과</option>
        <option value="바이오식품공학과">바이오식품공학과</option>
        <option value="바이오생명공학과">바이오생명공학과</option>
        <option value="간호학과">간호학과</option>
        <option value="교육학과">교육학과</option>
        <option value="사회교육과">사회교육과</option>
        <option value="윤리교육과">윤리교육과</option>
        <option value="한문교육과">한문교육과</option>
        <option value="유아교육과">유아교육과</option>
        <option value="동양화과">동양화과</option>
        <option value="서양화과">서양화과</option>
        <option value="조소과">조소과</option>
        <option value="공예과">공예과</option>
        <option value="디자인과">디자인과</option>
        <option value="성악과">성악과</option>
        <option value="기악과">기악과</option>
        <option value="작곡과">작곡과</option>
      </select>
      {passwordMatchError && <p className="error">Passwords do not match</p>}
      {showErrorText && <p className="error">{errorMessage}</p>}
      <button className="register-button" onClick={handleRegister}>Register</button>
    </div>
  );
}
