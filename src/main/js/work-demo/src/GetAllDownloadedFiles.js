import React, { useEffect, useState } from 'react';
import { Container} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from "react-router-dom";


function GetAllDownloadedFiles() {


    const [filesList, setFilesList] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        fetch('api/list_downloaded_files')
            .then(response => response.json())
            .then(data => {
                setFilesList(data);
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
                <h1>Downloaded files</h1>
                <hr/>
                <div style={{height: '400px', width: '400px', overflow: 'auto'}}>
                    {filesList.map(file =>
                        <li>
                            <Link to={"/show_post/"+file.id}>{file.fileName}</Link>
                        </li>
                    )}
                    <hr/>
                </div>
            </Container>
        </div>
    );
}

export default GetAllDownloadedFiles;