import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router'
import PollApi from '../api/PollApi'

function PollDetail(props) {
    const pollId = props.match.params.id;

    let emptyPoll = {
        title: '',
        pollQuestions: [{ question: '' }],
        voters: [{ IpAddress: '' }]
    };
    const [poll, setPoll] = useState(emptyPoll);
    // const [vote, setVote] = useState('');

    const [selectedQuestionId, setSelectedQuestionId] = useState('');
    const history = useHistory()

    function handleSubmit(event) {
        event.preventDefault();
        votePoll(selectedQuestionId);
        history.go(0)
    }

    useEffect(() => {
        try {
            async function fetchData() {
                const response = await PollApi.getPollById(pollId);
                setPoll(response.data);
            }
            fetchData();
        } catch (e) {
            console.error(e);
        }
    }, [pollId]);

    async function votePoll(questionId) {
        try {
            const response = await PollApi.vote(questionId);
            const poll = response.data;
            const newPolls = this.state.polls.concat(poll);
            this.setState({
                polls: newPolls,
              });
        } catch (e) {
            console.error(e);
        }
    }

    const buttonDiv =
        <button
            className='btn btn-warning'
            onClick={handleSubmit}
            id='send'>
            Send
        </button>

    const pollCreaterIp = poll.ip;
    const showSendButtonView = poll.voters.filter((v) => v.ipAddress === pollCreaterIp).length === 0;


    return (
        <div className='card mt-3'>
            <div className='card-body'>
                <p>{poll.title}</p>
                {poll.pollQuestions.map((question, index) =>
                    <div className="radio" key={index}>
                        <label>
                            <input
                                type="radio"
                                value={question.id}
                                onChange={(e) => setSelectedQuestionId(e.target.value)}
                                checked={question.id === selectedQuestionId} />
                            {question.question}
                        </label>
                    </div>)}
                {showSendButtonView && buttonDiv}
                {showSendButtonView || "Thank you for your vote and you can close this page."}
            </div>
        </div>
    )
}

export default PollDetail
