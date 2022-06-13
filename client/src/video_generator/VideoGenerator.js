import './VideoGenerator.css';
import Form from './Form.js';
import Displayer from './Displayer.js';
import {useState} from 'react';

function VideoGenerator() {

    const [image, setImage] = useState("");
    const [video, setVideo] = useState("");
    const display = [image, video];
    return (
        <div>
            <Form setImage = {setImage} setVideo = {setVideo} />
            <Displayer key = {display} image = {image} video = {'data:video/mp4;base64,' + video}/>
        </div>
    );
}

export default VideoGenerator;
