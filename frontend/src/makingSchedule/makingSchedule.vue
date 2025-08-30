<template>
  <div>
    <setting
      @selectWeek="selectWeek"
      @SaveSchedule="SaveSchedule"
      v-model:ifChange="ifChange"
    />
    <course
      v-for="course in courses"
      :key="course.number"
      :course="course"
      @emitChange="emitChange"
    />
  </div>
</template>

<script>
import setting from './components/setting.vue'
import course from './components/course.vue'
import day from "@/makingSchedule/components/day.vue";

export default {
  name: 'makingSchedule',
  components: {
    day,
    setting,
    course
  },
  data() {
    return {
      ifChange: false,
      courses: {
        1: {number: 1, days:{}},
        2: {number: 2, days:{}},
        3: {number: 3, days:{}},
        4: {number: 4, days:{}}
      }
    }
  },
  methods: {
    emitChange(){
      this.ifChange = true
    },
    selectWeek(weekDays) {
      fetch('/api/getGroups')
      .then(res=>{return res.json()})
      .then(data=>{
        const groups = data
        for (const courseKey in this.courses) {
          const course = this.courses[courseKey]
          course.days = {}
          for (const dayKey in weekDays) {
            const day = weekDays[dayKey]
            course.days[day] = {
              date: day,
              groups: {}
            }
            for (const groupKey in groups) {
              const group = groups[groupKey]
              if (group.course==courseKey){
                course.days[day].groups[group.title] = {
                  id: group.id,
                  title: group.title,
                  couples:{}
                }
                for (let i = 1; i < 7; i++) {
                  course.days[day].groups[group.title].couples[i] = {
                    academicSubject: "",
                    professor: "",
                    office: "",
                    id: null,
                    number: i
                  }
                }
              }
            }
          }
        }
      })
      fetch('/api/getSchedule',{
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(weekDays)
      })
      .then(response => {
        return response.json();
      })
      .then(data => {
        const schedules = data.schedules
        console.log(schedules)
        console.log(this.courses)
        for (const scheduleKey in schedules) {
          const schedule = schedules[scheduleKey]
          const group = schedule.studyGroup
          const thisGroup = this.courses[group.course].days[schedule.date].groups[group.title]
          for (let i = 1; i < 7; i++) {
            thisGroup.couples.id = schedule.id

            thisGroup.couples = schedule.listCouple
          }

        }
      })
    },
    SaveSchedule(){

      const schedules = []
      for (const courseKey in this.courses) {
        const course = this.courses[courseKey]
        for (const dayKey in course.days) {
          const day = course.days[dayKey]
          for (const groupKey in day.groups) {
            const group = day.groups[groupKey];
            const couples = []
            for (let i = 1; i < 7; i++) {
              couples.push(group.couples[i])
            }
            schedules.push({
              date: day.date,
              idGroup: group.id,
              id: group.couples.id,
              couplesRequest: couples
            });
          }
        }
      }
      fetch('/api/setSchedule',{
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(schedules)
      })
    }
  },

}
</script>
