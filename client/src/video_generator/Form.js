import React from 'react';
import './Form.css'
class Form extends React.Component {
    constructor(props) {
        super(props);

        this.state = {expressions:['A', 'B'], currentExpression:'A', file: '', setImage:this.props.setImage, setVideo: this.props.setVideo}

        this.handleFileChange = this.handleFileChange.bind(this);
        this.handleExpressionChange = this.handleExpressionChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.fileInput = React.createRef();
    }

    componentDidMount() {
        const headers = new Headers();
        headers.append('Accept', 'application/json');
        const myInit = { method: 'GET',
            headers: headers,
            mode: 'cors'
        };
        const request = new Request('http://localhost:8080/expressions', myInit);
        fetch(request)
            .then(response => response.json())
            .then(data => this.setState({expressions: data}));
    }

    handleFileChange(event) {
        console.log("handling file change");
        this.state.setImage(URL.createObjectURL(event.target.files[0]));
        this.setState({file: event.target.files[0]});
    }

    handleExpressionChange(event) {
        console.log("changing expressiong to " + event.target.value);
        this.setState({currentExpression: event.target.value});
    }

    handleSubmit(event) {
        let formData = new FormData();
        console.log(event.target.sourceImage.files[0]);
        formData.append('sourceImage', event.target.sourceImage.files[0]);
        formData.append('expression', event.target.expression.value);

        const myInit = { method: 'POST',
            mode: 'cors',
            body: formData
        };
        const request = new Request('http://localhost:8080/generate', myInit);
        fetch(request)
            .then(response => response.text())
            .then(data =>{
                console.log(data);
                this.state.setVideo(data);
            });
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>

                <label>Source image:</label>
                <input name = 'sourceImage' type = 'file' ref = {this.fileInput} onChange = {this.handleFileChange}/>

                <label>Target expression:</label>
                <select name = 'expression' >
                    {this.state.expressions.map((option) => (
                        <option value={option}>{option}</option>
                    ))}
                </select>
                <input type = 'submit'/>
            </form>
        );
    }
}

export default Form;