<template>
  <div align="center">
    <sign-up-form @submit="onSubmit"/>
  </div>
</template>

<script>
import axios from 'axios'
import SignUpForm from '@/components/SignUpForm.vue'

export default {
  name: 'SignUp',
  components: {
    SignUpForm
  },
  methods: {
    onSubmit (payload) {
      console.log('payload: ' + payload.userId +
                  ', ' + payload.userName +
                  ', ' + payload.userPw)
      const { userId, userName, userPw } = payload
      axios.post('http://localhost:7777/users/setup',
        { userId, userName, userPw })
        .then(res => {
          alert('회원가입에 성공했습니다!!')
          this.$router.push({
            name: 'Home'
          })
        })
        .catch(err => {
          alert(err.response.data)
        })
    }
  }
}
</script>
