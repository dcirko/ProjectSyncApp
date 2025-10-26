

import {BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import Homepage from './pages/Homepage/Homepage'
import LoginPage from './pages/Login/LoginPage'
import RegisterPage from './pages/Register/RegisterPage'
import MainLayout from './main-layout/MainLayout.jsx';
import './App.css'
import DashboardPage from "./pages/Dashboard/DashboardPage.jsx";
import MyProfilePage from "./pages/MyProfile/MyProfilePage.jsx";
import ProjectsPage from "./pages/Projects/ProjectsPage.jsx";
import TeamsPage from "./pages/Teams/TeamsPage.jsx";
import TasksPage from "./pages/Tasks/TasksPage.jsx";
import AdminPage from "./pages/Admin/AdminPage.jsx";

const App = () => {
  const location = useLocation();
  return (
    <>
      {location.pathname === '/' || location.pathname === '/login' || location.pathname === '/register' ? null : <MainLayout/>}
      <Routes>
            <Route path='/' element={<Homepage/>}></Route>
            <Route path='/login' element={<LoginPage/>} />
            <Route path='/register' element={<RegisterPage/>}></Route>
            <Route path='/dashboard' element={<DashboardPage/>}></Route>
            <Route path='/projects' element={<ProjectsPage/>}></Route>
            <Route path='/myProfile/:userId' element={<MyProfilePage/>}></Route>
            <Route path='/teams' element={<TeamsPage/>}></Route>
            <Route path='/tasks' element={<TasksPage/>}></Route>
            <Route path='/admin' element={<AdminPage/>}></Route>
      </Routes>
    </>
  )
}

export default App
