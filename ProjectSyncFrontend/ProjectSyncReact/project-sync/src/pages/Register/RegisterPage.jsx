import React from 'react'
import styles from './RegisterPage.module.css'
import {useState} from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { register } from '../../api/authApi';
import Swal from 'sweetalert2';


const RegisterPage = () => {
  const navigate = useNavigate();
  const [regForma, setregForma] = useState({
    name:'',
    surname:'',
    email:'',
    password:'',
    confirmPassword:'',
  });

  const [error, seterror] = useState('');

  function handleChange(c){
    setregForma({
      ...regForma,
      [c.target.name]: c.target.value,
    })
  }

  function submitForm(e){
    e.preventDefault();
    if(regForma.confirmPassword != regForma.password){
      seterror('Passwords do not match!');
    }else{
      const {confirmPassword, ...userRegisterData} = regForma;
      console.log(userRegisterData);
      try{
        register(userRegisterData);
        Swal.fire('Registration successful.', '', 'success');
        navigate('/login');
      }catch(err){
        console.log(err);
        Swal.fire({
          icon: 'error',
          title: 'Registration failed',
          text: 'Please try again.'
        });
      }
      
      
    }

  }
  return (
    <div className={styles.container}>
      <div className={styles.registerForma}>
        <h1 className={styles.title}>Register</h1>
        <form className={styles.form} onSubmit={submitForm}>
          <input className={styles.input} type="text" required placeholder='Name' value={regForma.name} onChange={handleChange} name='name'/>
          <input className={styles.input} type="text" name="surname" placeholder="Surname" value={regForma.surname} onChange={handleChange} required />
          <input className={styles.input} type="email" name="email" placeholder="Email" value={regForma.email} onChange={handleChange} required />
          <input className={styles.input} type="password" name="password" placeholder="Password" value={regForma.password} onChange={handleChange} required />
          <input className={styles.input} type="password" name="confirmPassword" placeholder="Confirm password" value={regForma.confirmPassword} onChange={handleChange} required/>
          <span className={styles.error}>{error}</span>
          <button className={styles.button} type="submit">Register</button>
        </form>
        <Link to="/login" className={styles.loginLink}>
            Već imate račun? Prijavite se
        </Link>
      </div>
    </div>
  )
}

export default RegisterPage