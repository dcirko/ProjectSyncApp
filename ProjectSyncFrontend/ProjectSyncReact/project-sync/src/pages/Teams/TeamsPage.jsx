import React, { useEffect } from 'react'
import styles from './TeamsPage.module.css'
import { useState } from 'react'
import { getAllUsersForAdmin, getCurrentUser, isAdmin } from '../../api/userApi';
import Swal from 'sweetalert2';
import { addNewMember, addNewTeam, getAllTeamsByUserId, getAllTeamsForAdmin } from '../../api/teamsApi';
import { NavLink } from 'react-router-dom';
import { useTeamsPage } from './UseTeamsPage';


const TeamsPage = () => {
  const {admin, newTeam,users, user, allTeams, allTeamsByUser, refreshTeams, refreshNewTeam} = useTeamsPage();

  function handleChange(c){
    setnewTeam({
      ...newTeam,
      [c.target.name]: c.target.value
    })
  };

  function handleMembersChange(c){
    const selectedMemberId = Number(c.target.value);
    if(newTeam.membersIds.includes(selectedMemberId)){
      Swal.fire('User already in the team');
      return;
    }else{
      setnewTeam(prevTeam => ({
        ...prevTeam,
        membersIds: [...prevTeam.membersIds, selectedMemberId]
      }))
    }
  }

  //console.log("teams:", allTeams)
  console.log("user teams: ", allTeamsByUser)

  function submitNewTeam(e){
    e.preventDefault();
    const teamToSubmit = {...newTeam, createdById: user.id}
    console.log("New: ",teamToSubmit);
    try{
      addNewTeam(teamToSubmit)
      .then(res => {
        Swal.fire('Team created successfully!');
        
      })
      
    }catch(err){
      console.error('Error adding new team:', err);
    }
  }

  function addMemberToTeam(teamId){
    const select = document.getElementById(`member-select-${teamId}`);
    const userId = select.value;

    const newMember = {teamId, userId: Number(userId)};
    console.log(newMember);

    try{
      addNewMember(newMember)
      .then(res => {
        Swal.fire('Member added successfully!');
        refreshTeams();
      })
    }catch(err){
      console.error('Error adding new member to team:', err);
    }
  }


  return (
    <div className={styles.container}>
      <section >
        {admin ? (
          <div className={styles.section}>
            <h2 className={styles.title}>Create a New Team</h2>

            <form className={styles.form} onSubmit={submitNewTeam}>
              <input type="text" placeholder="Team Name" name="name" className={styles.input} value={newTeam.name} onChange={handleChange} required />
              <input type="text" name="description" placeholder="Team Description" className={styles.input} value={newTeam.description} onChange={handleChange} required />
              <button type="submit" className={styles.button}>Create</button>
            </form>

            <div className={styles.membersSection}>
              <label className={styles.label}>Choose users to add:</label>
              <select multiple name="membersIds" className={styles.select} onChange={handleMembersChange} value={newTeam.membersIds} required>
                {users.map(u => (
                  <option key={u.id} value={u.id}>{u.name} {u.surname}</option>
                ))}
              </select>
            </div>
          </div>
        ) : (
           <p className={styles.emptyMessage}>
            You do not have permission to create teams.
          </p>
        )}
      </section>
      <section className={styles.section}>
        {admin ? (
          <div>
            <h2 className={styles.sectionTitle}>All teams in the app</h2>
            <p className={styles.info}>You can add explore them and add or remove members.</p>
            {allTeams.length > 0 ? (allTeams.map(t =>(
              <div key={t.id} className={styles.card}>
                <h3 className={styles.name}>{t.name}</h3>
                <p className={styles.info}>{t.description}</p>
                
                <h4 className={styles.nameMembers}>Members included in the team: </h4>
                <div className={styles.members}>
                  {t.members.length > 0 ? (t.members.map(m=>(
                    <>
                      <li key={m.id} style={{marginBottom: '10px'}}>
                        <NavLink className={styles.link} to={`/myProfile/${m.userId}`}>{m.userEmail} - {m.role}</NavLink>
                      </li>
                    </>
                  ))) : (
                    <p className={styles.emptyMessage}>No members assigned to this team yet.</p>
                  )}
                </div>

                <h4 className={styles.nameMembers}>Add new member: </h4>
                <div className={styles.addMember}> 
                  <select className={styles.select} id={`member-select-${t.id}`}>
                    {users
                    .filter(u => !t.members.some(m => m.userId === u.id))
                    .map(u => (
                      <option key={u.id} value={u.id}>{u.name} {u.surname}</option>
                    )) 
                    }
                  </select>
                  <button className={styles.addMemberButton} onClick={() => addMemberToTeam(t.id)}>Add new member</button>
                </div>

                <h4 className={styles.nameMembers}>Projects made by this team: </h4>
                <div className={styles.members}>
                  {t.projects.length > 0 ? (t.projects.map(p=>(
                    <div key={p.id}>
                      <NavLink className={styles.link} /*to={`/projects/${p.id}`}*/>{p.name}</NavLink>
                    </div>
                  ))) : (
                    <p className={styles.emptyMessage}>No projects made in this team yet.</p>
                  )}
                </div>  
              </div>
            ))) : (
              <p className={styles.emptyMessage}>No teams made in te application yet.</p>
            )}
          </div>
          
        ) : (
          <div>
            <h2 className={styles.title}>All teams you are a part of</h2>
            <p className={styles.sectionTitle2}>You can examine them and see your colleagues</p>
            {allTeamsByUser.length > 0 ? (allTeamsByUser.map(at => (
              <>
                <div key={at.id} className={styles.card}>
                  <h3 className={styles.name}>{at.name}</h3>
                  <p className={styles.info}>{at.description}</p>

                  <h4 className={styles.nameMembers}>Members of this team: </h4>
                  <div className={styles.members}>
                    {at.members.length > 0 ? (at.members.map(m=>(
                      <>
                        <li key={m.id} style={{marginBottom: '10px'}}>
                          <NavLink className={styles.link} to={`/myProfile/${m.userId}`}>{m.userEmail} - {m.role}</NavLink>
                        </li>
                      </>
                    ))) : (
                      <p className={styles.emptyMessage}>No members assigned to this team yet.</p>
                    )}
                  </div>
                </div>
              </>
            ))
            ) : (
              <p className={styles.emptyMessage}>You are not a part of any team.</p>
            )}
          </div>
        )}
      </section>
    </div>
      
  );
}

export default TeamsPage