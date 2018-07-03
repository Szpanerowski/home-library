import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './components/app/App';
import config from 'react-global-configuration';
import configuration from './config';
import registerServiceWorker from './registerServiceWorker';

config.set(configuration);

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
