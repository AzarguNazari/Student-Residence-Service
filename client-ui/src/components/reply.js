import React from "react";
import "./Reply.css";

const Reply = ({ updateMessage, postReply, announcement }) => {
    return (
    <div className="reply-container">
        <h2>Add a reply</h2>
        <textarea className="text-area" onChange={(e) => updateMessage(e.target.value)} rows={15} />
        <div className="input-container">
            <button className="login" onClick={() => postReply(announcement['external_id'])}>Reply</button>
        </div>
    </div>
    )
}

export default Reply;