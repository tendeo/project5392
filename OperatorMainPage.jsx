import React from "react";
import { useNavigate } from "react-router-dom";
import './OperatorMainPage.css'; 
import { useLocation } from "react-router-dom";

const Operatorpage = () =>{
    const location = useLocation(); 
    const username = location.state?.username || "Guest";
    const navigate = useNavigate();

    return (
        <div>
            <div className="main-header">
                <div className="main-header-item left">Operator</div>
                <div className="main-header-item middle"><h1>Welcome</h1></div>
                <div className="main-header-item right">
                    {username}
                    <a href="#logout" className="main-header-item-logout-link" onClick={()=> navigate("/")}>Logout</a>
                </div>

                

            </div>
            <div className="second_header_operator">
                <div className="second_header_item lefty">
                    <h2>Tasks</h2>
                    <div className="dropdown-node-actions-operator">
                        <label htmlFor="node-actions-operator">Node Actions:</label>
                        <select id="node-actions-operator">
                            <option value="node-action-operator0">--</option>
                            <option value="node-action-operator1">Create a Node</option>
                            <option value="node-action-operator2">Delete A Node</option>
                            <option value="node-action-operator3">Send Message</option>
                        </select>
                    </div>

                </div>

                <div className="second_header_item middly"><h1>Network Display</h1></div>
                <div className="second_header_item righty">Dialogue Space</div>
                

            </div>
        </div>
    )

}

export default Operatorpage;