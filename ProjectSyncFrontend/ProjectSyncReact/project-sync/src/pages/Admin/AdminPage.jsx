import React, { useEffect } from 'react'
import styles from './AdminPage.module.css'
import { getAllUsersForAdmin } from '../../api/userApi';
import { useState } from 'react';
import { NavLink } from 'react-router-dom';


const AdminPage = () => {
  const [users, setusers] = useState([]);

  useEffect(()=>{
    getAllUsersForAdmin()
    .then(res => setusers(res.data))
    .catch(err => console.error('Error fetching users for admin:', err));
  }, [])

  console.log(users);
  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <h1>Welcome, Admin 👑</h1>
        <p className={styles.subtitle}>
          As an administrator, you have full control over the system.
        </p>
      </header>

      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>Your Abilities</h2>
        <ul className={styles.adminActions}>
          <li>👥 View all registered users</li>
          <li>➕  Create and manage teams</li>
          <li>🧭 Assign roles (Team Leader, Developer, Tester...)</li>
          <li>📊 View all projects and their statuses</li>
          <li>🗑️ Remove inactive users or teams</li>
          <li>⚙️ Manage system configuration</li>
        </ul>
      </section>

      <section className={styles.section}>
        <h2 className={styles.sectionTitle}>List of All Users</h2>
        <div className={styles.userList}>
          {users.length !== 0 ? (
            users.map(u => (
              <div key={u.id} className={styles.userCard}>
                <h3>
                  <NavLink className={styles.link} to={`/myProfile/${u.id}`}>
                    {u.name} {u.surname}
                  </NavLink>
                </h3>
                <p className={styles.role}>Role: {u.role}</p>
              </div>
            ))
          ) : (
            <p className={styles.emptyMessage}>
              There are no users registered currently.
            </p>
          )}
        </div>
      </section>
    </div>
  )
}

export default AdminPage