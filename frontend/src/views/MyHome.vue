<template>
  <Layout>
    <template #menubar>
     <div id="header" v-if="isAuthorized">
      <v-btn rounded @click="$router.push('MyHome')" color="orange dark white--text" style="margin: 3px">MY study</v-btn>
       <v-btn rounded @click="$router.push('MyHome2')" color="orange dark white--text" style="margin: 3px">join study</v-btn>
     </div>
    </template>
    <template #content>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-center" style="font-size:20px">스터디 이름</th>
              <th class="text-center" style="font-size:20px">시작일</th>
              <th class="text-center" style="font-size:20px">종료일</th>
              <th class="text-center" style="font-size:20px">삭      제</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="page of pageArray" :key="page.info_id">
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.studyname }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.start }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.end }}</a></td>
              <td class ="table"><v-btn rounded @click="onProccess(page.sno)" color="blue dark white--text" style="margin: 3px">수강취소</v-btn></td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import { mapState, mapGetters } from 'vuex'
import axios from 'axios'

export default {
  components: { Layout },
  computed: {
    ...mapState({
      lists: state => state.lists
    }),
    ...mapState(['myinfo']),
    ...mapGetters(['isAuthorized'])
  },
  methods: {
    clickNews (newsNo) {
      console.log('clickNews: ' + newsNo)
      this.$store.dispatch('crawlFindOne', newsNo)
    },
    onProccess (sno) {
      console.log('myinfo onDelete(): ' + sno)
      axios.post('http://localhost:7777/myinfo/delete', { sno })
        .then(res => {
          console.log(res.data)
          alert('수강이 취소되었습니다!!')
          this.$router.push({ name: 'Home' })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    },
    start (category) {
      this.$store.dispatch('crawlFind', category)
    }
  },
  data () {
    return {
      pageArray: []
    }
  },
  created () {
    if (!this.isAuthorized) {
      this.$router.push('/')
    }
    axios.get('http://localhost:7777/myinfo')
      .then(res => {
        console.log(res)
        this.pageArray = res.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
