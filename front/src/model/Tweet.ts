export interface Tweet {
    id: number;
    contents: string;
    tweetAt: number;
}

export interface TweetApiResponse {
    tweets: Array<Tweet>
}
