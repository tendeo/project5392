import React, { useState } from "react";
import './LoginForm.css';

import { FaUser, FaLock, FaClosedCaptioning } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [passwordValid, setIsValid] = useState(true);
    const [selectedRole, setSelectedRole] = useState("Operator"); 
    const navigate = useNavigate();

    const passwordValidation = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$%#@&])[A-Za-z\d$%#@&]{6,}$/;

    const handleLogin = (e) => {
        e.preventDefault(); 

        //To do: Once backend complete, validate user info before routing
        //Otherwise: give error message "Invalid user"
        if (selectedRole === "Admin") {
            navigate("/MainPage", { state: { username, password } });
        } else if (selectedRole === "Operator") {
            navigate("/OperatorMainPage", { state: { username, password } });
        }
    };

    const handlePasswordValidation = (e) => {
        const newPassword = e.target.value;
        setPassword(newPassword);
        console.log("new password is ",  password);
        setIsValid(passwordValidation.test(newPassword))
    };

    return (
        <div className="wrapper">
                <h1 className="login-title">CS5392 Communication Network</h1>
                <form onSubmit={handleLogin}>
                <h1>Login</h1>
                <div className="input-box">
                    <input type="text" 
                        placeholder="Username"
                        value={username} 
                        onChange={(e) => setUsername(e.target.value)} 
                        required />
                    <FaUser className="icon" />
                </div>
                <div className="input-box">
                    <input type="password" 
                        placeholder="Password" 
                        value={password}
                        onChange={handlePasswordValidation}
                        required />
                    <FaLock className="icon" />
                </div>
                <p style={{ color: !passwordValid &&!!password.length && 'red' }}>
                        {!passwordValid && !!password.length && 'Password must be at least 6 characters long, contain at least one letter, one number, and one special character ($, %, #, @, &).'}
                        </p>
                <label>
                    Select Role: &nbsp;
                    <select
                        name="selectedRole"
                        value={selectedRole}
                        onChange={(e) => setSelectedRole(e.target.value)} 
                    >
                        <option value="Admin">Administrator</option>
                        <option value="Operator">Operator</option>
                    </select>
                </label>

                <button type="submit" className="submit-button">
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginForm;
