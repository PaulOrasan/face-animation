import React from "react";
import {useNavigate} from "react-router-dom";

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {setToken: this.props.setToken}
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        const formData = new FormData(event.target);
        const data = JSON.stringify(Object.fromEntries(formData));

        const myInit = { method: 'POST',
            mode: 'cors',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: data
        };
        const request = new Request('http://localhost:8080/register', myInit);
        fetch(request)
            .then(response => response.json())
            .then(data =>{
                console.log(data);
            });
        event.preventDefault();
        window.open("/");
    }

    render()
    {
        return (
            <div className="login-wrapper">
                <h1>Please register</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        <p>Name</p>
                        <input type="text" name = "name"/>
                    </label>
                    <label>
                        <p>Surname</p>
                        <input type="text" name = "surname"/>
                    </label>
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
                    </div>
                </form>
            </div>
        )
    }
}

export default Register;