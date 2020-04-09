import Vue from 'vue';
import App from './components/App.vue';
import VueCompositionApi from "@vue/composition-api";

import './css/tailwind.scss';

Vue.use(VueCompositionApi);

const vue = new Vue({
    el: '#app',
    render: h => h(App)
});

export default vue
