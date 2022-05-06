import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate'
import {getToken,setToken,removeToken} from "@/utils/auth";


export const store = createStore({
    state: {
        token: getToken(),
        id : ''
    },
    mutations: {
        setToken(state,token) {
            state.token =token;
            setToken(token);
        },
        setID(state,id) {
            state.id =id;

        },
        removeToken(state) {
            state.token =null;
            removeToken();
        },
        removeID(state) {
            state.id =null;
        }
    },
    plugins:[createPersistedState({
        // 默认是localstorage
        storage: window.sessionStorage,
        reducer(val) {
            return {
                id: val.id
            }
        }
    }),]
})



