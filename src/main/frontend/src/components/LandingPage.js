import React from 'react';
import { Link } from 'react-router-dom';

function LandingPage() {



    return (
        <div className="card">
            <div className="card-body">
                welcome to the simple poll app.
            <p></p>
                <Link to="/polls" >
                    <button type="button" className="btn btn-primary">
                        make a poll
                     </button>
                </Link>
            </div>
        </div>
    )
}

export default LandingPage
