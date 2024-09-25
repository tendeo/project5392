import React, {useState} from "react";
import './MainPage.css'; 
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Mainpage = ()=>{
    const [selectedAction, setSelectedAction] = useState("");

    

    const [nodeId, setNodeId] = useState("");
    const [leftNeighbor, setLeftNeighbor] = useState("");
    const [rightNeighbor, setRightNeighbor] = useState("");
    const [inboxSize,setInboxSize] = useState("");

    const [DelNodeId,setDelNodeId] = useState("");

    const location = useLocation(); 
    const username = location.state?.username || "Guest";
    const navigate = useNavigate();

   const handleDeleteNode = (e) =>{
        e.preventDefault();
        setDelNodeId("");






   }


    const handleCreateNode = (e) => {
        e.preventDefault();
        console.log(`Creating Node with ID: ${nodeId}, Left Neighbor: ${leftNeighbor}, Right Neighbor: ${rightNeighbor}`);
        setNodeId("");
        setLeftNeighbor("");
        setRightNeighbor("");
        setInboxSize("");
    };
    return (
        <div>
            <div className="header">
                <div className="header-item blank">Administrator</div>
                <div className="header-item welcome"><h1>Welcome</h1></div>
                <div className="header-item username">
                    {username}
                    <a href="#logout" className="logout-link" onClick={()=> navigate("/")}>Logout</a>
                </div>

                

            </div>
            <div className="second_header">
                <div className="second-item left">
                    <h2>Tasks</h2>
                    <div className="dropdown-node-actions">
                        <label htmlFor="node-actions">Node Actions:</label>
                        <select id="node-actions"
                                value={selectedAction}
                                onChange={(e) => setSelectedAction(e.target.value)} >
                            <option value="node-action0">--</option>
                            <option value="create-node">Create a Node</option>
                            <option value="delete-node">Delete A Node</option>
                            <option value="send-message">Send Message</option>
                        </select>
                    </div>
                    <a href="#Status" className="node-status">Node Status</a>

                    <div className="dropdown-create-delete-node">
                        <label htmlFor="create-delete-node">Create or Delete:</label>
                        <select id="create-delete">
                            <option value="crdl-action0">--</option>
                            <option value="crdl-action1">Create a Node</option>
                            <option value="crdl-action2">Delete A Node</option>
                        </select>
                    </div>
                    



                </div>
                <div className="second-item middle">
                    <h1>Network Display</h1>

                    
            
                    
                </div>
                <div className="second-item right">
                    <h2>Dialogue Space</h2>
                    {selectedAction === "create-node" && (
                        <div className="create-node-form">
                            <h3>Create a Node</h3>
                            <form onSubmit={handleCreateNode}>
                                <div>
                                    <label>Node ID:</label>
                                    <input
                                        type="text"
                                        value={nodeId}
                                        onChange={(e) => setNodeId(e.target.value)}
                                        required
                                    />
                                </div>
                                <div>
                                    <label>Left Neighbor:</label>
                                    <input
                                        type="text"
                                        value={leftNeighbor}
                                        onChange={(e) => setLeftNeighbor(e.target.value)}
                                        required
                                    />
                                </div>
                                <div>
                                    <label>Right Neighbor:</label>
                                    <input
                                        type="text"
                                        value={rightNeighbor}
                                        onChange={(e) => setRightNeighbor(e.target.value)}
                                        required
                                    />
                                </div>
                                <div>
                                    <label>Inbox Size:</label>
                                    <input
                                        type="number"
                                        value={inboxSize}
                                        onChange={(e) => {
                                            const value = e.target.value;
                                            if (value >= 0) {
                                                setInboxSize(value); 
                                            }
                                        }}
                                        min="0"
                                        required
                                    />
                                </div>
                                <button type="submit">Create</button>
                            </form>
                        </div>
                    )}
                    {selectedAction === "delete-node" && (
                        <div className="delete-node-form">
                            <h3>delete a Node</h3>
                            <form onSubmit={handleDeleteNode}>
                                <div>
                                    <label>Node ID:</label>
                                    <input
                                        type="text"
                                        value={DelNodeId}
                                        onChange={(e) => setNodeId(e.target.value)}
                                        required
                                    />
                                </div>
                                <button type="submit">Delete</button>
                            </form>
                        </div>
                    )}

                </div>
                
                



            </div>
        </div>
 
    )
}

export default Mainpage;