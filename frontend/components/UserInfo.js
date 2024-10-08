import React from 'react';
import styles from '../styles/userInfo.module.css';

const UserInfo = ({ user }) => {
    return (
        <div className={styles.container}>
            <h2 className={styles.title}>User Information</h2>
            <div className={styles.info}>
                <p><strong>Email:</strong> {user.username}</p>
                <p><strong>First Name:</strong> {user.firstname}</p>
                <p><strong>Last Name:</strong> {user.lastname}</p>
                <p><strong>Country:</strong> {user.country}</p>
            </div>
        </div>
    );
};

export default UserInfo;
