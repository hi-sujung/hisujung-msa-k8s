// import React from 'react';
// import { Link } from 'react-router-dom';

// function Header() {
//   return (
//     <header>
//       <nav>
//         <ul>
//           <li><Link to="/">Home</Link></li>
//           <li><Link to="/register">회원가입</Link></li>
//           <li><Link to="/login">로그인</Link></li>
//           <li><Link to="/noticeList">공지사항</Link></li>
//           <li><Link to="/activityList">대외활동</Link></li>
//           <li><Link to="/likedNotice">찜한 활동</Link></li>
//           <li><Link to="/portfolioList">포트폴리오 목록</Link></li>
//           <li><Link to="/attendActList">참여한 대외활동</Link></li>
//         </ul>
//       </nav>
//     </header>
//   );
// }

// export default Header;

import React from 'react';
import { Link } from 'react-router-dom';
import { Navbar, Nav } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function Header() {
  const linkStyle = {
    color: '#4A55A2',
    textDecoration: 'none'
  };

  const activeLinkStyle = {
    color: 'darkorchid',
    textDecoration: 'none'
  };

  return (
    <header>
      <Navbar bg="light" expand="lg" className="navbar-light">
        <Navbar.Brand as={Link} to="/" style={{ paddingLeft: '1rem' }}>HI-SUJUNG</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarNav" />
        <Navbar.Collapse id="navbarNav">
          <Nav className="ml-auto"> {/* ml-auto for right alignment */}
            <Nav.Item>
              <Nav.Link as={Link} to="/" style={linkStyle} aria-current="page">홈</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/mailSend" style={linkStyle}>회원가입</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/login" style={linkStyle}>로그인</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/noticeList" style={linkStyle}>공지사항</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/activityList" style={linkStyle}>대외활동</Nav.Link>
            </Nav.Item>
            {/* <Nav.Item>
              <Nav.Link as={Link} to="/likedNotice" style={linkStyle}>찜한 활동</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/attendActList" style={linkStyle}>참여한 대외활동</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link as={Link} to="/portfolioList" style={linkStyle}>포트폴리오 목록</Nav.Link>
            </Nav.Item> */}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </header>
  );
}

export default Header;