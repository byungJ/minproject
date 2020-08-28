<template>
  <div align="center">
    <login-form @submit="onSubmit"/>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'
import { mapActions } from 'vuex'

export default {
  name: 'LoginPage',
  components: {
    LoginForm
  },
  methods: {
    onSubmit (payload) {
      console.log('loginPage onSubmit()')
      this.login(payload).then(res => {
        alert('로그인 성공!!')
        this.$router.push({ name: 'Home' })
      }).catch(err => {
        // alert(err.response.data.message)
        alert('로그인 실패!!', err)
      })
    },
    ...mapActions(['login'])
  }
}
</script>
