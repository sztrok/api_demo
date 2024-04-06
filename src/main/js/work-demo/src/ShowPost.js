import React, { useEffect, useState } from 'react';
import { Container } from 'reactstrap';
import AppNavbar from './AppNavbar';
import {useParams} from "react-router-dom";

function ShowPost() {
    const [post, setPost] = useState([]);
    const [loading, setLoading] = useState(false);

    let{index} = useParams();

    useEffect(() => {
        setLoading(true);
        fetch('/api/show_post/'+index)
            .then(response => response.json())
            .then(data => {
                setPost(data);
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
                <h1>File data</h1>
                <hr/>
                <div>{Object.keys(post)
                    .map(key =>
                            <div
                                key={key}>
                                <h3>{key}: {post[key]}</h3>
                                <hr/>
                            </div>)
                }</div>
            </Container>
        </div>
    );

}

export default ShowPost;





