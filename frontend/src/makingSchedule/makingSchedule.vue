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
    async selectWeek(weekDays) {
      await fetch('/api/getGroups')
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
                  schedule:{}
                }
                for (let i = 1; i < 7; i++) {
                  course.days[day].groups[group.title].schedule[i] = {
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
        const couples = data
        console.log(couples)
        console.log(this.courses)
        for (const coupleKey in couples) {
          const couple = couples[coupleKey]
          const thisGroup = this.courses[couple.course].days[couple.date].groups[couple.titleStudyGroup]
          thisGroup.schedule.id = couple.idSchedule
          for (let i = 1; i < 7; i++) {

            thisGroup.schedule[couple.number] = {
              academicSubject: couple.academicSubject,
              professor: couple.professor,
              office: couple.office,
              id: couple.idCouple,
              number: couple.number
            }
          }

        }
      })
    },
    SaveSchedule(){

      const couples = []
      for (const courseKey in this.courses) {
        const course = this.courses[courseKey]
        for (const dayKey in course.days) {
          const day = course.days[dayKey]
          for (const groupKey in day.groups) {
            const group = day.groups[groupKey];
            for (let i = 1; i < 7; i++) {
              const couple = group.schedule[i]
              couples.push({
                course: course.number,
                date: day.date,
                idStudyGroup: group.id,
                idSchedule: group.schedule.id,

                idCouple: couple.id,
                academicSubject: couple.academicSubject,
                professor: couple.professor,
                office: couple.office,
                studyGroup: group.title,
                number: couple.number,
              });
            }

          }
        }
      }
      console.log(couples)
      fetch('/api/setSchedule',{
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(couples)
      })
    }
  },

}
</script>
