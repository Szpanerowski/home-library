import React, { Component } from 'react';
import AuthenticationScreen from '../layout/account/authentication/AuthenticationScreen';
import ConfirmationScreen from '../layout/account/confirmation/ConfirmationScreen';
import HomeScreen from '../layout/home/HomeScreen';
import { Route } from 'react-router-dom';
import qs from 'query-string';
import logo from './App.res/logo.png';
import './App.res/App.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.renderApplicationScreen = this.renderApplicationScreen.bind(this);
    this.onUserAuthenticated = this.onUserAuthenticated.bind(this);

    this.state = {
      sessionToken: null
    };
  }

  onUserAuthenticated(sessionToken) {
    this.setState({
      sessionToken: sessionToken
    });
  }

  renderApplicationScreen() {

    if (this.props.location.search != null) {
      let registrationToken = qs.parse(this.props.location.search)._rt;
      
      if (registrationToken != null) {
        return <ConfirmationScreen registrationToken={registrationToken} />
      }
    }

    if (this.state.sessionToken === null)
      return <AuthenticationScreen onUserAuthenticated={this.onUserAuthenticated} />

    return <HomeScreen />
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <Route render={({history}) => <img src={logo} className="App-logo" alt="logo" onClick={() => { history.push('/') }} />} />
          <h1 className="App-title">Welcome to Home Library</h1>
        </header>
        <main>
          {this.renderApplicationScreen()}
        </main>
      </div>
    );
  }
}

export default App;
