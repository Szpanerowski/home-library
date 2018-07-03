import React, { Component } from 'react';
import AuthenticationScreen from '../layout/authentication/AuthenticationScreen';
import HomeScreen from '../layout/home/HomeScreen';
import logo from './App.res/logo.svg';
import './App.res/App.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.renderHomeScreen = this.renderHomeScreen.bind(this);
    this.onUserAuthenticated = 

    this.state = {
      sessionToken: null
    };
  }

  renderHomeScreen() {

    if (sessionToken === null)
      return <AuthenticationScreen />

    return <HomeScreen />
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <main>
          {this.renderHomeScreen()}
        </main>
      </div>
    );
  }
}

export default App;
