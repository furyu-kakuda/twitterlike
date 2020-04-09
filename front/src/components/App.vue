<template>
    <div>
        <div class="flex justify-center">
            <div id="form" class="w-3/5 flex">
                <textarea class="flex-auto w-3/5 focus:outline-none focus:shadow-outline border border-gray-300 rounded-lg py-2 px-4" rows="2" v-model="tweet"/>
                <button class="flex-auto w-2/5 bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded font-sans" id="btn" @click="postTweet" v-if="!validationTweetWordCount">送信</button>
            </div>
        </div>
        <div class="font-sans">現在文字数: {{tweet.length}}</div>
        <div class="font-sans" v-if="validationTweetWordCount">文字数制限を超えています。140文字以下にしてください</div>
        <div id="timeline" class="flex justify-center">
            <ul class="max-w-md">
                <li class="border-solid border border-gray-400 py-4" v-for="tweet in result.tweets" :key="tweet.id">
                    <div class="flex">
                        <div class="flex-auto w-1/5 text-center self-center font-sans">{{format(tweet.tweetedAt)}}</div>
                        <div class="flex-auto w-3/5 text-center self-center font-sans" id="content">{{tweet.contents}}</div>
                        <div class="button flex-auto w-1/5 text-center self-center hover:text-red-600 font-sans" @click="deleteTweet(tweet.id)">削除</div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="flex justify-end" id="paging">
            <div class="button text-2xl font-extrabold hover:text-blue-600 font-sans" v-if="isNotFirstPage" @click="goBackPreviousPage">前へ</div>
            <label class="text-2xl font-extrabold font-sans" >/</label>
            <div class="button text-2xl font-extrabold hover:text-blue-600 font-sans" @click="goNextPage">次へ</div>
        </div>
    </div>
</template>

<script lang="ts">
    import axios from 'axios'
    import moment from 'moment'
    import {defineComponent, reactive, computed, toRefs} from "@vue/composition-api";

    export default defineComponent({
        setup(){
            const state: any = reactive({
                result:{},
                tweet: '',
                pageNumber: 1,
                isNotFirstPage : computed (() => state.pageNumber > 1),
                validationTweetWordCount : computed(() => state.tweet.length > 140)
            })

            const getTweets = () =>{
                return axios.get("/tweets", {
                    params: {
                        page: state.pageNumber
                    }
                }).then(result => {
                    state.result = result.data
                }).catch(result => {
                    console.log(result)
                    alert(result)
                })
            }

            const postTweet = () => {
                return axios.post("/tweets", {"id": 0, "contents": state.tweet, "tweetedAt": Date.now()})
                    .then(() => {
                        confirm("登録に成功しました")
                        state.tweet = ""
                        getTweets()
                    }).catch(result => {
                        alert(result)
                    })
            }

            const deleteTweet = (id :number) => {
                return axios.delete("/tweets", {
                    params: {
                        id: id
                    }
                })
                    .then(() => {
                        confirm("削除に成功しました")
                        getTweets()
                    }).catch(result => {
                        alert(result)
                    })
            }
            const format = (dayWithNotFormated : number) => {
                return moment(dayWithNotFormated).format('MM/DD HH:mm')
            }

            const goNextPage = () => {
                state.pageNumber++
                getTweets()

            }

            const goBackPreviousPage = () =>  {
                state.pageNumber--
                getTweets()
            }

            getTweets()

            return {
                ...toRefs(state),
                postTweet,
                deleteTweet,
                format,
                goNextPage,
                goBackPreviousPage
            }
        }
    })
</script>

<style scoped lang="scss">
    ul {
        list-style: none;
    }
    #content{
        white-space: pre-line
    }
</style>
