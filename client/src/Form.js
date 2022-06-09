import React from 'react';

class Form extends React.Component {
    constructor(props) {
        super(props);

        this.handleSubmit = this.handleSubmit.bind(this);

        this.fileInput = React.createRef();
    }


    handleSubmit(event) {
        alert('A file was submitted: ' + this.fileInput.current.files[0].name);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input type = 'file' ref = {this.fileInput}/>
                <input type = 'submit'/>
            </form>
        );
    }
}

export default Form;