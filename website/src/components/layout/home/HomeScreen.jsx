import React from 'react';
import $ from 'jquery';
import config from 'react-global-configuration';
import './HomeScreen.res/HomeScreen.css';

class HomeScreen extends React.Component {
    constructor(props) {
        super(props);

        this.onUsernameLoaded = this.onUsernameLoaded.bind(this);

        this.state = {
            username: null
        };
    }

    fetchUsername() {
        $.ajax({
            url: this.getFetchUrl(),
            method: 'GET',
            success: this.onUsernameLoaded
        });
    }

    getFetchUrl() {
        return config.serverUrl + `/user/details?st=${this.props.sessionToken}`;
    }

    onUsernameLoaded(response) {
        this.setState({
            username: response.username
        });
    }

    renderUsername(username) {
        if (this.state.username == null) {
            return <h2>Hello, Stranger!</h2>;
        }

        return <h1>Hello, {this.state.username}!</h1>
    }

    render() {
        return (
            <div className="HomeScreen">
                {this.renderUsername()}
            </div>
        );
    }
}

export default HomeScreen;