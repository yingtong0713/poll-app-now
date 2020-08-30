import axios from "axios";

const BASE_URL = "https://poll-app-now.herokuapp.com/api";

const Api = axios.create({
    baseURL: BASE_URL,
});


window.Api = Api;

export default Api;