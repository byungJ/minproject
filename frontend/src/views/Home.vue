<style>
.table {
  border: none;
  text-align: center;
  text-decoration: none;
}
button {
 color: blue;
}
</style>
<template>
  <Layout>
    <template #menubar>
     <div id="header" v-if="isAuthorized">
      <v-btn rounded @click="$router.push('MyHome')" color="orange dark white--text" style="margin: 3px">myinfo</v-btn>
      <v-btn rounded @click="$router.push('StudyRegister')" color="orange dark white--text" style="margin: 3px">Create study</v-btn>
      <v-btn rounded @click="onClickLogout" color="orange dark white--text" style="margin: 3px">LOGOUT</v-btn>
      </div>
     <div id="header" v-else>
     <v-btn rounded @click="$router.push('Login')" color="orange dark white--text" style="margin: 3px">LOGIN</v-btn>
     <v-btn rounded @click="$router.push('SignUp')" color="orange dark white--text" style="margin: 3px">SingUp</v-btn>
     </div>
    </template>
    <template #content>
    <div>
    <v-col sm ="3">
      <v-row>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Search"
        class="hidden-sm-and-down"
        v-model="search"
        @input="handlerSearchInput"
        @keydown.tab="KeydownTab"></v-text-field></v-row>
       </v-col>
    </div>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-center" style="font-size:20px">스터디 이름</th>
              <th class="text-center" style="font-size:20px">지역명</th>
              <th class="text-center" style="font-size:20px">시작일</th>
               <th class="text-center" style="font-size:20px">종료일</th>
                <th class="text-center" style="font-size:20px">과목명</th>
                <th class="text-center" style="font-size:20px">담기</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="page in pageArray" :key="page.sno">
              <td class cd ="table"><a @click="clickNews(page.studyname)">{{ page.studyname }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.area }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.start }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.end }}</a></td>
              <td class ="table"><a @click="clickNews(page.studyname)">{{ page.subject }}</a></td>
              <td class ="table"><v-btn rounded @click="onClick(page.sno)" color="blue dark white--text" style="margin: 3px">수강신청</v-btn></td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </template>
  </Layout>
</template>

<script>
import Layout from '../components/Layout'
import { mapState, mapGetters, mapActions } from 'vuex'
import axios from 'axios'

export default {
  components: { Layout },
  computed: {
    paginatedData () {
      const start = this.pageNum * this.pageSize
      const end = start + this.pageSize
      return this.listArray.slice(start, end)
    },
    ...mapState({
      lists: state => state.lists
    }),
    ...mapState(['myinfo']),
    ...mapGetters(['isAuthorized'])
  },
  methods: {
    clickNews (sno) {
      console.log('clickNews: ' + sno)
      this.$store.dispatch('crawlFindOne', sno)
    },
    start (category) {
      this.$store.dispatch('crawlFind', category)
    },
    onClick (sno) {
      console.log('myinfo onSubmit()')
      axios.post('http://localhost:7777/myinfo', { sno })
        .then(res => {
          if (res.data) {
            alert('수강신청 성공.')
            this.$router.push({
              name: 'Home'
            })
          } else {
            alert('이미 신청한 강의입니다.')
            this.$router.push({
              name: 'Home'
            })
          }
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    },
    handlerSearchInput (search) {
      if (search !== '') {
        axios.get('http://localhost:7777/boards/search/' + search)
          .then(res => {
            console.log(res)
            this.pageArray = res.data
          })
          .catch(err => {
            console.log(err)
          })
        this.$router.push({ name: 'Home' })
      } else {
        this.pageArray = this.pageArray2
      }
    },
    onClickLogout () {
      this.logout()
      alert('로그아웃 했습니다.')
      this.$router.push({ name: 'Home' })
    },
    ...mapActions(['logout'])
  },
  data () {
    return {
      pageArray: [],
      pageArray2: [],
      search: ''
    }
  },
  created () {
    axios.get('http://localhost:7777/boards')
      .then(res => {
        console.log(res)
        this.pageArray = res.data
        this.pageArray2 = res.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
