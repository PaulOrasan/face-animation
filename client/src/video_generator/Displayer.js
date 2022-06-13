import React from 'react'
import './Displayer.css'
class Displayer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {image: this.props.image, video: this.props.video}
    }

    render() {
        return (
            <div id = "displayerContainer">
                <div id = "imageContainer">
                    {console.log("rendering displayer" + this.state.image)}
                    <img src = {this.state.image} />
                </div>
                <div>
                    <video crossorigin="anonymous" src={this.state.video} autoplay controls></video>
                </div>
            </div>
        )
    }
}
export default Displayer;