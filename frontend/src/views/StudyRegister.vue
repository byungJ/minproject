<template>
  <div align="center">
    <study-register-form @submit="onSubmit"/>
  </div>
</template>

<script>
import StudyRegisterForm from '@/components/StudyRegisterForm.vue'
import axios from 'axios'

export default {
  name: 'StudyRegisterPage',
  components: {
    StudyRegisterForm
  },
  methods: {
    onSubmit (payload) {
      console.log('StudyRegisterPage onSubmit()')
      const { studyname, area, start, end, subject, email } = payload
      axios.post('http://localhost:7777/boards', { studyname, area, start, end, subject, email })
        .then(res => {
          console.log(res)
          alert('스터디 생성완료!!')
          this.$router.push({
            name: 'Home',
            params: { sno: res.data.sno.toString() }
          })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  }
}
</script>
