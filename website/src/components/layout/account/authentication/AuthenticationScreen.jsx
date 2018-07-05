import React from 'react';
import LoginForm from '../../../form/authentication/login/LoginForm';
import RegistrationForm from '../../../form/authentication/register/RegistrationForm';
import './AuthenticationScreen.res/AuthenticationScreen.css';


class AuthenticationScreen extends React.Component {
    constructor(props) {
        super(props);

        this.navigateToRegistration = this.navigateToRegistration.bind(this);
        this.navigateToLogin = this.navigateToLogin.bind(this);

        this.state = {
            registration: false
        };
    }

    navigateToRegistration() {
        this.setState({ registration: true });
    }

    navigateToLogin() {
        this.setState({ registration: false });
    }

    render() {
        if (this.state.registration) {
            return <RegistrationForm onLoginRequested={this.navigateToLogin} />
        }

        return <LoginForm onUserAuthenticated={this.props.onUserAuthenticated} onRegistrationRequested={this.navigateToRegistration} />
    }
}

export default AuthenticationScreen;