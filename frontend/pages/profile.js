import { useUser } from '../context/UserContext';
import Header from '../components/Header';
import '../styles/globals.css';

const UserPage = () => {
    const { user } = useUser();

    if (!user) {
        return (
            <>
                <Header />
                <div className="container">
                    <p>Loading user information...</p>
                </div>
            </>
        );
    }

    return (
        <>
            <Header />
            <div className="container">
                <h2>User Information</h2>
                <ul>
                    <li><strong>Email:</strong> {user.username}</li>
                    <li><strong>First Name:</strong> {user.firstname}</li>
                    <li><strong>Last Name:</strong> {user.lastname}</li>
                    <li><strong>Country:</strong> {user.country}</li>
                </ul>
            </div>
        </>
    );
};

export default UserPage;
