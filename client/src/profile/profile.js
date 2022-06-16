import React from 'react';
import {getToken} from "../App";
import './profile.css';

class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {name:'', surname:'', videos:[], currentVideo: null}
        this.handleClickVideo = this.handleClickVideo.bind(this);
    }

    componentDidMount() {
        const myInit = { method: 'GET',
            headers: {'Authorization': 'Bearer ' + getToken()},
            mode: 'cors'
        };
        const request = new Request('http://localhost:8080/profile', myInit);
        fetch(request)
            .then(response => response.json())
            .then(data =>{
                console.log(data);
                this.setState({name:data.name, surname:data.surname, videos:data.videos})
            });
    }

    handleClickVideo(name) {
        console.log(name);
        const myInit = { method: 'GET',
            headers: {'Authorization': 'Bearer ' + getToken()},
            mode: 'cors'
        };
        const request = new Request('http://localhost:8080/video/' + name, myInit);
        fetch(request)
            .then(response => response.text())
            .then(data =>{
                console.log(data);
                this.setState({currentVideo: data});
            });
    }

    render() {
        let video = null;
        if (this.state.currentVideo) {
            video = <video crossOrigin="anonymous" src={'data:video/mp4;base64,' + this.state.currentVideo} autoPlay controls></video>
        }
        return (
            <div>
                <h1>Hi {this.state.name + ' ' + this.state.surname}!</h1>
                <ul>
                    {this.state.videos.map((option) => (
                        <li value={option} onClick={() => this.handleClickVideo(option)}>{option}</li>
                    ))}
                </ul>
                {video}
            </div>
        );
    }
}

export default Profile;