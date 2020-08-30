import Api from "./Api";

class PollApi {
 
  getPollById(id) {
    return Api.get('/polls/' + id);
  }

  createPoll(poll) {
    return Api.post('/polls', poll);
  }

  vote(questionId) {
    return Api.put(`/polls/${questionId}/vote`, questionId)
  }

}

export default new PollApi();