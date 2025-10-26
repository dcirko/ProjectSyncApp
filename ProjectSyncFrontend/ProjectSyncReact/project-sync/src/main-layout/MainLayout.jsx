import React, { useEffect } from 'react'
import { getCurrentUser } from '../api/userApi';
import { useState } from 'react';
import st from './mainLayout.module.css'
import Swal from 'sweetalert2';
import { Link, useNavigate, NavLink } from 'react-router-dom';
import { logout } from '../api/authApi';
import DashboardPage from '../pages/Dashboard/DashboardPage';




const MainLayout = () => {
    const navigate =  useNavigate();
    const [user, setuser] = useState({
        id:null,
        name: '',
        surname: '',
        email: '',
        role: ''
    });
    useEffect(() => {
        getCurrentUser()
        .then(response => setuser(response.data))
        .catch(error => console.error('Error fetching user data:', error));
    }, []);

    console.log(user);
    function logoutButton(){
        try {
            logout();
            Swal.fire('Logout successful.', '', 'success');
            localStorage.removeItem('token');
            navigate('/');
        } catch (error) {
            console.error('Error during logout:', error);
            Swal.fire({
                icon: 'error',
                title: 'Logout failed',
                text: 'Please try again.'
            });
        }
    }

    return (
        <div className={st.main}>
            <nav className={st.nav}>
                <h1 ><NavLink className={st.logo} to="/dashboard">ProjectSync</NavLink></h1>
                <ul className={st.navList}>
                    <li><NavLink to="/dashboard">Dashboard</NavLink></li>
                    <li><NavLink to="/projects">Projects</NavLink></li>
                    <li><NavLink to="/teams">Team</NavLink></li>
                    <li><NavLink to={`/myProfile/${user.id}`}>{user.name} {user.surname}</NavLink></li>
                    {user.role === 'ADMIN' && <li><NavLink to="/admin">Admin</NavLink></li>}

                </ul>
                <button className={st.logout} onClick={logoutButton}>Logout</button>
            </nav>
            <footer>

            </footer>
        </div>
    )
}

export default MainLayout