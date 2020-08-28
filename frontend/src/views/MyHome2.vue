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
              <th class="text-center" style="font-size:20px">탈 퇴</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="list of lists" :key="list.title">
              <td style="color: gray">{{ list.newsNo }}</td>
              <td><a @click="clickNews(list.newsNo)">{{ list.title }}</a></td>
              <td><a @click="clickNews(list.newsNo)">{{ list.title }}</a></td>
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
    start (category) {
      this.$store.dispatch('crawlFind', category)
    }
  },
  created () {
    if (!this.isAuthorized) {
      this.$router.push('/')
    }
  }
}
</script>
