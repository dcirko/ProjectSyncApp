import React, { useEffect } from 'react'
import styles from './DashboardPage.module.css'
import { getCurrentUser } from '../../api/userApi'
import { useState } from 'react'
import { NavLink } from 'react-router-dom'
import { getTwoNewestProjectsByUserId } from '../../api/projectApi'
import { getTwoNewestTasksByUserId } from '../../api/tasksApi'


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
  const [tasks, settasks] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try{
        const userResp = await getCurrentUser();
        const user = userResp.data;
        setuser(user);
        console.log(user)

        const [projectResp, tasksResp] = await Promise.all([
          getTwoNewestProjectsByUserId(user.id),
          getTwoNewestTasksByUserId(user.id)
        ]);

        setprojects(projectResp.data);
        console.log(projectResp.data)
        settasks(tasksResp.data);
        console.log(tasksResp.data)

      } catch (error) {
        console.error('Error fetching user data:', error);
        return;
      }
    }
    fetchData();
  }, []);




  return (
    <div className={styles.container}>
        <h1 className={styles.title}>Welcome, <NavLink className={styles.link} to={`/myProfile/${user.id}`}>{user.name} {user.surname}</NavLink></h1>
        <section className={styles.section}>
          <h2 className={styles.sectionTitle}>My Projects</h2>
          <div>
            {
              projects.length != 0 ? (projects.map(p => (
                  <>
                    <div key={p.id} className={styles.card}>
                      <h4 className={styles.name}>{p.name}</h4>
                      <p className={styles.info}>{p.status}</p>
                      <p className={styles.info}>Deadline: {p.deadline}</p>
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
          <div>
            {tasks.length != 0 ? (tasks.map(t =>(
              <>
                <div key={t.id} className={styles.card}>
                  <h4 className={styles.name}>{t.name}</h4>
                  <h5>Project name: </h5>
                  <p className={styles.info}>{t.priority}</p>
                  <p className={styles.info}>{t.status}</p>
                  <p className={styles.info}>Deadline: {t.deadline}</p>
                </div>
                <NavLink className={styles.link} to="/tasks">See all your tasks</NavLink>
              </>
            ))) : (
                  <p className={styles.emptyMessage}>You do not have tasks left.</p>
              )
            }
          </div>
        </section>
        <section className={styles.section}>
          <h2 className={styles.sectionTitle}>My Teams</h2>
        </section>
    </div>
  )
}

export default DashboardPage