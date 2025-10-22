import React from 'react'
import { useParams } from 'react-router-dom'

const MyProfilePage = () => {
  const {userId} = useParams();
  return (
    <div>MyProfilePage</div>
  )
}

export default MyProfilePage