import React from 'react'
import {useNavigate} from 'react-router-dom'
//import './Homepage.css'
import styles from './Homepage.module.css'

const Homepage = () => {
    const navigate = useNavigate();
    function toLogin(){
        navigate('/login');
    }
    return (
        <div className={styles.container}>
            <div className={styles.home}> 
            <h1 className={styles.title}>ProjectSync</h1> 
            <button className={styles.button} onClick={toLogin}>Enter</button> 
        </div>
        </div>
        
    )
}

export default Homepage