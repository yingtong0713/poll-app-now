import React, { useEffect, useState } from 'react'
import PollApi from '../api/PollApi'
import { useInterval } from '../utils/util';


function PollDashBoard(props) {
    const pollId = props.match.params.id;
    let emptyPoll = {
        title: '',
        pollQuestions: [{ question: '' }],
        voters: [{ IpAddress: '' }]
    };
    const [poll, setPoll] = useState(emptyPoll);

    useInterval(() => {
        try {
            async function fetchData() {
                const response = await PollApi.getPollById(pollId);
                setPoll(response.data);
            }
            fetchData();
        } catch (e) {
            console.error(e);
        }
    }, 2000);

    const createdPollTitleDiv = <div>{poll.title}</div>

    const createdPollQuestionsDiv =
        poll.pollQuestions.map((question, index) =>
            <div key={index}>
                {question.question}
            </div>)

    const showPollLinkDiv = <div>
        <p></p>
        <p>Please copy and paste this link and share with your frineds! </p>
        <p>Meanwhile, keep the current page on to follow the result.</p>
        {window.location.host + '/polls/' + pollId}
    </div>

    const currentVoting =
        poll.pollQuestions.map((question, index) =>
            <div key={index}>
                {question.question}{": "}{question.numberOfPeopleSelected}{" people voted"}
            </div>)




    return (
        <div className='card mt-3'>
            <div className='card-body'>
                <ul className="list-group list-group-flush">
                    <li className="list-group-item">{createdPollTitleDiv}{createdPollQuestionsDiv}</li>
                    <li className="list-group-item">{showPollLinkDiv}</li>
                    <li className="list-group-item"> Result:  {currentVoting}</li>
                </ul>
            </div>
        </div>
    )
}

export default PollDashBoard
