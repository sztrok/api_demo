import React, { useEffect, useState } from 'react';
import AppNavbar from './AppNavbar';
import { Container } from 'reactstrap';

function DownloadData() {

    const [info, setInfo] = useState("");
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        fetch('api/get_posts')
            .then(response => response.text())
            .then(data => {
                setInfo(data);
                setLoading(false);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <h3>{info}</h3>
            </Container>
        </div>
    );

}
export default DownloadData;