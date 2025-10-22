import React, { useEffect } from 'react'
import styles from './DashboardPage.module.css'
import { getCurrentUser } from '../../api/userApi'
import { useState } from 'react'
import { NavLink } from 'react-router-dom'
import { getTwoNewestProjectsByUserId } from '../../api/projectApi'


const DashboardPage = () => {
  const [user, setuser] = useState({
    id:null,
    name: '',
    surname: '',
    email: '',
    role: ''
  });
  const [projects, setprojects] = useState([/*{
    id: null,
    name: '',
    description: '',
    deadline: '',
    status: '',
    createdById: null,
    teamId: null
  }*/]);

  useEffect(() => {
    getCurrentUser()
    .then((res) => {setuser(res.data), getTwoNewestProjectsByUserId(res.data.id).then((resp) => setprojects(resp.data))})
    .catch((error) => console.error('Error fetching data:', error));
  }, []);



  console.log(projects);
  return (
    <div className={styles.container}>
        <h1 className={styles.title}>Welcome, <NavLink className={styles.link} to={`/myProfile/${user.id}`}>{user.name} {user.surname}</NavLink></h1>
        <section className={styles.section}>
          <h2 className={styles.sectionTitle}>My Projects</h2>
          <div>
            {
              projects.length != 0 ? (projects.map(p => (
                  <>
                    <div key={p.id} className={styles.projectCard}>
                      <h4 className={styles.projectName}>{p.name}</h4>
                      <p className={styles.projectInfo}>{p.status}</p>
                      <p className={styles.projectInfo}>Deadline: {p.deadline}</p>
                    </div>  
                    <NavLink className={styles.link} to="/projects">See all your projects</NavLink>
                  </>
                ))) :  (
                  <p className={styles.emptyMessage}>You are not assigned to any projects as of right now.</p>
              )
            }
          </div>
        </section>
        <section className={styles.section}>
          <h2 className={styles.sectionTitle}>My Tasks</h2>
        </section>
        <section className={styles.section}>
          <h2 className={styles.sectionTitle}>My Teams</h2>
        </section>
    </div>
  )
}

export default DashboardPage