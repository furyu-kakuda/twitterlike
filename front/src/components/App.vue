<template>
  <div>
    <div class="flex justify-center">
      <div id="form" class="w-3/5 flex">
        <textarea
          class="flex-auto w-3/5 focus:outline-none focus:shadow-outline border border-gray-300 rounded-lg py-2 px-4"
          rows="2"
          v-model="tweet"
        />
        <button
          class="flex-auto w-2/5 bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded font-sans"
          id="btn"
          @click="post"
          v-if="!validationTweetWordCount"
        >
          送信
        </button>
      </div>
    </div>
    <div class="font-sans">現在文字数: {{ tweet.length }}</div>
    <div class="font-sans" v-if="validationTweetWordCount">
      文字数制限を超えています。140文字以下にしてください
    </div>
    <div id="timeline" class="flex justify-center">
      <ul class="max-w-md">
        <li
          class="border-solid border border-gray-400 py-4"
          v-for="tweet in tweets"
          :key="tweet.id"
        >
          <div class="flex">
            <div class="flex-auto w-1/5 text-center self-center font-sans">
              {{ format(tweet.tweetedAt) }}
            </div>
            <div
              class="flex-auto w-3/5 text-center self-center font-sans"
              id="content"
            >
              {{ tweet.contents }}
            </div>
            <div
              class="button flex-auto w-1/5 text-center self-center hover:text-red-600 font-sans"
              @click="del(tweet.id)"
            >
              削除
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="flex justify-end" id="paging">
      <div
        class="button text-2xl font-extrabold hover:text-blue-600 font-sans"
        v-if="isNotFirstPage"
        @click="goBackPreviousPage"
      >
        前へ
      </div>
      <label class="text-2xl font-extrabold font-sans">/</label>
      <div
        class="button text-2xl font-extrabold hover:text-blue-600 font-sans"
        @click="goNextPage"
      >
        次へ
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import moment from 'moment';
import {
  defineComponent,
  reactive,
  computed,
  toRefs,
  Ref,
} from '@vue/composition-api';
import { UnwrapRef } from '@vue/composition-api/dist/reactivity';
import { useTweet } from '../js/useTweet.ts';

export default defineComponent({
  setup() {
    const tweetModel = useTweet();

    const state: UnwrapRef<{
      pageNumber: number;
      isNotFirstPage: Ref<boolean>;
      validationTweetWordCount: Ref<boolean>;
      tweet: string;
    }> = reactive({
      pageNumber: 1 as number,
      isNotFirstPage: computed(() => state.pageNumber > 1) as Ref<boolean>,
      validationTweetWordCount: computed(() => state.tweet.length > 140) as Ref<
        boolean
      >,
      tweet: '' as string,
    });

    const getTweet = async (): Promise<void> =>
      await tweetModel.getTweet(state.pageNumber);
    getTweet();

    const format = (dayWithNotFormated: number): string => {
      return moment(dayWithNotFormated).format('MM/DD HH:mm');
    };

    const post = async (): Promise<void> => {
      await tweetModel
        .postTweet(state.tweet)
        .then(() => {
          alert('登録しました');
          state.tweet = '';
          getTweet();
        })
        .catch((error: any) => alert(error));
    };

    const del = async (id: number): Promise<void> => {
      await tweetModel
        .deleteTweet(id)
        .then(() => {
          alert('削除しました');
          getTweet();
        })
        .catch((error: any) => alert(error));
    };

    const goNextPage = async (): Promise<void> => {
      await tweetModel.getTweet(++state.pageNumber);
    };

    const goBackPreviousPage = async (): Promise<void> => {
      await tweetModel.getTweet(--state.pageNumber);
    };

    return {
      ...toRefs(state),
      ...toRefs(tweetModel),
      post,
      del,
      format,
      goNextPage,
      goBackPreviousPage,
    };
  },
});
</script>

<style scoped lang="scss">
ul {
  list-style: none;
}
#content {
  white-space: pre-line;
}
</style>
