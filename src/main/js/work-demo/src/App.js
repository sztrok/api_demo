import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DownloadData from './DownloadData';
import ShowPost from "./ShowPost";
import GetAllDownloadedFiles from "./GetAllDownloadedFiles";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<Home/>}/>
                <Route path='/get_data' exact={true} element={<DownloadData/>}/>
                <Route path='/show_post'>
                    <Route path=':index' exact={true} element={<ShowPost/>}/>
                </Route>
                <Route path='/get_all_posts' exact={true} element={<GetAllDownloadedFiles/>}/>
            </Routes>
        </Router>
    )
}

export default App;