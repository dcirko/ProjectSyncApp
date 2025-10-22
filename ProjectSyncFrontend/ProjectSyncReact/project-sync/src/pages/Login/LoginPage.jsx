import React from 'react'
import {useNavigate} from 'react-router-dom'
import {useState} from 'react'
import styles from './LoginPage.module.css'
import { Link } from 'react-router-dom'
import { login } from '../../api/authApi'
import Swal from 'sweetalert2'


const LoginPage = () => {
  const navigate = useNavigate();
  const [forma, setforma] = useState({
    email: '',
    password: ''
  });

  function handleChange(c){
    setforma({
      ...forma,
      [c.target.name]: c.target.value,
    });
  }

  async function submitForm(c){
    c.preventDefault();
    try{
      const data = await login(forma);
      console.log(data);
      
      Swal.fire('Login successful.', '', 'success');
      localStorage.setItem('token',data.data.accessToken);
      console.log(data.data.accessToken);
      navigate('/dashboard')
    }catch(err){
      //console.log(err);
      Swal.fire({
        icon: 'error',
        title: 'Login failed',
        text: 'Bad credentials'
      });
    }
  }

  return (
    <div className={styles.container}>
      <div className={styles.loginForma}>
        <h1 className={styles.title}>Login</h1>
        <form className={styles.form} onSubmit={submitForm}>
          <input className={styles.input} type="email" name="email" placeholder="Email" value={forma.email} onChange={handleChange} required />
          <input className={styles.input} type="password" name="password" placeholder="Password" value={forma.password} onChange={handleChange} required/>
          <button className={styles.button} type="submit" >Login</button>
        </form>
        <Link to="/register" className={styles.registerLink}>
          Nemate račun? Registrirajte se
        </Link>
      </div>
    </div>
    
  )
}

export default LoginPage