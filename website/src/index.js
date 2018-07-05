import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route} from 'react-router-dom';
import './index.css';
import App from './components/app/App';
import config from 'react-global-configuration';
import configuration from './config';
import registerServiceWorker from './registerServiceWorker';

config.set(configuration);

ReactDOM.render(
    <Router>
        <Route path="/" component={App} />
    </Router>,
    document.getElementById('root')
);
registerServiceWorker();
