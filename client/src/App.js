import './App.css';
import {useState} from 'react';
import {BrowserRouter, Route, Routes, useNavigate} from 'react-router-dom';
import VideoGenerator from "./video_generator/VideoGenerator";
import Login from "./login/login";
import {replaceBehavior} from "@testing-library/user-event/dist/keyboard/plugins";

function App() {
    const [token, setToken] = useState();

    const navigate = useNavigate();
    if(!token) {
        console.log("navigating");
        navigate("/login", {replace:true});
    }

    return (
        <div className="wrapper">
            <h1>Application</h1>
        </div>
    );
}

export default App;
