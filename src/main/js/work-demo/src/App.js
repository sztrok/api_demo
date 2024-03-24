import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DownloadData from './DownloadData';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path='/get_data' exact={true} element={<DownloadData/>}/>
            </Routes>
        </Router>
    )
}

export default App;