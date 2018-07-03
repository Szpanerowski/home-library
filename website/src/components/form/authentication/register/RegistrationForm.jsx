import React from 'react';
import $ from 'jquery';
import config from 'react-global-configuration';
import { FormGroup, FormControl, ControlLabel, Button } from 'react-bootstrap';
import './RegistrationForm.res/RegistrationForm.css';


class RegistrationForm extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.validateForm = this.validateForm.bind(this);

        this.state = {
            username: '',
            email: '',
            password: '',
            registrationMessage: null
        }
    }

    handleChange(e) {
        this.setState({
            [e.target.id]: event.target.id
        });
    }

    validateForm() {
        return true;
    }

    onUserRegistered(response) {
        this.props.onRegistrationCompleted();
    }

    onRegistrationFailed(response) {
        this.setState({
            registrationMessage: 'Registration failed...'
        });
    }

    handleRegistration(e) {
        this.setState({
            registrationMessage: null
        });

        $.ajax({
           url: config.serverUrl + '/register',
           method: 'POST',
            data: {
                username: this.state.username,
                email: this.state.email,
                password: this.state.password
            },
            success: this.onUserRegistered,
            error: this.onRegistrationFailed
        });

        e.preventDefault();
    }

    render() {
        return (
            <div className="RegistrationForm">
                <form id="registrationForm" onSubmit={this.handleRegistration}>
                    <FormGroup controlId="username" bsSize="large">
                        <ControlLabel>Username</ControlLabel>
                        <FormControl type="text" value={this.state.username} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup controlId="email" bsSize="large">
                        <ControlLabel>Email</ControlLabel>
                        <FormControl type="email" value={this.state.email} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup controlId="password" bsSize="large">
                        <ControlLabel>Password</ControlLabel>
                        <FormControl type="password" value={this.state.password} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup controlId="registrationButton" validationState={this.state.registrationMessage}>
                        <Button type="submit" block bsSize="large" disabled={ !this.validateForm() } />
                        <FormControl.Feedback />
                    </FormGroup>
                </form>
            </div>
        );
    }
}

export default RegistrationForm;