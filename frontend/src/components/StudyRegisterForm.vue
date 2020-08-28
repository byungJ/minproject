<template>
  <v-app id="inspire">
    <v-main>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Create Study</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                    <v-btn
                      :href="source"
                      icon
                      large
                      target="_blank"
                      v-on="on"
                    >
                      <v-icon>mdi-code-tags</v-icon>
                    </v-btn>
                  </template>
                  <span>Source</span>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form @submit.prevent="onSubmit">
                  <v-text-field
                    label="Study name"
                    name="login"
                    prepend-icon="mdi-pen"
                    type="text"
                    v-model="studyname"
                  ></v-text-field>

                  <v-text-field
                  label="Area"
                  name="login"
                  prepend-icon="mdi-pen"
                  type="text"
                  v-model="area"
                  ></v-text-field>

                  <v-text-field
                  label="Start"
                  name="login"
                  prepend-icon="mdi-pen"
                  type="date"
                  v-model="start"
                  ></v-text-field>

                  <v-text-field
                  label="End"
                  name="login"
                  prepend-icon="mdi-pen"
                  type="date"
                  v-model="end"
                  ></v-text-field>

                  <v-text-field
                  label="Subject"
                  name="subject"
                  prepend-icon="mdi-pen"
                  type="text"
                  v-model="subject"
                  ></v-text-field>

                  <v-text-field
                  label="Email"
                  name="email"
                  prepend-icon="mdi-pen"
                  type="email"
                  v-model="email"
                  ></v-text-field>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" type="submit">Create</v-btn>
                  </v-card-actions>
                </v-form>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'StudyRegisterForm',
  data () {
    return {
      studyname: '',
      area: '',
      start: '',
      end: '',
      subject: '',
      email: ''
    }
  },
  props: {
    source: String
  },
  methods: {
    onSubmit () {
      var DateStart = new Date(this.start)
      var DateEnd = new Date(this.end)
      if (this.studyname === '' || this.area === '' || this.start === '' || this.end === '' || this.subject === '' || this.email === '') {
        alert('모든항목을 입력해주세요.')
        return
      }
      if (DateStart > DateEnd) {
        alert('종료일이 시작일보다 빠를 수 없습니다.')
        this.end = ''
        return
      }
      const { studyname, area, start, end, subject, email } = this
      this.$emit('submit', { studyname, area, start, end, subject, email })
    }
  }
}
</script>
