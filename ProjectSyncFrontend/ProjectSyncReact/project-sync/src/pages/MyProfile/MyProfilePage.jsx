import React, { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { getCurrentUser, getProfile } from '../../api/userApi';
import { useState } from 'react';
import styles from './MyProfilePage.module.css'


const MyProfilePage = () => {
  const {userId} = useParams();
  const [user, setuser] = useState({
    id:'',
    name:'',
    surname:'',
    email:'',
    role:''
  });

  useEffect(() =>{
    console.log(userId)
    getProfile(userId)
    .then(res => setuser(res.data))
    .catch(err => console.log(err));
  }, [])
  
  return (
    <div className={styles.container}>
      <div className={styles.card}>
      <section className={styles.infoGrid}>
        <div className={styles.infoItem}>
          <span className={styles.label}>Full name</span>
          <span className={styles.value}>{user.name} {user.surname}</span>
        </div>
        <div className={styles.infoItem}>
          <span className={styles.label}>Email</span>
          <span className={styles.value}>{user.email}</span>
        </div>
        <div className={styles.infoItem}>
          <span className={styles.label}>Role</span>
          <span className={styles.value}>{user.role}</span>
        </div>
      </section>

      <div className={styles.actions}>
        <button className={styles.primaryBtn}>Edit profile</button>
        <button className={styles.secondaryBtn}>Change password</button>
      </div>
    </div>
    </div>
    
  )
}

export default MyProfilePage