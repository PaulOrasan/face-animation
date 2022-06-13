import React from 'react';
import './login.css';
import {Link, Route, Routes} from 'react-router-dom';
import VideoGenerator from "../video_generator/VideoGenerator";
import Register from '../register/register';
class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {setToken: this.props.setToken}
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        let formData = new FormData();
        formData.append('username', event.target.username.value);
        formData.append('password', event.target.password.value);
        console.log(JSON.stringify(formData));

        const myInit = { method: 'POST',
            mode: 'cors',
            body: formData
        };
        const request = new Request('http://localhost:8080/login', myInit);
        fetch(request)
            .then(response => response.json())
            .then(data =>{
                console.log(data);
            });
        event.preventDefault();
    }

    render()
    {
        return (
            <div className="login-wrapper">
                <h1>Please Log In</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        <p>Username</p>
                        <input type="text" name = "username"/>
                    </label>
                    <label>
                        <p>Password</p>
                        <input type="password" name = "password"/>
                    </label>
                    <div>
                        <button type="submit">Submit</button>
                        <Link to='/register'>Register</Link>
                    </div>
                </form>
            </div>
        )
    }
}

export default Login;