import React from 'react';
import $ from 'jquery';
import config from 'react-global-configuration';
import { Button } from 'react-bootstrap';
import './ConfirmationScreen.res/ConfirmationScreen.css';


class ConfirmationScreen extends React.Component {
    constructor(props) {
        super(props);

        this.onAccountConfirm = this.onAccountConfirm.bind(this);
        this.onAccountConfirmed = this.onAccountConfirmed.bind(this);
        this.onConfirmationFailed = this.onConfirmationFailed.bind(this);

        this.state = {
            message: null
        };
    }

    onAccountConfirmed() {
        this.setState({
            message: 'Your account has been confirmed! You can sign in now.'
        });
    }

    onConfirmationFailed() {
        this.setState({
            message: 'Failed to confirm your account. The registration is either not valid or does not exist.'
        });
    }

    onAccountConfirm() {
        $.ajax({
            url: config.serverUrl + '/account/confirm',
            method: 'POST',
            data: this.props.registrationToken,
            success: this.onAccountConfirmed,
            error: this.onConfirmationFailed
        })
    }

    render() {
        if (this.state.message != null) {
            return (
                <div className="ConfirmationScreen">
                    <h3>{this.state.message}</h3>
                </div>
            )
        }

        return (
            <div className="ConfirmationScreen">
                <h3>Confirm your account by clicking the button below.</h3>
                <Button onClick={this.onAccountConfirm}>Confirm</Button>
            </div>
        );
    }
}

export default ConfirmationScreen;