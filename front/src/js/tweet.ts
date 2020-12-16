import axios from 'axios';
import { Tweet, TweetApiResponse } from '../model/Tweet';
import { reactive, toRefs } from '@vue/composition-api';

export const useTweet = (): any => {
  const state = reactive<{ tweets: Tweet[] }>({
    tweets: [],
  });

  const getTweet = async (pageNumber: number): Promise<void> => {
    await axios
      .get<TweetApiResponse>('/tweets', {
        params: {
          page: pageNumber,
        },
      })
      .then((result) => (state.tweets = result.data.tweets))
      .catch((error) => alert(error));
  };

  const postTweet = async (tweet: string): Promise<void> => {
    await axios.post('/tweets', {
      id: 0,
      contents: tweet,
      tweetedAt: Date.now(),
    });
  };

  const deleteTweet = async (id: number): Promise<void> => {
    await axios.delete('/tweets', {
      params: {
        id: id,
      },
    });
  };

  return {
    ...toRefs(state),
    getTweet,
    postTweet,
    deleteTweet,
  };
};
