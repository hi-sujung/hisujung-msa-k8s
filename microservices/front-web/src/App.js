import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Home from './pages/Home';
import Register from './pages/Register';
import NoticeList from './pages/NoticeList';
import ActivityList from './pages/ActivityList';
import PortfolioList from './pages/PortfolioList';
import MyPortfolio from './pages/MyPortfolio';
import CreatePortfolio from './pages/CreatePortfolio';
import Activity from './pages/Activity';
import Notice from './pages/Notice';
import LikedNotice from './pages/LikedNotice';
import Login from './pages/Login';
import MailSend from './pages/MailSend';
import AttendActList from './pages/AttendActList';
import { AuthProvider } from './utils/AuthContext';

function App() {
  return (
    <AuthProvider>
      <div>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} /> 
        <Route path="/mailSend" element={<MailSend />} /> 
        <Route path="/register" element={<Register />} />
        <Route path="/notice/:activityId" element={<Notice />} />
        <Route path="/activity/:activityId" element={<Activity />} />
        <Route path="/likedNotice" element={<LikedNotice />} />
        <Route path="/noticeList" element={<NoticeList />} />
        <Route path="/activityList" element={<ActivityList />} />
        <Route path="/attendActList" element={<AttendActList />} />
        <Route path='/portfolioList' element={<PortfolioList />} />
        <Route path='/myPortfolio/:portfolioId' element={<MyPortfolio />} />
        <Route path='/createportfolio' element={<CreatePortfolio />} />
        <Route path="/mailSend" element={<MailSend />} /> 
      </Routes>
    </div>
    </AuthProvider>
  );
}

export default App;
