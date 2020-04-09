import axios from 'axios'
import {Tweet} from '../model/Tweet'

export default {
    get:() => {
        return axios.get("/tweets").then(result => {
            return new Tweet(result.data);
        }).catch(result => {
            console.log(result)
        });
    }
}
