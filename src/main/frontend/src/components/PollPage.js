import React, { useState, useEffect } from 'react'
import { useHistory } from 'react-router-dom';
import PollApi from '../api/PollApi';

function PollPage() {

    let emptyPoll = {
        title: '',
        pollQuestions: [{ question: '' }]
    };
    const history = useHistory();
    const [title, setTitle] = useState('');
    const [pollQuestions, setPollQuestions] = useState([{}]);
    const [poll, setPoll] = useState(emptyPoll);
    const [pollId, setPollId] = useState('');

    function handleSubmit(event) {
        event.preventDefault();
        const onSubmit = {
            title: title,
            pollQuestions: pollQuestions
        };
        createPoll(onSubmit);

    }


    async function createPoll(pollData) {
        try {
            const response = await PollApi.createPoll(pollData);
            const newPoll = response.data
            //  setPoll(newPoll);
            const pollId = newPoll.id
            setPollId(newPoll.id);
            history.push(`/polls/${pollId}/dashboard`);
            // window.location = (`/polls/${pollId}/dashboard`);
        } catch (e) {
            console.error(e);
        }
    }


    function createTitle() {
        return <div className='form-group'>
            Title
            <textarea
                className='form-control'
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
        </div>
    }

    function createQuestions() {
        return (pollQuestions.map((el, i) =>
            <div key={i}>
                <input type="text" value={el.question || ''} onChange={(event) => handleChange(event, i)} />
                <input type='button' value='remove' onClick={removeClick.bind(i)} />
            </div>
        ));
    }

    function handleChange(event, index) {
        let questions = [...pollQuestions];
        questions[index].question = event.target.value;
        setPollQuestions(questions);
    }

    const addClick = () => {
        setPollQuestions([...pollQuestions, { question: "" }])
    }

    const removeClick = () => {
        let questions = [...pollQuestions];
        questions.splice(this, 1);
        setPollQuestions(questions);
    }

    const formDiv = <form>
        {createTitle()}
        <p>Questions</p>
        {createQuestions()}
        <input type='button' value='add one question' onClick={addClick} />
        <input type="submit" value="Submit" onClick={handleSubmit} />
    </form>

   
    return (
        <div className='card mt-3'>
            <div className='card-body'>
                {formDiv}
            </div>
        </div>
    )
}

export default PollPage
