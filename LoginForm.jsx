import React, { useState } from "react";
import './LoginForm.css';

import { FaUser, FaLock } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
    const [username, setUsername] = useState("");
    const [selectedRole, setSelectedRole] = useState("Operator"); 
    const navigate = useNavigate();

    const handleLogin = (e) => {
        e.preventDefault(); 

        
        if (selectedRole === "Admin") {
            navigate("/MainPage", { state: { username } });
        } else if (selectedRole === "Operator") {
            navigate("/OperatorMainPage", { state: { username } });
        }
    };

    return (
        <div className="wrapper">
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
                    <input type="password" placeholder="Password" required />
                    <FaLock className="icon" />
                </div>

                <label>
                    Pick One:
                    <select
                        name="selectedone"
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
