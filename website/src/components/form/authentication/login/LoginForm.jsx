import React from 'react';
import $ from 'jquery';
import { FormGroup, FormControl, ControlLabel, Button } from 'react-bootstrap';
import config from 'react-global-configuration';
import './LoginForm.res/LoginForm.css';


class LoginForm extends React.Component {
    constructor(props) {
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

        this.onUserLogged = this.onUserLogged.bind(this);
        this.onLoginFailed = this.onLoginFailed.bind(this);

        this.validateForm = this.validateForm.bind(this);

        this.state = {
            loginEmail: '',
            password: '',
            loginMessage: null
        };
    }

    handleChange(e) {
        this.setState({
            [e.target.id]: event.target.id
        });
    }

    validateForm() {
        return true;
    }

    onUserLogged(response) {
        this.props.onAuthenticationSucceeded(response.sessionToken);
    }

    onLoginFailed(response) {
        this.setState({
            loginMessage: 'Login failed...'
        });
    }

    handleLogin(e) {
        this.setState({
            loginMessage: null
        });
        $.ajax({
            url: config.serverUrl + '/login',
            method: 'POST',
            data: {
                email: this.state.loginEmail,
                password: this.state.password
            },
            success: this.onUserLogged,
            error: this.onLoginFailed
        });
        e.preventDefault();
    }

    validateForm() {
        return true;
    }

    render() {
        return (
            <div className="LoginForm">
                <form id="loginForm" onSubmit={this.handleLogin}>
                    <FormGroup controlId="loginEmail" bsSize="large">
                        <ControlLabel>Email</ControlLabel>
                        <FormControl autoFocus type="email" value={this.state.loginEmail} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup controlId="password" bsSize="large">
                        <ControlLabel>Password</ControlLabel>
                        <FormControl type="password" value={this.state.password} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup controlId="loginButton" validationState={this.state.loginMessage}>
                        <Button type="submit" block bsSize="large" disabled={ !this.validateForm() } />
                        <FormControl.Feedback />
                    </FormGroup>
                </form>
            </div>
        )
    }
}

export default LoginForm;