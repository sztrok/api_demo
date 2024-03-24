import React from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

function Home() {
    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <Button color="link"><Link to="/get_data">Download data from API</Link></Button>
            </Container>
        </div>
    );
}

export default Home;